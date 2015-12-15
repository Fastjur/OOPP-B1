import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Govert on 14-12-2015.
 */
public class Backend {
    private static Socket socket;
    private static boolean connected = false;
    public static int serverPort;
    public static String serverAddress;
    private static ListenThread listenThread;
    private static ArrayList<IMessageListener> messageListeners = new ArrayList<>();
    private static ArrayList<IDisconnectListener> disconnectListeners = new ArrayList<>();

    public static void addMessageListener(IMessageListener listener) {
        messageListeners.add(listener);
    }

    public static void addDisconnectListener(IDisconnectListener listener) {
        disconnectListeners.add(listener);
    }

    public static void onResponse(Response response) {
        for(IMessageListener listener : messageListeners) {
            listener.onIncomingResponse(response);
        }
    }

    public static void onDisconnect(boolean erroneous) {
        for(IDisconnectListener listener : disconnectListeners) {
            listener.onDisconnect(erroneous);
        }
    }

    public static boolean connectToServer() {
        if (isConnected())
            return true;
        if (serverPort == 0 || serverAddress.equals(null))
            return false;
        try {
            socket = new Socket(serverAddress, serverPort);
            listenThread = new ListenThread(socket);
            listenThread.start();
            connected = true;
            return true;
        } catch (java.io.IOException e) {
            return false;
        }
    }

    public static void closeConnection() throws IOException {
        if (isConnected()) {
            logout();
            listenThread.end();
            socket.close();
            connected = false;
        }
    }

    public static void login(String email, String password) {
        if (!isConnected())
            return;

        try {
            // TODO: hash password here

            Request request = new Request("login");
            request.putData("email", email);
            request.putData("pass", password);

            listenThread.sendMessage((new ObjectMapper()).writeValueAsString(request));
        } catch (java.io.IOException e) {

        }
    }

    public static void logout() {
        if (!isConnected())
            return;

        try {
            Request request = new Request("logout");

            listenThread.sendMessage((new ObjectMapper()).writeValueAsString(request));
        } catch (java.io.IOException e) {

        }
    }

    public static void register(User newuser) {
        if (!isConnected())
            return;

        try {
            Request request = new Request("register");

            request.putData("newUser", newuser);
            listenThread.sendMessage((new ObjectMapper()).writeValueAsString(request));
        } catch (java.io.IOException e) {

        }
    }

    public static boolean isConnected() {
        return connected;
    }

    public static InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
}
