package gui.views;

import communication.Backend;
import communication.IMessageListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import shared.Response;
import shared.TimePeriod;
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
    private static BorderPane GUI;
    private static GuiLoginConstructor login;
    private static GuiTopBar topbar;
    private static GuiProfileConstructor profile;
    private static GuiFindMatchConstructor findMatch;
    private static GuiSideBarFindMatchConstructor findMatchSideBar;
    private static GUISideBarConstructor sidebar;
    private static GuiContacts matches;
    private static ArrayList<String> buddyCourses;
    private static ArrayList<String> learningCourses;
    private static ArrayList<String> teachingCourses;

    private static String pfURL;//TODO implement
    private static GuiChat chatPage;

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
            login.setLoginMessage("Could not connect to server!", Color.RED);
        }

        if (!Backend.isConnected()) {
            System.out.println("Could not establish connection to server (" + Backend.serverAddress + " using port "
                    + Backend.serverPort + ")");
        } else {
            login.setLoginMessage("Connected to server!", Color.GREEN);
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
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        sbCourse.setId("selectedCourseButton");

        String course = sbCourse.getText();

        Backend.getMatches(Backend.getSelfObject());
        User self = Backend.getSelfObject();

        //TODO
    }

    public static void findMatchLearningCoursesClick(Button lCourse) {
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        lCourse.setId("selectedCourseButton");

        String course = lCourse.getText();

        // TODO: Get all users from database who teach this course & show the first user on the Match Page
    }

    public static void findMatchTeachingCoursesClick(Button tCourse) {
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        tCourse.setId("selectedCourseButton");

        String course = tCourse.getText();

        // TODO: Get all users from database who need a tutor for this course & show the first user on the Match Page
    }

    // Events TopBar
    public static void findMatchClick(Button fMatch, Button yourMatches, Button chat, Button profile) {
        fMatch.setId("findMatchActive");
        yourMatches.setId("yourMatches");
        chat.setId("chat");
        profile.setId("profileBtn");

        updateFindMatchSidebar();
        GUI.setCenter(findMatch);
        GUI.setLeft(findMatchSideBar);
    }

    public static void yourMatchesClick(Button findMatch, Button yourMatches, Button chat, Button profile) {
        yourMatches.setId("yourMatchesActive");
        findMatch.setId("findMatch");
        chat.setId("chat");
        profile.setId("profileBtn");

        updateFindMatchSidebar();
        GUI.setCenter(matches);
        GUI.setLeft(findMatchSideBar);
    }

    public static void chatClick(Button findMatch, Button yourMatches, Button chat, Button profile) {
        chat.setId("chatActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        profile.setId("profileBtn");

        GUI.setCenter(chatPage);
    }

    public static void matchesChatButton(){
        //TODO go to chatconversation with this specific match
        GUI.setCenter(chatPage);
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
            //TODO repeatpass field
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

    private static String listToString (ArrayList list) {
        String res = "";
        if (list.size() == 0)
            return res;
        for (Object o : list) {
            if (o instanceof String) {
                res += o + ",";
            } else if (o instanceof TimePeriod) {
                res += o.toString() + ",";
            }
        }
        res = res.substring(0, res.length() - 1);
        return res;
    }

    private static void updateFindMatchSidebar() {
        buddyCourses = Backend.getSelfObject().getBuddyList();
        teachingCourses = Backend.getSelfObject().getCoursesTeachingList();
        learningCourses = Backend.getSelfObject().getCoursesLearningList();
        findMatchSideBar = new GuiSideBarFindMatchConstructor(buddyCourses, learningCourses, teachingCourses);
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
            }
        });
    }
}
