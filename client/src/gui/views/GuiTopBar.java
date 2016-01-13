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
    private Button profileBtn;

    public GuiTopBar(){
        super();
        buttons();
        super.getItems().addAll(findMatch, yourMatches, chat, profileBtn);
        super.setId("toolbar");
    }

    public void buttons() {
        findMatch = new Button();
        findMatch.setText("Find Match");
        findMatch.setId("findMatch");
        findMatch.setOnAction(e -> GUILauncher.findMatchClick(findMatch, yourMatches, chat, profileBtn));

        yourMatches = new Button();
        yourMatches.setText("Your Matches");
        yourMatches.setId("yourMatches");
        yourMatches.setOnAction(e -> GUILauncher.yourMatchesClick(findMatch, yourMatches, chat, profileBtn));

        chat = new Button();
        chat.setText("Chat");
        chat.setId("chat");
        chat.setOnAction(e -> GUILauncher.chatClick(findMatch, yourMatches, chat, profileBtn));

        profileBtn = new Button();
        profileBtn.setText("Profile");
        profileBtn.setId("profileBtn");
        profileBtn.setOnAction(e -> GUILauncher.profileClick(findMatch, yourMatches, chat, profileBtn));
    }
}