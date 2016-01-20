package gui.views;

import communication.Backend;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import shared.AvailableTimes;
import shared.TimePeriod;
import shared.User;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Constructor for building the profile page of the GUI
 * Author: Sebastiaan Hester
 */
public class GuiProfileConstructor extends BorderPane {

    private boolean languagesUpdated = false,
                    studiesUpdated = false,
                    nationalityUpdated = false,
                    universityUpdated = false,
                    findBuddyUpdated = false,
                    findTutorUpdated = false,
                    becomeTutorUpdated = false,
                    availabilityUpdated = false;
    protected TextField name, age, email, telephoneNumber, location, studyYear, monday, tuesday,
            wednesday, thursday, friday, saturday, sunday;
    protected ChoiceBox<String> sex;
    protected Accordion nationality, university, languages, study, findTutor, becomeTutor, findBuddy;
    protected DatePicker dateOfBirth;
    protected PasswordField repeatPwField1,repeatPwField2;
    protected VBox nationalityBox, universityBox, languagesBox, studyBox, findTutorBox, becomeTutorBox, findBuddyBox;
    protected TitledPane nationalityPane, universityPane, languagesPane, studyPane, findTutorPane, becomeTutorPane,
            findBuddyPane;
    private ToggleGroup findBuddyGroup, findTutorGroup, becomeTutorGroup, universitiesGroup, nationalitiesGroup,
            studiesGroup;

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

        //Shape settings
        Circle editToggleBtnShape = new Circle(70);

        //Fields for information
        name = new TextField();
        name.setDisable(true);
        sex = new ChoiceBox<>();
        sex.setDisable(true);
        sex.getItems().add("Male");
        sex.getItems().add("Female");
        sex.setMaxWidth(815);
        sex.setPrefWidth(815);
        sex.setId("choiceBox");
        age = new TextField(){
            @Override
            public void replaceText(int start, int end, String text){
                if(text.matches("[0-9]*")){
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text){
                if(text.matches("[0-9]*")){
                    super.replaceSelection(text);
                }
            }
        };
        age.setDisable(true);
        dateOfBirth = new DatePicker(LocalDate.now());
        dateOfBirth.setDisable(true);
        dateOfBirth.setMaxWidth(815);
        dateOfBirth.setPrefWidth(815);
        nationalityBox = new VBox(10);
        nationalityBox.getStyleClass().addAll("vbox");
        nationalityPane = new TitledPane("Select Nationality", nationalityBox);
        nationality = new Accordion();
        nationality.setDisable(true);
        nationality.getPanes().addAll(nationalityPane);
        nationality.setMaxWidth(815);
        nationality.setPrefWidth(815);
        nationality.setId("choiceBox");

        universityBox = new VBox(10);
        universityBox.getStyleClass().addAll("vbox");
        universityPane = new TitledPane("Select University", universityBox);
        university = new Accordion();
        university.setDisable(true);
        university.getPanes().addAll(universityPane);
        university.setMaxWidth(815);
        university.setPrefWidth(815);
        university.setId("choiceBox");

        languagesBox = new VBox(10);
        languagesBox.getStyleClass().add("vbox");
        languagesPane = new TitledPane("Select Languages", languagesBox);
        languages = new Accordion();
        languages.getPanes().addAll(languagesPane);
        languages.setDisable(true);
        languages.setMaxWidth(815);
        languages.setPrefWidth(815);
        languages.setId("accordion");
        email = new TextField();
        email.setDisable(true);
        telephoneNumber = new TextField(){
            @Override
            public void replaceText(int start, int end, String text){
                if(text.matches("[0-9]*|\\+")){
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text){
                if(text.matches("[0-9]*|\\+")){
                    super.replaceSelection(text);
                }
            }
        };
        telephoneNumber.setDisable(true);
        location = new TextField(){
            //Using a regex that isn't completely correct, trying to figure out why the correct one won't work
            @Override
            public void replaceText(int start, int end, String text){
                if(text.matches("[0-9]*|,|\\.")){
                    super.replaceText(start, end, text);
                }
            }

            @Override
            public void replaceSelection(String text){
                if(text.matches("[0-9]*|,|\\.")){
                    super.replaceSelection(text);
                }
            }
        };
        location.setDisable(true);
        studyBox = new VBox(10);
        studyBox.getStyleClass().addAll("vbox");
        studyPane = new TitledPane("Study", studyBox);
        study = new Accordion();
        study.getPanes().addAll(studyPane);
        study.setPrefWidth(525);
        study.setMaxWidth(525);
        study.setDisable(true);
        study.setId("choiceBox");
        studyYear = new TextField();
        studyYear.setDisable(true);
        findTutorBox = new VBox(10);
        findTutorBox.getStyleClass().add("vbox");
        findTutorPane = new TitledPane("Find tutor for", findTutorBox);
        findTutor = new Accordion();
        findTutor.getPanes().addAll(findTutorPane);
        findTutor.setPrefWidth(525);
        findTutor.setMaxWidth(525);
        findTutor.setDisable(true);
        findTutor.setId("choiceBox");
        becomeTutorBox = new VBox(10);
        becomeTutorBox.getStyleClass().add("vbox");
        becomeTutorPane = new TitledPane("Become tutor for", becomeTutorBox);
        becomeTutor = new Accordion();
        becomeTutor.getPanes().addAll(becomeTutorPane);
        becomeTutor.setPrefWidth(525);
        becomeTutor.setMaxWidth(525);
        becomeTutor.setDisable(true);
        becomeTutor.setId("choiceBox");
        findBuddyBox = new VBox(10);
        findBuddyBox.getStyleClass().addAll("vbox");
        findBuddyPane = new TitledPane("Find buddy for", findBuddyBox);
        findBuddy = new Accordion();
        findBuddy.getPanes().addAll(findBuddyPane);
        findBuddy.setPrefWidth(525);
        findBuddy.setMaxWidth(525);
        findBuddy.setDisable(true);
        findBuddy.setId("choiceBox");
        monday = new TextField();
        monday.setDisable(true);
        tuesday = new TextField();
        tuesday.setDisable(true);
        wednesday = new TextField();
        wednesday.setDisable(true);
        thursday = new TextField();
        thursday.setDisable(true);
        friday = new TextField();
        friday.setDisable(true);
        saturday = new TextField();
        saturday.setDisable(true);
        sunday = new TextField();
        sunday.setDisable(true);
        repeatPwField1 = new PasswordField();
        repeatPwField1.setDisable(true);
        repeatPwField1.setPromptText("Type your new password");
        repeatPwField1.setMinWidth(200);
        repeatPwField1.setPrefWidth(400);
        repeatPwField1.setMaxWidth(400);
        repeatPwField2 = new PasswordField();
        repeatPwField2.setDisable(true);
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
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(GUILauncher.stage);
            //TODO: Upload image to server
        });
        uploadPicBtn.setId("uploadPicBtn");

        Button editToggleBtn = new Button();
        editToggleBtn.setShape(editToggleBtnShape);
        editToggleBtn.setPrefSize(70,70);
        editToggleBtn.setOnMouseClicked(event -> {
            if (editToggleBtn.getId().equals("editToggleBtn")) {
                name.setDisable(false);
                sex.setDisable(false);
                dateOfBirth.setDisable(false);
                nationality.setDisable(false);
                languages.setDisable(false);
                email.setDisable(false);
                telephoneNumber.setDisable(false);
                location.setDisable(false);
                university.setDisable(false);
                study.setDisable(false);
                studyYear.setDisable(false);
                findTutor.setDisable(false);
                becomeTutor.setDisable(false);
                findBuddy.setDisable(false);
                monday.setDisable(false);
                tuesday.setDisable(false);
                wednesday.setDisable(false);
                thursday.setDisable(false);
                friday.setDisable(false);
                saturday.setDisable(false);
                sunday.setDisable(false);
                repeatPwField1.setDisable(false);
                repeatPwField2.setDisable(false);
                editToggleBtn.setId("editToggleBtnActive");
            } else {
                updateUser();
                name.setDisable(true);
                sex.setDisable(true);
                dateOfBirth.setDisable(true);
                nationality.setDisable(true);
                languages.setDisable(true);
                email.setDisable(true);
                telephoneNumber.setDisable(true);
                location.setDisable(true);
                university.setDisable(true);
                study.setDisable(true);
                studyYear.setDisable(true);
                findTutor.setDisable(true);
                becomeTutor.setDisable(true);
                findBuddy.setDisable(true);
                monday.setDisable(true);
                tuesday.setDisable(true);
                wednesday.setDisable(true);
                thursday.setDisable(true);
                friday.setDisable(true);
                saturday.setDisable(true);
                sunday.setDisable(true);
                repeatPwField1.setDisable(true);
                repeatPwField2.setDisable(true);
                editToggleBtn.setId("editToggleBtn");
            }
        });
        editToggleBtn.setId("editToggleBtn");

        this.monday.setOnKeyPressed(event -> {
            System.out.println("Availability updated, adding to update queue");
            this.availabilityUpdated = true;
        });

        this.tuesday.setOnKeyPressed(event -> {
            System.out.println("Availability updated, adding to update queue");
            this.availabilityUpdated = true;
        });

        this.wednesday.setOnKeyPressed(event -> {
            System.out.println("Availability updated, adding to update queue");
            this.availabilityUpdated = true;
        });

        this.thursday.setOnKeyPressed(event -> {
            System.out.println("Availability updated, adding to update queue");
            this.availabilityUpdated = true;
        });

        this.friday.setOnKeyPressed(event -> {
            System.out.println("Availability updated, adding to update queue");
            this.availabilityUpdated = true;
        });

        this.saturday.setOnKeyPressed(event -> {
            System.out.println("Availability updated, adding to update queue");
            this.availabilityUpdated = true;
        });

        this.sunday.setOnKeyPressed(event -> {
            System.out.println("Availability updated, adding to update queue");
            this.availabilityUpdated = true;
        });

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
        repeatPwLabel.setMinSize(675,35);
        repeatPwLabel.setPrefSize(1350,70);
        repeatPwLabel.setMaxSize(1350,70);
        repeatPwLabel.setPadding(labelInsets);
        repeatPwLabel.setId("repeatPwLabel");

        //Image settings
        //TODO: Retrieve profile picture from DB
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
        profileInfoGridPane.addColumn(1, name, sex, age, dateOfBirth, nationality, this.languages, email, telephoneNumber, location);
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
        profileInfoContinuedGridPane.addColumn(1, this.university, this.study, this.studyYear);
        ColumnConstraints continuedInfoCol2Constraints = new ColumnConstraints(263,525,525, Priority.ALWAYS, HPos.LEFT, true);
        continuedInfoCol2Constraints.setPercentWidth(75);
        profileInfoContinuedGridPane.getColumnConstraints().addAll(continuedInfoCol1Constraints, continuedInfoCol2Constraints);
        profileInfoContinuedGridPane.setId("profileInfoContinuedGridPane");

        HBox profileInfoCoursesLabelPane = new HBox();
        profileInfoCoursesLabelPane.setPadding(labelInsets);
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
        profileInfoCoursesGridPane.addColumn(1, findTutor, becomeTutor, findBuddy);
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
        profileAvailabilityGridPane.addColumn(1, this.monday, this.tuesday, this.wednesday, this.thursday, this.friday, this.saturday, this.sunday);
        ColumnConstraints availabilityCol2Constraints = new ColumnConstraints(265, 530, 530, Priority.ALWAYS, HPos.LEFT, true);
        availabilityCol2Constraints.setPercentWidth(75);
        profileAvailabilityGridPane.getColumnConstraints().addAll(availabilityCol1Constraints, availabilityCol2Constraints);
        profileAvailabilityGridPane.setId("profileAvailabilityGridPane");

        VBox profileAvailabilityPane = new VBox();
        profileAvailabilityPane.setPadding(profileAvailabilityPaneInsets);
        profileAvailabilityPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityPane.setMinWidth(380);
        profileAvailabilityPane.setPrefWidth(760);
        profileAvailabilityPane.setMaxWidth(760);
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

        VBox profileMain = new VBox();
        profileMain.setPadding(profileInsets);
        profileMain.setSpacing(30);
        profileMain.getChildren().addAll(profileTop,profileBotContainerPane,profilePwField);
        profileMain.setAlignment(Pos.CENTER);
        profileMain.setId("profile");

        ScrollPane profile = new ScrollPane();
        profile.setContent(profileMain);
        profile.setFitToHeight(true);
        profile.setFitToWidth(true);

        super.setCenter(profile);
    }

    /**
     * First imports all the nationalities into the choicebox. Then uses the selfobject to select the correct one
     * @param dbNationalities HashMap containing all the nationalities from the DB
     */
    public void setNationalities(HashMap<Integer, String> dbNationalities) {
        nationalityBox.getChildren().clear();
        nationalitiesGroup = new ToggleGroup();
        for (Object o : dbNationalities.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            RadioButton cb = new RadioButton(pair.getValue().toString());
            cb.setUserData(Integer.parseInt(pair.getKey().toString()));
            cb.setToggleGroup(nationalitiesGroup);
            if (Backend.getSelfObject().getNationality().equals(pair.getValue().toString())) {
                cb.setSelected(true);
            }
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("[INFO] Nationality changed, adding to queue for updating to database");
                this.nationalityUpdated = true;
            });
            nationalityBox.getChildren().addAll(cb);
        }
    }

    /**
     * First imports all the languages into the choicebox. Then uses the selfobject to check the correct ones.
     * @param dbLanguages HashMap containing all the languages from the DB
     */
    public void setLanguages(HashMap<Integer, String> dbLanguages) {
        languagesBox.getChildren().clear();
        for (Object o : dbLanguages.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            CheckBox cb = new CheckBox(pair.getValue().toString());
            cb.setUserData(Integer.parseInt(pair.getKey().toString()));
            if (Backend.getSelfObject().getLanguageList().contains(pair.getValue().toString())) {
                cb.setSelected(true);
            }
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("[INFO] Languages changed, adding to queue for updating to database");
                this.languagesUpdated = true;
            });
            languagesBox.getChildren().addAll(cb);
        }
    }

    /**
     * First imports all the studies into the choicebox. Then uses the selfobject to select the correct one
     * @param dbStudies HashMap containing all the studies from the DB
     */
    public void setStudies(HashMap<Integer, String> dbStudies) {
        studyBox.getChildren().clear();
        studiesGroup = new ToggleGroup();
        for (Object o : dbStudies.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            RadioButton cb = new RadioButton(pair.getValue().toString());
            cb.setUserData(Integer.parseInt(pair.getKey().toString()));
            cb.setToggleGroup(studiesGroup);
            if (Backend.getSelfObject().getStudy().equals(pair.getValue().toString())) {
                cb.setSelected(true);
            }
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("[INFO] Studies changed, adding to queue for updating to database");
                this.studiesUpdated = true;
            });
            studyBox.getChildren().addAll(cb);
        }
    }

    /**
     * First imports all the universities into the choicebox. Then uses the selfobject to select the correct one
     * @param dbUniversities HashMap containing all the universities from the DB
     */
    public void setUniversity(HashMap<Integer, String> dbUniversities) {
        universityBox.getChildren().clear();
        universitiesGroup = new ToggleGroup();
        for (Object o : dbUniversities.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            RadioButton cb = new RadioButton(pair.getValue().toString());
            cb.setUserData(Integer.parseInt(pair.getKey().toString()));
            cb.setToggleGroup(universitiesGroup);
            if (Backend.getSelfObject().getUniversity().equals(pair.getValue().toString())) {
                cb.setSelected(true);
            }
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("[INFO] University changed, adding to queue for updating to database");
                this.universityUpdated = true;
            });
            universityBox.getChildren().addAll(cb);
        }
    }

    /**
     * First imports all the courses into the choicebox. Then uses the selfobject to check the correct ones.
     * @param dbCourses ArrayList containing all the courses from the DB
     */
    public void setCourses(HashMap<Integer, String> dbCourses) {
        findBuddyBox.getChildren().clear();
        findTutorBox.getChildren().clear();
        becomeTutorBox.getChildren().clear();
        findBuddyGroup = new ToggleGroup();
        findTutorGroup = new ToggleGroup();
        becomeTutorGroup = new ToggleGroup();
        for (Object o : dbCourses.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            CheckBox cb = new CheckBox(pair.getValue().toString());
            cb.setUserData(Integer.parseInt(pair.getKey().toString()));
            if (Backend.getSelfObject().getBuddyList().contains(pair.getValue().toString())) {
                cb.setSelected(true);
            }
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("[INFO] findBuddy changed, adding to queue for updating to database");
                this.findBuddyUpdated = true;
            });
            findBuddyBox.getChildren().addAll(cb);
        }
        for (Object o : dbCourses.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            CheckBox cb = new CheckBox(pair.getValue().toString());
            cb.setUserData(Integer.parseInt(pair.getKey().toString()));
            if (Backend.getSelfObject().getCoursesLearningList().contains(pair.getValue().toString())) {
                cb.setSelected(true);
            }
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("[INFO] findTutor changed, adding to queue for updating to database");
                this.findTutorUpdated = true;
            });
            findTutorBox.getChildren().addAll(cb);
        }
        for (Object o : dbCourses.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            CheckBox cb = new CheckBox(pair.getValue().toString());
            cb.setUserData(Integer.parseInt(pair.getKey().toString()));
            if (Backend.getSelfObject().getCoursesTeachingList().contains(pair.getValue().toString())) {
                cb.setSelected(true);
            }
            cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("[INFO] becomeTutor changed, adding to queue for updating to database");
                this.becomeTutorUpdated = true;
            });
            becomeTutorBox.getChildren().addAll(cb);
        }
    }

    private void updateUser() {
        User self = Backend.getSelfObject();
        boolean error = false,
                changed = false;
        if (null != name.getText() && !name.getText().equals("")) {
            String currName = self.getFirstname() + " " + self.getLastname();
            if (name.getText().matches("\\w+\\s\\w+")) {
                if (!currName.equals(name.getText())) {
                    Backend.updateName(name.getText());
                    changed = true;
                }
            } else {
                error = true;
                updateUserAlert("Your name is invalid.");
            }
        }
        if (null != sex.getValue() && !self.getGender().toLowerCase().equals(sex.getValue().toLowerCase()) && !error) {
            if (sex.getValue().toLowerCase().equals("male") || sex.getValue().toLowerCase().equals("female")) {
                Backend.updateSex(sex.getValue().toLowerCase());
                changed = true;
            } else {
                error = true;
                updateUserAlert("Invalid gender specified!");
            }
        }
        if (null != dateOfBirth.getValue() && !error) {
            LocalDate currDate = self.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (!currDate.equals(dateOfBirth.getValue())) {
                Backend.updateDateOfBirth(dateOfBirth.getValue());
                changed = true;
            }
        }
        if (null != (nationalitiesGroup.getSelectedToggle()) && this.nationalityUpdated && !error) {
            Backend.updateNationality((int) nationalitiesGroup.getSelectedToggle().getUserData());
            changed = true;
        }
        if (null != languagesBox.getChildren() && !error && this.languagesUpdated) {
            ArrayList<Integer> languages = getSelected(languagesBox);
            if (languages.size() > 0) {
                Backend.updateLanguages(languages);
                changed = true;
            }
        }
        if (null != email.getText() && !email.getText().equals("") && !email.getText().equals(self.getMail()) && !error) {
            Backend.updateEmail(email.getText());
            changed = true;
        }
        if (null != telephoneNumber.getText() && !telephoneNumber.getText().equals("") && !telephoneNumber.getText()
                .equals(self.getPhonenumber()) && !error) {
            Backend.updateTelephoneNumber(telephoneNumber.getText());
            changed = true;
        }
        if (null != location.getText() && !error) {
            if (location.getText().matches("[0-9]{1,2}.[0-9]*,[0-9]{1,2}.[0-9]*")) {
                Backend.updateLocation(location.getText());
                changed = true;
            } else {
                error = true;
                updateUserAlert("Invalid location!");
            }
        }
        if (null != universitiesGroup.getSelectedToggle() && this.universityUpdated && !error) {
            Backend.updateUniversity((int) universitiesGroup.getSelectedToggle().getUserData());
            changed = true;
        }
        if (null != studiesGroup.getSelectedToggle() && this.studiesUpdated && !error) {
            Backend.updateStudy((int) studiesGroup.getSelectedToggle().getUserData());
            changed = true;
        }
        if (null != studyYear.getText() && !error) {
            if (Integer.parseInt(studyYear.getText()) != self.getStudyYear()) {
                Backend.updateStudyYear(Integer.parseInt(studyYear.getText()));
                changed = true;
            }
        }
        if (null != findTutorBox.getChildren() && this.findTutorUpdated && !error) {
            ArrayList<Integer> learning = getSelected(findTutorBox);
            Backend.updateLearning(learning);
            changed = true;
        }
        if (null != becomeTutorBox.getChildren() && this.becomeTutorUpdated && !error) {
            ArrayList<Integer> teaching = getSelected(becomeTutorBox);
            Backend.updateTeaching(teaching);
            changed = true;
        }
        if (null != findBuddyBox.getChildren() && this.findBuddyUpdated && !error) {
            ArrayList<Integer> buddies = getSelected(findBuddyBox);
            Backend.updateBuddies(buddies);
            changed = true;
        }
        if (this.availabilityUpdated && !error) {
            try {
                System.out.println(this.monday.getText());
                ArrayList<TimePeriod> monday = AvailableTimes.fromReadable(this.monday.getText());
                ArrayList<TimePeriod> tuesday = AvailableTimes.fromReadable(this.tuesday.getText());
                ArrayList<TimePeriod> wednesday = AvailableTimes.fromReadable(this.wednesday.getText());
                ArrayList<TimePeriod> thursday = AvailableTimes.fromReadable(this.thursday.getText());
                ArrayList<TimePeriod> friday = AvailableTimes.fromReadable(this.friday.getText());
                ArrayList<TimePeriod> saturday = AvailableTimes.fromReadable(this.saturday.getText());
                ArrayList<TimePeriod> sunday = AvailableTimes.fromReadable(this.sunday.getText());
                AvailableTimes aTimes = new AvailableTimes();
                aTimes.setMonday(monday);
                aTimes.setTuesday(tuesday);
                aTimes.setWednesday(wednesday);
                aTimes.setThursday(thursday);
                aTimes.setFriday(friday);
                aTimes.setSaturday(saturday);
                aTimes.setSunday(sunday);
                Backend.updateAvailability(aTimes);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                updateUserAlert("Invalid availability given!");
                error = true;
            }
        }
        if (!error && changed) {
            Backend.getSelf();
        }
    }

    private ArrayList<Integer> getSelected (VBox box) {
        ArrayList<Integer> res = new ArrayList<>();
        for (Node node : box.getChildren()) {
            CheckBox cb = (CheckBox) node;
            if (cb.isSelected()) {
                res.add(Integer.parseInt(cb.getUserData().toString()));
            }
        }
        return res;
    }

    private void updateUserAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cannot update profile");
        alert.setHeaderText(text);
        alert.setContentText("Aborting save to database");
        alert.showAndWait();
    }

}
