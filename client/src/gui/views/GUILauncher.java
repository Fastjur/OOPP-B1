package gui.views;

import communication.Backend;
import communication.IMessageListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import shared.Response;
import shared.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

public class GUILauncher extends Application implements IMessageListener {
    static Scene GUIScene;
    static Stage stage;
    static BorderPane GUI;
    static GuiLoginConstructor login;
    static GuiTopBar topbar;
    static GuiProfileConstructor profile;
    static GuiFindMatchConstructor findMatch;
    static GuiSideBarFindMatchConstructor findMatchSideBar;
    static GUISideBarConstructor sidebar;
    static GuiContacts myMatches;
    static GuiSideBarMatchesConstructor matchSidebar;
    private static ArrayList<String> buddyCourses;
    private static ArrayList<String> learningCourses;
    private static ArrayList<String> teachingCourses;
    private static ArrayList<String> emergencyCourses;
    private static ArrayList<User> buddies;
    private static ArrayList<User> students;
    private static ArrayList<User> tutors;

    private static String pfURL;//TODO implement
    private static GuiChat chatPage;
    private static boolean matchpagecheck;

    private static ArrayList<User> courseMatches;
    private static ArrayList<GuiChat> chatConversations;

    //TODO send typeOfMatch + matchCourse together with match (in MatchClick method)
    private static String typeOfMatch;
    private static String matchCourse;

    @Override
    public void start(Stage PrimaryStage) throws Exception{
        stage = PrimaryStage;
        pfURL = this.getClass().getResource("resources/pfExample.jpg").toExternalForm();//TODO implement
        findMatch = new GuiFindMatchConstructor();
        GUI = new BorderPane();
        GUIScene = new Scene(GUI);
        profile = new GuiProfileConstructor();
        sidebar = new GUISideBarConstructor();
        topbar = new GuiTopBar();
        login = new GuiLoginConstructor();
        chatConversations = new ArrayList<>();
        chatPage = new GuiChat();

        /**
         * Do not change this. Most functions rely on an active connection. The login page automatically sets a test
         * user for login.
         */
        GUI.setCenter(login);

        PrimaryStage.setScene(GUIScene);
        GUIScene.getStylesheets().addAll("/gui/views/css/chat.css","/gui/views/css/ContactsStyle.css","/gui/views/css/TopBar.css","/gui/views/css/ProfileStyle.css","/gui/views/css/SideBarStyle.css", "/gui/views/css/MatchPage.css", "/gui/views/css/SideBarMatchPage.css", "/gui/views/css/login.css");
        PrimaryStage.show();

        Backend.serverAddress = "::1";
        Backend.serverPort = 8372;
        Backend.connectToServer();
        Backend.addMessageListener(this);

        if (!Backend.isConnected()) {
            login.setLoginMessage(" Could not connect to server!", Color.RED);
            login.setRegisterMessage(" Could not connect to server!", Color.RED);
            login.setResetMessage(" Could not connect to server!", Color.RED);
        }

        if (!Backend.isConnected()) {
            System.out.println("Could not establish connection to server (" + Backend.serverAddress + " using port "
                    + Backend.serverPort + ")");
        //} else {
        //    login.setLoginMessage("Connected to server!", Color.GREEN);
        }
    }

    public void stop() {
        try {
            Backend.closeConnection();
            System.out.println("Successfully closed connection to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }

    //Events Login page
    public static void switchToRegister(){
        GUI.setCenter(login.bpRegister);
    }

    public static void switchToLogin(){
        GUI.setCenter(login.bpLogin);
    }

    public static void switchToReset(){
        GUI.setCenter(login.bpReset);
    }


    //Events Find Match Page
    public static void findMatchBuddyCoursesClick(Button sbCourse) {
        GUIScene.setCursor(Cursor.WAIT);
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        sbCourse.setId("selectedCourseButton");

        typeOfMatch = "buddy";
        matchCourse = sbCourse.getText();
        Backend.findStudyBuddy(matchCourse);
    }

    public static void findMatchLearningCoursesClick(Button lCourse) {
        GUIScene.setCursor(Cursor.WAIT);
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        lCourse.setId("selectedCourseButton");

        typeOfMatch = "learning";
        matchCourse = lCourse.getText();
        Backend.findTutorMatch(matchCourse);
    }

    public static void findMatchTeachingCoursesClick(Button tCourse) {
        GUIScene.setCursor(Cursor.WAIT);
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        tCourse.setId("selectedCourseButton");

        typeOfMatch = "teaching";
        matchCourse = tCourse.getText();
        Backend.findBecomeTutorMatch(matchCourse);
    }

    public static void findMatchEmergencyCoursesClick(Button eCourse) {
        GUIScene.setCursor(Cursor.WAIT);
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        eCourse.setId("selectedCourseButton");

        typeOfMatch = "learning";
        matchCourse = eCourse.getText();
        Backend.findEmergency(matchCourse);
    }

    private static void findMatchProcessBuddyMatches(){
        if(!courseMatches.isEmpty()) {
            User match = courseMatches.get(0);
            LocalDate now = LocalDate.now();
            int age = Period.between(match.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), now).getYears();

            //TODO profile pic

            findMatch = new GuiFindMatchConstructor(match.getLanguageList(), match.getFirstname() + " " + match.getLastname(), age, match.getDescription(), pfURL);
            GUI.setCenter(findMatch);
        }
        else{
            findMatch = new GuiFindMatchConstructor();
            GUI.setCenter(findMatch);
        }
    }

    // Events matchpage (sidebar)
    public static void myMatchesBuddyClick(Button sbMatch, User match) {
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        sbMatch.setId("selectedCourseButton");
        if (GUI.getCenter() instanceof GuiChat) {
            chatConversation(match);
        } else{
            displayMyMatch(match);
        }
    }

    public static void myMatchesLearningClick(Button lMatch, User match) {
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        lMatch.setId("selectedCourseButton");
        if (GUI.getCenter() instanceof GuiChat) {
            chatConversation(match);
        } else{
            displayMyMatch(match);
        }
    }

    public static void myMatchesTeachingClick(Button tMatch, User match) {
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        tMatch.setId("selectedCourseButton");
        if (GUI.getCenter() instanceof GuiChat) {
            chatConversation(match);
        } else{
            displayMyMatch(match);
        }
    }

    public static void displayMyMatch(User match){
        LocalDate now = LocalDate.now();
        int age = Period.between(match.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), now).getYears();

        myMatches = new GuiContacts(match.getFirstname() + " " + match.getLastname(), age, pfURL, match.getDescription(), match.getUniversity(), match.getStudy(), match.getAvailableDates(), match.getLanguageList());
        GUI.setCenter(myMatches);
    }

    // Events TopBar
    public static void findMatchClick(Button fMatch, Button yourMatches, Button chat, Button profile) {
        fMatch.setId("findMatchActive");
        yourMatches.setId("yourMatches");
        chat.setId("chat");
        profile.setId("profileBtn");
        findMatch = new GuiFindMatchConstructor();
        GUI.setCenter(findMatch);
        updateFindMatchSidebar();
        GUI.setLeft(findMatchSideBar);
    }

    public static void noMatchClick() {
        courseMatches.remove(0);
        findMatchProcessBuddyMatches();
    }

    public static void matchClick(){
        User match = courseMatches.get(0);
        int matchId = match.getUserID();

        Backend.acceptMatch(matchId, typeOfMatch, matchCourse);

        courseMatches.remove(0);
        findMatchProcessBuddyMatches();
    }

    public static void yourMatchesClick(Button findMatchButton, Button yourMatches, Button chat, Button profile) {
        yourMatches.setId("yourMatchesActive");
        findMatchButton.setId("findMatch");
        chat.setId("chat");
        profile.setId("profileBtn");
        GUI.setCenter(new GuiFindMatchConstructor());
        GUIScene.setCursor(Cursor.WAIT);
        matchpagecheck = true;
        Backend.getBuddies();
        Backend.getTutors();
        Backend.getStudents();
    }

    public static void chatClick(Button findMatch, Button yourMatches, Button chat, Button profile) {
        chat.setId("chatActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        profile.setId("profileBtn");

        GUI.setCenter(chatPage);
        matchpagecheck = true;
        Backend.getBuddies();
        Backend.getTutors();
        Backend.getStudents();
    }

    public static void matchesChatButton(){
        topbar.setChatButtonActive();
        GUI.setCenter(chatPage);
        matchpagecheck = true;
        Backend.getBuddies();
        Backend.getTutors();
        Backend.getStudents();
    }

    public static void chatConversation(User match) {
        boolean exists = false;
        if(chatConversations.size() != 0) {
            for (int i = 0; i < chatConversations.size(); i++) {
                if (chatConversations.get(i).getMatch().equals(match)) {
                    exists = true;
                    GUI.setCenter(chatConversations.get(i));
                }
            }
        }

        if(!exists) {
            GuiChat conversation = new GuiChat(match);
            chatConversations.add(conversation);
            GUI.setCenter(conversation);
        }
    }

    public static void sendMessage(String message, User receiver){
        Backend.sendChatMessage(message, receiver.getUserID());
    }

    public static void getMessage(int senderId, String message) {
        if(chatConversations.size() != 0) {
            for (int i = 0; i < chatConversations.size(); i++) {
                if (chatConversations.get(i).getMatch().getUserID() == senderId){
                    chatConversations.get(i).receiveMessage(message);
                }
            }
        }
    }

    public static void profileClick(Button findMatch, Button yourMatches, Button chat, Button prof) {
        prof.setId("profileBtnActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        chat.setId("chat");

        GUI.setLeft(null);
        GUI.setCenter(profile);

        if (Backend.getSelfObject() != null) {
            User self = Backend.getSelfObject();
            profile.name.setText(self.getFirstname() + " " + self.getLastname());
            profile.sex.setValue(self.getGender());
            if(self.getGender().toLowerCase().equals("male")) {
                profile.sex.getSelectionModel().select(0);
            }
            if(self.getGender().toLowerCase().equals("female")){
                profile.sex.getSelectionModel().select(1);
            }
            LocalDate now = LocalDate.now(),
                    birthday = self.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(birthday, now).getYears();
            profile.age.setText(String.valueOf(age));
            profile.dateOfBirth.setValue(birthday);
            profile.email.setText(self.getMail());
            profile.telephoneNumber.setText(self.getPhonenumber());
            profile.location.setText(self.getLongitude() + "," + self.getLatitude());
            profile.studyYear.setText(String.valueOf(self.getStudyYear()));
            profile.monday.setText(self.getAvailableDates().toReadable(1));
            profile.tuesday.setText(self.getAvailableDates().toReadable(2));
            profile.wednesday.setText(self.getAvailableDates().toReadable(3));
            profile.thursday.setText(self.getAvailableDates().toReadable(4));
            profile.friday.setText(self.getAvailableDates().toReadable(5));
            profile.saturday.setText(self.getAvailableDates().toReadable(6));
            profile.sunday.setText(self.getAvailableDates().toReadable(7));
        }
    }

    private static void updateFindMatchSidebar() {
        buddyCourses = Backend.getSelfObject().getBuddyList();
        teachingCourses = Backend.getSelfObject().getCoursesTeachingList();
        learningCourses = Backend.getSelfObject().getCoursesLearningList();
        emergencyCourses = Backend.getSelfObject().getCoursesLearningList();
        findMatchSideBar = new GuiSideBarFindMatchConstructor(buddyCourses, learningCourses, teachingCourses, emergencyCourses);
    }

    private static void updateMatchSidebar() {
        matchSidebar = new GuiSideBarMatchesConstructor(buddies, tutors, students);
        GUI.setLeft(matchSidebar);
    }

    @Override
    public void onIncomingResponse(Response response) {
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        Platform.runLater(() -> {
            switch (response.responseTo) {
                case "login":
                    if (response.errorCode == 0) {
                        login.setLoginMessage(response.errorMessage, Color.GREEN);
                        Backend.getSelf();
                        Backend.getNationalities();
                        Backend.getLanguages();
                        Backend.getStudies();
                        Backend.getUniversities();
                        Backend.getCourses();
                    } else {
                        login.setLoginMessage(response.errorMessage, Color.RED);
                    }
                    break;

                case "getSelf":
                    if (response.errorCode == 0) {
                        login.setLoginMessage(response.errorMessage, Color.GREEN);
                        try {
                            Backend.setSelfObject(mapper.readValue(response.getResponseData().get("self").toString(),
                                    User.class));

                            updateFindMatchSidebar();
                            GUI.setLeft(findMatchSideBar);
                            GUI.setTop(topbar);
                            GUI.setCenter(findMatch);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        login.setLoginMessage(response.errorMessage, Color.RED);
                    }
                    break;

                case "getNationalities":
                    GUIScene.setCursor(Cursor.DEFAULT);
                    if (response.errorCode == 0) {
                        HashMap<Integer, String> dbNationalities = new HashMap<>();
                        try {
                            TypeReference<HashMap<Integer, String>> typeRef = new TypeReference<HashMap<Integer,
                                    String>>() {};
                            dbNationalities = mapper.readValue(response.getResponseData().get("nationalities").toString(),
                                    typeRef);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        profile.setNationalities(dbNationalities);
                    }
                    break;

                case "getLanguages":
                    GUIScene.setCursor(Cursor.DEFAULT);
                    if (response.errorCode == 0) {
                        HashMap<Integer, String> dbLanguages = new HashMap<>();
                        try {
                            TypeReference<HashMap<Integer, String>> typeRef = new TypeReference<HashMap<Integer,
                                    String>>() {};
                            dbLanguages = mapper.readValue(response.getResponseData().get("dbLanguages").toString(),
                                    typeRef);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        profile.setLanguages(dbLanguages);
                    }
                    break;

                case "getStudies":
                    GUIScene.setCursor(Cursor.DEFAULT);
                    if (response.errorCode == 0) {
                        HashMap<Integer, String> dbStudies = new HashMap<>();
                        try {
                            TypeReference<HashMap<Integer, String>> typeRef = new TypeReference<HashMap<Integer,
                                    String>>() {};
                            dbStudies = mapper.readValue(response.getResponseData().get("dbStudies").toString(),
                                    typeRef);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        profile.setStudies(dbStudies);
                    }
                    break;

                case "getUniversities":
                    GUIScene.setCursor(Cursor.DEFAULT);
                    if (response.errorCode == 0) {
                        HashMap<Integer, String> dbUniversities = new HashMap<>();
                        try {
                            TypeReference<HashMap<Integer, String>> typeRef = new TypeReference<HashMap<Integer,
                                    String>>() {};
                            dbUniversities = mapper.readValue(response.getResponseData().get("dbUniversities")
                                    .toString(), typeRef);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        profile.setUniversity(dbUniversities);
                    }
                    break;

                case "getCourses":
                    GUIScene.setCursor(Cursor.DEFAULT);
                    if (response.errorCode == 0) {
                        HashMap<Integer, String> dbCourses = new HashMap<>();
                        try {
                            TypeReference<HashMap<Integer, String>> typeRef = new TypeReference<HashMap<Integer,
                                    String>>() {};
                            dbCourses = mapper.readValue(response.getResponseData().get("dbCourses").toString(),
                                    typeRef);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        profile.setCourses(dbCourses);
                    }
                    break;

                case "findBuddy":
                    GUIScene.setCursor(Cursor.DEFAULT);
                    if (response.errorCode == 0) {
                        TypeReference<ArrayList<User>> typeRef = new TypeReference<ArrayList<User>>() {};
                        try {
                            courseMatches = mapper.readValue(response.getResponseData().get("findBuddyRes").toString(), typeRef);
                            findMatchProcessBuddyMatches();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (response.errorCode == 9){
                        findMatch.noMatches(matchCourse);
                    }
                    break;

                case "getBuddies":
                    if (response.errorCode == 0) {
                        TypeReference<ArrayList<User>> typeRef = new TypeReference<ArrayList<User>>() {};
                        try {
                            buddies = mapper.readValue(response.getResponseData().get("buddies")
                                    .toString(), typeRef);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "getStudents":
                    if (response.errorCode == 0) {
                        TypeReference<ArrayList<User>> typeRef = new TypeReference<ArrayList<User>>() {};
                        try {
                            students = mapper.readValue(response.getResponseData().get("students")
                                    .toString(), typeRef);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "getTutors":
                    if (response.errorCode == 0) {
                        TypeReference<ArrayList<User>> typeRef = new TypeReference<ArrayList<User>>() {};
                        try {
                            tutors = mapper.readValue(response.getResponseData().get("tutors")
                                    .toString(), typeRef);
                            if(matchpagecheck) {
                                updateMatchSidebar();
                                matchpagecheck = false;
                                GUIScene.setCursor(Cursor.DEFAULT);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "getChatMessage":
                    if (response.errorCode == 0) {
                        try {
                            String chatMessage = mapper.readValue(response.getResponseData().get("chatMessage")
                                    .toString(), String.class);
                            String sender = mapper.readValue(response.getResponseData().get("senderId").toString(), String.class);
                            int senderId = Integer.parseInt(sender);
                            getMessage(senderId, chatMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                case "register":
                    if (response.errorCode == 0){
                        switchToLogin();
                        login.setLoginMessage(" Registration succesful", Color.GREEN);
                        login.userNameLogin.setText(login.userNameReg.getText());
                        login.pswLogin.setText("");
                        login.userNameReg.setText("");
                        login.pswReg.setText("");
                    } else {
                        login.setRegisterMessage(response.errorMessage, Color.RED);
                    }
                    break;
            }
        });
    }
}
