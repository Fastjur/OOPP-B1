package gui.views;

import communication.Backend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Constructor for the login and register page of the application
 * Author: Sebastiaan Hester
 */
public class GuiLoginConstructor extends BorderPane {

    private final Label loginMessage = new Label(),
                        registerMessage = new Label(),
                        resetMessage = new Label(),
                        resetMessage2 = new Label();
    protected BorderPane bp;
    protected BorderPane bp2;
    protected BorderPane bp3;

    public GuiLoginConstructor() {
        super();

        //BorderPane
        //Login
        bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Register
        bp2 = new BorderPane();
        bp2.setPadding(new Insets(10, 50, 50, 50));

        bp3 = new BorderPane();
        bp3.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        //Login
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 30));

        //Register
        HBox hb2 = new HBox();
        hb2.setPadding(new Insets(20, 20, 20, 30));

        //Reset Password
        HBox hb3 = new HBox();
        hb3.setPadding(new Insets(20, 20, 20, 30));


        //Adding GridPane
        //Login
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setMaxWidth(400);
        gridPane.setMaxHeight(300);
        hb.setId("hb");

        //Register
        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);
        gridPane2.setMaxWidth(400);
        gridPane2.setMaxHeight(300);
        hb2.setId("hb2");

        //Reset Password
        GridPane gridPane3 = new GridPane();
        gridPane3.setHgap(10);
        gridPane3.setVgap(5);
        gridPane3.setMaxWidth(400);
        gridPane3.setMaxHeight(300);
        hb3.setId("hb3");

        //Implementing Nodes for GridPane
        //Login
        final TextField txtUserName = new TextField();
        final PasswordField pf = new PasswordField();

        //TODO Remove, this is solely for ease of debugging
        txtUserName.setText("sinterklaas@sintmail.nl");
        pf.setText("Pepernoten01");

        Button btnLoginTop = new Button("Login");
        Button btnLoginBot = new Button("Login");
        Button btnRegister = new Button("Register");
        Button btnResetLog = new Button("Reset");

        //Labels
        final Label loginLabel = new Label("Login");

        //Register
        final TextField txtUserName2 = new TextField();
        final PasswordField pf2 = new PasswordField();
        final PasswordField pf3 = new PasswordField();
        Button btnLoginReg = new Button("Login");
        Button btnRegisterTop = new Button("Register");
        Button btnRegisterBot = new Button("Register");
        final Label registerLabel = new Label("Register");
        Button btnResetReg = new Button("Reset");

        //Reset Password
        final Label resetPasswordLabel = new Label("Reset Password");
        final TextField txtUserName3 = new TextField();
        final TextField txtUserName4 = new TextField();
        Button btnLoginRes = new Button("Login");
        Button btnRegisterRes = new Button("Register");
        Button btnResetRes = new Button("Reset");
        Button btnResetBot = new Button("Reset");

        //Adding Nodes to GridPane layout
        //Login
        gridPane.add(btnLoginTop, 2, 0);
        gridPane.add(btnRegister, 3, 0);
        gridPane.add(btnResetLog, 4, 0);
        gridPane.add(loginLabel, 2, 1, 3, 1);
        gridPane.add(txtUserName, 2, 2, 3, 1);
        gridPane.add(pf, 2, 3, 3, 1);
        gridPane.add(btnLoginBot, 2, 4, 3, 1);
        gridPane.add(loginMessage, 2, 5, 3, 1);

        //Register
        gridPane2.add(btnLoginReg, 2, 0);
        gridPane2.add(btnRegisterTop, 3, 0);
        gridPane2.add(btnResetReg, 4, 0);
        gridPane2.add(registerLabel, 2, 1, 3, 1);
        gridPane2.add(txtUserName2, 2, 2, 3, 1);
        gridPane2.add(pf2, 2, 3, 3, 1);
        gridPane2.add(pf3, 2, 4, 3, 1);
        gridPane2.add(btnRegisterBot, 2, 5, 3, 1);
        gridPane2.add(registerMessage, 2, 6, 3, 1);

        //Reset Password
        gridPane3.add(btnLoginRes, 2, 0);
        gridPane3.add(btnRegisterRes, 3, 0);
        gridPane3.add(btnResetRes, 4, 0);
        gridPane3.add(resetPasswordLabel, 2, 1, 3, 1);
        gridPane3.add(txtUserName3, 2, 2, 3, 1);
        gridPane3.add(txtUserName4, 2, 3, 3, 1);
        gridPane3.add(btnResetBot, 2, 4, 3, 1);
        gridPane3.add(resetMessage, 2, 5, 3, 1);
        gridPane3.add(resetMessage2, 2, 6, 3, 1);


        //Adding text
        Text text = new Text("MindMatch");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        hb.setAlignment(Pos.CENTER_LEFT);

        //Register
        Text text2 = new Text("MindMatch");
        text2.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        hb.setAlignment(Pos.CENTER_LEFT);

        //Reset
        Text text3 = new Text("MindMatch");
        text3.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        hb.setAlignment(Pos.CENTER_LEFT);

        //Adding text to HBox
        hb.getChildren().add(text);
        hb2.getChildren().add(text2);
        hb3.getChildren().add(text3);

        //Adding Pic to Vbox
        //Login
        VBox vb = new VBox();
        ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/MM Logo-v2.png")));
        vb.getChildren().add(iv);

        //Register
        VBox vb2 = new VBox();
        ImageView iv2 = new ImageView(new Image(getClass().getResourceAsStream("/MM Logo-v2.png")));
        vb2.getChildren().add(iv2);

        //Reset
        VBox vb3 = new VBox();
        ImageView iv3 = new ImageView(new Image(getClass().getResourceAsStream("/MM Logo-v3.png")));
        vb3.getChildren().add(iv3);

        //Add ID's to Nodes
        //Login
        gridPane.setId("root");
        bp.setId("bp");
        btnLoginTop.setId("BLT");
        btnRegister.setId("BR");
        gridPane.setId("root");
        btnLoginBot.setId("btnLogin");
        text.setId("text");
        text2.setId("text2");
        loginLabel.setId("loginLabel");
        btnResetLog.setId("resLog");

        //Register
        gridPane2.setId("root2");
        bp2.setId("bp2");
        btnLoginReg.setId("BLR");
        btnRegisterTop.setId("BRT");
        btnRegisterBot.setId("BRB");
        registerLabel.setId("registerLabel");
        btnResetReg.setId("resReg");

        //Reset
        gridPane3.setId("root3");
        bp3.setId("bp3");
        btnLoginRes.setId("BLRES");
        btnRegisterRes.setId("BRR");
        btnResetRes.setId("BRR2");
        btnResetBot.setId("BRR3");
        resetPasswordLabel.setId("resLabel");
        text3.setId("text3");

        //Login
        bp.setTop(hb);
        bp.setCenter(gridPane);
        bp.setLeft(vb);
        vb.setTranslateY(300);
        vb.setTranslateX(130);

        //Register
        bp2.setTop(hb2);
        bp2.setCenter(gridPane2);
        bp2.setLeft(vb2);
        vb2.setTranslateY(300);
        vb2.setTranslateX(130);

        //Reset
        bp3.setTop(hb3);
        bp3.setCenter(gridPane3);
        bp3.setLeft(vb3);
        vb3.setTranslateY(300);
        vb3.setTranslateX(130);

        //MouseHover
        mouseHover(btnLoginBot, GUILauncher.GUIScene);
        mouseHover(btnLoginTop, GUILauncher.GUIScene);
        mouseHover(btnRegister, GUILauncher.GUIScene);
        mouseHover(btnLoginReg, GUILauncher.GUIScene);
        mouseHover(btnRegisterTop, GUILauncher.GUIScene);
        mouseHover(btnRegisterBot, GUILauncher.GUIScene);
        mouseHover(btnLoginRes, GUILauncher.GUIScene);
        mouseHover(btnLoginRes, GUILauncher.GUIScene);
        mouseHover(btnLoginRes, GUILauncher.GUIScene);
        mouseHover(btnRegisterRes, GUILauncher.GUIScene);
        mouseHover(btnResetLog, GUILauncher.GUIScene);
        mouseHover(btnResetReg, GUILauncher.GUIScene);
        mouseHover(btnResetRes, GUILauncher.GUIScene);
        mouseHover(btnResetBot, GUILauncher.GUIScene);

        btnLoginBot.setOnMouseClicked(event -> {
            if (!Backend.isConnected()) {
                setLoginMessage("Could not connect to server!", Color.RED);
            } else {
                GUILauncher.GUIScene.setCursor(Cursor.WAIT);
                String checkUser = txtUserName.getText(),
                        checkPw = pf.getText();
                Backend.login(checkUser, checkPw);
                System.out.println("Trying to login: " + checkUser + " " + checkPw);
                txtUserName.setText("");
                pf.setText("");
                setLoginMessage("Logging in...", Color.ORANGE);
            }
        });

        btnRegisterBot.setOnMouseClicked(event -> {
            GUILauncher.GUIScene.setCursor(Cursor.WAIT);
            String regMail = txtUserName2.getText(),
                    regPass = pf2.getText(),
                    repeatPass = pf3.getText();
            if (regMail.equals("") || regPass.equals("") || repeatPass.equals("")) {
                setLoginMessage("Please fill in all the fields!", Color.RED);
            } else if (!regPass.equals(repeatPass)) {
                setLoginMessage("Passwords don't match!", Color.RED);
            } else if (regPass.length() < 8) {
                setLoginMessage("Password must be atleast 8 characters long", Color.RED);
            } else {
                Backend.register(regMail, regPass);
            }
        });

        //Prompt Text
        txtUserName.setPromptText("Email Adress");
        pf.setPromptText("Password");
        txtUserName2.setPromptText("Email Adress");
        pf2.setPromptText("Password");
        pf3.setPromptText("Confirm Password");
        txtUserName3.setPromptText("Email Adress");
        txtUserName4.setPromptText("Confirm Email Adress");
        super.setCenter(bp);
        btnRegister.setOnMouseClicked(e -> GUILauncher.switchToRegister());
        btnLoginReg.setOnMouseClicked(e -> GUILauncher.switchToLogin());
        btnResetLog.setOnAction(e -> GUILauncher.switchToReset());
        btnResetReg.setOnAction(e -> GUILauncher.switchToReset());
        btnLoginRes.setOnAction(e -> GUILauncher.switchToLogin());
        btnRegisterRes.setOnAction(e -> GUILauncher.switchToRegister());
        btnLoginBot.setDefaultButton(true);
        btnRegisterBot.setDefaultButton(true);
        btnResetBot.setDefaultButton(true);

        btnRegisterBot.setOnAction(event -> {
            if (!Backend.isConnected()) {
                setRegisterMessage("Could not connect to server!", Color.RED);
            }
            //TODO response listeners in GUILauncher
        });

        btnResetBot.setOnAction(event -> {
            if (!Backend.isConnected()) {
                setResetMessage("Could not connect to server", "", Color.RED);
            }
            //TODO response listeners in GUILauncher
        });

    }

    protected void setLoginMessage(String text, Color color) {
        Platform.runLater(() -> {
            loginMessage.setText(text);
            loginMessage.setTextFill(color);
        });
    }

    protected void setRegisterMessage(String text, Color color) {
        Platform.runLater(() -> {
            registerMessage.setText(text);
            registerMessage.setTextFill(color);
        });
    }

    protected void setResetMessage(String textTop, String textBottom, Color color) {
        Platform.runLater(() -> {
            resetMessage.setText(textTop);
            resetMessage2.setText(textBottom);
            resetMessage.setTextFill(color);
            resetMessage2.setTextFill(color);
        });
    }

    public static void mouseHover(Button btn, Scene scene) {
        btn.setOnMouseEntered(event -> scene.setCursor(Cursor.HAND));
        btn.setOnMouseExited(event -> {
            scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
        });
    }
}
