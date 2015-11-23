package server;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
                if (messagelength > 0 && messagelength < 65536) {
                    byte[] buffer = new byte[messagelength];
                    inputStream.read(buffer, 0, messagelength);
                    JSONObject message = decodeMessage(buffer);
                    handleMessage(message);
                } else {
                    System.out.println("Client sent an invalid request, disconnecting client.");
                    this.client.closeConnection();
                }
            } catch (java.io.IOException ex) {
                System.out.println("Something went wrong while reading a message from the network.\n" + ex.getLocalizedMessage());
            }
        }
    }

    public void end() {
        shouldStop = true;
    }

    private JSONObject decodeMessage(byte[] buf) {
        return new JSONObject(new String(buf, StandardCharsets.UTF_8));
    }

    private byte[] encodeMessage(JSONObject input) {
        return input.toString(0).getBytes(StandardCharsets.UTF_8); // TODO: encryption?
    }

    public synchronized void sendMessage(JSONObject message) throws java.io.IOException {
        byte[] msg = encodeMessage(message);
        outputStream.write(ByteBuffer.allocate(4).putInt(msg.length).array());
        outputStream.write(msg);
    }

    private void handleMessage(JSONObject message) {
        // big function inbound
    }
}
