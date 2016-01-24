import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import shared.AvailableTimes;
import shared.Response;
import shared.TimePeriod;
import shared.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    public String getXAsString(Socket socket, String whatToGet) throws Exception {
        Map<String, Object> request = new HashMap<>();
        request.put("action", "get" + whatToGet);
        SendRequest(socket, request);
        return readResponseAsString(socket.getInputStream());
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
        String str = new String(responsebuf, StandardCharsets.UTF_8);
        return mapper.readValue(str, Response.class);
    }

    String readResponseAsString(InputStream inputStream) throws Exception {
        byte[] lenbuf = new byte[4];
        inputStream.read(lenbuf);
        int len = ByteBuffer.wrap(lenbuf).getInt();
        byte[] responsebuf = new byte[len];
        inputStream.read(responsebuf);
        return new String(responsebuf, StandardCharsets.UTF_8);
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

    @Test
    public void testFindMatchEmergency() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "emergency");
        requestData.put("course", "Web en Database technologie");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Matched emergency tutor!", response.errorMessage);
    }

    @Test
    public void testFindMatchEmergencyNoMatch() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "findMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("type", "emergency");
        requestData.put("course", "Haxorz");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(9, response.errorCode);
        assertEquals("Couldn't match any emergency tutors!", response.errorMessage);
    }

    @Test
    public void testAcceptMatchNotLoggedIn() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "acceptMatch");

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(2, response.errorCode);
        assertEquals("You are not logged in", response.errorMessage);
    }

    @Test
    public void testAcceptMatchInvalidMatchType() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "acceptMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("matchUser", 5);
        requestData.put("matchType", "drinking");
        requestData.put("matchCourse", "Beer");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(5, response.errorCode);
        assertEquals("Wrong match type received!", response.errorMessage);
    }

    @Test
    public void testAcceptMatchInvalidUserID() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "acceptMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("matchUser", 0);
        requestData.put("matchType", "teaching");
        requestData.put("matchCourse", "Calculus");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(5, response.errorCode);
        assertEquals("Didn't receive matchUserId", response.errorMessage);
    }

    @Test
    public void testAcceptMatch() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "acceptMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("matchUser", 80000);
        requestData.put("matchType", "teaching");
        requestData.put("matchCourse", "Calculus");
        request.put("requestData", requestData);

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Added match to database!", response.errorMessage);
    }

    @Test
    public void testRemoveMatchNotLoggedIn() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "removeMatch");

        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(2, response.errorCode);
        assertEquals("You are not logged in!", response.errorMessage);
    }

    @Test
    public void testRemoveMatch() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "removeMatch");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("matchId", 80000);
        request.put("requestData", requestData);
        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals("Removed match from database!", response.errorMessage);
    }

    @Test
    public void testGetSelfNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getSelf");
    }

    @Test
    public void testGetSelf() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        new DatabaseTest().setUp();

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        String responsestr = getXAsString(socket, "Self");
        Response response = mapper.readValue(responsestr, Response.class);

        assertEquals(0, response.errorCode);
        assertEquals("Retrieved your information!", response.errorMessage);
        User dbself = mapper.treeToValue(mapper.readTree(responsestr).get("responseData").get("self"), User.class);
        assertEquals(DatabaseTest.user, dbself);
    }

    public void testUniversalGet(String whattoget, String expectedmessage, String requestdataobject, String expected) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "get" + whattoget);
        SendRequest(socket, request);
        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals(expectedmessage, response.errorMessage);
        Object result = response.getResponseData().get(requestdataobject);
        assertEquals(expected, result.toString());
    }

    public void testUniversalNotLoggedIn(String command) throws Exception{
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", command);
        SendRequest(socket, request);
        Response response = readResponse(socket.getInputStream());

        assertEquals(2, response.errorCode);
        assertEquals("You are not logged in!", response.errorMessage);
    }

    @Test
    public void testGetNationalitiesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getNationalities");
    }

    @Test
    public void testGetNationalities() throws Exception {
        testUniversalGet("Nationalities", "Retreived database nationalities!",
                "nationalities", "{1=Nederlands, 2=Engels, 3=Duits, 4=Frans}");
    }

    @Test
    public void testGetLanguagesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getLanguages");
    }

    @Test
    public void testGetLanguages() throws Exception {
        testUniversalGet("Languages","Retreived database languages!",
                "dbLanguages", "{1=Nederlands, 2=Duits, 3=Frans, 4=Engels, 5=C++, 6=Java}");
    }

    @Test
    public void testGetStudiesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getStudies");
    }

    @Test
    public void testGetStudies() throws Exception {
        testUniversalGet("Studies","Retreived database studies!", "dbStudies",
                "{1=Technische Wiskunde, 2=Technische Natuurkunde, 3=Computer Sciences and Engineering, 4=Nanobiology}");
    }

    @Test
    public void testGetUniversitiesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getUniversities");
    }

    @Test
    public void testGetUniversities() throws Exception {
        testUniversalGet("Universities", "Retreived database universities!", "dbUniversities",
                "{1=Technische Universiteit Delft, 2=Wageningen University, 3=Technische Universiteit Eindhoven, "
                        + "4=Leiden University College}");
    }

    @Test
    public void testGetCoursesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getCourses");
    }

    @Test
    public void testGetCourses() throws Exception {
        testUniversalGet("Courses", "Retreived database courses!", "dbCourses",
                "{1=Calculus, 2=Web en Database technologie, 3=Object Oriënted Programming}");
    }

    public void testUpdateUniversal(String whattoupdate, String expectedmessage, Map<String, Object> requestData) throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        LoginWithTestAccount(socket);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "update" + whattoupdate);
        request.put("requestData", requestData);
        SendRequest(socket, request);

        Response response = readResponse(socket.getInputStream());

        assertEquals(0, response.errorCode);
        assertEquals(expectedmessage, response.errorMessage);
    }

    @Test
    public void testUpdateNationalityNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateNationality");
    }

    @Test
    public void testUpdateNationality() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("nationality", 1);
        testUpdateUniversal("Nationality", "Updated nationality!", requestData);
    }

    @Test
    public void testUpdateNameNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateName");
    }

    @Test
    public void testUpdateName() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("firstname", "Sinter");
        requestData.put("lastname", "Klaas");
        testUpdateUniversal("Name", "Updated name!", requestData);
    }

    @Test
    public void testUpdateDateOfBirthNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateDateOfBirth");
    }

    @Test
    public void testUpdateDateOfBirth() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("date", 218592000000L);
        testUpdateUniversal("DateOfBirth", "Updated dateOfBirth!", requestData);
    }

    @Test
    public void testUpdateLanguagesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateLanguages");
    }

    @Test
    public void testUpdateLanguages() throws Exception {
        ArrayList<Integer> newlanguages = new ArrayList<>();
        newlanguages.add(1);
        newlanguages.add(4);
        newlanguages.add(5);
        newlanguages.add(6);
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("languages", newlanguages);
        testUpdateUniversal("Languages", "Updated languages!", requestData);
    }

    @Test
    public void testUpdateSexNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateSex");
    }

    @Test
    public void testUpdateSex() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("sex", "male");
        testUpdateUniversal("Sex", "Updated sex!", requestData);
    }

    @Test
    public void testUpdateEmailNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateEmail");
    }

    @Test
    public void testUpdateEmail() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("email", "sinterklaas@sintmail.nl");
        testUpdateUniversal("Email", "Updated email!", requestData);
    }

    @Test
    public void testUpdatePhoneNumberNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updatePhoneNumber");
    }

    @Test
    public void testUpdatePhoneNumber() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("phoneNumber", "+31612345678");
        testUpdateUniversal("PhoneNumber", "Updated phoneNumber!", requestData);
    }

    @Test
    public void testUpdateLocationNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateLocation");
    }

    @Test
    public void testUpdateLocation() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("longitude", 4.34825);
        requestData.put("latitude", 51.9827);
        testUpdateUniversal("Location", "Updated location!", requestData);
    }

    @Test
    public void testUpdateUniversityNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateUniversity");
    }

    @Test
    public void testUpdateUniversity() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("university", 1);
        testUpdateUniversal("University", "Updated university!", requestData);
    }

    @Test
    public void testUpdateStudyNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateStudy");
    }

    @Test
    public void testUpdateStudy() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("study", 3);
        testUpdateUniversal("Study", "Updated study!", requestData);
    }

    @Test
    public void testUpdateStudyYearNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateStudyYear");
    }

    @Test
    public void testUpdateStudyYear() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("studyYear", 1);
        testUpdateUniversal("StudyYear", "Updated studyYear!", requestData);
    }

    @Test
    public void testUpdateLearningNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateLearning");
    }

    @Test
    public void testUpdateLearning() throws Exception {
        ArrayList<Integer> newlearning = new ArrayList<>();
        newlearning.add(1);
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("learning", newlearning);
        testUpdateUniversal("Learning", "Updated learning!", requestData);
    }

    @Test
    public void testUpdateTeachingNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateTeaching");
    }

    @Test
    public void testUpdateTeaching() throws Exception {
        ArrayList<Integer> newteaching = new ArrayList<>();
        newteaching.add(2);
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("teaching", newteaching);
        testUpdateUniversal("Teaching", "Updated teaching!", requestData);
    }

    @Test
    public void testUpdateBuddiesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateBuddies");
    }

    @Test
    public void testUpdateBuddies() throws Exception {
        ArrayList<Integer> newbuddies = new ArrayList<>();
        newbuddies.add(1);
        newbuddies.add(2);
        newbuddies.add(3);
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("buddies", newbuddies);
        testUpdateUniversal("Buddies", "Updated buddies!", requestData);
    }

    @Test
    public void testUpdateAvailabilityNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("updateAvailability");
    }

    @Test
    public void testAvailabilityBuddies() throws Exception {
        AvailableTimes newavailability = new AvailableTimes();
        newavailability.addTimePeriod(1, new TimePeriod(450, 900));
        newavailability.addTimePeriod(1, new TimePeriod(1110, 1200));
        newavailability.addTimePeriod(2, new TimePeriod(0, 0));
        newavailability.addTimePeriod(3, new TimePeriod(0, 0));
        newavailability.addTimePeriod(4, new TimePeriod(0, 0));
        newavailability.addTimePeriod(5, new TimePeriod(0, 0));
        newavailability.addTimePeriod(6, new TimePeriod(0, 0));
        newavailability.addTimePeriod(7, new TimePeriod(0, 0));
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("availability", newavailability);
        testUpdateUniversal("Availability", "Updated availability!", requestData);
    }

    @Test
    public void testGetBuddiesNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getBuddies");
    }

    @Test
    public void testGetBuddies() throws Exception {
        testUniversalGet("Buddies","Retrieved your buddies.",
                "buddies", "[{password=1000:e247dda8cdac0848b9871e5ab87b68b685a771e15b8ec115:" +
                        "f1c7a7b2c70ebd2cd94dfd72fd44ee30821d2672cb384a1c, firstname=Jurriaan, lastname=Den Toonder, " +
                        "mail=speedy4104@hotmail.com, phonenumber=+31625599899, " +
                        "study=Computer Sciences and Engineering, university=Technische Universiteit Delft, " +
                        "gender=male, nationality=Nederlands, description=, birthday=1970-01-01, userID=40, " +
                        "studyYear=1, latitude=48.0, longitude=4.0, maxDistance=0.0, " +
                        "coursesTeachingList=[Calculus], coursesLearningList=[Web en Database technologie], " +
                        "buddyList=[Calculus, Web en Database technologie], languageList=[Nederlands, Engels, Java]," +
                        " availableDates={monday=[{start=450, end=900}], tuesday=[{start=0, end=0}], " +
                        "wednesday=[{start=0, end=0}], thursday=[{start=0, end=0}], friday=[{start=0, end=0}], " +
                        "saturday=[{start=0, end=0}], sunday=[{start=0, end=0}]}}, " +
                        "{password=1000:1cd546acb6e9356411622506a8d091c5a9c4bde1e62f9559:" +
                        "7e10b3cc937a6805705238c6dd22a8e6cec5a54004bbdad8, firstname=Zoe, lastname=van Steijn, " +
                        "mail=zoevansteijn@hotmail.com, phonenumber=0636382999, " +
                        "study=Computer Sciences and Engineering, university=Technische Universiteit Delft, " +
                        "gender=female, nationality=Nederlands, description=, birthday=1997-01-17, userID=59, " +
                        "studyYear=0, latitude=0.0, longitude=0.0, maxDistance=0.0, coursesTeachingList=[Calculus], " +
                        "coursesLearningList=[Web en Database technologie], buddyList=[Calculus, " +
                        "Web en Database technologie, Object Oriënted Programming], languageList=[Nederlands, " +
                        "Engels, Java], availableDates={monday=[{start=450, end=900}], tuesday=[{start=0, end=0}], " +
                        "wednesday=[{start=0, end=0}], thursday=[{start=0, end=0}], friday=[{start=0, end=0}], " +
                        "saturday=[{start=0, end=0}], sunday=[{start=0, end=0}]}}]");
    }

    @Test
    public void testGetStudentsNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getStudents");
    }

    @Test
    public void testGetStudents() throws Exception {
        testUniversalGet("Students","Retrieved your students.",
                "students", "[]");
    }

    @Test
    public void testGetTutorsNotLoggedIn() throws Exception {
        testUniversalNotLoggedIn("getTutors");
    }

    @Test
    public void testGetTutors() throws Exception {
        testUniversalGet("Tutors","Retrieved your tutors.",
                "tutors", "[{password=1000:1cd546acb6e9356411622506a8d091c5a9c4bde1e62f9559:" +
                        "7e10b3cc937a6805705238c6dd22a8e6cec5a54004bbdad8, firstname=Zoe, lastname=van Steijn, " +
                        "mail=zoevansteijn@hotmail.com, phonenumber=0636382999, " +
                        "study=Computer Sciences and Engineering, university=Technische Universiteit Delft, " +
                        "gender=female, nationality=Nederlands, description=, birthday=1997-01-17, userID=59, " +
                        "studyYear=0, latitude=0.0, longitude=0.0, maxDistance=0.0, coursesTeachingList=[Calculus], " +
                        "coursesLearningList=[Web en Database technologie], buddyList=[Calculus, " +
                        "Web en Database technologie, Object Oriënted Programming], languageList=[Nederlands, " +
                        "Engels, Java], availableDates={monday=[{start=450, end=900}], tuesday=[{start=0, end=0}], " +
                        "wednesday=[{start=0, end=0}], thursday=[{start=0, end=0}], friday=[{start=0, end=0}], " +
                        "saturday=[{start=0, end=0}], sunday=[{start=0, end=0}]}}]");
    }

    @Test
    public void testUnknownCommand() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "not-a-command");
        SendRequest(socket, request);
        Response response = readResponse(socket.getInputStream());

        assertEquals(1, response.errorCode);
        assertEquals("Unknown command.", response.errorMessage);
    }

    @Test
    public void testMalformedRequest() throws Exception {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        Thread.sleep(100);

        Map<String, Object> request = new HashMap<>();
        request.put("action", "login");
        SendRequest(socket, request);
    }
}