import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Govert on 15-12-2015.
 */
public class BackendTest implements IMessageListener, IDisconnectListener {

    TestServer testServer;
    Response response;
    Date lastconnectionchange;
    boolean erronousDisconnect;

    @Before
    public void setUp() throws InterruptedException {
        this.response = null;
        lastconnectionchange = null;
        erronousDisconnect = false;

        testServer = new TestServer();
        testServer.start(3334);
        Backend.serverAddress = "::1";
        Backend.serverPort = 3334;

        Thread.sleep(100);
    }

    @After
    public void breakDown() throws IOException {
        Backend.closeConnection();
    }

    public void onIncomingResponse(Response response) {
        this.response = response;
    }

    public void onDisconnect(boolean erroneous) {
        lastconnectionchange = new Date();
        erronousDisconnect = erroneous;
    }

    @Test
    public void testOnResponseZeroListeners() throws Exception {
        Backend.onResponse(new Response("test"));
    }

    @Test
    public void testOnResponseSingleListener() throws Exception {
        Backend.addMessageListener(this);
        Backend.onResponse(new Response("test"));
        assertEquals(new Response("test"), this.response);
    }

    @Test
    public void testOnDisconnectZeroListeners() throws Exception {
        Backend.onDisconnect(true);
    }

    @Test
    public void testOnDisconnectSingleListener() throws Exception {
        Backend.addDisconnectListener(this);
        Date begin = new Date();
        Backend.onDisconnect(true);
        assertTrue(begin.before(lastconnectionchange));
        assertTrue(erronousDisconnect);
    }

    @Test
    public void testConnectToServer() throws Exception {
        assertTrue(Backend.connectToServer());
    }

    @Test
    public void testLogin() throws Exception {

    }

    @Test
    public void testLogout() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {

    }

    @Test
    public void testIsConnectedTrue() throws Exception {
        Backend.connectToServer();
        assertTrue(Backend.isConnected());
    }

    @Test
    public void testIsConnectedFalse() throws Exception {
        Backend.connectToServer();
        Backend.closeConnection();
        Thread.sleep(100);
        assertFalse(Backend.isConnected());
    }

    @Test
    public void testCloseConnection() throws Exception {
        Date begin = new Date();
        Backend.closeConnection();
        Thread.sleep(100);
        assertTrue(begin.before(lastconnectionchange));
    }
}