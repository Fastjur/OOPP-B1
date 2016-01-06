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

/**Gui find match page
 * @author Zoë van Steijn
 */
public class GuiFindMatchConstructor extends BorderPane {
    private HBox bottom;
    private HBox center;
    private ScrollPane right;
    private String name;
    private String age;
    private String description;
    private String matchIconUrl;
    private String nomatchIconUrl;
    private String profilePicUrl;

    public GuiFindMatchConstructor(String name, String age, String descr, String matchIconUrl, String nomatchIconUrl, String profilePicUrl){
        super();
        this.name = name;
        this.age = age;
        this.description = descr;
        this.matchIconUrl = matchIconUrl;
        this.nomatchIconUrl = nomatchIconUrl;
        this.profilePicUrl = profilePicUrl;
        bottomBox();
        rightBox();
        pfBox();
        super.setBottom(bottom);
        super.setRight(right);
        super.setCenter(center);
    }

    private void bottomBox(){
        ImageView nmView = new ImageView(new Image(nomatchIconUrl));
        ImageView mView = new ImageView(new Image(matchIconUrl));

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

        Text nameUser = new Text(name);
        Text ageUser = new Text("Age: " + age);
        Text descr = new Text("Description\n");
        descr.setStyle("-fx-font-size: 150%");
        Text descriptionUser = new Text(description);

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
