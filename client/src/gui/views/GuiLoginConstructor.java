package gui.views;

import communication.Backend;
import communication.IMessageListener;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    BorderPane bp3;

    public GuiLoginConstructor(){
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
        Button btnLoginTop = new Button("Login");
        Button btnLoginBot = new Button("Login");
        Button btnRegister = new Button("Register");

        //Labels
        final Label regMessage = new Label();
        final Label lblMessage = new Label();
        final Label resMessage = new Label();
        final Label res2Message = new Label();
        final Label loginLabel = new Label("Login");
        Button btnResetLog = new Button("Reset");

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
        final Label info = new Label("An email with instructions will");
        final Label info2 = new Label("be sent to you shortly.");
        final TextField txtUserName3 = new TextField();
        final TextField txtUserName4 = new TextField();
        Button btnLoginRes = new Button("Login");
        Button btnRegisterRes = new Button("Register");
        Button btnResetRes = new Button("Reset");
        Button btnResetRes2 = new Button("Reset");

        //Adding Nodes to GridPane layout
        //Login
        gridPane.add(btnLoginTop, 2, 0);
        gridPane.add(btnRegister, 3, 0 );
        gridPane.add(btnResetLog, 4, 0 );
        gridPane.add(loginLabel, 2, 1, 3, 1);
        gridPane.add(txtUserName, 2, 2, 3, 1);
        gridPane.add(pf, 2, 3, 3, 1);
        gridPane.add(btnLoginBot, 2, 4, 3, 1);
        gridPane.add(lblMessage, 2, 5, 3, 1);

        //Register
        gridPane2.add(btnLoginReg, 2, 0);
        gridPane2.add(btnRegisterTop, 3, 0);
        gridPane2.add(btnResetReg, 4, 0 );
        gridPane2.add(registerLabel, 2, 1, 3, 1);
        gridPane2.add(txtUserName2, 2, 2, 3, 1);
        gridPane2.add(pf2, 2, 3, 3, 1);
        gridPane2.add(pf3, 2, 4, 3, 1 );
        gridPane2.add(btnRegisterBot, 2, 5, 3, 1);
        gridPane2.add(regMessage, 2, 6, 3, 1 );

        //Reset Password
        gridPane3.add(btnLoginRes, 2,0);
        gridPane3.add(btnRegisterRes, 3,0);
        gridPane3.add(btnResetRes, 4,0);
        gridPane3.add(resetPasswordLabel, 2, 1, 3, 1);
        gridPane3.add(txtUserName3, 2, 2, 3, 1);
        gridPane3.add(txtUserName4, 2, 3, 3, 1);
        gridPane3.add(btnResetRes2, 2, 4, 3, 1);
        gridPane3.add(resMessage, 2, 5, 3, 1);
        gridPane3.add(res2Message, 2, 6, 3, 1);


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
        btnResetRes2.setId("BRR3");
        resetPasswordLabel.setId("resLabel");
        text3.setId("text3");
        info.setId("info");
        info2.setId("info2");

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

        //Reset button message
        btnResetRes2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                resMessage.setText("An email with instructions");
                res2Message.setText("will be sent to you shortly.");
                resMessage.setTextFill(Color.WHITE);
                res2Message.setTextFill(Color.WHITE);
                //txtUserName.setText("");
                // pf.setText("");
            }
        });

        //Add V/HBox and GridPane layout to BorderPane Layout
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
        mouseHover(btnResetRes2, GUILauncher.GUIScene);

       /* btnLoginBot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GUILauncher.GUIScene.setCursor(Cursor.WAIT);
            }
        });
        */

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
        btnResetRes2.setDefaultButton(true);

        //Initialize Backend
        Backend.serverAddress = "::1";
        Backend.serverPort = 8372;
        Backend.connectToServer();
        Backend.addMessageListener(this);


        btnLoginBot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
        if (!Backend.isConnected()) {
            Platform.runLater(new Runnable() {
                public void run() {
                    lblMessage.setText("Could not connect to server!");
                    lblMessage.setTextFill(Color.RED);
                }
            });
        }
        else{       regMessage.setText("Register Succesful!");
            regMessage.setTextFill(Color.WHITE);
        }
        }});

        btnRegisterBot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!Backend.isConnected()) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            regMessage.setText("Could not connect to server!");
                            regMessage.setTextFill(Color.RED);
                        }
                    });
                }
                else{       regMessage.setText("Register Succesful!");
                            regMessage.setTextFill(Color.WHITE);
                }
            }});

        btnResetRes2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (!Backend.isConnected()) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            resMessage.setText("Could not connect to server!");
                            resMessage.setTextFill(Color.RED);
                        }
                    });
                }
                else{       resMessage.setText("An email with instructions");
                            res2Message.setText("will be sent to you shortly.");
                            resMessage.setTextFill(Color.WHITE);
                            res2Message.setTextFill(Color.WHITE);
                }
            }});

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
