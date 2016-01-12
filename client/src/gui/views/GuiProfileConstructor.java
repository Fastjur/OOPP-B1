package gui.views;

import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Constructor for building the profile page of the GUI
 * Author: Sebastiaan Hester
 */
public class GuiProfileConstructor extends BorderPane {

    public GuiProfileConstructor() {
        super();


        //Insets:
        Insets noPaddingInsets = new Insets(0);
        Insets labelInsets = new Insets(0, 0, 0, 20);
        Insets profileAdditionalInfoInsets = new Insets(10, 30, 10, 10);
        Insets profileAvailabilityTitlePaneInsets = new Insets(10);
        Insets profileAvailabilityGridPaneInsets = new Insets(0, 10, 5, 10);
        Insets profileAvailabilityPaneInsets = new Insets(0, 20, 0, 0);
        Insets editToggleBtnInsets = new Insets(0,20,20,10);
        Insets profileInsets = new Insets(10, 30, 30, 30);

        //Shape settings
        Circle editToggleBtnShape = new Circle(70);

        //TextFields
        TextField tf1 = new TextField();
        tf1.setPromptText("Info goes here");
        tf1.setEditable(false);
        tf1.setMaxHeight(20);
        tf1.setPrefHeight(20);
        TextField tf2 = new TextField();
        tf2.setPromptText("Info goes here");
        tf2.setEditable(false);
        tf2.setMaxHeight(20);
        tf2.setPrefHeight(20);
        TextField tf3 = new TextField();
        tf3.setPromptText("Info goes here");
        tf3.setEditable(false);
        tf3.setMaxHeight(20);
        tf3.setPrefHeight(20);
        TextField tf4 = new TextField();
        tf4.setPromptText("Info goes here");
        tf4.setEditable(false);
        tf4.setMaxHeight(20);
        tf4.setPrefHeight(20);
        TextField tf5 = new TextField();
        tf5.setPromptText("Info goes here");
        tf5.setEditable(false);
        tf5.setMaxHeight(20);
        tf5.setPrefHeight(20);
        TextField tf6 = new TextField();
        tf6.setPromptText("Info goes here");
        tf6.setEditable(false);
        tf6.setMaxHeight(20);
        tf6.setPrefHeight(20);
        TextField tf7 = new TextField();
        tf7.setPromptText("Info goes here");
        tf7.setEditable(false);
        tf7.setMaxHeight(20);
        tf7.setPrefHeight(20);
        TextField tf8 = new TextField();
        tf8.setPromptText("Info goes here");
        tf8.setEditable(false);
        tf8.setMaxHeight(20);
        tf8.setPrefHeight(20);
        TextField tf9 = new TextField();
        tf9.setPromptText("Info goes here");
        tf9.setEditable(false);
        tf9.setMaxHeight(20);
        tf9.setPrefHeight(20);
        TextField tf10 = new TextField();
        tf10.setPromptText("Info goes here");
        tf10.setEditable(false);
        tf10.setMaxHeight(20);
        tf10.setPrefHeight(20);
        TextField tf11 = new TextField();
        tf11.setPromptText("Info goes here");
        tf11.setEditable(false);
        tf11.setMaxHeight(20);
        tf11.setPrefHeight(20);
        TextField tf12 = new TextField();
        tf12.setPromptText("Info goes here");
        tf12.setEditable(false);
        tf12.setMaxHeight(20);
        tf12.setPrefHeight(20);
        TextField tf13 = new TextField();
        tf13.setPromptText("Info goes here");
        tf13.setEditable(false);
        tf13.setMaxHeight(20);
        tf13.setPrefHeight(20);
        TextField tf14 = new TextField();
        tf14.setPromptText("Info goes here");
        tf14.setEditable(false);
        tf14.setMaxHeight(20);
        tf14.setPrefHeight(20);
        TextField tf15 = new TextField();
        tf15.setPromptText("Info goes here");
        tf15.setEditable(false);
        tf15.setMaxHeight(20);
        tf15.setPrefHeight(20);
        TextField tf16 = new TextField();
        tf16.setPromptText("Info goes here");
        tf16.setEditable(false);
        tf16.setMaxHeight(20);
        tf16.setPrefHeight(20);
        TextField tf17 = new TextField();
        tf17.setPromptText("Info goes here");
        tf17.setEditable(false);
        tf17.setMaxHeight(20);
        tf17.setPrefHeight(20);
        TextField tf18 = new TextField();
        tf18.setPromptText("Info goes here");
        tf18.setEditable(false);
        tf18.setMaxHeight(20);
        tf18.setPrefHeight(20);
        TextField tf19 = new TextField();
        tf19.setPromptText("Info goes here");
        tf19.setEditable(false);
        tf19.setMaxHeight(20);
        tf19.setPrefHeight(20);
        TextField tf20 = new TextField();
        tf20.setPromptText("Info goes here");
        tf20.setEditable(false);
        tf20.setMaxHeight(20);
        tf20.setPrefHeight(20);
        TextField tf21 = new TextField();
        tf21.setPromptText("Info goes here");
        tf21.setEditable(false);
        tf21.setMaxHeight(20);
        tf21.setPrefHeight(20);
        TextField tf22 = new TextField();
        tf22.setPromptText("Info goes here");
        tf22.setEditable(false);
        tf22.setMaxHeight(20);
        tf22.setPrefHeight(20);
        TextField tf23 = new TextField();
        tf23.setPromptText("Info goes here");
        tf23.setEditable(false);
        tf23.setMaxHeight(20);
        tf23.setPrefHeight(20);

        //Buttons:
        Button uploadPicBtn = new Button();
        uploadPicBtn.setPadding(noPaddingInsets);
        uploadPicBtn.setMinSize(410, 40);
        uploadPicBtn.setPrefSize(410, 40);
        uploadPicBtn.setMaxSize(410, 40);
        uploadPicBtn.setText("Upload picture");
        uploadPicBtn.setOnMouseClicked(event -> {

        });
        uploadPicBtn.setId("uploadPicBtn");

        Button editToggleBtn = new Button();
        editToggleBtn.setShape(editToggleBtnShape);
        editToggleBtn.setPrefSize(70,70);
        editToggleBtn.setOnMouseClicked(event -> {
            if (editToggleBtn.getId().equals("editToggleBtn")) {
                tf1.setEditable(true);
                tf2.setEditable(true);
                tf3.setEditable(true);
                tf4.setEditable(true);
                tf5.setEditable(true);
                tf6.setEditable(true);
                tf7.setEditable(true);
                tf8.setEditable(true);
                tf9.setEditable(true);
                tf10.setEditable(true);
                tf11.setEditable(true);
                tf12.setEditable(true);
                tf13.setEditable(true);
                tf14.setEditable(true);
                tf15.setEditable(true);
                tf16.setEditable(true);
                tf17.setEditable(true);
                tf18.setEditable(true);
                tf19.setEditable(true);
                tf20.setEditable(true);
                tf21.setEditable(true);
                tf22.setEditable(true);
                tf23.setEditable(true);
                editToggleBtn.setId("editToggleBtnActive");
            }
            else{
                tf1.setEditable(false);
                tf2.setEditable(false);
                tf3.setEditable(false);
                tf4.setEditable(false);
                tf5.setEditable(false);
                tf6.setEditable(false);
                tf7.setEditable(false);
                tf8.setEditable(false);
                tf9.setEditable(false);
                tf10.setEditable(false);
                tf11.setEditable(false);
                tf12.setEditable(false);
                tf13.setEditable(false);
                tf14.setEditable(false);
                tf15.setEditable(false);
                tf16.setEditable(false);
                tf17.setEditable(false);
                tf18.setEditable(false);
                tf19.setEditable(false);
                tf20.setEditable(false);
                tf21.setEditable(false);
                tf22.setEditable(false);
                tf23.setEditable(false);
                editToggleBtn.setId("editToggleBtn");
            }
        });
        editToggleBtn.setId("editToggleBtn");



        //Labels
        Label profileInfoLabel = new Label("Personal");
        profileInfoLabel.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoLabel.setMinSize(1050, 60);
        profileInfoLabel.setPrefSize(1050, 60);
        profileInfoLabel.setMaxSize(1050, 60);
        profileInfoLabel.setPadding(labelInsets);
        profileInfoLabel.setId("profileInfoLabel");
        Label name = new Label("Name:");
        Label sex = new Label("Sex:");
        Label age = new Label("Age:");
        Label dateOfBirth = new Label("Date of Birth:");
        Label nationality = new Label("Nationality");
        Label languages = new Label("Languages:");
        Label email = new Label("Email:");
        Label telephoneNumber = new Label("Telephone Number:");
        Label address = new Label("Address:");
        Label password = new Label("Password:");
        Label university = new Label("University:");
        Label study = new Label("Study:");
        Label studyYear = new Label("Study Year:");
        Label courses = new Label("Courses");
        Label findATutor = new Label("Find a tutor:");
        Label becomeATutor = new Label("Become a tutor:");
        Label findStudyBuddy = new Label("Find a study buddy:");
        Label availability = new Label("Availability");
        Label monday = new Label("Monday:");
        monday.setId("availabilityDateLabel");
        Label tuesday = new Label("Tuesday:");
        tuesday.setId("availabilityDateLabel");
        Label wednesday = new Label("Wednesday:");
        wednesday.setId("availabilityDateLabel");
        Label thursday = new Label("Thursday:");
        thursday.setId("availabilityDateLabel");
        Label friday = new Label("Friday:");
        friday.setId("availabilityDateLabel");
        Label saturday = new Label("Saturday:");
        saturday.setId("availabilityDateLabel");
        Label sunday = new Label("Sunday:");
        sunday.setId("availabilityDateLabel");

        //Image settings
        Image profilePic = new Image("/gui/views/resources/ProfilePicTestImage.jpg");
        ImageView profilePicView = new ImageView(profilePic);
        profilePicView.setFitHeight(460);
        profilePicView.setFitWidth(410);
        profilePicView.setPreserveRatio(true);
        profilePicView.setId("profilePicView");

        //Panes
        VBox profilePicPane = new VBox();
        profilePicPane.setPadding(noPaddingInsets);
        profilePicPane.setAlignment(Pos.TOP_LEFT);
        profilePicPane.getChildren().addAll(profilePicView, uploadPicBtn);
        profilePicPane.setId("profilePicPane");

        GridPane profileInfoGridPane = new GridPane();
        profileInfoGridPane.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoGridPane.setPadding(labelInsets);
        profileInfoGridPane.setMinWidth(1050);
        profileInfoGridPane.setPrefWidth(1050);
        profileInfoGridPane.setMaxWidth(1050);
        profileInfoGridPane.addColumn(0, name, sex, age, dateOfBirth, nationality, languages, email, telephoneNumber, address, password);
        profileInfoGridPane.addColumn(1, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10);
        ColumnConstraints col1Constraints = new ColumnConstraints(140, 280, 280, Priority.NEVER, HPos.LEFT, false);
        col1Constraints.setPercentWidth(21);
        ColumnConstraints col2Constraints = new ColumnConstraints(750, 750, 750, Priority.ALWAYS, HPos.LEFT, true);
        col2Constraints.setPercentWidth(79);
        profileInfoGridPane.getColumnConstraints().addAll(col1Constraints, col2Constraints);
        /*for (int i = 1; i <= 10; i++) {
            profileInfoGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(30, 30, 30, Priority.NEVER, VPos.CENTER, true);
            profileInfoGridPane.getRowConstraints().addAll(rowConstraints);
        }
        */
        profileInfoGridPane.setId("profileInfoGridPane");

        VBox profileInfoPane = new VBox(0);
        profileInfoPane.setMinWidth(1050);
        profileInfoPane.setPrefWidth(1050);
        profileInfoPane.setMaxWidth(1050);
        profileInfoPane.setPadding(noPaddingInsets);
        profileInfoPane.setAlignment(Pos.TOP_LEFT);
        profileInfoPane.getChildren().addAll(profileInfoLabel, profileInfoGridPane);
        profileInfoPane.setId("profileInfoPane");

        GridPane profileInfoContinuedGridPane = new GridPane();
        profileInfoContinuedGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoContinuedGridPane.setMinWidth(340);
        profileInfoContinuedGridPane.setPrefWidth(680);
        profileInfoContinuedGridPane.setMaxWidth(680);
        profileInfoContinuedGridPane.addColumn(0, university, study, studyYear);
        ColumnConstraints continuedInfoCol1Constraints = new ColumnConstraints(80, 160, 160, Priority.NEVER, HPos.LEFT, false);
        continuedInfoCol1Constraints.setPercentWidth(24);
        profileInfoContinuedGridPane.addColumn(1, tf11, tf12, tf13);
        ColumnConstraints continuedInfoCol2Constraints = new ColumnConstraints(260, 520, 520, Priority.ALWAYS, HPos.LEFT, true);
        continuedInfoCol2Constraints.setPercentWidth(76);
        profileInfoContinuedGridPane.getColumnConstraints().addAll(continuedInfoCol1Constraints, continuedInfoCol2Constraints);
        /*for (int i = 0; i <= 2; i++) {
            profileInfoContinuedGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(30, 30, 30, Priority.NEVER, VPos.CENTER, true);
            profileInfoContinuedGridPane.getRowConstraints().addAll(rowConstraints);
        }*/
        profileInfoContinuedGridPane.setId("profileInfoContinuedGridPane");

        HBox profileInfoCoursesLabelPane = new HBox();
        profileInfoCoursesLabelPane.setMinSize(340,55);
        profileInfoCoursesLabelPane.setPrefSize(680,110);
        profileInfoCoursesLabelPane.setMaxSize(680,110);
        profileInfoCoursesLabelPane.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoCoursesLabelPane.getChildren().addAll(courses);
        profileInfoCoursesLabelPane.setId("profileInfoCoursesLabelPane");

        GridPane profileInfoCoursesGridPane = new GridPane();
        profileInfoCoursesGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoCoursesGridPane.setMinWidth(340);
        profileInfoCoursesGridPane.setPrefWidth(680);
        profileInfoCoursesGridPane.setMaxWidth(680);
        profileInfoCoursesGridPane.addColumn(0, findATutor, becomeATutor, findStudyBuddy);
        ColumnConstraints coursesCol1Constraints = new ColumnConstraints(80, 160, 160, Priority.NEVER, HPos.LEFT, false);
        coursesCol1Constraints.setPercentWidth(24);
        profileInfoCoursesGridPane.addColumn(1, tf14, tf15, tf16);
        ColumnConstraints coursesCol2Constraints = new ColumnConstraints(260, 520, 520, Priority.NEVER, HPos.LEFT, true);
        coursesCol2Constraints.setPercentWidth(76);
        profileInfoCoursesGridPane.getColumnConstraints().addAll(coursesCol1Constraints, coursesCol2Constraints);
        /*for (int i = 0; i <= 2; i++) {
            profileInfoCoursesGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(30, 30, 30, Priority.NEVER, VPos.CENTER, true);
            profileInfoCoursesGridPane.getRowConstraints().addAll(rowConstraints);
        }*/
        profileInfoCoursesGridPane.setId("profileInfoCoursesGridPane");

        VBox profileAdditionalInfoGridPaneContainerPane = new VBox();
        profileAdditionalInfoGridPaneContainerPane.setPadding(profileAvailabilityTitlePaneInsets);
        profileAdditionalInfoGridPaneContainerPane.setSpacing(0);
        profileAdditionalInfoGridPaneContainerPane.setAlignment(Pos.TOP_LEFT);
        profileAdditionalInfoGridPaneContainerPane.setMinWidth(340);
        profileAdditionalInfoGridPaneContainerPane.setPrefWidth(680);
        profileAdditionalInfoGridPaneContainerPane.setMaxWidth(680);
        profileAdditionalInfoGridPaneContainerPane.getChildren().addAll(profileInfoContinuedGridPane, profileInfoCoursesLabelPane, profileInfoCoursesGridPane);
        profileAdditionalInfoGridPaneContainerPane.setId("profileAdditionalInfoGridPaneContainerPane");

        HBox profileAvailabilityTitlePane = new HBox();
        profileAvailabilityTitlePane.setPadding(profileAvailabilityTitlePaneInsets);
        profileAvailabilityTitlePane.getChildren().addAll(availability);
        profileAvailabilityTitlePane.setId("profileAvailabilityTitlePane");

        GridPane profileAvailabilityGridPane = new GridPane();
        profileAvailabilityGridPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityGridPane.setPadding(profileAvailabilityGridPaneInsets);
        profileAvailabilityGridPane.addColumn(0, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        ColumnConstraints availabilityCol1Constraints = new ColumnConstraints(130, 130, 130, Priority.NEVER, HPos.LEFT, false);
        availabilityCol1Constraints.setPercentWidth(24);
        profileAvailabilityGridPane.addColumn(1, tf17, tf18, tf19, tf20, tf21, tf22, tf23);
        ColumnConstraints availabilityCol2Constraints = new ColumnConstraints(420, 420, 420, Priority.ALWAYS, HPos.LEFT, true);
        availabilityCol2Constraints.setPercentWidth(76);
        profileAvailabilityGridPane.getColumnConstraints().addAll(availabilityCol1Constraints, availabilityCol2Constraints);
        /*for (int i = 0; i < 7; i++) {
            profileAvailabilityGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(48, 48, 48, Priority.NEVER, VPos.CENTER, true);
            profileAvailabilityGridPane.getRowConstraints().addAll(rowConstraints);
        }*/
        profileAvailabilityGridPane.setId("profileAvailabilityGridPane");

        VBox profileAvailabilityPane = new VBox();
        profileAvailabilityPane.setPadding(profileAvailabilityPaneInsets);
        profileAvailabilityPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityPane.setMinWidth(650);
        profileAvailabilityPane.setPrefWidth(650);
        profileAvailabilityPane.setMaxWidth(650);
        profileAvailabilityPane.setPrefHeight(320);
        profileAvailabilityPane.getChildren().addAll(profileAvailabilityTitlePane, profileAvailabilityGridPane);
        profileAvailabilityPane.setId("profileAvailabilityPane");

        HBox profileBotContainerPane = new HBox();
        profileBotContainerPane.setPadding(profileAdditionalInfoInsets);
        profileBotContainerPane.setSpacing(20);
        profileBotContainerPane.setMinWidth(1400);
        profileBotContainerPane.setPrefWidth(1400);
        profileBotContainerPane.setMaxWidth(1400);
        profileBotContainerPane.setAlignment(Pos.TOP_LEFT);
        profileBotContainerPane.getChildren().addAll(profileAdditionalInfoGridPaneContainerPane, profileAvailabilityPane);
        profileBotContainerPane.setId("profileBotContainerPane");

        VBox editToggleBtnPane = new VBox();
        editToggleBtnPane.setPadding(editToggleBtnInsets);
        editToggleBtnPane.setAlignment(Pos.BOTTOM_CENTER);
        editToggleBtnPane.getChildren().addAll(editToggleBtn);
        editToggleBtnPane.setId("editToggleBtnPane");

        HBox profileTop = new HBox();
        profileTop.setPadding(noPaddingInsets);
        profileTop.setSpacing(20);
        profileTop.getChildren().addAll(profilePicPane,profileInfoPane);
        profileTop.setId("profileTop");

        HBox profileBot = new HBox();
        profileTop.setPadding(noPaddingInsets);
        profileBot.setSpacing(10);
        profileBot.getChildren().addAll(profileBotContainerPane, editToggleBtnPane);
        profileBot.setId("profileBot");

        VBox profile = new VBox();
        profile.setPadding(profileInsets);
        profile.setSpacing(30);
        profile.getChildren().addAll(profileTop, profileBot);
        profile.setId("profile");

        super.setCenter(profile);
    }

}
