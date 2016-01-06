package gui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import shared.AvailableTimes;
import shared.TimePeriod;
import java.util.ArrayList;

/**
 * Created by Emma on 16-12-15.
 */
public class GuiContacts extends BorderPane{
    private ScrollPane center;
    private Button bottom;
    private String name;
    private String age;
    private String img;
    private String description;
    private String university;
    private String study;
    private AvailableTimes availabletimes;


    public GuiContacts(String name, String age, String img, String description,
                       String university, String study, AvailableTimes availabletimes) {
        super();
        this.name = name;
        this.age = age;
        this.img = img;
        this.description = description;
        this.university =university;
        this.study = study;
        this.availabletimes = availabletimes;
        chatButton();
        center();
        super.setCenter(center);
        super.setAlignment(bottom, Pos.BOTTOM_RIGHT);
        super.setBottom(bottom);
        super.setId("background");
    }

    public void chatButton() {
        bottom = new Button();
        bottom.setText("Chat");
        chatButtonNormal();
        bottom.setOnAction(e -> chatButtonAction());
        bottom.setOnMouseEntered(e -> chatButtonHover());
        bottom.setOnMouseExited(e -> chatButtonNormal());
    }

    public void chatButtonNormal() {
        ImageView chatIcon = new ImageView("/gui/views/resources/chatIcon.png");
        chatIcon.setFitHeight(50);
        chatIcon.setPreserveRatio(true);
        bottom.setGraphic(chatIcon);
        bottom.setId("buttonChat");
    }

    public void chatButtonHover() {
        ImageView chatIcon = new ImageView("/gui/views/resources/chatIconHover.png");
        chatIcon.setFitHeight(50);
        chatIcon.setPreserveRatio(true);
        bottom.setGraphic(chatIcon);
        bottom.setId("buttonChatHover");
    }

    public void chatButtonAction() {
        ImageView chatIcon = new ImageView("/gui/views/resources/chatIconHover.png");
        chatIcon.setFitHeight(60);
        chatIcon.setPreserveRatio(true);
        bottom.setGraphic(chatIcon);
        bottom.setId("buttonChatAction");
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
        Text descriptionTitle  = new Text("Description");
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
    public String getElements(ArrayList<TimePeriod> list) {
        String text = "";
        if (list.size() == 0) {
            text = "-";
        } else {
            for (int i = 0; i < list.size(); i++) {
                text += list.get(i).getStart() + "-" + list.get(i).getEnd();
                if (i < list.size() - 1) {
                    text += "\n";
                }
            }
        }
        return text;
    }

    // Available
    public VBox right(AvailableTimes availabletimes) {
        VBox vbox = new VBox();
        VBox available = new VBox();
        VBox days = new VBox();

        Text availability = new Text("Availability");
        available.getChildren().addAll(availability);

        HBox mon = new HBox();
        Text monday = new Text("Monday:\t\t");
        Text monTimes = new Text(getElements(availabletimes.getMonday())
        );
        mon.getChildren().addAll(monday, monTimes);

        HBox tue = new HBox();
        Text tuesday = new Text("Tuesday:\t\t");
        Text tueTimes = new Text(getElements(availabletimes.getTuesday()));
        tue.getChildren().addAll(tuesday, tueTimes);

        HBox wed = new HBox();
        Text wednesday = new Text("Wednesday:\t");
        Text wedTimes = new Text(getElements(availabletimes.getWednesday()));
        wed.getChildren().addAll(wednesday, wedTimes);

        HBox thu = new HBox();
        Text thursday = new Text("Thursday:\t\t");
        Text thuTimes = new Text(getElements(availabletimes.getThursday()));
        thu.getChildren().addAll(thursday, thuTimes);

        HBox fri = new HBox();
        Text friday = new Text("Friday:\t\t");
        Text friTimes = new Text(getElements(availabletimes.getFriday()));
        fri.getChildren().addAll(friday, friTimes);

        HBox sat = new HBox();
        Text saturday = new Text("Saturday:\t\t");
        Text satTimes = new Text(getElements(availabletimes.getSaturday()));
        sat.getChildren().addAll(saturday, satTimes);

        HBox sun = new HBox();
        Text sunday = new Text("Sunday:\t\t");
        Text sunTimes = new Text(getElements(availabletimes.getSunday()));
        sun.getChildren().addAll(sunday, sunTimes);

        days.getChildren().addAll(mon, tue, wed, thu, fri, sat, sun);
        vbox.getChildren().addAll(available, days);
        available.setId("available");
        days.setId("days");
        vbox.setId("right");
        return vbox;
    }

    // the whole screen (except footer)
    public HBox content(VBox _left, VBox _right) {
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
        scrollPane.setFitToWidth(true);
        scrollPane.setId("ID");
        return scrollPane;
    }

    // center
    public void center() {
        center = scroll(content(left(head(img, headText(name, age)), centerLeft(description, university, study)), right(availabletimes)));
        center.setId("screen");
    }


}
