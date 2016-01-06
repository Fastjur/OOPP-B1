package gui.views;

import communication.Backend;
import communication.IMessageListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shared.Response;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class GuiLogin extends Application implements IMessageListener {

    private final Label lblMessage = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MindMatch Login");

        //BorderPane
        //Login
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Image img = new Image("mm.jpg");
        //ImageView imgView = new ImageView(img);
        //bp.getChildren().add(imgView);

        //Register
        BorderPane bp2 = new BorderPane();
        bp2.setPadding(new Insets(10, 50, 50, 50));

        //Reset Password
        BorderPane bp3 = new BorderPane();
        bp3.setPadding(new Insets(10, 50, 50, 50));


        //Adding HBox
        //Login
        HBox hb = new HBox();
        hb.setStyle("-fx-background-color: #26A69A;" +
                "-fx-border-color: #00796B transparent transparent transparent;" +
                "-fx-border-width: 20px 0 0 200px;-fx-padding: 50%, 0, 0, 0;");



        //Register
        HBox hb2 = new HBox();
        hb2.setStyle("-fx-background-color: #26A69A;" +
                "-fx-border-color: #00796B transparent transparent transparent;" +
                "-fx-border-width: 20px 0 0 200px;-fx-padding: 50%, 0, 0, 0;");


        //Reset Password
        HBox hb3 = new HBox();
        hb3.setStyle("-fx-background-color: #26A69A;" +
                "-fx-border-color: #00796B transparent transparent transparent;" +
                "-fx-border-width: 20px 0 0 200px;-fx-padding: 50%, 0, 0, 0;");


        //Adding GridPane
        //Login
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(60, 60, 60, 60));
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setMaxWidth(500);
        gridPane.setMaxHeight(200);
        hb.setId("hb");

        //Register
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(60, 60, 60, 60));
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);
        gridPane2.setMaxWidth(500);
        gridPane2.setMaxHeight(300);
        hb2.setId("hb2");

        //Reset Password
        GridPane gridPane3 = new GridPane();
        gridPane3.setPadding(new Insets(60, 60, 60, 60));
        gridPane3.setHgap(10);
        gridPane3.setVgap(5);
        gridPane3.setMaxWidth(500);
        gridPane3.setMaxHeight(300);
        hb3.setId("hb3");

        //Implementing Nodes for GridPane
        //Login
        final TextField txtUserName = new TextField();
        final PasswordField pf = new PasswordField();
        Button btnLoginTop = new Button("Login");
        Button btnLoginBot = new Button("Login");
        Button btnRegister = new Button("Register");

        final Label lblMessage = new Label();
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
        gridPane.add(loginLabel, 2, 1);
        gridPane.add(txtUserName, 2, 2, 3, 1);
        gridPane.add(pf, 2, 3, 3, 1);
        gridPane.add(btnLoginBot, 2, 4);
        gridPane.add(lblMessage, 2, 5, 3, 1);

        //Register
        gridPane2.add(btnLoginReg, 2, 0);
        gridPane2.add(btnRegisterTop, 3, 0);
        gridPane2.add(btnResetReg, 4, 0 );
        gridPane2.add(registerLabel, 2, 1, 3, 1);
        gridPane2.add(txtUserName2, 2, 2, 3, 1);
        gridPane2.add(pf2, 2, 3, 3, 1);
        gridPane2.add(pf3, 2, 4, 3,1 );
        gridPane2.add(btnRegisterBot, 2, 5, 2, 1);

        //Reset Password
        gridPane3.add(btnLoginRes, 2,0);
        gridPane3.add(btnRegisterRes, 3,0);
        gridPane3.add(btnResetRes, 4,0);
        gridPane3.add(resetPasswordLabel, 2, 1, 3, 1);
        gridPane3.add(txtUserName3, 2, 2, 3, 1);
        gridPane3.add(txtUserName4, 2, 3, 3, 1);
        // gridPane3.add(info, 2, 4, 3, 1);
        //gridPane3.add(info2, 2, 5, 3, 1);
        gridPane3.add(btnResetRes2, 2, 4, 3, 1);


        //Adding text
        Text text = new Text("MindMatch");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        hb.setAlignment(Pos.CENTER_LEFT);

        //Register
        Text text2 = new Text("MindMatch");
        text2.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

        //Reset
        Text text3 = new Text("MindMatch");
        text3.setFont(Font.font("Courier New", FontWeight.BOLD, 28));


        //Adding text to HBox
        hb.getChildren().add(text);
        hb2.getChildren().add(text2);
        hb3.getChildren().add(text3);

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

        //Pop-up window for Reset button
        btnResetRes2.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(primaryStage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.getChildren().add(new Text("An email with instructions will be sent to you shortly."));
                        Scene dialogScene = new Scene(dialogVbox, 350, 200);
                        dialog.setScene(dialogScene);
                        dialog.show();
                    }
                });



        //Action for btnLogin
        btnLoginBot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String checkUser = txtUserName.getText(),
                       checkPw = pf.getText();
                Backend.login(checkUser, checkPw);
                txtUserName.setText("");
                pf.setText("");
            }
        });

        //Add HBox and GridPane layout to BorderPane Layout
        //Login
        bp.setTop(hb);
        bp.setCenter(gridPane);

        //Register
        bp2.setTop(hb2);
        bp2.setCenter(gridPane2);

        //Reset
        bp3.setTop(hb3);
        bp3.setCenter(gridPane3);


        //Adding BorderPane to the scene and loading CSS
        Scene loginScene = new Scene(bp);
        Scene registerScene = new Scene(bp2);
        Scene resetScene = new Scene(bp3);

        //MouseHover
        mouseHover(btnLoginBot, loginScene);
        mouseHover(btnLoginTop, loginScene);
        mouseHover(btnRegister, loginScene);
        mouseHover(btnLoginReg, registerScene);
        mouseHover(btnRegisterTop, registerScene);
        mouseHover(btnRegisterBot, registerScene);
        mouseHover(btnLoginRes, loginScene);
        mouseHover(btnLoginRes, registerScene);
        mouseHover(btnLoginRes, resetScene);
        mouseHover(btnRegisterRes, resetScene);
        mouseHover(btnResetLog, loginScene);
        mouseHover(btnResetReg, registerScene);
        mouseHover(btnResetRes, resetScene);
        mouseHover(btnResetRes2, resetScene);

        btnLoginBot.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loginScene.setCursor(Cursor.WAIT);
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

        btnRegister.setOnAction(e -> primaryStage.setScene(registerScene));
        btnLoginReg.setOnAction(e -> primaryStage.setScene(loginScene));
        btnResetLog.setOnAction(e -> primaryStage.setScene(resetScene));
        btnResetReg.setOnAction(e -> primaryStage.setScene(resetScene));
        btnLoginRes.setOnAction(e -> primaryStage.setScene(loginScene));
        btnRegisterRes.setOnAction(e -> primaryStage.setScene(registerScene));
        loginScene.getStylesheets().add("/gui/views/css/login.css");
        registerScene.getStylesheets().add("/gui/views/css/login.css");
        resetScene.getStylesheets().add("/gui/views/css/login.css");
        primaryStage.setScene(loginScene);

        primaryStage.setResizable(false);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.show();

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

    public void stop() {
        try {
            Backend.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
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
