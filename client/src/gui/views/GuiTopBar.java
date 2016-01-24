package gui.views;
import communication.Backend;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import java.io.IOException;

/**
 * Created by Emma on 16-12-15.
 */
public class GuiTopBar extends ToolBar {
    // variables

    private Button findMatch;
    private Button yourMatches;
    private Button chat;
    private Button profileBtn;
    private Button logOut;

    public GuiTopBar(){
        super();
        buttons();
        super.getItems().addAll(findMatch, yourMatches, chat, profileBtn, logOut);
        super.setId("toolbar");
    }

    public void buttons() {
        findMatch = new Button();
        findMatch.setText("Find Match");
        findMatch.setId("findMatchActive");

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

        logOut = new Button();
        logOut.setText("Log Out");
        logOut.setId("profileBtn");
        logOut.setOnAction(e -> {
            try {
                Backend.closeConnection();
                System.out.println("Successfully closed connection to server");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            //Server.main(new String[0]);
            Backend.connectToServer();
            GUILauncher.GUI.setCenter(GUILauncher.login);
            GUILauncher.GUI.setLeft(null);
            GUILauncher.GUI.setTop(null);
        });

    }

    public void setChatButtonActive(){
        chat.setId("chatActive");
        findMatch.setId("findMatch");
        yourMatches.setId("yourMatches");
        profileBtn.setId("profileBtn");
    }
}