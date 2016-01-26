package test;

import communication.Backend;
import communication.IDisconnectListener;
import communication.IMessageListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import shared.AvailableTimes;
import shared.Response;
import shared.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Govert on 15-12-2015.
 * Dependencies: Hamcrest (http://hamcrest.org/JavaHamcrest/)
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

        Thread.sleep(500);
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
        Backend.register("TestUser", "TestPass");
        String result = testServer.receiveMessage();
        assertEquals("{\"action\":\"register\",\"requestData\":{\"password\":\"TestPass\",\"email\":\"TestUser\"}}", result);
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
        Backend.register("", "");
        //Backend.match(new User());
        assertNull(this.response);
    }
    
    @Test
    public void testFindStudyBuddy() throws Exception {
    	Backend.connectToServer();
    	Backend.findStudyBuddy("myCourse");
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"findMatch\",\"requestData\":{\"course\":\"myCourse\",\"type\":\"buddy\"}}", result);
    }
    
    @Test
    public void testNotConnectedFindStudyBuddy() {
    	Backend.findStudyBuddy("");
    	assertNull(this.response);
    }
    
    @Test
    public void testAcceptMatch() throws Exception {
    	Backend.connectToServer();
    	/*Backend.acceptMatch(new User(1, "Pepernoten01", "Sinter", "Klaas", new Date(1),
                "sinterklaas@sintmail.nl", "+316123456789",
                "study1", "university", 3, new AvailableTimes(), new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>(), "male", "NLD",
                new ArrayList<String>(), "It's-a-me", 0, 3, 5), "myMatchType");*/
        Backend.acceptMatch(1, "myMatchType", "Blargh");
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"acceptMatch\",\"requestData\":{\"matchType\":\"myMatchType\"," +
                "\"matchUser\":1,\"matchCourse\":\"Blargh\"}}", result);
    }
    
    @Test
    public void testNotConnectedAcceptMatch() {
    	Backend.acceptMatch(1, "", "");
    	assertNull(this.response);    	
    }
    
    @Test
    public void testGetSelf() throws Exception {
    	Backend.connectToServer();
    	Backend.getSelf();
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"getSelf\",\"requestData\":{}}", result);
    }
    
    @Test
    public void testNotConnectedGetSelf() {
    	Backend.getSelf();
    	assertNull(this.response);
    }
    
    @Test
    public void testGetNationalities() throws Exception {
    	Backend.connectToServer();
    	Backend.getNationalities();
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"getNationalities\",\"requestData\":{}}", result);
    }
    
    @Test
    public void testNotConnectedGetNationalities() {
    	Backend.getNationalities();
    	assertNull(this.response);
    }
    
    @Test
    public void testGetLanguages() throws Exception {
    	Backend.connectToServer();
    	Backend.getLanguages();
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"getLanguages\",\"requestData\":{}}", result);
    }
    
    @Test
    public void testNotConnectedGetLanguages() {
    	Backend.getLanguages();
    	assertNull(this.response);
    }
    
    @Test
    public void testGetStudies() throws Exception {
    	Backend.connectToServer();
    	Backend.getStudies();
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"getStudies\",\"requestData\":{}}", result);
    }
    
    @Test
    public void testNotConnectedGetStudies() {
    	Backend.getStudies();
    	assertNull(this.response);
    }
    
    @Test
    public void testGetUniversities() throws Exception {
    	Backend.connectToServer();
    	Backend.getUniversities();
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"getUniversities\",\"requestData\":{}}", result);
    }
    
    @Test
    public void testNotConnectedGetUniversities() {
    	Backend.getUniversities();
    	assertNull(this.response);
    }
    
    @Test
    public void testGetCourses() throws Exception {
    	Backend.connectToServer();
    	Backend.getCourses();
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"getCourses\",\"requestData\":{}}", result);
    }
    
    @Test
    public void testNotConnectedGetCourses() {
    	Backend.getCourses();
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateNationality() throws Exception {
    	Backend.connectToServer();
    	Backend.updateNationality(2);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateNationality\",\"requestData\":{\"nationality\":2}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateNationality() {
    	Backend.updateNationality(2);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateName() throws Exception {
    	Backend.connectToServer();
    	Backend.updateName("first last");
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateName\",\"requestData\":{\"firstname\":\"first\",\"lastname\":\"last\"}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateName() {
    	Backend.updateName("first last");
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateSex() throws Exception {
    	Backend.connectToServer();
    	Backend.updateSex("female");
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateSex\",\"requestData\":{\"sex\":\"female\"}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateSex() {
    	Backend.updateSex("female");
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateDateOfBirth() throws Exception {
    	Backend.connectToServer();
    	Backend.updateDateOfBirth(LocalDate.of(2012, 12, 12));
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateDateOfBirth\",\"requestData\":{\"date\":1355266800000}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateDateOfBirth() {
    	Backend.updateDateOfBirth(LocalDate.of(2012, 12, 12));
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateLanguages() throws Exception {
    	Backend.connectToServer();
    	ArrayList<Integer> languages = new ArrayList<Integer>();
    	languages.add(1);
    	Backend.updateLanguages(languages);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateLanguages\",\"requestData\":{\"languages\":[1]}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateLanguages() {
    	ArrayList<Integer> languages = new ArrayList<Integer>();
    	languages.add(1);
    	Backend.updateLanguages(languages);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateEmail() throws Exception {
    	Backend.connectToServer();
    	Backend.updateEmail("email@live.com");
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateEmail\",\"requestData\":{\"email\":\"email@live.com\"}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateEmail() {
    	Backend.updateEmail("email@live.com");
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateTelephoneNumber() throws Exception {
    	Backend.connectToServer();
    	Backend.updateTelephoneNumber("+31612345678");
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updatePhoneNumber\",\"requestData\":{\"phoneNumber\":\"+31612345678\"}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateTelephoneNumber() {
    	Backend.updateTelephoneNumber("+31612345678");
    	assertNull(this.response);
    }
  /*  TODO
    @Test
    public void testUpdateLocation() throws Exception {
    	System.out.println("1yoo");
    	Backend.connectToServer();
    	System.out.println("2yoo");
    	Backend.updateLocation("5,10");
    	System.out.println("3yoo");
    	String result = testServer.receiveMessage();
    	System.out.println("result");
    	assertEquals("{\"action\":\"updateLocation\",\"requestData\":\"{\\\"location\\\":\\\"longitude\\\"}\"}", result);
    }
   */ 
    @Test
    public void testNotConnectedUpdateLocation() {
    	Backend.updateLocation("Delft");
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateUniversity() throws Exception {
    	Backend.connectToServer();
    	Backend.updateUniversity(5);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateUniversity\",\"requestData\":{\"university\":5}}", result);
    }
 
    @Test
    public void testNotConnectedUpdateUniversity() {
    	Backend.updateUniversity(5);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateStudy() throws Exception {
    	Backend.connectToServer();
    	Backend.updateStudy(5);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateStudy\",\"requestData\":{\"study\":5}}", result);
    }
 
    @Test
    public void testNotConnectedUpdateStudy() {
    	Backend.updateStudy(5);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateStudyYear() throws Exception {
    	Backend.connectToServer();
    	Backend.updateStudyYear(5);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateStudyYear\",\"requestData\":{\"studyYear\":5}}", result);
    }
 
    @Test
    public void testNotConnectedUpdateStudyYear() {
    	Backend.updateStudyYear(5);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateLearning() throws Exception {
    	Backend.connectToServer();
    	ArrayList<Integer> learn = new ArrayList<Integer>();
    	learn.add(1);
    	Backend.updateLearning(learn);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateLearning\",\"requestData\":{\"learning\":[1]}}", result);
    }
 
    @Test
    public void testNotConnectedUpdateLearning() {
    	ArrayList<Integer> learn = new ArrayList<Integer>();
    	learn.add(1);
    	Backend.updateLearning(learn);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateTeaching() throws Exception {
    	Backend.connectToServer();
    	ArrayList<Integer> teach = new ArrayList<Integer>();
    	teach.add(1);
    	Backend.updateTeaching(teach);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateTeaching\",\"requestData\":{\"teaching\":[1]}}", result);
    }
 
    @Test
    public void testNotConnectedUpdateTeaching() {
    	ArrayList<Integer> teach = new ArrayList<Integer>();
    	teach.add(1);
    	Backend.updateTeaching(teach);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateBuddies() throws Exception {
    	Backend.connectToServer();
    	ArrayList<Integer> buddy = new ArrayList<Integer>();
    	buddy.add(1);
    	Backend.updateBuddies(buddy);
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateBuddies\",\"requestData\":{\"buddies\":[1]}}", result);
    }
 
    @Test
    public void testNotConnectedUpdateBuddies() {
    	ArrayList<Integer> buddy = new ArrayList<Integer>();
    	buddy.add(1);
    	Backend.updateBuddies(buddy);
    	assertNull(this.response);
    }
    
    @Test
    public void testUpdateAvailability() throws Exception {
    	Backend.connectToServer();
    	Backend.updateAvailability(new AvailableTimes());
    	String result = testServer.receiveMessage();
    	assertEquals("{\"action\":\"updateAvailability\",\"requestData\":{\"availability\":{\"monday\":[],\"tuesday\"" +
                ":[],\"wednesday\":[],\"thursday\":[],\"friday\":[],\"saturday\":[],\"sunday\":[]}}}", result);
    }
    
    @Test
    public void testNotConnectedUpdateAvailability() {
    	Backend.updateAvailability(new AvailableTimes());
    	assertNull(this.response);
    }

    @Test
    public void testSetGetSelfObject() throws Exception {
    	Backend.connectToServer();

    	User testuser = new User(1, "Pepernoten01", "Sinter", "Klaas", new Date(1),
                "sinterklaas@sintmail.nl", "+316123456789",
                "study1", "university", 3, new AvailableTimes(), new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>(), "male", "NLD",
                new ArrayList<String>(), "It's-a-me", 0, 3, 5); 

        Backend.setSelfObject(testuser);
        
        User testuser2 = Backend.getSelfObject();
        assertEquals(testuser, testuser2);
    }

    @Test
    public void testNotConnectedFindTutorEmercencyBecomeTutorGetBuddiesStudentsTutors() {
        Backend.findTutorMatch("");
        Backend.findEmergency("");
        Backend.findBecomeTutorMatch("");
        Backend.getBuddies();
        Backend.getStudents();
        Backend.getTutors();
        Backend.sendChatMessage("", 0);
        assertNull(this.response);
    }
}