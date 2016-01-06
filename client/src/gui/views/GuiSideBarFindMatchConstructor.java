package gui.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

/**Gui sidebar for find match page
 * @author Zoë van Steijn
 */
public class GuiSideBarFindMatchConstructor extends VBox{
    private TitledPane studyBuddyDropDown;
    private TitledPane learningDropDown;
    private TitledPane teachingDropDown;
    private ArrayList<String> buddyCourses;
    private ArrayList<String> learningCourses;
    private ArrayList<String> teachingCourses;

    public GuiSideBarFindMatchConstructor(ArrayList<String> buddyCourses, ArrayList<String> learningCourses, ArrayList<String> teachingCourses){
        super();
        this.buddyCourses = buddyCourses;
        this.learningCourses = learningCourses;
        this.teachingCourses = teachingCourses;
        content();
        super.getChildren().addAll(studyBuddyDropDown, learningDropDown, teachingDropDown);
        super.setId("sideBarMatch");
    }

    public void content() {
        // Create Study Buddy drop-down menu
        VBox studyBuddySubj = new VBox();
        studyBuddySubj.setId("dropDownCourses");
        for(int i=0; i<buddyCourses.size(); i++){
            Button sbCourse = new Button(buddyCourses.get(i));
            sbCourse.setId("courseButton");
            sbCourse.setOnAction(event -> GUILauncher.findMatchBuddyCoursesClick(sbCourse));
            studyBuddySubj.getChildren().add(i, sbCourse);
        }

        studyBuddyDropDown = new TitledPane("Find a Study Buddy", studyBuddySubj);
        studyBuddyDropDown.setId("sideBarTitledPanes");
        studyBuddyDropDown.setExpanded(false);
        studyBuddyDropDown.setMaxWidth(400);

        // Create Learning Courses drop-down menu
        VBox learningSubj = new VBox();
        learningSubj.setId("dropDownCourses");
        for (int i = 0; i < learningCourses.size(); i++) {
            Button lCourse = new Button(learningCourses.get(i));
            lCourse.setId("courseButton");
            lCourse.setOnAction(event -> GUILauncher.findMatchLearningCoursesClick(lCourse));
            learningSubj.getChildren().add(i, lCourse);
        }

        learningDropDown = new TitledPane("Find a Tutor", learningSubj);
        learningDropDown.setId("sideBarTitledPanes");
        learningDropDown.setExpanded(false);
        learningDropDown.setMaxWidth(400);

        // Create Teaching Courses drop-down menu
        VBox teachingSubj = new VBox();
        teachingSubj.setId("dropDownCourses");
        for (int i = 0; i < teachingCourses.size(); i++) {
            Button tCourse = new Button(teachingCourses.get(i));
            tCourse.setId("courseButton");
            tCourse.setOnAction(event -> GUILauncher.findMatchTeachingCoursesClick(tCourse));
            teachingSubj.getChildren().add(i, tCourse);
        }

        teachingDropDown = new TitledPane("Become a Tutor", teachingSubj);
        teachingDropDown.setId("sideBarTitledPanes");
        teachingDropDown.setExpanded(false);
        teachingDropDown.setMaxWidth(400);
    }
}
