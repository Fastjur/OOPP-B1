import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * The GUI for the profile page of the application
 *
 * @author Sebastiaan Hester
 */
public class GuiProfile extends Application {

    @Override
    public void start(Stage PrimaryStage) throws Exception {

        BorderPane ProfileInfoPane = new BorderPane();
        Scene profileScene = new Scene(ProfileInfoPane, 1920, 1080);
        PrimaryStage.setScene(profileScene);

        //Insets:
        Insets noPaddingInsets = new Insets(0);
        Insets sideBarBtnInsets = new Insets(0, 0, 0, 20);
        Insets profileAdditionalInfoInsets = new Insets(10, 30, 10, 10);
        Insets profileAvailabilityTitlePaneInsets = new Insets(10);
        Insets profileAvailabilityGridPaneInsets = new Insets(0, 10, 5, 10);
        Insets profileAvailabilityPaneInsets = new Insets(0, 20, 0, 0);
        Insets profileInsets = new Insets(10, 30, 30, 30);

        //Shape settings
        Circle editToggleBtnShape = new Circle(50);

        //Buttons:
        findMatch = new Button();
        findMatch.setText("Find Match");
        setStyleButton(findMatch);
        findMatch.setOnAction(e -> findMatchClick());
        findMatch.setOnMouseEntered(e -> setStyleHoverButton(findMatch));
        findMatch.setOnMouseExited(e -> setStyleButtonOriginal(findMatch));

        yourMatches = new Button();
        yourMatches.setText("Your Matches");
        setStyleButton(yourMatches);
        yourMatches.setOnAction(e -> yourMatchesClick());
        yourMatches.setOnMouseEntered(e -> setStyleHoverButton(yourMatches));
        yourMatches.setOnMouseExited(e -> setStyleButtonOriginal(yourMatches));

        chat = new Button();
        chat.setText("Chat");
        setStyleButton(chat);
        chat.setOnAction(e -> chatClick());
        chat.setOnMouseEntered(e -> setStyleHoverButton(chat));
        chat.setOnMouseExited(e -> setStyleButtonOriginal(chat));

        profile = new Button();
        profile.setText("Profile");
        setStyleButton(profile);
        profile.setOnAction(e -> profileClick());
        profile.setOnMouseClicked(e -> PrimaryStage.setScene(profileScene));
        profile.setOnMouseEntered(e -> setStyleHoverButton(profile));
        profile.setOnMouseExited(e -> setStyleButtonOriginal(profile));

        Button editProfileBtn = new Button();
        editProfileBtn.setPadding(sideBarBtnInsets);
        editProfileBtn.setMinSize(200, 50);
        editProfileBtn.setPrefSize(200, 50);
        editProfileBtn.setMaxSize(200, 50);
        editProfileBtn.setText("Edit Profile");
        editProfileBtn.setTextAlignment(TextAlignment.LEFT);
        editProfileBtn.getStyleClass().add("sidebarBtn");

        Button logoutBtn = new Button();
        logoutBtn.setPadding(sideBarBtnInsets);
        logoutBtn.setMinSize(200, 50);
        logoutBtn.setPrefSize(200, 50);
        logoutBtn.setMaxSize(200, 50);
        logoutBtn.setText("Logout");
        logoutBtn.setOnMouseClicked(e -> System.exit(0));
        logoutBtn.getStyleClass().add("sidebarBtn");

        Button uploadPicBtn = new Button();
        uploadPicBtn.setPadding(noPaddingInsets);
        uploadPicBtn.setMinSize(410, 30);
        uploadPicBtn.setPrefSize(410, 30);
        uploadPicBtn.setMaxSize(410, 30);
        uploadPicBtn.setText("Upload picture");
        uploadPicBtn.setId("uploadPicBtn");

        Button editToggleBtn = new Button();
        editToggleBtn.setShape(editToggleBtnShape);
        editToggleBtn.setText("V");

        //TextFields
        TextField tf1 = new TextField("Info goes here");
        tf1.setEditable(false);
        TextField tf2 = new TextField("Info goes here");
        tf2.setEditable(false);
        TextField tf3 = new TextField("Info goes here");
        tf3.setEditable(false);
        TextField tf4 = new TextField("Info goes here");
        tf4.setEditable(false);
        TextField tf5 = new TextField("Info goes here");
        tf5.setEditable(false);
        TextField tf6 = new TextField("Info goes here");
        tf6.setEditable(false);
        TextField tf7 = new TextField("Info goes here");
        tf7.setEditable(false);
        TextField tf8 = new TextField("Info goes here");
        tf8.setEditable(false);
        TextField tf9 = new TextField("Info goes here");
        tf9.setEditable(false);
        TextField tf10 = new TextField("Info goes here");
        tf10.setEditable(false);
        TextField tf11 = new TextField("Info goes here");
        tf11.setEditable(false);
        TextField tf12 = new TextField("Info goes here");
        tf12.setEditable(false);
        TextField tf13 = new TextField("Info goes here");
        tf13.setEditable(false);
        TextField tf14 = new TextField("Info goes here");
        tf14.setEditable(false);
        TextField tf15 = new TextField("Info goes here");
        tf15.setEditable(false);
        TextField tf16 = new TextField("Info goes here");
        tf16.setEditable(false);
        TextField tf17 = new TextField("Info goes here");
        tf17.setEditable(false);
        TextField tf18 = new TextField("Info goes here");
        tf18.setEditable(false);
        TextField tf19 = new TextField("Info goes here");
        tf19.setEditable(false);
        TextField tf20 = new TextField("Info goes here");
        tf20.setEditable(false);
        TextField tf21 = new TextField("Info goes here");
        tf21.setEditable(false);
        TextField tf22 = new TextField("Info goes here");
        tf22.setEditable(false);
        TextField tf23 = new TextField("Info goes here");
        tf23.setEditable(false);

        //Labels
        Label profileInfoLabel = new Label("Personal");
        profileInfoLabel.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoLabel.setMinSize(1250, 60);
        profileInfoLabel.setPrefSize(1250, 60);
        profileInfoLabel.setMaxSize(1250, 60);
        profileInfoLabel.setPadding(sideBarBtnInsets);
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
        Image profilePic = new Image("/images/ProfilePicTestImage.jpg");
        ImageView profilePicView = new ImageView(profilePic);
        profilePicView.setFitHeight(460);
        profilePicView.setFitWidth(410);
        profilePicView.setPreserveRatio(true);
        profilePicView.setId("profilePicView");

        //Stage settings
        PrimaryStage.setTitle("Profile");
        PrimaryStage.setMinHeight(600);
        PrimaryStage.setMinWidth(800);
        PrimaryStage.setMaxHeight(1080);
        PrimaryStage.setMaxWidth(1920);
        PrimaryStage.setResizable(true);
        PrimaryStage.setFullScreen(true);

        //Panes
        ToolBar toolbar = new ToolBar(findMatch, yourMatches, chat, profile);
        toolbar.setStyle("-fx-background-color: lightseagreen;" +
                "-fx-border-color: darkcyan transparent transparent transparent;" +
                "-fx-border-width: 20px 0 0 200px;-fx-padding: 50%, 0, 0, 0;");

        VBox sidebarPane = new VBox();
        sidebarPane.setPadding(noPaddingInsets);
        sidebarPane.setSpacing(0);
        sidebarPane.getChildren().addAll(editProfileBtn, logoutBtn);
        sidebarPane.setAlignment(Pos.TOP_LEFT);
        sidebarPane.setId("sidebar");

        VBox profilePicPane = new VBox();
        profilePicPane.setPadding(noPaddingInsets);
        profilePicPane.setAlignment(Pos.TOP_LEFT);
        profilePicPane.getChildren().addAll(profilePicView, uploadPicBtn);
        profilePicPane.setId("profilePicPane");

        GridPane profileInfoGridPane = new GridPane();
        profileInfoGridPane.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoGridPane.setPadding(sideBarBtnInsets);
        profileInfoGridPane.setMinWidth(1230);
        profileInfoGridPane.setPrefWidth(1230);
        profileInfoGridPane.setMaxWidth(1230);
        profileInfoGridPane.addColumn(0, name, sex, age, dateOfBirth, nationality, languages, email, telephoneNumber, address, password);
        profileInfoGridPane.addColumn(1, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10);
        ColumnConstraints col1Constraints = new ColumnConstraints(400, 400, 400, Priority.NEVER, HPos.LEFT, false);
        col1Constraints.setPercentWidth(33);
        ColumnConstraints col2Constraints = new ColumnConstraints(830, 830, 830, Priority.ALWAYS, HPos.LEFT, true);
        col2Constraints.setPercentWidth(67);
        profileInfoGridPane.getColumnConstraints().addAll(col1Constraints, col2Constraints);
        for (int i = 1; i <= 10; i++) {
            profileInfoGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(30, 30, 30, Priority.NEVER, VPos.CENTER, true);
            profileInfoGridPane.getRowConstraints().addAll(rowConstraints);
        }
        profileInfoGridPane.setId("profileInfoGridPane");

        VBox profileInfoPane = new VBox(0);
        profileInfoPane.setMinWidth(1250);
        profileInfoPane.setPrefWidth(1250);
        profileInfoPane.setMaxWidth(1250);
        profileInfoPane.setPadding(noPaddingInsets);
        profileInfoPane.setAlignment(Pos.TOP_LEFT);
        profileInfoPane.getChildren().addAll(profileInfoLabel, profileInfoGridPane);
        profileInfoPane.setId("profileInfoPane");

        GridPane profileInfoContinuedGridPane = new GridPane();
        profileInfoContinuedGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoContinuedGridPane.setMinWidth(970);
        profileInfoContinuedGridPane.setPrefWidth(970);
        profileInfoContinuedGridPane.setMaxWidth(970);
        profileInfoContinuedGridPane.addColumn(0, university, study, studyYear);
        ColumnConstraints continuedInfoCol1Constraints = new ColumnConstraints(300, 300, 300, Priority.NEVER, HPos.LEFT, false);
        continuedInfoCol1Constraints.setPercentWidth(31);
        profileInfoContinuedGridPane.addColumn(1, tf11, tf12, tf13);
        ColumnConstraints continuedInfoCol2Constraints = new ColumnConstraints(670, 670, 670, Priority.ALWAYS, HPos.LEFT, true);
        continuedInfoCol2Constraints.setPercentWidth(69);
        profileInfoContinuedGridPane.getColumnConstraints().addAll(continuedInfoCol1Constraints, continuedInfoCol2Constraints);
        for (int i = 0; i <= 2; i++) {
            profileInfoContinuedGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(30, 30, 30, Priority.NEVER, VPos.CENTER, true);
            profileInfoContinuedGridPane.getRowConstraints().addAll(rowConstraints);
        }
        profileInfoContinuedGridPane.setId("profileInfoContinuedGridPane");

        HBox profileInfoCoursesLabelPane = new HBox();
        profileInfoCoursesLabelPane.setMinWidth(970);
        profileInfoCoursesLabelPane.setPrefWidth(970);
        profileInfoCoursesLabelPane.setMaxWidth(970);
        profileInfoCoursesLabelPane.setAlignment(Pos.BOTTOM_LEFT);
        profileInfoCoursesLabelPane.getChildren().addAll(courses);
        profileInfoCoursesLabelPane.setId("profileInfoCoursesLabelPane");

        GridPane profileInfoCoursesGridPane = new GridPane();
        profileInfoCoursesGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoCoursesGridPane.setMinWidth(970);
        profileInfoCoursesGridPane.setPrefWidth(970);
        profileInfoCoursesGridPane.setMaxWidth(970);
        profileInfoCoursesGridPane.addColumn(0, findATutor, becomeATutor, findStudyBuddy);
        ColumnConstraints coursesCol1Constraints = new ColumnConstraints(100, 300, 300, Priority.NEVER, HPos.LEFT, true);
        coursesCol1Constraints.setPercentWidth(30);
        profileInfoCoursesGridPane.addColumn(1, tf14, tf15, tf16);
        ColumnConstraints coursesCol2Constrains = new ColumnConstraints(300, 700, 700, Priority.NEVER, HPos.LEFT, true);
        coursesCol2Constrains.setPercentWidth(70);
        profileInfoCoursesGridPane.getColumnConstraints().addAll(continuedInfoCol1Constraints, continuedInfoCol2Constraints);
        for (int i = 0; i <= 2; i++) {
            profileInfoCoursesGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(30, 30, 30, Priority.NEVER, VPos.CENTER, true);
            profileInfoContinuedGridPane.getRowConstraints().addAll(rowConstraints);
        }
        profileInfoCoursesGridPane.setId("profileInfoCoursesGridPane");

        VBox profileAdditionalInfoGridPaneContainerPane = new VBox();
        profileAdditionalInfoGridPaneContainerPane.setSpacing(0);
        profileAdditionalInfoGridPaneContainerPane.setAlignment(Pos.TOP_LEFT);
        profileAdditionalInfoGridPaneContainerPane.setMinSize(1000, 200);
        profileAdditionalInfoGridPaneContainerPane.setPrefSize(1000, 400);
        profileAdditionalInfoGridPaneContainerPane.setMaxSize(1000, 400);
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
        ColumnConstraints availabilityCol1Constraints = new ColumnConstraints(220, 220, 220, Priority.NEVER, HPos.LEFT, true);
        availabilityCol1Constraints.setPercentWidth(34);
        profileAvailabilityGridPane.addColumn(1, tf17, tf18, tf19, tf20, tf21, tf22, tf23);
        ColumnConstraints availabilityCol2Constraints = new ColumnConstraints(420, 420, 420, Priority.ALWAYS, HPos.LEFT, true);
        availabilityCol2Constraints.setPercentWidth(66);
        profileAvailabilityGridPane.getColumnConstraints().addAll(availabilityCol1Constraints, availabilityCol2Constraints);
        for (int i = 0; i < 7; i++) {
            profileAvailabilityGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(30, 30, 30, Priority.NEVER, VPos.CENTER, true);
            profileAvailabilityGridPane.getRowConstraints().addAll(rowConstraints);
        }
        profileAvailabilityGridPane.setId("profileAvailabilityGridPane");

        VBox profileAvailabilityPane = new VBox();
        profileAvailabilityPane.setPadding(profileAvailabilityPaneInsets);
        profileAvailabilityPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityPane.setMinWidth(670);
        profileAvailabilityPane.setPrefWidth(670);
        profileAvailabilityPane.setMaxWidth(670);
        profileAvailabilityPane.getChildren().addAll(profileAvailabilityTitlePane, profileAvailabilityGridPane);
        profileAvailabilityPane.setId("profileAvailabilityPane");

        HBox profileAdditionalInfoPane = new HBox();
        profileAdditionalInfoPane.setPadding(profileAdditionalInfoInsets);
        profileAdditionalInfoPane.setAlignment(Pos.TOP_LEFT);
        profileAdditionalInfoPane.getChildren().addAll(profileAdditionalInfoGridPaneContainerPane, profileAvailabilityPane);
        profileAdditionalInfoPane.setId("profileAdditionalInfoPane");

        FlowPane profile = new FlowPane(Orientation.HORIZONTAL, 20, 20);
        profile.setPadding(profileInsets);
        profile.getChildren().addAll(profilePicPane, profileInfoPane, profileAdditionalInfoPane, editToggleBtn);
        profile.setId("profile");

        ProfileInfoPane.setTop(toolbar);
        ProfileInfoPane.setLeft(sidebarPane);
        ProfileInfoPane.setCenter(profile);

        String styleURL = this.getClass().getResource("css/ProfileStyle.css").toExternalForm();
        profileScene.getStylesheets().add(styleURL);
        try {
            PrimaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Launching GUI");
        launch(args);
    }

    //Variables for topbar
    Button findMatch;
    Button yourMatches;
    Button chat;
    Button profile;

    String styleButton = "-fx-font: 24 light-roboto;-fx-base: lightseagreen;" +
            "-fx-text-fill: white;-fx-focus-color: lightseagreen;" +
            "-fx-faint-focus-color: lightseagreen;-fx-border-width: 50px 1px 10px 1px;" +
            "-fx-border-color: lightseagreen;-fx-inner-border: lightseagreen;-fx-body-color: lightseagreen;";
    String styleActiveButton = "-fx-font: 24 light-roboto;-fx-base: lightseagreen;" +
            "-fx-text-fill: white; -fx-focus-color: lightseagreen;-fx-faint-focus-color: lightseagreen;" +
            "-fx-border-color: lightseagreen lightseagreen palevioletred lightseagreen;" +
            "-fx-inner-border: lightseagreen;-fx-body-color: lightseagreen;" +
            "-fx-border-width: 50px 1px 10px 1px;";
    String styleHoverNonActiveButton = "-fx-font: 24 light-roboto;-fx-base: lightseagreen;" +
            "-fx-text-fill: black;-fx-focus-color: lightseagreen;-fx-faint-focus-color: lightseagreen; " +
            "-fx-border-color: lightseagreen;-fx-inner-border: lightseagreen;-fx-body-color: lightseagreen;" +
            "-fx-border-width: 50px 1px 10px 1px;";
    String styleHoverActiveButton = "-fx-font: 24 light-roboto; -fx-base: lightseagreen;" +
            "-fx-text-fill: black;-fx-focus-color: lightseagreen;-fx-faint-focus-color: lightseagreen; " +
            "-fx-border-color: lightseagreen lightseagreen palevioletred lightseagreen;" +
            "-fx-inner-border: lightseagreen;-fx-body-color: lightseagreen;" +
            "-fx-border-width: 50px 1px 10px 1px;";

    // Style of a button when it isn't active
    public void setStyleButton(Button button) {
        button.setStyle(styleButton);

    }

    // Style of a button when it is active
    public void setStyleActiveButton(Button button) {
        button.setStyle(styleActiveButton);
    }

    // Style when mouse hovers over button
    public void setStyleHoverButton(Button button) {
        if (button.getStyle().equals(styleActiveButton)) {
            setStyleHoverActiveButton(button);
        } else {
            setStyleHoverNonActiveButton(button);
        }
    }

    // Style when mouse goes over non-active button
    public void setStyleHoverNonActiveButton(Button button) {
        button.setStyle(styleHoverNonActiveButton);
    }

    // Style when mouse goes over active button
    public void setStyleHoverActiveButton(Button button) {
        button.setStyle(styleHoverActiveButton);
    }

    // Remember style of button before hover
    public void setStyleButtonOriginal(Button button) {
        if (button.getStyle().equals(styleActiveButton) ||
                button.getStyle().equals(styleHoverActiveButton)) {
            setStyleActiveButton(button);
        } else {
            setStyleButton(button);
        }
    }

    // events that happen when you click on a button in the toolbar
    public void findMatchClick() {
        setStyleActiveButton(findMatch);
        setStyleButton(yourMatches);
        setStyleButton(chat);
        setStyleButton(profile);

    }

    public void yourMatchesClick() {
        setStyleActiveButton(yourMatches);
        setStyleButton(findMatch);
        setStyleButton(chat);
        setStyleButton(profile);
    }

    public void chatClick() {
        setStyleActiveButton(chat);
        setStyleButton(findMatch);
        setStyleButton(yourMatches);
        setStyleButton(profile);
    }

    public void profileClick() {
        setStyleActiveButton(profile);
        setStyleButton(findMatch);
        setStyleButton(chat);
        setStyleButton(yourMatches);
    }
}