package server;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * A thread that handles the connection with a client
 *
 * @author Govert de Gans
 * @version 0.1
 */
public class ClientConnectionThread extends Thread {
    private Socket socket;
    private ConnectedClient client;
    private boolean shouldStop;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientConnectionThread(ConnectedClient client) {
        this.socket = client.getSocket();
        this.client = client;
    }

    public void run() {
        shouldStop = false;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (java.io.IOException ex) {
            System.out.println("Err: could not open input/output streams for socket");
        }

        while (!shouldStop) {
            try {
                byte[] buf = new byte[4];
                inputStream.read(buf);
                int messagelength = ByteBuffer.wrap(buf).getInt();

                byte[] buffer = new byte[messagelength];
                inputStream.read(buffer, 0, messagelength);
                JSONObject message = decodeMessage(buffer);
                handleMessage(message);

            } catch (java.io.IOException ex) {
                System.out.println("Something went wrong while reading a message from the network.\n" + ex.getLocalizedMessage());
            }
        }
    }

    public void end() {
        shouldStop = true;
    }

    private JSONObject decodeMessage(byte[] buf) {
        JSONObject output = new JSONObject();
        // TODO: decide on encryption/encoding method
        return output;
    }

    private byte[] encodeMessage(JSONObject input) throws java.io.IOException {
        // TODO
        return new byte[0];
    }

    public synchronized void sendMessage(JSONObject message) throws java.io.IOException {
        outputStream.write(encodeMessage(message));
    }

    private void handleMessage(JSONObject message) {
        // big function inbound
    }
}
