package gui.views;

import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

/**Gui find match page
 * @author Zoë van Steijn
 */
public class GuiFindMatchConstructor extends BorderPane {
    private HBox bottom;
    private HBox center;
    private ScrollPane right;
    private String name;
    private String age;
    public static String description;
    public String profilePicUrl;
    public ArrayList<String> languages;
    public double distance;

    public GuiFindMatchConstructor(ArrayList<String> languages, double distance, String name, String age, String descr, String profilePicUrl){
        super();
        this.name = name;
        this.age = age;
        this.description = descr;
        this.profilePicUrl = profilePicUrl;
        this.languages = languages;
        this.distance = distance;
        bottomBox();
        rightBox();
        pfBox();
        super.setBottom(bottom);
        super.setRight(right);
        super.setCenter(center);
    }

    private void bottomBox(){
        String nomatchURL = this.getClass().getResource("resources/noMatchIcon2.png").toExternalForm();
        String matchURL = this.getClass().getResource("resources/matchIcon2.png").toExternalForm();

        ImageView nmView = new ImageView(new Image(nomatchURL));
        ImageView mView = new ImageView(new Image(matchURL));

        nmView.setPreserveRatio(true);
        nmView.setFitWidth(100);
        nmView.setCursor(Cursor.HAND);
        mView.setPreserveRatio(true);
        mView.setFitWidth(100);
        mView.setCursor(Cursor.HAND);

        bottom = new HBox(40);
        bottom.setId("bottomBox");
        bottom.getChildren().addAll(nmView, mView);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20,0,20,0));
        bottom.setMinHeight(120);
    }

    private void rightBox(){
        VBox vbox = new VBox(5);

        //name
        Text nameUser = new Text(name);
        TextFlow name = new TextFlow(nameUser);
        name.setStyle("-fx-font-size: 300%");

        //age
        Text ageUser = new Text("Age: " + age);
        TextFlow age = new TextFlow(ageUser);
        age.setStyle("-fx-font-size: 150%");

        //description
        Text descr = new Text("Description\n");
        descr.setStyle("-fx-font-size: 150%");
        Text descriptionUser = new Text(description);
        TextFlow description = new TextFlow();
        description.getChildren().addAll(descr, descriptionUser);
        description.setPadding(new Insets(30,0,0,0));

        //languages
        String languageList = "";
        for(int i = 0; i<languages.size()-1; i++){
            languageList = languageList + languages.get(i) + "\n";
        }
        languageList = languageList + languages.get(languages.size() -1);
        Text lang = new Text("Languages\n");
        lang.setStyle("-fx-font-size: 150%");
        Text languagesUser = new Text(languageList);
        TextFlow languagesBox = new TextFlow();
        languagesBox.getChildren().addAll(lang, languagesUser);
        languagesBox.setPadding(new Insets(30,0,0,0));

        //distance
        Text dist = new Text("Distance\n");
        dist.setStyle("-fx-font-size: 150%");
        Text distanceUser = new Text(Double.toString(distance) + " km");
        TextFlow distance = new TextFlow();
        distance.getChildren().addAll(dist, distanceUser);
        distance.setPadding(new Insets(30,0,0,0));

        vbox.getChildren().addAll(name, age, description, languagesBox, distance);
        vbox.setAlignment(Pos.TOP_LEFT);

        vbox.setPadding(new Insets(30, 30, 30, 30));
        vbox.setPrefWidth(400);
        vbox.setId("rightBox");

        right = new ScrollPane();
        right.setContent(vbox);
        right.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        right.setFitToHeight(true);
    }

    private void pfBox(){
        Image pf = new Image(profilePicUrl);
        BackgroundImage pfView= new BackgroundImage(pf, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(100, 100, true, true, false, true));
        center = new HBox();
        center.setBackground(new Background(pfView));
    }
}
