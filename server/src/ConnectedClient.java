import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A connected client
 *
 * @author Govert de Gans
 * @version 0.1
 */
public class ConnectedClient {
    private Socket socket;
    private ArrayList<ConnectedClient> clientList;
    private ClientConnectionThread connectionThread;
    public int userId;

    public ConnectedClient(Socket socket, ArrayList<ConnectedClient> clientList) {
        this.userId = -1;
        this.socket = socket;
        this.clientList = clientList;
        this.connectionThread = new ClientConnectionThread(this);
        this.connectionThread.start();
        System.out.println(socket.getInetAddress().toString() + " connected.");
    }

    /**
     * Closes the connection with this client. Don't forget to remove this client from the clientlist afterwards.
     *
     * @throws IOException when the socket is being written to while closing
     */
    public void closeConnection() throws IOException {
        this.connectionThread.end();
        this.socket.close();
        clientList.remove(this); // I guess this checks for memory address equality, but that's what we want.
    }

    /**
     * Returns the socket associated with this client
     *
     * @return the socket associated with this client
     */
    public Socket getSocket() {
        return this.socket;
    }

    /**
     * Sends a message to this client
     *
     * @param message the message to send
     */
    public void sendMessage(String message) {
        try {
            connectionThread.sendMessage(message);
        } catch (java.io.IOException ex) {
            System.out.println("Err: could not send the message\n" + ex.getLocalizedMessage());
        }
    }

    public void sendChatMessage(String message, int receiverClientId) {
        for(int i = 0; i<clientList.size(); i++){
            if(clientList.get(i).userId == receiverClientId){
                clientList.get(i).sendMessage(message);
            }
        }
    }
}
