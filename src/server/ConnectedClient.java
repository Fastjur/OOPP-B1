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
        System.out.println(socket.getInetAddress().toString() + " connected.");
    }

    /**
     * Closes the connection with this client. Don't forget to remove this client from the clientlist afterwards.
     * @throws IOException when the socket is being written to while closing
     */
    public void closeConnection() throws IOException {
        this.connectionThread.end();
        this.socket.close();
    }

    /**
     * Returns the socket associated with this client
     * @return the socket associated with this client
     */
    public Socket getSocket() {
        return this.socket;
    }

    /**
     * Sends a message to this client
     * @param message the message to send
     */
    public void sendMessage(JSONObject message) {
        try {
            connectionThread.sendMessage(message);
        } catch(java.io.IOException ex) {
            System.out.println("Err: could not send the message\n" + ex.getLocalizedMessage());
        }
    }
}