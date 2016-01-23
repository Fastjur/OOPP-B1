import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import shared.Response;

import java.io.IOException;
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
    public void startlistenthread() throws Exception {
        mapper = new ObjectMapper();
        clients = new ArrayList<>();
        thread = new ListenThread(clients, 9999);
        thread.start();
        Thread.sleep(100);
    }

    @After
    public void stoplisthenthread() {
        thread.end();
    }

    public void SendRequest(Socket socket, Map<String, Object> request) throws IOException{
        byte[] msg = mapper.writeValueAsString(request).getBytes(StandardCharsets.UTF_8);

        socket.getOutputStream().write(ByteBuffer.allocate(4).putInt(msg.length).array());
        socket.getOutputStream().write(msg);
    }

    public void LoginWithTestAccount(Socket socket) throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        requestData.put("pass", "Pepernoten01");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        readResponse(socket.getInputStream());
    }

    @Test
    public void testCloseConnection() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        assertTrue(socket.isConnected());
        assertTrue(clients.get(0).getSocket().isConnected());


        clients.get(0).closeConnection();

        Thread.sleep(100);

        assertEquals(-1, socket.getInputStream().read());
    }

    @Test
    public void testSendMessage() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

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

    @Test
    public void testSendMessageFail() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        InputStream stream = socket.getInputStream();

        String json = "{ \"action\", \"test\" }";
        clients.get(0).getSocket().getOutputStream().close();
        clients.get(0).sendMessage(json);
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

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        requestData.put("pass", "Pepernoten01");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Login successful!", response.errorMessage);
    }

    @Test
    public void testLoginWhenLoggedIn() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        requestData.put("pass", "Pepernoten01");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        SendRequest(socket, request);

        response = readResponse(socket.getInputStream());

        assertEquals(2, response.errorCode);
        assertEquals("You are already logged in.", response.errorMessage);
    }

    @Test
    public void testLoginInvalidPass() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        requestData.put("pass", "Pepernoten02");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(3, response.errorCode);
        assertEquals("Invalid email/password.", response.errorMessage);
    }

    @Test
    public void testLoginInvalidUser() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "iudgioaiuaiuaisllldflbgb");
        requestData.put("pass", "nop");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(3, response.errorCode);
        assertEquals("Couldn't find user!", response.errorMessage);
    }

    @Test
    public void testLogoutNotLoggedIn() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "logout");

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(2, response.errorCode);
        assertEquals("You are not logged in.", response.errorMessage);
    }

    @Test
    public void testLogout() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        requestData.put("pass", "Pepernoten01");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        request = new HashMap<>();
        request.put("action", "logout");

        SendRequest(socket, request);

        response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Logout successful.", response.errorMessage);
    }

    @Test
    public void testRegisterNew() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "register");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "DeleteMe@JUnit.test");
        requestData.put("password", "Wowheyguys");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Registration successful.", response.errorMessage);

        Server.getDb().removeUser(Server.getDb().getUser("DeleteMe@JUnit.test").getUserID());
    }

    @Test
    public void testRegisterExisting() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "register");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        requestData.put("password", "Wowheyguys");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(2, response.errorCode);
        assertEquals("That user already exists.", response.errorMessage);
    }

    @Test
    public void testSendBogusMessageStrLevel() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        OutputStream outStream = socket.getOutputStream();

        byte[] msg = "Do you have battletoads?".getBytes(StandardCharsets.UTF_8);

        outStream.write(ByteBuffer.allocate(4).putInt(msg.length).array());
        outStream.write(msg);

        Thread.sleep(100);
    }

    @Test
    public void testSendBogusMessageByteLevel() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        OutputStream outStream = socket.getOutputStream();

        byte[] msg = {(byte)0xDE, (byte)0xAD, (byte)0xBE, (byte)0xEF, (byte)0xC0, (byte)0xFF, (byte)0xEE};
        outStream.write(msg);

        Thread.sleep(100);


        Socket socket2 = new Socket(InetAddress.getLocalHost(), 9999);
        socket2.getOutputStream().write(new byte[] {5,5,5,5});
        Thread.sleep(100);
    }

    @Test
    public void testRemoteClose() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        socket.close();

        Thread.sleep(100);
    }

    @Test
    public void testFindMatchNotLoggedIn() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(2, response.errorCode);
        assertEquals("You are not logged in.", response.errorMessage);
    }

    @Test
    public void testFindMatchBuddy() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "buddy");
        requestData.put("course", "Calculus");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Matched buddies!", response.errorMessage);
    }

    @Test
    public void testFindMatchBuddyNoMatch() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "buddy");
        requestData.put("course", "Haxorz");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(9, response.errorCode);
        assertEquals("Couldn't match any buddies!", response.errorMessage);
    }

    @Test
    public void testFindMatchLearning() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "learning");
        requestData.put("course", "Calculus");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Matched tutor!", response.errorMessage);
    }

    @Test
    public void testFindMatchLearningNoMatch() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "learning");
        requestData.put("course", "Haxorz");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(9, response.errorCode);
        assertEquals("Couldn't match any tutors!", response.errorMessage);
    }

    @Test
    public void testFindMatchTeaching() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "teaching");
        requestData.put("course", "Web en Database technologie");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Matched student!", response.errorMessage);
    }

    @Test
    public void testFindMatchTeachingNoMatch() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "teaching");
        requestData.put("course", "Haxorz");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(9, response.errorCode);
        assertEquals("Couldn't match any students!", response.errorMessage);
    }
}