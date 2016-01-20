package gui.views;

import communication.Backend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    protected BorderPane bpLogin;
    protected BorderPane bpRegister;
    protected BorderPane bpReset;

    public GuiLoginConstructor() {
        super();

        bpLogin = new BorderPane();
        bpLogin.setId("bpLogin");
        bpRegister = new BorderPane();
        bpRegister.setId("bpRegister");
        bpReset = new BorderPane();
        bpReset.setId("bpReset");

        HBox hbLogin = new HBox();
        hbLogin.setId("hbLogin");
        HBox hbRegister = new HBox();
        hbRegister.setId("hbRegister");
        HBox hbReset = new HBox();
        hbReset.setId("hbReset");

        GridPane gpLogin = new GridPane();
        gpLogin.setHgap(0);
        gpLogin.setVgap(5);
        gpLogin.setMaxWidth(260);
        gpLogin.setMinWidth(260);
        gpLogin.setMaxHeight(250);
        gpLogin.setMinHeight(250);
        gpLogin.setId("gpLogin");

        GridPane gpRegister = new GridPane();
        gpRegister.setHgap(0);
        gpRegister.setVgap(5);
        gpRegister.setMaxWidth(260);
        gpRegister.setMinWidth(260);
        gpRegister.setMaxHeight(250);
        gpRegister.setMinHeight(250);
        gpRegister.setId("gpRegister");

        GridPane gpReset = new GridPane();
        gpReset.setHgap(0);
        gpReset.setVgap(5);
        gpReset.setMaxWidth(260);
        gpReset.setMinWidth(260);
        gpReset.setMaxHeight(250);
        gpReset.setMinHeight(250);
        gpReset.setId("gpReset");

        GridPane gpLog = new GridPane();
        gpLog.setHgap(0);
        gpLog.setVgap(5);
        gpLog.setId("gpLog");

        GridPane gpReg = new GridPane();
        gpReg.setHgap(0);
        gpReg.setVgap(5);
        gpReg.setId("gpReg");

        GridPane gpRes = new GridPane();
        gpRes.setHgap(0);
        gpRes.setVgap(5);
        gpRes.setId("gpRes");

        //Implementing Nodes for GridPane
        final TextField userNameLogin = new TextField();
        userNameLogin.setMaxWidth(220);
        userNameLogin.setMinWidth(220);
        final PasswordField pswLogin = new PasswordField();
        pswLogin.setMaxWidth(220);
        pswLogin.setMinWidth(220);

        //TODO Remove, this is solely for ease of debugging
        userNameLogin.setText("sinterklaas@sintmail.nl");
        pswLogin.setText("Pepernoten01");

        final TextField userNameReg = new TextField();
        userNameReg.setMaxWidth(220);
        userNameReg.setMinWidth(220);
        final PasswordField pswReg = new PasswordField();
        pswReg.setMaxWidth(220);
        pswReg.setMinWidth(220);
        final PasswordField pswConfirmReg = new PasswordField();
        pswConfirmReg.setMaxWidth(220);
        pswConfirmReg.setMinWidth(220);

        final TextField userNameRes = new TextField();
        userNameRes.setMaxWidth(220);
        userNameRes.setMinWidth(220);
        final TextField userNameRes2 = new TextField();
        userNameRes2.setMaxWidth(220);
        userNameRes2.setMinWidth(220);

        Button btnLogLogin = new Button("Login");
        btnLogLogin.setId("btnLogLogin");
        Button btnRegLogin = new Button("Login");
        btnRegLogin.setId("btnRegLogin");
        Button btnResLogin = new Button("Login");
        btnResLogin.setId("btnResLogin");

        Button btnLogRegister = new Button("Register");
        btnLogRegister.setId("btnLogRegister");
        Button btnRegRegister = new Button("Register");
        btnRegRegister.setId("btnRegRegister");
        Button btnResRegister = new Button("Register");
        btnResRegister.setId("btnResRegister");

        Button btnLogReset = new Button("Reset Password");
        btnLogReset.setId("btnLogReset");
        Button btnRegReset = new Button("Reset Password");
        btnRegReset.setId("btnRegReset");
        Button btnResReset = new Button("Reset Password");
        btnResReset.setId("btnResReset");

        Button btnLogSubmit = new Button("Login");
        btnLogSubmit.setId("logSubmit");
        Button btnRegSubmit = new Button("Register");
        btnRegSubmit.setId("regSubmit");
        Button btnResSubmit = new Button("Reset");
        btnResSubmit.setId("resSubmit");

        //Labels
        final Label lbLogin  = new Label("Login");
        lbLogin.setId("lbLogin");
        final Label lbRegister = new Label("Register");
        lbRegister.setId("lbRegister");
        final Label lbReset = new Label("Reset");
        lbReset.setId("lbReset");

        //Adding nodes to gridpane
        gpLogin.add(btnLogLogin, 0, 0, 1, 1);
        gpLogin.add(btnLogRegister, 1, 0, 1, 1);
        gpLogin.add(btnLogReset, 2, 0, 1, 1);
        gpLogin.add(gpLog, 0, 1, 3, 1);
        gpLog.add(lbLogin, 0, 0, 3, 1);
        gpLog.add(userNameLogin, 0, 1, 3, 1);
        gpLog.add(pswLogin, 0, 3, 3, 1);
        gpLog.add(btnLogSubmit, 0, 4, 3, 1);
        gpLog.add(loginMessage, 0, 5, 3, 1);

        gpRegister.add(btnRegLogin, 0, 0, 1, 1);
        gpRegister.add(btnRegRegister, 1, 0, 1, 1);
        gpRegister.add(btnRegReset, 2, 0, 1, 1);
        gpRegister.add(gpReg, 0, 1, 3, 1);
        gpReg.add(lbRegister, 0, 0, 3, 1);
        gpReg.add(userNameReg, 0, 1, 3, 1);
        gpReg.add(pswReg, 0, 2, 3, 1);
        gpReg.add(pswConfirmReg, 0, 3, 3, 1);
        gpReg.add(btnRegSubmit, 0, 4, 3, 1);
        gpReg.add(registerMessage, 0, 5, 3, 1);

        gpReset.add(btnResLogin, 0, 0);
        gpReset.add(btnResRegister, 1, 0);
        gpReset.add(btnResReset, 2, 0);
        gpReset.add(gpRes, 0, 1, 3, 1);
        gpRes.add(lbReset, 0, 0, 3, 1);
        gpRes.add(userNameRes, 0, 1, 3, 1);
        gpRes.add(userNameRes2, 0, 2, 3, 1);
        gpRes.add(btnResSubmit, 0, 3, 3, 1);
        gpRes.add(resetMessage, 0, 4, 3, 1);
        gpRes.add(resetMessage2, 0, 5, 3, 1);

        //Adding text
        Text mmLog = new Text("MindMatch");
        mmLog.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        mmLog.setId("mmLog");
        mmLog.setFill(Color.WHITE);
        Text mmReg = new Text("MindMatch");
        mmReg.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        mmReg.setId("mmReg");
        mmReg.setFill(Color.WHITE);
        Text mmRes = new Text("MindMatch");
        mmRes.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        mmRes.setId("mmRes");
        mmRes.setFill(Color.WHITE);

        hbLogin.getChildren().add(mmLog);
        hbLogin.setAlignment(Pos.CENTER_LEFT);
        hbRegister.getChildren().add(mmReg);
        hbRegister.setAlignment(Pos.CENTER_LEFT);
        hbReset.getChildren().add(mmRes);
        hbReset.setAlignment(Pos.CENTER_LEFT);

        //Adding picture
        HBox hbLog = new HBox();
        hbLog.setId("hbLog");
        hbLog.setMinWidth(813);
        hbLog.setMaxWidth(813);
        hbLog.setMinHeight(275);
        hbLog.setMaxHeight(275);
        HBox hbReg = new HBox();
        hbReg.setId("hbReg");
        hbReg.setMinWidth(813);
        hbReg.setMaxWidth(813);
        hbReg.setMinHeight(275);
        hbReg.setMaxHeight(275);
        HBox hbRes = new HBox();
        hbRes.setId("hbRes");
        hbRes.setMinWidth(813);
        hbRes.setMaxWidth(813);
        hbRes.setMinHeight(275);
        hbRes.setMaxHeight(275);

        HBox hbIvLog = new HBox();
        HBox hbIvReg = new HBox();
        HBox hbIvRes = new HBox();

        ImageView ivLog = new ImageView(new Image(getClass().getResourceAsStream("/gui/views/resources/logoMindMatch-01.png")));
        ivLog.setPreserveRatio(true);
        ivLog.setFitWidth(350);
        hbIvLog.getChildren().add(ivLog);
        hbIvLog.setId("picLog");
        ImageView ivRes = new ImageView(new Image(getClass().getResourceAsStream("/gui/views/resources/logoMindMatch-01.png")));
        ivRes.setPreserveRatio(true);
        ivRes.setFitWidth(350);
        hbIvRes.getChildren().add(ivRes);
        hbIvRes.setId("picRes");
        ImageView ivReg = new ImageView(new Image(getClass().getResourceAsStream("/gui/views/resources/logoMindMatch-02.png")));
        ivReg.setPreserveRatio(true);
        ivReg.setFitWidth(350);
        hbIvReg.getChildren().add(ivReg);
        hbIvReg.setId("picReg");
        hbLog.getChildren().addAll(hbIvLog, gpLogin);
        hbReg.getChildren().addAll(hbIvReg, gpRegister);
        hbRes.getChildren().addAll(hbIvRes, gpReset);


        bpLogin.setTop(hbLogin);
        bpLogin.setCenter(hbLog);


        bpRegister.setTop(hbRegister);
        bpRegister.setCenter(hbReg);

        bpReset.setTop(hbReset);
        bpReset.setCenter(hbRes);


        //MouseHover
        mouseHover(btnLogLogin, GUILauncher.GUIScene);
        mouseHover(btnLogSubmit, GUILauncher.GUIScene);
        mouseHover(btnLogRegister, GUILauncher.GUIScene);
        mouseHover(btnRegSubmit, GUILauncher.GUIScene);
        mouseHover(btnLogReset, GUILauncher.GUIScene);
        mouseHover(btnResSubmit, GUILauncher.GUIScene);
        mouseHover(btnRegLogin, GUILauncher.GUIScene);
        mouseHover(btnRegRegister, GUILauncher.GUIScene);
        mouseHover(btnRegReset, GUILauncher.GUIScene);
        mouseHover(btnResLogin, GUILauncher.GUIScene);
        mouseHover(btnResRegister, GUILauncher.GUIScene);
        mouseHover(btnResReset, GUILauncher.GUIScene);

        btnLogSubmit.setOnMouseClicked(event -> {
            if (!Backend.isConnected()) {
                setLoginMessage("Could not connect to server!", Color.RED);
            } else {
                GUILauncher.GUIScene.setCursor(Cursor.WAIT);
                String checkUser = userNameLogin.getText(),
                        checkPw = pswLogin.getText();
                Backend.login(checkUser, checkPw);
                System.out.println("Trying to login: " + checkUser + " " + checkPw);
                userNameLogin.setText("");
                pswLogin.setText("");
                setLoginMessage("Logging in...", Color.ORANGE);
            }
        });

        btnRegSubmit.setOnMouseClicked(event -> {
            GUILauncher.GUIScene.setCursor(Cursor.WAIT);
            String regMail = userNameReg.getText(),
                    regPass = pswReg.getText(),
                    repeatPass = pswConfirmReg.getText();
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

        userNameLogin.setPromptText("Email Adress");
        pswLogin.setPromptText("Password");
        userNameReg.setPromptText("Email Adress");
        pswReg.setPromptText("Password");
        pswConfirmReg.setPromptText("Confirm Password");
        userNameRes.setPromptText("Email Adress");
        userNameRes2.setPromptText("Confirm Email Adress");
        super.setCenter(bpLogin);
        btnResRegister.setOnMouseClicked(e -> GUILauncher.switchToRegister());
        btnResLogin.setOnMouseClicked(e -> GUILauncher.switchToLogin());
        btnLogReset.setOnAction(e -> GUILauncher.switchToReset());
        btnRegReset.setOnAction(e -> GUILauncher.switchToReset());
        btnRegLogin.setOnAction(e -> GUILauncher.switchToLogin());
        btnLogRegister.setOnAction(e -> GUILauncher.switchToRegister());
        btnLogSubmit.setDefaultButton(true);
        btnRegSubmit.setDefaultButton(true);
        btnResSubmit.setDefaultButton(true);

        btnRegSubmit.setOnAction(event -> {
            if (!Backend.isConnected()) {
                setRegisterMessage("Could not connect to server!", Color.RED);
            }
            //TODO response listeners in GUILauncher
        });

        btnResSubmit.setOnAction(event -> {
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
