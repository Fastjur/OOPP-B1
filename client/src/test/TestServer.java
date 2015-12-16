package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by Govert on 15-12-2015.
 */
public class TestServer extends Thread {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;

    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {

        }
        this.start();
    }

    @Override
    public void run() {
        try {
            this.socket = serverSocket.accept();
            this.inputStream = this.socket.getInputStream();
        } catch (IOException e) {

        }
    }

    public String receiveMessage() throws IOException {
        byte[] buf = new byte[4];
        inputStream.read(buf);
        int messagelength = ByteBuffer.wrap(buf).getInt();
        byte[] buffer = new byte[messagelength];
        inputStream.read(buffer, 0, messagelength);
        return decodeMessage(buffer);
    }

    private String decodeMessage(byte[] buf) {
        return new String(buf, StandardCharsets.UTF_8);
    }
}
