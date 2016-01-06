package gui.views;

import communication.Backend;
import communication.IMessageListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import shared.Response;

/**
 * Constructor for the login and register page of the application
 * Author: Sebastiaan Hester
 */
public class GuiLoginConstructor extends BorderPane implements IMessageListener {

    private final Label lblMessage = new Label();
    BorderPane bp;
    BorderPane bp2;

    public GuiLoginConstructor(){
        super();

        //BorderPane
        //Login
        bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Register
        bp2 = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        //Login
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 30));

        //Register
        HBox hb2 = new HBox();
        hb2.setPadding(new Insets(20, 20, 20, 30));

        //Adding GridPane
        //Login
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(60, 60, 60, 60));
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        //Register
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(60, 60, 60, 60));
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);

        //Implementing Nodes for GridPane
        //Login
        final TextField txtUserName = new TextField();
        final PasswordField pf = new PasswordField();
        Button btnLoginTop = new Button("Login");
        Button btnLoginBot = new Button("Login");
        Button btnRegister = new Button("Register");

        //final Label lblMessage = new Label();
        final Label loginLabel = new Label("Login");

        //Register
        final TextField txtUserName2 = new TextField();
        final PasswordField pf2 = new PasswordField();
        final PasswordField pf3 = new PasswordField();
        Button btnLoginReg = new Button("Login");
        Button btnRegisterTop = new Button("Register");
        Button btnRegisterBot = new Button("Register");

        //Adding Nodes to GridPane layout
        //Login
        gridPane.add(txtUserName, 2, 2, 2, 1);
        gridPane.add(pf, 2, 3, 2, 1);
        gridPane.add(btnLoginTop, 2,0, 2,1);
        gridPane.add(loginLabel, 2,1);
        gridPane.add(btnLoginBot, 2, 4);
        gridPane.add(btnRegister, 4,0,2,1 );
        gridPane.add(lblMessage, 2, 5, 2,1);

        //Register
        gridPane2.add(txtUserName2, 2, 1, 2, 1);
        gridPane2.add(pf2, 2, 2, 2, 1);
        gridPane2.add(pf3, 2, 3, 2,1);
        gridPane2.add(btnRegisterTop, 3,0);
        gridPane2.add(btnLoginReg, 2,0);
        gridPane2.add(btnRegisterBot, 2,4, 2, 1);

        //DropShadow effect
        //Login
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //Register
        DropShadow dropShadow2 = new DropShadow();
        dropShadow2.setOffsetX(5);
        dropShadow2.setOffsetY(5);

        //Adding text and DropShadow effect to it
        Text text = new Text("MindMatch");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);

        Text text2 = new Text("MindMatch");
        text2.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text2.setEffect(dropShadow2);

        //Adding text to HBox
        hb.getChildren().add(text);
        hb2.getChildren().add(text2);

        //Add ID's to Nodes
        bp.setId("bp");
        btnLoginTop.setId("BLT");
        btnRegister.setId("BR");
        gridPane.setId("root");
        btnLoginBot.setId("btnLogin");
        text.setId("text");
        text2.setId("text2");
        loginLabel.setId("loginLabel");

        //Action for btnLogin
        btnLoginBot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String checkUser = txtUserName.getText(),
                        checkPw = pf.getText();
                Backend.login(checkUser, checkPw);
                txtUserName.setText("");
                pf.setText("");
            }
        });

        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);

        bp2.setTop(hb2);
        bp2.setCenter(gridPane2);

        //MouseHover
        mouseHover(btnLoginBot, GUILauncher.GUIScene);
        mouseHover(btnLoginTop, GUILauncher.GUIScene);
        mouseHover(btnRegister, GUILauncher.GUIScene);
        mouseHover(btnLoginReg, GUILauncher.GUIScene);
        mouseHover(btnRegisterTop, GUILauncher.GUIScene);
        mouseHover(btnRegisterBot, GUILauncher.GUIScene);

        btnLoginBot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GUILauncher.GUIScene.setCursor(Cursor.WAIT);
            }
        });

        //Prompt Text
        txtUserName.setPromptText("Username");
        pf.setPromptText("Password");
        txtUserName2.setPromptText("Username");
        pf2.setPromptText("Password");
        pf3.setPromptText("Retype Password");
        super.setCenter(bp);
        btnRegister.setOnMouseClicked(e -> GUILauncher.switchToRegister());
        btnLoginReg.setOnMouseClicked(e -> GUILauncher.switchToLogin());

        //Initialize Backend
        Backend.serverAddress = "::1";
        Backend.serverPort = 8372;
        Backend.connectToServer();
        Backend.addMessageListener(this);

        if (!Backend.isConnected()) {
            Platform.runLater(new Runnable() {
                public void run() {
                    lblMessage.setText("Could not connect to server!");
                    lblMessage.setTextFill(Color.RED);
                }
            });
        }
    }

    public static void mouseHover(Button btn, Scene scene) {
        btn.setOnMouseEntered(event -> scene.setCursor(Cursor.HAND));
        btn.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
        });
    }

    @Override
    public void onIncomingResponse(Response response) {
        Platform.runLater(new Runnable(){
            public void run() {
                System.out.println(response);
                if (response.responseTo.equals("login")) {
                    if (response.errorCode == 0) {
                        lblMessage.setText(response.errorMessage);
                        lblMessage.setTextFill(Color.GREEN);
                    } else {
                        lblMessage.setText(response.errorMessage);
                        lblMessage.setTextFill(Color.RED);
                    }
                }
            }
        });
    }
}
