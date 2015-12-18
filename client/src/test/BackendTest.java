package test;

import communication.Backend;
import communication.IDisconnectListener;
import communication.IMessageListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import shared.Response;
import shared.User;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

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
        testServer.dispose();
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
        Assert.assertEquals(new Response("test"), this.response);
    }

    @Test
    public void testOnDisconnectZeroListeners() throws Exception {
        Backend.onDisconnect(true);
    }

    @Test
    public void testOnDisconnectSingleListener() throws Exception {
        Backend.addDisconnectListener(this);
        Date begin = new Date();
        Thread.sleep(1); // My computer was too fast ~G
        Backend.onDisconnect(true);
        assertTrue(begin.before(lastconnectionchange));
        assertTrue(erronousDisconnect);
    }

    @Test
    public void testRemoveAllListeners() {
        Backend.addMessageListener(this);
        Backend.removeAllListeners();
        Backend.onResponse(new Response("whoops"));
        assertNull(this.response);
    }

    @Test
    public void testConnectToServer() throws Exception {
        assertTrue(Backend.connectToServer());
    }

    @Test
    public void testConnectToServerAlreadyConnected() {
        Backend.connectToServer();
        assertTrue(Backend.connectToServer());
    }

    @Test
    public void testConnectToServerInvalidPort() {
        Backend.serverPort = -1;
        assertFalse(Backend.connectToServer());
    }

    @Test
    public void testConnectToServerInvalidAddress() {
        Backend.serverAddress = "3456789";
        assertFalse(Backend.connectToServer());
    }

    @Test
    public void testConnectToServerNullAddress() {
        Backend.serverAddress = null;
        assertFalse(Backend.connectToServer());
    }

    @Test
    public void testLogin() throws Exception {
        Backend.connectToServer();
        Backend.login("my_email", "my_pass");
        String result = testServer.receiveMessage();
        assertEquals("{\"action\":\"login\",\"requestData\":{\"pass\":\"my_pass\",\"email\":\"my_email\"}}", result);
    }

    @Test
    public void testLogout() throws Exception {
        Backend.connectToServer();
        Backend.logout();
        String result = testServer.receiveMessage();
        assertEquals("{\"action\":\"logout\",\"requestData\":{}}", result);
    }

    @Test
    public void testRegister() throws Exception {
        Backend.connectToServer();
        Backend.register(new User());
        String result = testServer.receiveMessage();
        assertEquals("{\"action\":\"register\",\"requestData\":{\"newUser\":" +
                "{\"password\":null,\"firstname\":null,\"lastname\":null,\"mail\":null," +
                "\"phonenumber\":null,\"study\":null,\"university\":null,\"gender\":null," +
                "\"nationality\":null,\"description\":null,\"birthday\":null,\"userID\":0," +
                "\"studyYear\":0,\"latitude\":0.0,\"longitude\":0.0,\"coursesTeachingList\":null," +
                "\"coursesLearningList\":null,\"buddyList\":null,\"languageList\":null," +
                "\"availableDates\":null}}}", result);
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
        Backend.addDisconnectListener(this);
        Backend.connectToServer();
        Date begin = new Date();
        Backend.closeConnection();
        Thread.sleep(100);
        assertTrue(begin.before(lastconnectionchange));
        assertFalse(erronousDisconnect);
    }

    @Test
    public void testNotConnectedLoginRegisterLogoutMatch() {
        Backend.login("email","pass");
        Backend.logout();
        Backend.register(new User());
        Backend.match(new User());
        assertNull(this.response);
    }
}