import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiTopBar extends Application {
    // variables
    Button findMatch;
    Button yourMatches;
    Button chat;
    Button profile;

    String styleButton = "-fx-font: 24 light-roboto;-fx-base: #26A69A;" +
            "-fx-text-fill: #FFFFFF;-fx-focus-color: #26A69A;" +
            "-fx-faint-focus-color: #26A69A;-fx-border-width: 50px 1px 10px 1px;" +
            "-fx-border-color: #26A69A;-fx-inner-border: #26A69A;-fx-body-color: #26A69A;";
    String styleActiveButton = "-fx-font: 24 light-roboto;-fx-base: #26A69A;" +
            "-fx-text-fill: #FFFFFF; -fx-focus-color: #26A69A;-fx-faint-focus-color: #26A69A;" +
            "-fx-border-color: #26A69A #26A69A #F06292 #26A69A;" +
            "-fx-inner-border: #26A69A;-fx-body-color: #26A69A;" +
            "-fx-border-width: 50px 1px 10px 1px;";
    String styleHoverNonActiveButton = "-fx-font: 24 light-roboto;-fx-base: #26A69A;" +
            "-fx-text-fill: #000000;-fx-focus-color: #26A69A;-fx-faint-focus-color: #26A69A; " +
            "-fx-border-color: #26A69A;-fx-inner-border: #26A69A;-fx-body-color: #26A69A;" +
            "-fx-border-width: 50px 1px 10px 1px;";
    String styleHoverActiveButton = "-fx-font: 24 light-roboto; -fx-base: #26A69A;" +
            "-fx-text-fill: #000000;-fx-focus-color: #26A69A;-fx-faint-focus-color: #26A69A; " +
            "-fx-border-color: #26A69A #26A69A #F06292 #26A69A;" +
            "-fx-inner-border: #26A69A;-fx-body-color: #26A69A;" +
            "-fx-border-width: 50px 1px 10px 1px;";



    @Override
    public void start(Stage primaryStage) throws Exception{
        // create buttons
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

        profile = new Button ();
        profile.setText("Profile");
        setStyleButton(profile);
        profile.setOnAction(e -> profileClick());
        profile.setOnMouseEntered(e -> setStyleHoverButton(profile));
        profile.setOnMouseExited(e -> setStyleButtonOriginal(profile));

        // Add buttons to a layout pane in a toolbar
        BorderPane pane = new BorderPane();
        ToolBar toolbar = new ToolBar(findMatch, yourMatches, chat, profile);
        toolbar.setStyle("-fx-background-color: #26A69A;" +
                "-fx-border-color: #00796B transparent transparent transparent;" +
                "-fx-border-width: 20px 0 0 200px;-fx-padding: 50%, 0, 0, 0;");
        pane.setTop(toolbar);

        // Add the lay-out pane to scene
        Scene scene = new Scene(pane, 1024, 768);

        // Finalize and show stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Two Brains");
        primaryStage.show();

    }

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

    public static void main(String[] args) {
        System.out.println("Launching JavaFX");
        launch(args);
        System.out.println("Finished");
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
