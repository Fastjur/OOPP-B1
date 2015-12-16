package gui.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**Sidebar for the match page
 * @author Zoë van Steijn.
 */
public class GuiSideBarMatchPage extends Application {

    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

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

        // Create Study Buddy drop-down menu
        VBox studyBuddySubj = new VBox();
        studyBuddySubj.setId("dropDownCourses");
        for(int i=0; i<buddyCourses.size(); i++){
            Button sbCourse = new Button(buddyCourses.get(i));
            sbCourse.setId("courseButton");
            sbCourse.setOnAction(event -> buddyCoursesClick(sbCourse));
           studyBuddySubj.getChildren().add(i, sbCourse);
        }

        TitledPane studyBuddyDropDown = new TitledPane("Find a Study Buddy", studyBuddySubj);
        studyBuddyDropDown.setId("sideBarTitledPanes");
        studyBuddyDropDown.setExpanded(false);
        studyBuddyDropDown.setMaxWidth(400);

        // Create Learning Courses drop-down menu
        VBox learningSubj = new VBox();
        learningSubj.setId("dropDownCourses");
        for(int i=0; i<learningCourses.size(); i++){
            Button lCourse = new Button(learningCourses.get(i));
            lCourse.setId("courseButton");
            lCourse.setOnAction(event -> learningCoursesClick(lCourse));
            learningSubj.getChildren().add(i, lCourse);
        }

        TitledPane learningDropDown = new TitledPane("Find a Tutor", learningSubj);
        learningDropDown.setId("sideBarTitledPanes");
        learningDropDown.setExpanded(false);
        learningDropDown.setMaxWidth(400);

        // Create Teaching Courses drop-down menu
        VBox teachingSubj = new VBox();
        teachingSubj.setId("dropDownCourses");
        for(int i=0; i<teachingCourses.size(); i++){
            Button tCourse = new Button(teachingCourses.get(i));
            tCourse.setId("courseButton");
            tCourse.setOnAction(event -> teachingCourseClick(tCourse));
            teachingSubj.getChildren().add(i, tCourse);
        }

        TitledPane teachingDropDown = new TitledPane("Become a Tutor", teachingSubj);
        teachingDropDown.setId("sideBarTitledPanes");
        teachingDropDown.setExpanded(false);
        teachingDropDown.setMaxWidth(400);

        // Create side-bar containing the three drop-down menus
        VBox sideBar = new VBox();
        sideBar.getChildren().addAll(studyBuddyDropDown, learningDropDown, teachingDropDown);
        sideBar.setId("sideBarMatch");
        sideBar.setMaxWidth(400);

        scene = new Scene(sideBar, 400, 600);

        String cssURL = this.getClass().getResource("/gui/views/css/SideBarMatchPage.css").toExternalForm();
        scene.getStylesheets().add(cssURL);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SideBar");
        primaryStage.show();
    }

    private void buddyCoursesClick(Button sbCourse) {
        if(scene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) scene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        sbCourse.setId("selectedCourseButton");

        String course = sbCourse.getText();

        // TODO: Get all users from database who need study buddy for this course & show the first user on the Match Page
    }

    private void learningCoursesClick(Button lCourse) {
        if(scene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) scene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        lCourse.setId("selectedCourseButton");

        String course = lCourse.getText();

        // TODO: Get all users from database who teach this course & show the first user on the Match Page
    }

    private void teachingCourseClick(Button tCourse) {
        if(scene.lookup("#selectedCourseButton") instanceof Button) {
            Button oldCourse = (Button) scene.lookup("#selectedCourseButton");
            oldCourse.setId("courseButton");
        }
        tCourse.setId("selectedCourseButton");
    
        String course = tCourse.getText();

        // TODO: Get all users from database who need a tutor for this course & show the first user on the Match Page
    }

    public static void main(String[] args){ launch(args);}
}
