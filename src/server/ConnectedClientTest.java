package server;

import org.junit.Test;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Govert on 29-11-2015.
 */
public class ConnectedClientTest {

    @Test
    public void testCloseConnection() throws Exception {
        ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>();
        ListenThread thread = new ListenThread(clients, 9999);
        thread.start();

        Thread.sleep(1000);

        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        Thread.sleep(1000);

        assertTrue(socket.isConnected());
        assertTrue(clients.get(0).getSocket().isConnected());


        clients.get(0).closeConnection();

        Thread.sleep(1000);

        assertEquals(-1, socket.getInputStream().read());

        thread.end();
    }

    @Test
    public void testSendMessage() throws Exception {
        ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>();
        ListenThread thread = new ListenThread(clients, 9999);
        thread.start();

        Thread.sleep(1000);

        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        Thread.sleep(1000);

        InputStream stream = socket.getInputStream();

        String json = "{ \"action\", \"test\" }";

        clients.get(0).sendMessage(json);
        byte[] buffer = new byte[4];
        stream.read(buffer);
        int messagelength = ByteBuffer.wrap(buffer).getInt();
        byte[] messagebuffer = new byte[messagelength];
        stream.read(messagebuffer);

        String message = new String(messagebuffer, StandardCharsets.UTF_8);
        message.equals(json);

        assertEquals(json, message);

        thread.end();
    }
}