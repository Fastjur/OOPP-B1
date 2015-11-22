package server;

import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;

/**
 * A connected client
 *
 * @author Govert de Gans
 * @version 0.1
 */
public class ConnectedClient {
    private Socket socket;
    private ClientConnectionThread connectionThread;

    public ConnectedClient(Socket socket) {
        this.socket = socket;
        this.connectionThread = new ClientConnectionThread(this);
        this.connectionThread.start();
    }

    public void closeConnection() throws IOException {
        this.connectionThread.end();
        this.socket.close();
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void sendMessage(JSONObject message) {
        try {
            connectionThread.sendMessage(message);
        } catch(java.io.IOException ex) {
            System.out.println("Err: could not send the message\n" + ex.getLocalizedMessage());
        }
    }
}
