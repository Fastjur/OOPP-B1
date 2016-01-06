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
import shared.Response;
import shared.User;

import java.io.IOException;
import java.util.ArrayList;

public class GUILauncher extends Application implements IMessageListener {
    static Scene GUIScene;
    private static BorderPane GUI;
    private static GuiLoginConstructor login;
    private static GuiTopBar topbar;
    private static GuiProfileConstructor profile;
    private static GuiFindMatchConstructor findMatch;
    private static GuiSideBarFindMatchConstructor findMatchSideBar;
    private static GUISideBarConstructor sideBar;

    @Override
    public void start(Stage PrimaryStage) throws Exception {
        String nomatchURL = this.getClass().getResource("resources/nomatch.png").toExternalForm();
        String matchURL = this.getClass().getResource("resources/match.png").toExternalForm();

        // Needs to be replaced with details of potential match
        String pfURL = this.getClass().getResource("resources/pfExample.jpg").toExternalForm();
        String name = "Rebecca Black";
        String age = "18";
        String descr = "Seven a.m. waking up in the morning. Gotta be fresh, gotta go downstairs. Gotta have my bowl, gotta have cereal. Seein' everything the time is goin'. Tickin' on and on, everybody's rushin'. Gotta get down to the bus stop. Gotta catch my bus. I see my friends.";

        // Needs to be replaced with match's list of buddy courses
        ArrayList<String> buddyCourses = new ArrayList<>();
        buddyCourses.add("Calculus");
        buddyCourses.add("Redeneren & Logica");

        // Needs to be replaced with match's list of learning courses
        ArrayList<String> learningCourses = new ArrayList<>();
        learningCourses.add("Calculus");
        learningCourses.add("OOProgrammeren");
        learningCourses.add("Web & Database Technology");

        // Needs to be replaced with match's list of teaching courses
        ArrayList<String> teachingCourses = new ArrayList<>();
        teachingCourses.add("Computer Organisation");
        teachingCourses.add("Redeneren & Logica");

        // Needs to be replaced with languages of match
        ArrayList<String> languages = new ArrayList<>();
        languages.add("Nederlands");
        languages.add("Engels");

        // Needs to be replaced with distance to match
        double distance = 1.5;

        GUI = new BorderPane();
        GUIScene = new Scene(GUI);

        findMatch = new GuiFindMatchConstructor(languages, distance, name, age, descr, matchURL, nomatchURL, pfURL);
        findMatchSideBar  = new GuiSideBarFindMatchConstructor(buddyCourses, learningCourses, teachingCourses);
        profile = new GuiProfileConstructor();
        sideBar = new GUISideBarConstructor();
        topbar = new GuiTopBar();
        login = new GuiLoginConstructor();

        GUI.setCenter(login);

        PrimaryStage.setScene(GUIScene);
        GUIScene.getStylesheets().addAll("/gui/views/css/TopBar.css","/gui/views/css/ProfileStyle.css","/gui/views/css/SideBarStyle.css", "/gui/views/css/MatchPage.css", "/gui/views/css/SideBarMatchPage.css", "/gui/views/css/login.css");
        PrimaryStage.show();

        Backend.serverAddress = "::1";
        Backend.serverPort = 8372;
        Backend.connectToServer();
        Backend.addMessageListener(this);

        if (!Backend.isConnected()) {
            Platform.runLater(() -> {
                login.lblMessage.setText("Could not connect to server!");
                login.lblMessage.setTextFill(Color.RED);
            });
        }

        if (!Backend.isConnected()) {
            System.out.println("Could not establish connection to server (" + Backend.serverAddress + " using port "
                    + Backend.serverPort + ")");
        } else {
            Platform.runLater(() -> {
                login.lblMessage.setText("Connected to server!");
                login.lblMessage.setTextFill(Color.GREEN);
            });
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

    @SuppressWarnings("Duplicates")
    public static void findMatchClick(Button fMatch, Button yourMatches, Button chat, Button profile) {
        //FIXME duplicate code
        fMatch.setId("findMatchActive");
        yourMatches.setId("yourMatches");
        chat.setId("chat");
        profile.setId("profile");

        GUI.setCenter(findMatch);
        GUI.setLeft(findMatchSideBar);
    }

    public static void yourMatchesClick(Button findMatch, Button yourMatches, Button chat, Button profile) {
        yourMatches.setId("yourMatchesActive");
        findMatch.setId("findMatch");
        chat.setId("chat");
        profile.setId("profile");
    }

    public static void chatClick(Button findMatch, Button yourMatches, Button chat, Button profile) {
        chat.setId("chatActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        profile.setId("profile");
    }

    @SuppressWarnings("Duplicates")
    public static void profileClick(Button findMatch, Button yourMatches, Button chat, Button prof) {
        //FIXME duplicate code
        prof.setId("profileActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        chat.setId("chat");

        GUI.setCenter(profile);
        GUI.setLeft(sideBar);
    }

    @Override
    public void onIncomingResponse(Response response) {
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        Platform.runLater(() -> {
            switch (response.responseTo) {
                case "login":
                    if (response.errorCode == 0) {
                        login.lblMessage.setText(response.errorMessage);
                        login.lblMessage.setTextFill(Color.GREEN);
                        Backend.getSelf();
                    } else {
                        login.lblMessage.setText(response.errorMessage);
                        login.lblMessage.setTextFill(Color.RED);
                    }
                    break;

                case "getSelf":
                    if (response.errorCode == 0) {
                        login.lblMessage.setText(response.errorMessage);
                        login.lblMessage.setTextFill(Color.GREEN);
                        try {
                            System.out.println(response.getResponseData());
                            Backend.setSelfObject(mapper.readValue(response.getResponseData().get("self").toString(),
                                    User.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        login.lblMessage.setText(response.errorMessage);
                        login.lblMessage.setTextFill(Color.RED);
                    }
            }
        });
    }
}
