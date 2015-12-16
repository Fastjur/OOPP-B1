package gui.views;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

/**
 * Created by Emma on 16-12-15.
 */
public class GuiTopBar extends ToolBar {
    // variables
    private Button findMatch;
    private Button yourMatches;
    private Button chat;
    private Button profile;

    public GuiTopBar(){
        super();
        buttons();
        super.getItems().addAll(findMatch, yourMatches, chat, profile);
        super.setId("toolbar");
    }

    public void buttons() {
        findMatch = new Button();
        findMatch.setText("Find Match");
        findMatch.setId("findMatch");
        findMatch.setOnAction(e -> findMatchClick());

        yourMatches = new Button();
        yourMatches.setText("Your Matches");
        yourMatches.setId("yourMatches");
        yourMatches.setOnAction(e -> yourMatchesClick());

        chat = new Button();
        chat.setText("Chat");
        chat.setId("chat");
        chat.setOnAction(e -> chatClick());

        profile = new Button ();
        profile.setText("Profile");
        profile.setId("profile");
        profile.setOnAction(e -> profileClick());
    }

    // Events
    public void findMatchClick() {
        findMatch.setId("findMatchActive");
        yourMatches.setId("yourMatches");
        chat.setId("chat");
        profile.setId("profile");

    }

    public void yourMatchesClick() {
        yourMatches.setId("yourMatchesActive");
        findMatch.setId("findMatch");
        chat.setId("chat");
        profile.setId("profile");

    }

    public void chatClick() {
        chat.setId("chatActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        profile.setId("profile");

    }

    public void profileClick() {
        profile.setId("profileActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        chat.setId("chat");

    }
}