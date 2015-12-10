package server;

import org.junit.Test;

import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Govert on 25-11-2015.
 */
public class ListenThreadTest {

    @Test
    public void testCloseAllConnections() throws Exception {
        ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>();
        ListenThread thread = new ListenThread(clients, 9999);
        thread.start();

        Thread.sleep(1000);

        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        Thread.sleep(1000);

        assertTrue(socket.isConnected() && !socket.isClosed());
        assertTrue(clients.get(0).getSocket().isConnected() && !clients.get(0).getSocket().isClosed());


        thread.closeAllConnections();

        Thread.sleep(1000);

        //assertTrue(!socket.isConnected() && socket.isClosed()); // I hate java.
        assertEquals(-1, socket.getInputStream().read());
        assertEquals(0, clients.size());

        thread.end();
    }

    @Test
    public void testStartThreadClientConnect() throws Exception {
        ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>();
        ListenThread thread = new ListenThread(clients, 9999);
        thread.start();

        Thread.sleep(1000);

        assertEquals(0, clients.size());

        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        Thread.sleep(1000);

        assertTrue(socket.isConnected());
        assertEquals(1, clients.size());

        thread.end();
    }
}