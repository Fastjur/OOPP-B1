package server;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Govert on 29-11-2015.
 */
public class ConnectedClientTest {
    ArrayList<ConnectedClient> clients;
    ListenThread thread;
    ObjectMapper mapper;

    @BeforeClass
    public static void setUpDatabase() {
        Server.SetupDudDatabaseForTesting();
    }

    @Before
    public void startlistenthread() throws Exception{
        mapper = new ObjectMapper();
        clients = new ArrayList<>();
        thread = new ListenThread(clients, 9999);
        thread.start();
        Thread.sleep(1000);
    }

    @After
    public void stoplisthenthread() {
        thread.end();
    }

    @Test
    public void testCloseConnection() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(1000);

        assertTrue(socket.isConnected());
        assertTrue(clients.get(0).getSocket().isConnected());


        clients.get(0).closeConnection();

        Thread.sleep(1000);

        assertEquals(-1, socket.getInputStream().read());
    }

    @Test
    public void testSendMessage() throws Exception {
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

        assertEquals(json, message);
    }

    Response readResponse(InputStream inputStream) throws Exception {
        byte[] lenbuf = new byte[4];
        inputStream.read(lenbuf);
        int len = ByteBuffer.wrap(lenbuf).getInt();
        byte[] responsebuf = new byte[len];
        inputStream.read(responsebuf);
        return mapper.readValue(new String(responsebuf, StandardCharsets.UTF_8), Response.class);
    }

    @Test
    public void testLogin() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(1000);

        OutputStream outStream = socket.getOutputStream();
        InputStream inStream = socket.getInputStream();

        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        requestData.put("pass","Pepernoten01");
        request.put("requestData", requestData);

        byte[] msg = mapper.writeValueAsString(request).getBytes(StandardCharsets.UTF_8);

        outStream.write(ByteBuffer.allocate(4).putInt(msg.length).array());
        outStream.write(msg);

        Response response = readResponse(inStream);

        assertEquals(0, response.errorCode);
        assertEquals("Login successful.", response.errorMessage);
    }
}