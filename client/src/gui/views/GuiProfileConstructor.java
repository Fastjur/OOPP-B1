package gui.views;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

/**
 * Constructor for building the profile page of the GUI
 * Author: Sebastiaan Hester
 */
public class GuiProfileConstructor extends BorderPane {

    protected TextField name, age, email, telephoneNumber, location, tf11, tf12, tf13, tf14, tf15, tf16, tf17, tf18,
            tf19, tf20, tf21, tf22, tf23;
    protected ChoiceBox<String> sex, nationality, languages;
    protected DatePicker dateOfBirth;
    protected PasswordField password,repeatPwField1,repeatPwField2;

    public GuiProfileConstructor() {
        super();


        //Insets:
        Insets noPaddingInsets = new Insets(0);
        Insets labelInsets = new Insets(0, 0, 0, 20);
        Insets profileInfoInsets = new Insets(10,20,10,20);
        Insets profileInfoContinuedInsets = new Insets(10,0,30,10);
        Insets profileInfoCoursesInsets = new Insets(10,0,10,10);
        Insets profileAvailabilityTitlePaneInsets = new Insets(10);
        Insets profileAvailabilityPaneInsets = new Insets(0, 20, 0, 20);
        Insets editToggleBtnInsets = new Insets(0, 0, 20, 40);
        Insets profileInsets = new Insets(10, 30, 30, 10);
        Insets repeatPwInsets = new Insets(5, 0, 10 , 30);

        //Shape settings
        Circle editToggleBtnShape = new Circle(70);

        //Fields for information
        name = new TextField();
        name.setPromptText("Info goes here");
        name.setEditable(false);
        sex = new ChoiceBox<>();
        sex.setDisable(true);
        sex.getItems().add("Male");
        sex.getItems().add("Female");
        sex.setMaxWidth(815);
        sex.setPrefWidth(815);
        sex.setId("sexChoiceBox");
        age = new TextField(){
            @Override
            public void replaceText(int start, int end, String text){
                if(text.matches("\\d+")){
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text){
                if(text.matches("\\d+")){
                    super.replaceSelection(text);
                }
            }
        };
        age.setPromptText("Info goes here");
        age.setEditable(false);
        dateOfBirth = new DatePicker();
        dateOfBirth.setPromptText("Info goes here");
        dateOfBirth.setDisable(true);
        dateOfBirth.setMaxWidth(815);
        dateOfBirth.setPrefWidth(815);
        nationality = new ChoiceBox<>();
        nationality.setDisable(true);
        nationality.getItems().addAll("Items retrieved from database here");
        nationality.setMaxWidth(815);
        nationality.setPrefWidth(815);
        nationality.setId("nationalityChoiceBox");
        languages = new ChoiceBox<>();
        languages.setDisable(true);
        languages.getItems().addAll("Items retrieved from database here");
        languages.setMaxWidth(815);
        languages.setPrefWidth(815);
        languages.setId("languagesChoiceBox");
        email = new TextField();
        email.setPromptText("Info goes here");
        email.setEditable(false);
        telephoneNumber = new TextField(){
            @Override
            public void replaceText(int start, int end, String text){
                if(text.matches("\\d+")){
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text){
                if(text.matches("\\d+")){
                    super.replaceSelection(text);
                }
            }
        };
        telephoneNumber.setPromptText("Info goes here");
        telephoneNumber.setEditable(false);
        location = new TextField(){
            //Using a regex that isn't completely correct, trying to figure out why the correct one won't work
            @Override
            public void replaceText(int start, int end, String text){
                if(text.matches("[0-9,]+")){
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text){
                if(text.matches("[0-9,]+")){
                    super.replaceSelection(text);
                }
            }
        };
        location.setPromptText("Info goes here");
        location.setEditable(false);
        password = new PasswordField();
        password.setPromptText("Info goes here");
        password.setEditable(false);
        tf11 = new TextField();
        tf11.setPromptText("Info goes here");
        tf11.setEditable(false);
        tf12 = new TextField();
        tf12.setPromptText("Info goes here");
        tf12.setEditable(false);
        tf13 = new TextField();
        tf13.setPromptText("Info goes here");
        tf13.setEditable(false);
        tf14 = new TextField();
        tf14.setPromptText("Info goes here");
        tf14.setEditable(false);
        tf15 = new TextField();
        tf15.setPromptText("Info goes here");
        tf15.setEditable(false);
        tf16 = new TextField();
        tf16.setPromptText("Info goes here");
        tf16.setEditable(false);
        tf17 = new TextField();
        tf17.setPromptText("Info goes here");
        tf17.setEditable(false);
        tf18 = new TextField();
        tf18.setPromptText("Info goes here");
        tf18.setEditable(false);
        tf19 = new TextField();
        tf19.setPromptText("Info goes here");
        tf19.setEditable(false);
        tf20 = new TextField();
        tf20.setPromptText("Info goes here");
        tf20.setEditable(false);
        tf21 = new TextField();
        tf21.setPromptText("Info goes here");
        tf21.setEditable(false);
        tf22 = new TextField();
        tf22.setPromptText("Info goes here");
        tf22.setEditable(false);
        tf23 = new TextField();
        tf23.setPromptText("Info goes here");
        tf23.setEditable(false);
        repeatPwField1 = new PasswordField();
        repeatPwField1.setEditable(false);
        repeatPwField1.setPromptText("Type your new password");
        repeatPwField1.setMinWidth(200);
        repeatPwField1.setPrefWidth(400);
        repeatPwField1.setMaxWidth(400);
        repeatPwField2 = new PasswordField();
        repeatPwField2.setEditable(false);
        repeatPwField2.setPromptText("Repeat your new password");
        repeatPwField2.setMinWidth(200);
        repeatPwField2.setPrefWidth(400);
        repeatPwField2.setMaxWidth(400);

        //Buttons:
        Button uploadPicBtn = new Button();
        uploadPicBtn.setPadding(noPaddingInsets);
        uploadPicBtn.setMinSize(410, 45);
        uploadPicBtn.setPrefSize(410, 45);
        uploadPicBtn.setMaxSize(410, 45);
        uploadPicBtn.setText("Upload picture");
        uploadPicBtn.setOnMouseClicked(event -> {

        });
        uploadPicBtn.setId("uploadPicBtn");

        Button editToggleBtn = new Button();
        editToggleBtn.setShape(editToggleBtnShape);
        editToggleBtn.setPrefSize(70,70);
        editToggleBtn.setOnMouseClicked(event -> {
            if (editToggleBtn.getId().equals("editToggleBtn")) {
                name.setEditable(true);
                sex.setDisable(false);
                age.setEditable(true);
                dateOfBirth.setDisable(false);
                nationality.setDisable(false);
                languages.setDisable(false);
                email.setEditable(true);
                telephoneNumber.setEditable(true);
                location.setEditable(true);
                password.setEditable(true);
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
                repeatPwField1.setEditable(true);
                repeatPwField2.setEditable(true);
                editToggleBtn.setId("editToggleBtnActive");
            }
            else{
                name.setEditable(false);
                sex.setDisable(true);
                age.setEditable(false);
                dateOfBirth.setDisable(true);
                nationality.setDisable(true);
                languages.setDisable(true);
                email.setEditable(false);
                telephoneNumber.setEditable(false);
                location.setEditable(false);
                password.setEditable(false);
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
                repeatPwField1.setEditable(false);
                repeatPwField2.setEditable(false);
                editToggleBtn.setId("editToggleBtn");
            }
        });
        editToggleBtn.setId("editToggleBtn");

        //Labels
        Label profileInfoLabel = new Label("Personal");
        profileInfoLabel.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoLabel.setMinSize(525, 30);
        profileInfoLabel.setPrefSize(1050, 60);
        profileInfoLabel.setMaxSize(1050, 60);
        profileInfoLabel.setPadding(labelInsets);
        profileInfoLabel.setId("profileInfoLabel");
        Label nameLabel = new Label("Name:");
        Label sexLabel = new Label("Sex:");
        Label ageLabel = new Label("Age:");
        Label dateOfBirthLabel = new Label("Date of Birth:");
        Label nationalityLabel = new Label("Nationality");
        Label languagesLabel = new Label("Languages:");
        Label emailLabel = new Label("Email:");
        Label telephoneNumberLabel = new Label("Telephone Number:");
        Label locationLabel = new Label("Location:");
        Label passwordLabel = new Label("Password:");
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
        Label repeatPwLabel = new Label("Change your password");
        repeatPwLabel.setAlignment(Pos.BOTTOM_LEFT);
        repeatPwLabel.setMinSize(675,25);
        repeatPwLabel.setPrefSize(1350,50);
        repeatPwLabel.setMaxSize(1350,50);
        repeatPwLabel.setPadding(repeatPwInsets);
        repeatPwLabel.setId("repeatPwLabel");

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
        profileInfoGridPane.setPadding(profileInfoInsets);
        profileInfoGridPane.setMinWidth(525);
        profileInfoGridPane.setPrefWidth(1050);
        profileInfoGridPane.setMaxWidth(1050);
        profileInfoGridPane.addColumn(0, nameLabel, sexLabel, ageLabel, dateOfBirthLabel, nationalityLabel, languagesLabel, emailLabel, telephoneNumberLabel, locationLabel);
        profileInfoGridPane.addColumn(1, name, sex, age, dateOfBirth, nationality, languages, email, telephoneNumber, location);
        ColumnConstraints col1Constraints = new ColumnConstraints(140, 280, 280, Priority.NEVER, HPos.LEFT, false);
        col1Constraints.setPercentWidth(21);
        ColumnConstraints col2Constraints = new ColumnConstraints(375, 750, 750, Priority.ALWAYS, HPos.LEFT, true);
        col2Constraints.setPercentWidth(79);
        profileInfoGridPane.getColumnConstraints().addAll(col1Constraints, col2Constraints);
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
        profileInfoContinuedGridPane.setPadding(profileInfoContinuedInsets);
        profileInfoContinuedGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoContinuedGridPane.setMinWidth(350);
        profileInfoContinuedGridPane.setPrefWidth(700);
        profileInfoContinuedGridPane.setMaxWidth(700);
        profileInfoContinuedGridPane.addColumn(0, university, study, studyYear);
        ColumnConstraints continuedInfoCol1Constraints = new ColumnConstraints(83,175,175, Priority.NEVER, HPos.LEFT, false);
        continuedInfoCol1Constraints.setPercentWidth(25);
        profileInfoContinuedGridPane.addColumn(1, tf11, tf12, tf13);
        ColumnConstraints continuedInfoCol2Constraints = new ColumnConstraints(263,525,525, Priority.ALWAYS, HPos.LEFT, true);
        continuedInfoCol2Constraints.setPercentWidth(75);
        profileInfoContinuedGridPane.getColumnConstraints().addAll(continuedInfoCol1Constraints, continuedInfoCol2Constraints);
        profileInfoContinuedGridPane.setId("profileInfoContinuedGridPane");

        HBox profileInfoCoursesLabelPane = new HBox();
        profileInfoCoursesLabelPane.setMinSize(350,55);
        profileInfoCoursesLabelPane.setPrefSize(700,110);
        profileInfoCoursesLabelPane.setMaxSize(700,110);
        profileInfoCoursesLabelPane.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoCoursesLabelPane.getChildren().addAll(courses);
        profileInfoCoursesLabelPane.setId("profileInfoCoursesLabelPane");

        GridPane profileInfoCoursesGridPane = new GridPane();
        profileInfoCoursesGridPane.setPadding(profileInfoCoursesInsets);
        profileInfoCoursesGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoCoursesGridPane.setMinWidth(350);
        profileInfoCoursesGridPane.setPrefWidth(700);
        profileInfoCoursesGridPane.setMaxWidth(700);
        profileInfoCoursesGridPane.addColumn(0, findATutor, becomeATutor, findStudyBuddy);
        ColumnConstraints coursesCol1Constraints = new ColumnConstraints(83, 175, 175, Priority.NEVER, HPos.LEFT, false);
        coursesCol1Constraints.setPercentWidth(25);
        profileInfoCoursesGridPane.addColumn(1, tf14, tf15, tf16);
        ColumnConstraints coursesCol2Constraints = new ColumnConstraints(263, 525, 525, Priority.NEVER, HPos.LEFT, true);
        coursesCol2Constraints.setPercentWidth(75);
        profileInfoCoursesGridPane.getColumnConstraints().addAll(coursesCol1Constraints, coursesCol2Constraints);
        profileInfoCoursesGridPane.setId("profileInfoCoursesGridPane");

        VBox profileAdditionalInfoGridPaneContainerPane = new VBox();
        profileAdditionalInfoGridPaneContainerPane.setPadding(noPaddingInsets);
        profileAdditionalInfoGridPaneContainerPane.setSpacing(0);
        profileAdditionalInfoGridPaneContainerPane.setAlignment(Pos.TOP_LEFT);
        profileAdditionalInfoGridPaneContainerPane.getChildren().addAll(profileInfoContinuedGridPane, profileInfoCoursesLabelPane, profileInfoCoursesGridPane);
        profileAdditionalInfoGridPaneContainerPane.setId("profileAdditionalInfoGridPaneContainerPane");

        HBox profileAvailabilityTitlePane = new HBox();
        profileAvailabilityTitlePane.setPadding(profileAvailabilityTitlePaneInsets);
        profileAvailabilityTitlePane.getChildren().addAll(availability);
        profileAvailabilityTitlePane.setId("profileAvailabilityTitlePane");

        GridPane profileAvailabilityGridPane = new GridPane();
        profileAvailabilityGridPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityGridPane.setPadding(profileAvailabilityTitlePaneInsets);
        profileAvailabilityGridPane.addColumn(0, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        ColumnConstraints availabilityCol1Constraints = new ColumnConstraints(75, 150, 150, Priority.NEVER, HPos.LEFT, false);
        availabilityCol1Constraints.setPercentWidth(25);
        profileAvailabilityGridPane.addColumn(1, tf17, tf18, tf19, tf20, tf21, tf22, tf23);
        ColumnConstraints availabilityCol2Constraints = new ColumnConstraints(265, 530, 530, Priority.ALWAYS, HPos.LEFT, true);
        availabilityCol2Constraints.setPercentWidth(75);
        profileAvailabilityGridPane.getColumnConstraints().addAll(availabilityCol1Constraints, availabilityCol2Constraints);
        profileAvailabilityGridPane.setId("profileAvailabilityGridPane");

        VBox profileAvailabilityPane = new VBox();
        profileAvailabilityPane.setPadding(profileAvailabilityPaneInsets);
        profileAvailabilityPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityPane.setMinWidth(375);
        profileAvailabilityPane.setPrefWidth(750);
        profileAvailabilityPane.setMaxWidth(750);
        profileAvailabilityPane.setPrefHeight(320);
        profileAvailabilityPane.getChildren().addAll(profileAvailabilityTitlePane, profileAvailabilityGridPane);
        profileAvailabilityPane.setId("profileAvailabilityPane");

        HBox profileBotContainerPane = new HBox();
        profileBotContainerPane.setPadding(noPaddingInsets);
        profileBotContainerPane.setSpacing(20);
        profileBotContainerPane.setMinWidth(740);
        profileBotContainerPane.setPrefWidth(1480);
        profileBotContainerPane.setMaxWidth(1480);
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
        /*
        HBox profileBot = new HBox();
        profileBot.setPadding(noPaddingInsets);
        profileBot.setSpacing(10);
        profileBot.getChildren().addAll(profileBotContainerPane);
        profileBot.setId("profileBot");
        */
        HBox repeatPwFieldBot = new HBox();
        repeatPwFieldBot.setPadding(profileInsets);
        repeatPwFieldBot.setSpacing(30);
        repeatPwFieldBot.setMinWidth(675);
        repeatPwFieldBot.setPrefWidth(1350);
        repeatPwFieldBot.setMaxWidth(1350);
        repeatPwFieldBot.getChildren().addAll(repeatPwField1,repeatPwField2);
        repeatPwFieldBot.setId("repeatPwFieldBot");

        VBox repeatPwField = new VBox();
        repeatPwField.setPadding(noPaddingInsets);
        repeatPwField.setSpacing(0);
        repeatPwField.getChildren().addAll(repeatPwLabel,repeatPwFieldBot);
        repeatPwField.setId("repeatPwField");

        HBox profilePwField = new HBox();
        profilePwField.setPadding(noPaddingInsets);
        profilePwField.setSpacing(0);
        profilePwField.getChildren().addAll(repeatPwField,editToggleBtnPane);

        VBox profile = new VBox();
        profile.setPadding(profileInsets);
        profile.setSpacing(30);
        profile.getChildren().addAll(profileTop,profileBotContainerPane,profilePwField);
        profile.setId("profile");

        super.setCenter(profile);
    }

}
