package gui.views;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import shared.User;

import java.util.ArrayList;

/**
 * Created by Zoë on 20-1-2016.
 */
public class GuiSideBarMatchesConstructor extends VBox{
    private TitledPane studyBuddyDropDown;
    private TitledPane learningDropDown;
    private TitledPane teachingDropDown;
    ArrayList<User> buddyMatches;
    ArrayList<User> learningMatches;
    ArrayList<User> teachingMatches;

    public GuiSideBarMatchesConstructor(ArrayList<User> buddyMatches, ArrayList<User> learningMatches, ArrayList<User> teachingMatches){
        super();
        this.buddyMatches = buddyMatches;
        this.learningMatches = learningMatches;
        this.teachingMatches = teachingMatches;
        content();
        super.getChildren().addAll(studyBuddyDropDown, learningDropDown, teachingDropDown);
        super.setId("sideBarMatch");
        }

    public void content() {
        // Create Study Buddy drop-down menu
        VBox studyBuddySubj = new VBox();
        studyBuddySubj.setId("dropDownCourses");
        if(buddyMatches != null && !buddyMatches.isEmpty()) {
            for (int i = 0; i < buddyMatches.size(); i++) {
                Button sbMatch = new Button(buddyMatches.get(i).getFirstname() + " " + buddyMatches.get(i).getLastname());
                sbMatch.setId("courseButton");
                User match = buddyMatches.get(i);
                sbMatch.setOnAction(event -> GUILauncher.myMatchesBuddyClick(sbMatch, match));
                studyBuddySubj.getChildren().add(i, sbMatch);
            }
        }

        studyBuddyDropDown = new TitledPane("My Study Buddies", studyBuddySubj);
        studyBuddyDropDown.setId("sideBarTitledPanes");
        studyBuddyDropDown.setExpanded(false);
        studyBuddyDropDown.setMaxWidth(400);

        // Create Learning Courses drop-down menu
        VBox learningSubj = new VBox();
        learningSubj.setId("dropDownCourses");
        if(learningMatches != null && !learningMatches.isEmpty()) {
            for (int i = 0; i < learningMatches.size(); i++) {
                Button lCourse = new Button(learningMatches.get(i).getFirstname() + " " + learningMatches.get(i).getLastname());
                lCourse.setId("courseButton");
                User match = learningMatches.get(i);
                lCourse.setOnAction(event -> GUILauncher.myMatchesLearningClick(lCourse, match));
                learningSubj.getChildren().add(i, lCourse);
            }
        }

        learningDropDown = new TitledPane("My Tutors", learningSubj);
        learningDropDown.setId("sideBarTitledPanes");
        learningDropDown.setExpanded(false);
        learningDropDown.setMaxWidth(400);

        // Create Teaching Courses drop-down menu
        VBox teachingSubj = new VBox();
        teachingSubj.setId("dropDownCourses");
        if(teachingMatches != null && !teachingMatches.isEmpty()) {
            for (int i = 0; i < teachingMatches.size(); i++) {
                Button tCourse = new Button(teachingMatches.get(i).getFirstname() + " " + teachingMatches.get(i).getLastname());
                tCourse.setId("courseButton");
                User match = teachingMatches.get(i);
                tCourse.setOnAction(event -> GUILauncher.myMatchesTeachingClick(tCourse, match));
                teachingSubj.getChildren().add(i, tCourse);
            }
        }

        teachingDropDown = new TitledPane("My Students", teachingSubj);
        teachingDropDown.setId("sideBarTitledPanes");
        teachingDropDown.setExpanded(false);
        teachingDropDown.setMaxWidth(400);
    }
}
