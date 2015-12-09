package client;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class GuiMatchPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String nomatchURL = this.getClass().getResource("images/nomatch.png").toExternalForm();
        String matchURL = this.getClass().getResource("images/match.png").toExternalForm();
        HBox bottomBox = bottomBox(nomatchURL, matchURL);

        String pfURL = this.getClass().getResource("images/pfExample.jpg").toExternalForm();
        HBox pfBox = pfBox(pfURL);

        String name = "Rebecca Black";
        String age = "18";
        String descr = "Seven a.m. waking up in the morning. Gotta be fresh, gotta go downstairs. Gotta have my bowl, gotta have cereal. Seein' everything the time is goin'. Tickin' on and on, everybody's rushin'. Gotta get down to the bus stop. Gotta catch my bus. I see my friends.";
        ScrollPane rightBox = rightBox(name, age, descr);

        BorderPane pane = new BorderPane();
        pane.setCenter(pfBox);
        pane.setBottom(bottomBox);
        pane.setRight(rightBox);

        Scene scene = new Scene(pane);

        String cssURL = this.getClass().getResource("MatchPage.css").toExternalForm();
        scene.getStylesheets().add(cssURL);

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.setTitle("Match Page");
        primaryStage.show();
    }

    private HBox bottomBox(String Url1, String Url2){
        ImageView nmView = new ImageView(new Image(Url1));
        ImageView mView = new ImageView(new Image(Url2));

        nmView.setPreserveRatio(true);
        nmView.setFitWidth(100);
        nmView.setCursor(Cursor.HAND);
        mView.setPreserveRatio(true);
        mView.setFitWidth(100);
        mView.setCursor(Cursor.HAND);

        HBox hbox = new HBox(40);
        hbox.setId("bottomBox");
        hbox.getChildren().addAll(nmView, mView);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20,0,20,0));
        hbox.setMinHeight(120);
        return hbox;
    }

    private ScrollPane rightBox(String _name, String _age, String _description){
        VBox vbox = new VBox(5);

        Text nameUser = new Text(_name);
        Text ageUser = new Text("Age: " + _age);
        Text descr = new Text("Description\n");
        descr.setStyle("-fx-font-size: 150%");
        Text descriptionUser = new Text(_description);

        TextFlow name = new TextFlow(nameUser);
        name.setStyle("-fx-font-size: 300%");
        TextFlow age = new TextFlow(ageUser);
        age.setStyle("-fx-font-size: 150%");
        TextFlow description = new TextFlow();
        description.getChildren().addAll(descr, descriptionUser);
        description.setPadding(new Insets(30,0,0,0));
        vbox.getChildren().addAll(name, age, description);
        vbox.setAlignment(Pos.TOP_LEFT);

        vbox.setPadding(new Insets(30, 30, 30, 30));
        vbox.setPrefWidth(400);
        vbox.setId("rightBox");

        ScrollPane rightBox = new ScrollPane();
        rightBox.setContent(vbox);
        rightBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rightBox.setFitToHeight(true);

        return rightBox;
    }

    private static HBox pfBox(String pfURL){
        Image pf = new Image(pfURL);
        BackgroundImage pfView= new BackgroundImage(pf, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        HBox pfBox = new HBox();
        pfBox.setBackground(new Background(pfView));
        return pfBox;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
