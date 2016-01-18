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
import org.codehaus.jackson.map.deser.std.TimestampDeserializer;
import shared.AvailableTimes;
import shared.Response;
import shared.TimePeriod;
import shared.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;

public class GUILauncher extends Application implements IMessageListener {
    static Scene GUIScene;
    private static BorderPane GUI;
    private static GuiLoginConstructor login;
    private static GuiTopBar topbar;
    private static GuiProfileConstructor profile;
    private static GuiFindMatchConstructor findMatch;
    private static GuiSideBarFindMatchConstructor findMatchSideBar;
    private static GUISideBarConstructor sidebar;
    private static GuiContacts matches;
    private static GuiChat chatPage;

    @Override
    public void start(Stage PrimaryStage) throws Exception{
        ArrayList<String> languages = new ArrayList<>();
        languages.add("English");
        Double distance = 2500.0;

        // Needs to be replaced with details of potential match
        String pfURL = this.getClass().getResource("resources/pfExample.jpg").toExternalForm();
        String name = "Rebecca Black";
        String age = "18";
        String descr = "Seven a.m. waking up in the morning. Gotta be fresh, gotta go downstairs. Gotta have my bowl, gotta have cereal. Seein' everything the time is goin'. Tickin' on and on, everybody's rushin'. Gotta get down to the bus stop. Gotta catch my bus. I see my friends.";

        // Needs to be replaced with user's list of buddy courses
        ArrayList<String> buddyCourses = new ArrayList<>();
        buddyCourses.add("Calculus");
        buddyCourses.add("Redeneren & Logica");

        // Needs to be replaced with user's list of learning courses
        ArrayList<String> learningCourses = new ArrayList<>();
        learningCourses.add("Calculus");
        learningCourses.add("OOProgrammeren");
        learningCourses.add("Web & Database Technology");

        // Needs to be replaced with user's list of teaching courses
        ArrayList<String> teachingCourses = new ArrayList<>();
        teachingCourses.add("Computer Organisation");
        teachingCourses.add("Redeneren & Logica");

        AvailableTimes at = new AvailableTimes();
        TimePeriod tp = new TimePeriod(2,3);
        at.addTimePeriod(1,tp);

        GUI = new BorderPane();
        GUIScene = new Scene(GUI);

        matches = new GuiContacts(name, age, pfURL, descr, "TU Delft", "TI", at, languages, distance);
        findMatch = new GuiFindMatchConstructor(languages, distance, name, age, descr, pfURL);
        findMatchSideBar  = new GuiSideBarFindMatchConstructor(buddyCourses, learningCourses, teachingCourses);
        profile = new GuiProfileConstructor();
        sidebar = new GUISideBarConstructor();
        topbar = new GuiTopBar();
        login = new GuiLoginConstructor();
        chatPage = new GuiChat();

        GUI.setCenter(findMatch);

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
        GUI.setCenter(login.bp2);
    }

    public static void switchToLogin(){
        GUI.setCenter(login.bp);
    }

    public static void switchToReset(){
        GUI.setCenter(login.bp3);
    }

    //Events Find Match Page
    public static void findMatchBuddyCoursesClick(Button sbCourse) {
        if(GUIScene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) GUIScene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        sbCourse.setId("selectedCourseButton");

        String course = sbCourse.getText();

        // TODO: Get all users from database who need study buddy for this course & show the first user on the Match Page
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
        //FIXME duplicate code
        fMatch.setId("findMatchActive");
        yourMatches.setId("yourMatches");
        chat.setId("chat");
        profile.setId("profileBtn");

        GUI.setCenter(findMatch);
        GUI.setLeft(findMatchSideBar);
    }

    public static void yourMatchesClick(Button findMatch, Button yourMatches, Button chat, Button profile) {
        yourMatches.setId("yourMatchesActive");
        findMatch.setId("findMatch");
        chat.setId("chat");
        profile.setId("profileBtn");

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

        GUI.setCenter(profile);
        GUI.setLeft(sidebar);

        if (Backend.getSelfObject() != null) {
            User self = Backend.getSelfObject();
            profile.name.setText(self.getFirstname() + " " + self.getLastname());
            profile.sex.setValue(self.getGender());
            LocalDate now = LocalDate.now(),
                      birthday = self.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(birthday, now).getYears();
            profile.age.setText(String.valueOf(age));
            profile.dateOfBirth.setValue(birthday);
            profile.nationality.setValue(self.getNationality());
            profile.languages.setValue(listToString(self.getLanguageList()));
            profile.email.setText(self.getMail());
            profile.telephoneNumber.setText(self.getPhonenumber());
            profile.location.setText(self.getLongitude() + "," + self.getLatitude());
            profile.password.setText("");
            //TODO repeatpass field
            profile.tf11.setText(self.getUniversity());
            profile.tf12.setText(self.getStudy());
            profile.tf13.setText(String.valueOf(self.getStudyYear()));
            profile.tf14.setText(listToString(self.getCoursesLearningList()));
            profile.tf15.setText(listToString(self.getCoursesTeachingList()));
            profile.tf16.setText(listToString(self.getBuddyList()));

            profile.tf17.setText(listToString(self.getAvailableDates().getMonday()));
            profile.tf18.setText(listToString(self.getAvailableDates().getTuesday()));
            profile.tf19.setText(listToString(self.getAvailableDates().getWednesday()));
            profile.tf20.setText(listToString(self.getAvailableDates().getThursday()));
            profile.tf21.setText(listToString(self.getAvailableDates().getFriday()));
            profile.tf22.setText(listToString(self.getAvailableDates().getSaturday()));
            profile.tf23.setText(listToString(self.getAvailableDates().getSunday()));
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
                            GUI.setLeft(findMatchSideBar);
                            GUI.setTop(topbar);
                            GUI.setCenter(findMatch);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        login.setLoginMessage(response.errorMessage, Color.RED);
                    }
            }
        });
    }
}
