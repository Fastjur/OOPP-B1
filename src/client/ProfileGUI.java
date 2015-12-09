import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public class ProfileGUI extends Application {

    @Override
    public void start(Stage PrimaryStage) throws Exception {

        BorderPane ProfileInfoPane = new BorderPane();
        Scene scene = new Scene(ProfileInfoPane, 1920, 1080);
        PrimaryStage.setScene(scene);

        //Insets:
        Insets noPaddingInsets = new Insets(0);
        //Insets noPaddingInsets = new Insets(0, 960, 20, 960); -> exitBarInsets
        //Insets noPaddingInsets = new Insets(10, 15, 10, 15); -> exitBtnInsets
        //Insets noPaddingInsets = new Insets(95, 250, 65, 1730); -> topButtonBarInsets
        Insets topBarBtnInsets = new Insets(40, 20, 0, 20);
        //Insets noPaddingInsets = new Insets(0, 960, 180, 960); -> topBarInsets
        //Insets noPaddingInsets = new Insets(25, 100, 25, 100); -> sideBarBtnInsets
        Insets sideBarBtnInsets = new Insets(0, 0, 0, 20);
        //Insets noPaddingInsets = new Insets(0, 100, 880, 100); -> sideBarInsets
        //Insets noPaddingInsets = new Insets(15, 205, 15, 205); -> uploadPicBtnInsets
        //Insets noPaddingInsets = new Insets(230, 205, 230, 205); -> profilePicPaneInsets
        //Insets noPaddingInsets = new Insets(25,645,25,645); -> profileInfoTitlePaneInsets
        Insets profileInfoTitleInsets = new Insets(20, 0, 10, 10);
        Insets profileInfoInsets = new Insets(15, 20, 20, 10);
        Insets profileAdditionalInfoInsets = new Insets(10);
        Insets profileAvailabilityTitlePaneInsets = new Insets(10, 10, 0, 10);
        Insets profileAvailabilityGridPaneInsets = new Insets(0, 10, 5, 10);
        Insets profileAvailabilityPaneInsets = new Insets(0, 0, 0, 20);
        //Insets editToggleBtnInsets = new Insets(20,20,20,20);                     //Might not be needed
        Insets profileInsets = new Insets(10, 30, 30, 30);

        //Shape settings
        Circle editToggleBtnShape = new Circle(20);

        //Buttons:
        Button findMatchBtn = new Button();
        findMatchBtn.setPadding(topBarBtnInsets);
        findMatchBtn.setMinSize(80, 80);
        findMatchBtn.setPrefSize(80, 80);
        findMatchBtn.setMaxSize(200, 80);
        findMatchBtn.setText("Find Match");
        findMatchBtn.setId("findMatchBtn");
        Button yourMatchesBtn = new Button();
        yourMatchesBtn.setPadding(topBarBtnInsets);
        yourMatchesBtn.setMinSize(80, 80);
        yourMatchesBtn.setPrefSize(80, 80);
        yourMatchesBtn.setMaxSize(200, 80);
        yourMatchesBtn.setText("Your Matches");
        Button chatBtn = new Button();
        chatBtn.setPadding(topBarBtnInsets);
        chatBtn.setMinSize(80, 80);
        chatBtn.setPrefSize(80, 80);
        chatBtn.setMaxSize(200, 80);
        chatBtn.setText("Chat");
        Button profileBtn = new Button();
        profileBtn.setPadding(topBarBtnInsets);
        profileBtn.setMinSize(80, 80);
        profileBtn.setPrefSize(80, 80);
        profileBtn.setMaxSize(200, 80);
        profileBtn.setText("Profile");
        Button editProfileBtn = new Button();
        editProfileBtn.setPadding(sideBarBtnInsets);
        editProfileBtn.setMinSize(200, 50);
        editProfileBtn.setPrefSize(200, 50);
        editProfileBtn.setMaxSize(200, 50);
        editProfileBtn.setText("Edit Profile");
        editProfileBtn.setTextAlignment(TextAlignment.LEFT);
        Button logoutBtn = new Button();
        logoutBtn.setPadding(sideBarBtnInsets);
        logoutBtn.setMinSize(200, 50);
        logoutBtn.setPrefSize(200, 50);
        logoutBtn.setMaxSize(200, 50);
        logoutBtn.setText("Logout");
        Button exitBtn = new Button();
        exitBtn.setPadding(noPaddingInsets);
        exitBtn.setMinSize(40, 20);
        exitBtn.setPrefSize(40, 20);
        exitBtn.setMaxSize(40, 20);
        exitBtn.setText("X");
        exitBtn.setOnMouseClicked(e -> System.exit(0));
        Button uploadPicBtn = new Button();
        uploadPicBtn.setPadding(noPaddingInsets);
        uploadPicBtn.setMinSize(410, 30);
        uploadPicBtn.setPrefSize(410, 30);
        uploadPicBtn.setMaxSize(410, 30);
        uploadPicBtn.setText("Upload picture");
        Button editToggleBtn = new Button();
        editToggleBtn.setShape(editToggleBtnShape);
        editToggleBtn.setText("V");

        //TextFields
        TextField test = new TextField("Info goes here");
        test.setEditable(false);

        //Labels
        Label profileInfoLabel = new Label("Personal");
        profileInfoLabel.setAlignment(Pos.BASELINE_LEFT);
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
        Label university = new Label("University");
        Label study = new Label("Study:");
        Label studyYear = new Label("Study Year:");
        Label courses = new Label("Courses");
        Label findATutor = new Label("Find a tutor:");
        Label becomeATutor = new Label("Become a tutor:");
        Label findStudyBuddy = new Label("Find a study buddy:");
        Label availability = new Label("Availability");
        Label monday = new Label("Monday:");
        Label tuesday = new Label("Tuesday:");
        Label wednesday = new Label("Wednesday:");
        Label thursday = new Label("Thursday:");
        Label friday = new Label("Friday:");
        Label saturday = new Label("Saturday:");
        Label sunday = new Label("Sunday:");

        //Image settings

        Image profilePic = new Image("ProfilePicTestImage.jpg");
        ImageView profilePicView = new ImageView(profilePic);
        profilePicView.setFitHeight(460);
        profilePicView.setFitWidth(410);
        profilePicView.setPreserveRatio(true);
        /*
        VBox randBox = new VBox();
        randBox.setMinSize(0,0);
        randBox.setPrefSize(410,460);
        randBox.setMaxSize(410,460);
        */

        //Stage settings
        PrimaryStage.setTitle("Sidebar-only version 1.0");
        PrimaryStage.setMinHeight(600);
        PrimaryStage.setMinWidth(800);
        PrimaryStage.setMaxHeight(1080);
        PrimaryStage.setMaxWidth(1920);
        PrimaryStage.setFullScreen(true);

        //Panes
        HBox exitBarPane = new HBox();
        exitBarPane.setPadding(noPaddingInsets);
        exitBarPane.getChildren().addAll(exitBtn);
        exitBarPane.setAlignment(Pos.BASELINE_RIGHT);

        HBox topBarButtonsPane = new HBox();
        topBarButtonsPane.setPadding(noPaddingInsets);
        topBarButtonsPane.getChildren().addAll(findMatchBtn, yourMatchesBtn, chatBtn, profileBtn);
        topBarButtonsPane.setAlignment(Pos.BASELINE_CENTER);

        VBox topBarPane = new VBox();
        topBarPane.setPadding(noPaddingInsets);
        topBarPane.setSpacing(0);
        topBarPane.setAlignment(Pos.TOP_LEFT);
        topBarPane.getChildren().addAll(exitBarPane, topBarButtonsPane);

        VBox sidebarPane = new VBox();
        sidebarPane.setPadding(noPaddingInsets);
        sidebarPane.setSpacing(0);
        sidebarPane.getChildren().addAll(editProfileBtn, logoutBtn);
        sidebarPane.setAlignment(Pos.TOP_LEFT);

        VBox profilePicPane = new VBox();
        profilePicPane.setPadding(noPaddingInsets);
        profilePicPane.setAlignment(Pos.TOP_LEFT);
        profilePicPane.getChildren().addAll(profilePicView, uploadPicBtn);

        /*
        HBox profileInfoTitlePane = new HBox();
        profileInfoTitlePane.setPadding(profileInfoTitleInsets);
        profileInfoTitlePane.getChildren().addAll(profileInfoLabel);
        */

        GridPane profileInfoGridPane = new GridPane();
        profileInfoGridPane.setAlignment(Pos.CENTER_LEFT);
        profileInfoGridPane.addColumn(1, name, sex, age, dateOfBirth, nationality, languages, email, telephoneNumber, address, password);
        profileInfoGridPane.addColumn(2, new Label(""), new Label(""), new Label(""), new Label(""), new Label(""), new Label(""),new Label(""), new Label(""), new Label(""), new Label(""), new Label(""));

        ColumnConstraints col1Constraints = new ColumnConstraints(400, 400, 400, Priority.NEVER, HPos.LEFT, true);
        col1Constraints.setPercentWidth(31);
        ColumnConstraints col2Constraints = new ColumnConstraints(890, 890, 890, Priority.ALWAYS, HPos.LEFT, true);
        col2Constraints.setPercentWidth(69);
        profileInfoGridPane.getColumnConstraints().addAll(col1Constraints, col2Constraints);
        for (int i = 1; i <= 10; i++) {
            profileInfoGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(20, 20, 20, Priority.NEVER, VPos.CENTER, false);
            profileInfoGridPane.getRowConstraints().addAll(rowConstraints);
        }

        VBox profileInfoPane = new VBox();
        profileInfoPane.setPadding(profileInfoInsets);
        profileInfoPane.setAlignment(Pos.TOP_LEFT);
        profileInfoPane.getChildren().addAll(profileInfoLabel, profileInfoGridPane);

        GridPane profileInfoContinuedGridPane = new GridPane();
        profileInfoContinuedGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoContinuedGridPane.addColumn(1, university, study, studyYear);
        ColumnConstraints continuedInfoCol1Constraints = new ColumnConstraints(300, 300, 300, Priority.NEVER, HPos.LEFT, true);
        continuedInfoCol1Constraints.setPercentWidth(30);
        profileInfoContinuedGridPane.addColumn(2, new Label(""),new Label(""),new Label(""));
        ColumnConstraints continuedInfoCol2Constraints = new ColumnConstraints(700, 700, 700, Priority.ALWAYS, HPos.LEFT, true);
        continuedInfoCol2Constraints.setPercentWidth(70);
        for (int i = 0; i <= 3; i++) {
            profileInfoContinuedGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(20, 20, 20, Priority.NEVER, VPos.CENTER, false);
            profileInfoContinuedGridPane.getRowConstraints().addAll(rowConstraints);
        }

        HBox profileInfoCoursesLabelPane = new HBox();
        profileInfoCoursesLabelPane.setMinSize(1000, 40);
        profileInfoCoursesLabelPane.setPrefSize(1000, 40);
        profileInfoCoursesLabelPane.setMaxSize(1000, 40);
        profileInfoCoursesLabelPane.setAlignment(Pos.BASELINE_LEFT);
        profileInfoCoursesLabelPane.getChildren().addAll(courses);


        GridPane profileInfoCoursesGridPane = new GridPane();
        profileInfoCoursesGridPane.setAlignment(Pos.TOP_LEFT);
        profileInfoCoursesGridPane.addColumn(1, findATutor, becomeATutor, findStudyBuddy);
        ColumnConstraints coursesCol1Constraints = new ColumnConstraints(300, 300, 300, Priority.NEVER, HPos.LEFT, true);
        coursesCol1Constraints.setPercentWidth(30);
        profileInfoCoursesGridPane.addColumn(2, new Label(""),new Label(""),new Label(""));
        ColumnConstraints coursesCol2Constrains = new ColumnConstraints(700, 700, 700, Priority.ALWAYS, HPos.LEFT, true);
        coursesCol2Constrains.setPercentWidth(70);
        for (int i = 0; i <= 3; i++) {
            profileInfoCoursesGridPane.addRow(i);
            RowConstraints rowConstraints = new RowConstraints(20, 20, 20, Priority.NEVER, VPos.CENTER, false);
            profileInfoContinuedGridPane.getRowConstraints().addAll(rowConstraints);
        }

        VBox profileAdditionalInfoGridPaneContainerPane = new VBox();
        profileAdditionalInfoGridPaneContainerPane.setAlignment(Pos.TOP_LEFT);
        profileAdditionalInfoGridPaneContainerPane.getChildren().addAll(profileInfoContinuedGridPane, profileInfoCoursesGridPane);

        HBox profileAvailabilityTitlePane = new HBox();
        profileAvailabilityTitlePane.setPadding(profileAvailabilityTitlePaneInsets);
        profileAvailabilityTitlePane.getChildren().addAll(availability);

        GridPane profileAvailabilityGridPane = new GridPane();
        profileAvailabilityGridPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityGridPane.setPadding(profileAvailabilityGridPaneInsets);
        profileAvailabilityGridPane.addColumn(1, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        ColumnConstraints availabilityCol1Constraints = new ColumnConstraints(150, 150, 150, Priority.NEVER, HPos.LEFT, true);
        availabilityCol1Constraints.setPercentWidth(21);
        ColumnConstraints availabilityCol2Constraints = new ColumnConstraints(550, 550, 550, Priority.ALWAYS, HPos.LEFT, true);
        availabilityCol2Constraints.setPercentWidth(79);

        VBox profileAvailabilityPane = new VBox();
        profileAvailabilityPane.setPadding(profileAvailabilityPaneInsets);
        profileAvailabilityPane.setAlignment(Pos.TOP_LEFT);
        profileAvailabilityPane.getChildren().addAll(profileAvailabilityTitlePane, profileAvailabilityGridPane);

        HBox profileAdditionalInfoPane = new HBox();
        profileAdditionalInfoPane.setPadding(profileAdditionalInfoInsets);
        profileAdditionalInfoPane.setAlignment(Pos.TOP_LEFT);
        profileAdditionalInfoPane.getChildren().addAll(profileAdditionalInfoGridPaneContainerPane, profileAvailabilityPane);

        FlowPane profile = new FlowPane(Orientation.HORIZONTAL, 20, 20);
        profile.setPadding(profileInsets);
        profile.getChildren().addAll(profilePicPane, profileInfoPane, profileAdditionalInfoPane, editToggleBtn);

        ProfileInfoPane.setTop(topBarPane);
        ProfileInfoPane.setLeft(sidebarPane);
        ProfileInfoPane.setCenter(profile);

        try {
            PrimaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
