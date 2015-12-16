package gui.views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GuiLogin extends Application {

    String user = "Yosin12";
    String pw = "Yosin12";
    String checkUser, checkPw;

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

        //Register
        BorderPane bp2 = new BorderPane();
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
        final Label lblMessage = new Label();

        //Register
        final TextField txtUserName2 = new TextField();
        final PasswordField pf2 = new PasswordField();
        final PasswordField pf3 = new PasswordField();
        Button btnLoginReg = new Button("Login");
        Button btnRegisterTop = new Button("Register");
        Button btnRegisterBot = new Button("Register");
        final PasswordField txtRetypePas = new PasswordField();

        //Adding Nodes to GridPane layout
        //Login
        gridPane.add(txtUserName, 2, 1, 2, 1);
        gridPane.add(pf, 2, 2, 2, 1);
        gridPane.add(btnLoginTop, 2, 0);
        gridPane.add(btnLoginBot, 2, 3);
        gridPane.add(btnRegister, 3, 0);
        gridPane.add(lblMessage, 2, 4, 2, 1);

        //Register
        gridPane2.add(txtUserName2, 2, 1, 2, 1);
        gridPane2.add(pf2, 2, 2, 2, 1);
        gridPane2.add(txtRetypePas, 2, 3, 2, 1);
        gridPane2.add(btnRegisterTop, 3, 0);
        gridPane2.add(btnLoginReg, 2, 0);
        gridPane2.add(btnRegisterBot, 2, 4, 2, 1);

        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);
        gridPane2.setEffect(r);

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

        //Action for btnLogin
        btnLoginBot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                checkUser = txtUserName.getText().toString();
                checkPw = pf.getText().toString();
                if (checkUser.equals(user) && checkPw.equals(pw)) {
                    lblMessage.setText("Congratulations!");
                    lblMessage.setTextFill(Color.GREEN);
                } else {
                    lblMessage.setText("Incorrect user or pw.");
                    lblMessage.setTextFill(Color.RED);
                }
                txtUserName.setText("");
                pf.setText("");
            }
        });

        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);

        bp2.setTop(hb2);
        bp2.setCenter(gridPane2);

        //Adding BorderPane to the scene and loading CSS
        Scene loginScene = new Scene(bp);
        Scene registerScene = new Scene(bp2);

        //MouseHover
        //Login
        mouseHover(btnLoginBot, loginScene);
        mouseHover(btnLoginTop, loginScene);
        mouseHover(btnRegister, loginScene);
        mouseHover(btnLoginReg, registerScene);
        mouseHover(btnRegisterTop, registerScene);
        mouseHover(btnRegisterBot, registerScene);

        btnRegister.setOnAction(e -> primaryStage.setScene(registerScene));
        btnLoginReg.setOnAction(e -> primaryStage.setScene(loginScene));
        loginScene.getStylesheets().add("/gui/views/css/login.css");
        registerScene.getStylesheets().add("/gui/views/css/login.css");
        primaryStage.setScene(loginScene);

        //primaryStage.setResizable(false);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.show();


    }

    public static void mouseHover(Button btn, Scene scene) {
        btn.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);
            }
        });
        btn.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT); //Change cursor to crosshair
            }
        });
    }
}
