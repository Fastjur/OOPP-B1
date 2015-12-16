package gui.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GuiContacts extends Application {

    public static void main(String[] args) {
        System.out.println("Launching JavaFX");
        launch(args);
        System.out.println("Finished");
    }

    @Override
    public void start(Stage primaryStage) {
        String name = "Rebecca Black";
        String age = "18";
        String img = this.getClass().getResource("/gui/views/resources/pfExample.jpg").toExternalForm();
        String description = "Seven a.m. waking up in the morning. Gotta be fresh, gotta go downstairs. " +
                "Gotta have my bowl, gotta have cereal. Seein' everything the time is goin'. " +
                "Tickin' on and on, everybody's rushin'. Gotta get down to the bus stop. Gotta catch my bus. I see my friends.";
        String university = "U.C.L.A.";
        String study = "Academic writing";
        ArrayList<String> monTimes = new ArrayList<>();
        monTimes.add("18:00-21:00");
        ArrayList<String> tueTimes = new ArrayList<>();
        tueTimes.add("12:00-14:30");
        ArrayList<String> wedTimes = new ArrayList<>();
        wedTimes.add("13:00-14:00");
        wedTimes.add("20:00-21:00");
        ArrayList<String> thuTimes = new ArrayList<>();
        thuTimes.add("09:00-10:00");
        ArrayList<String> friTimes = new ArrayList<>();
        ArrayList<String> satTimes = new ArrayList<>();
        satTimes.add("23:00-00:00");
        ArrayList<String> sunTimes = new ArrayList<>();
        sunTimes.add("13:00-15:00");

        ScrollPane scroll = scroll(center(left(head(img, headText(name, age)), centerLeft(description, university, study)),
                right(monTimes, tueTimes, wedTimes, thuTimes, friTimes, satTimes, sunTimes)));
        scroll.setFitToHeight(true);

        BorderPane pane = new BorderPane(scroll);
        pane.setId("backgroundFoot");
        scroll.setId("background");

        Button btn = chatButton();
        pane.setAlignment(btn, Pos.CENTER_RIGHT);
        pane.setMargin(btn, new Insets(10, 10, 10, 10));
        pane.setBottom(btn);

        Scene scene = new Scene(pane, Color.WHITE);
        String css = this.getClass().getResource("/gui/views/css/ContactsStyle.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TwoBrains/Contacts");
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.show();
    }

    public Button chatButton() {
        Button chat = new Button();
        chat.getStylesheets().add(this.getClass().getResource("/gui/views/css/ContactsStyle.css").toExternalForm());
        chat.setText("Chat");
        ImageView chatIcon = new ImageView("/gui/views/resources/chatIcon.png");
        chatIcon.setFitHeight(30);
        chatIcon.setPreserveRatio(true);
        chat.setGraphic(chatIcon);
        chat.setId("buttonChat");
        return chat;
    }

    // picture and headtext (name and age)
    public HBox head(String _img, VBox _headText) {
        HBox hbox = new HBox();
        ImageView imageView = new ImageView(_img);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        Circle clip = new Circle(200, 100, 100);
        imageView.setClip(clip);

        hbox.getChildren().addAll(imageView, _headText);
        hbox.setId("head");
        return hbox;
    }

    // name and age
    public VBox headText(String _name, String _age) {
        VBox vbox = new VBox(5);
        Text name = new Text(_name);
        Text age = new Text("Age: " + _age);
        vbox.getChildren().addAll(name, age);
        vbox.setId("headText");
        name.setId("name");
        age.setId("age");
        return vbox;
    }

    // info
    public VBox centerLeft(String _description, String _university, String _study) {
        VBox vbox = new VBox(20);

        VBox descr = new VBox(2);
        TextFlow description = new TextFlow();
        Text descriptionTitle = new Text("Description");
        Text descriptionInfo = new Text(_description);
        description.getChildren().addAll(descriptionInfo);
        description.setPadding(new Insets(0, 30, 0, 0));
        descr.getChildren().addAll(descriptionTitle, description);
        descriptionTitle.setId("description");
        descriptionInfo.setId("descriptionInfo");

        VBox uni = new VBox(2);
        Text university = new Text("University");
        Text universityInfo = new Text(_university);
        uni.getChildren().addAll(university, universityInfo);
        university.setId("university");
        universityInfo.setId("universityInfo");

        VBox stu = new VBox(2);
        Text study = new Text("Study");
        Text studyInfo = new Text(_study);
        stu.getChildren().addAll(study, studyInfo);
        study.setId("study");
        studyInfo.setId("studyInfo");

        vbox.getChildren().addAll(descr, uni, stu);
        vbox.setPadding(new Insets(0, 0, 0, 100));
        vbox.setMaxWidth(750);
        vbox.setId("centerLeft");
        return vbox;

    }

    // left side of the screen: picture, name, age and info
    public VBox left(HBox _head, VBox _centerLeft) {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(_head, _centerLeft);
        vbox.setId("left");
        return vbox;
    }

    // return String of elements from the arraylist
    public String getElements(ArrayList list) {
        String text = "";
        if (list.size() == 0) {
            text = "-";
        } else {
            for (int i = 0; i < list.size(); i++) {
                text += list.get(i).toString();
                if (i < list.size() - 1) {
                    text += "\n";
                }
            }
        }
        return text;
    }

    // Available
    public VBox right(ArrayList _monTimes, ArrayList _tueTimes, ArrayList _wedTimes, ArrayList _thuTimes,
                      ArrayList _friTimes, ArrayList _satTimes, ArrayList _sunTimes) {
        VBox vbox = new VBox();
        VBox available = new VBox();
        VBox days = new VBox();

        Text availability = new Text("Availability");
        available.getChildren().addAll(availability);

        HBox mon = new HBox();
        Text monday = new Text("Monday:\t\t");
        Text monTimes = new Text(getElements(_monTimes));
        mon.getChildren().addAll(monday, monTimes);

        HBox tue = new HBox();
        Text tuesday = new Text("Tuesday:\t\t");
        Text tueTimes = new Text(getElements(_tueTimes));
        tue.getChildren().addAll(tuesday, tueTimes);

        HBox wed = new HBox();
        Text wednesday = new Text("Wednesday:\t");
        Text wedTimes = new Text(getElements(_wedTimes));
        wed.getChildren().addAll(wednesday, wedTimes);

        HBox thu = new HBox();
        Text thursday = new Text("Thursday:\t\t");
        Text thuTimes = new Text(getElements(_thuTimes));
        thu.getChildren().addAll(thursday, thuTimes);

        HBox fri = new HBox();
        Text friday = new Text("Friday:\t\t");
        Text friTimes = new Text(getElements(_friTimes));
        fri.getChildren().addAll(friday, friTimes);

        HBox sat = new HBox();
        Text saturday = new Text("Saturday:\t\t");
        Text satTimes = new Text(getElements(_satTimes));
        sat.getChildren().addAll(saturday, satTimes);

        HBox sun = new HBox();
        Text sunday = new Text("Sunday:\t\t");
        Text sunTimes = new Text(getElements(_sunTimes));
        sun.getChildren().addAll(sunday, sunTimes);

        days.getChildren().addAll(mon, tue, wed, thu, fri, sat, sun);
        vbox.getChildren().addAll(available, days);
        available.setId("available");
        days.setId("days");
        vbox.setId("right");
        return vbox;
    }

    // the whole screen (except footer)
    public HBox center(VBox _left, VBox _right) {
        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(_left, _right);
        hbox.setId("center");
        return hbox;
    }


    // scrollpane
    public ScrollPane scroll(HBox _center) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(_center);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

}
