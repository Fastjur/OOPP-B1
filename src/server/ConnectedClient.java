package server;

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
        this.connectionThread = new ClientConnectionThread(this.socket);
        this.connectionThread.start();
    }

    public void closeConnection() throws IOException {
        this.connectionThread.end();
        this.socket.close();
    }
}
