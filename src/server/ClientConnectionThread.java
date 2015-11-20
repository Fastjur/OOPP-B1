package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * A thread that handles the connection with a client
 *
 * @author Govert de Gans
 * @version 0.1
 */
public class ClientConnectionThread extends Thread {
    private Socket socket;
    private boolean shouldStop;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientConnectionThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        shouldStop = false;
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch(java.io.IOException ex) {
            System.out.println("Err: could not open input/output streams for socket");
        }

        while (!shouldStop) {
            // do stuff
        }
    }

    public void end() {
        shouldStop = true;
    }
}
