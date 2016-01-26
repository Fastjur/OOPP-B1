package test;

import communication.Backend;
import communication.IMessageListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Govert on 26-1-2016.
 */
public class ListenThreadTest implements IMessageListener {

    TestServer testServer;
    Response response;

    @Before
    public void setUp() throws Exception {
        this.response = null;

        testServer = new TestServer();
        testServer.start(3334);
        Backend.serverAddress = "::1";
        Backend.serverPort = 3334;

        Thread.sleep(500);
    }

    @After
    public void breakDown() throws Exception {
        testServer.dispose();
        Backend.closeConnection();
    }


    @Test
    public void testDatShit() throws Exception {
        assertNull(this.response);
        Backend.addMessageListener(this);
        Backend.connectToServer();
        testServer.sendMessage("{\"responseTo\":\"yourmom\",\"errorCode\":2,\"errorMessage\":\"u dun received tha message\",\"responseData\":{}}");
        Thread.sleep(500);
        assertEquals(2, this.response.errorCode);
        assertEquals("u dun received tha message", this.response.errorMessage);

    }

    public void onIncomingResponse(Response response) {
        this.response = response;
    }

    @Test
    public void testSendBullshit1() throws Exception {
        Backend.connectToServer();
        testServer.sendBytes(new byte[] {0, 0, 0, 0});
    }

    @Test
    public void testSendBullshit2() throws Exception {
        Backend.connectToServer();
        testServer.sendBytes(new byte[] {55,55,55,55});
    }
}