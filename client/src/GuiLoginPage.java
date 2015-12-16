import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class GuiLoginPage extends Application {

    Scene scene1, scene2;
    Button button1;
    Button button2;

    //public static void main (String[] args){
      //  launch(args);
    //}

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Button 1
        button1 = new Button();
        button1.setText("Register");
        button1.setOnAction(e -> primaryStage.setScene(scene2));

        //Layout 1
        StackPane layout1 = new StackPane();
        layout1.getChildren().add(button1);
        scene1 = new Scene(layout1, 600, 300);

        //Button 2
        button2 = new Button();
        button2.setText("Login");
        button2.setOnAction(e -> primaryStage.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 600, 300);

        //Show Stage
        primaryStage.setScene(scene1);
        primaryStage.setTitle("MindMatch");
        primaryStage.show();
    }

     }











