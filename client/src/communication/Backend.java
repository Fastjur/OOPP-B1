package communication;

import shared.Response;
import shared.User;

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
    private static User selfUserObj;

    public static void addMessageListener(IMessageListener listener) {
        messageListeners.add(listener);
    }

    public static void addDisconnectListener(IDisconnectListener listener) {
        disconnectListeners.add(listener);
    }

    public static void removeAllListeners() {
        messageListeners.clear();
        disconnectListeners.clear();
    }

    public static void onResponse(Response response) {
        for (IMessageListener listener : messageListeners) {
            listener.onIncomingResponse(response);
        }
    }

    public static void onDisconnect(boolean erroneous) {
        for (IDisconnectListener listener : disconnectListeners) {
            listener.onDisconnect(erroneous);
        }
    }

    public static boolean connectToServer() {
        if (isConnected())
            return true;
        if (serverPort < 1 || serverAddress.equals(null))
            return false;
        try {
            socket = new Socket(serverAddress, serverPort);
            listenThread = new ListenThread(socket);
            listenThread.start();
            connected = true;
            System.out.println("Connected to server!");
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
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
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot login: Not connected!");
            return;
        }
        try {
            // TODO: hash password here

            Request request = new Request("login");
            request.putData("email", email);
            request.putData("pass", password);

            listenThread.sendMessage(request.toSendableJSON());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void logout() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot logout: Not connected!");
            return;
        }

        try {
            Request request = new Request("logout");

            listenThread.sendMessage(request.toSendableJSON());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void register(User newuser) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot register: Not connected!");
            return;
        }

        try {
            Request request = new Request("register");

            request.putData("newUser", newuser);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMatches(User self) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get matches: Not connected!");
            return;
        }

        try {
            Request request = new Request("getMatches");

            request.putData("self", self);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void acceptMatch(User self, String matchType) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot accept match: Not connected!");
            return;
        }

        try {
            Request request = new Request("acceptMatch");

            request.putData("matchUser", self.getUserID());
            request.putData("matchType", matchType);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Note, this get's a user from the database by ID
     * THIS IS NOT THE GETTER FOR THE USER OBJECT IN BACKEND
     */
    public static void getSelf() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot retrieve your information: Not connected!");
            return;
        }

        try {
            Request request = new Request("getSelf");
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSelf(User self) {
        selfUserObj = self;
    }

    public static boolean isConnected() {
        return connected;
    }

    public static InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
}
