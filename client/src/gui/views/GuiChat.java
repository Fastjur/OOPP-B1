package gui.views;

/**
 * @author Zoë van Steijn, Emma Jimmink
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import shared.User;

public class GuiChat extends BorderPane {

    private VBox userChat;
    private TextField bottom;
    private ScrollPane chatfield;
    private HBox bottombox;
    private boolean scrollToBottom = false;
    private User match;

    public GuiChat() {
        super();
        Label label = new Label("Use the sidebar on the left to get started");
        label.setStyle("-fx-font-size: 16pt");
        super.setCenter(label);
    }

    public GuiChat(User match){
        super();
        this.match = match;
        userChat();
        chatScreen();
        bottomBar();
        super.setCenter(chatfield);
        super.setBottom(bottombox);
    }

    private void bottomBar() {
        bottom = new TextField();
        Button send = new Button();
        send.setPadding(new Insets(3,3,3,3));
        send.setId("send-button");
        send.setText("Send");
        send.setOnAction(e -> sendMessage(bottom.getText()));

        bottom.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    sendMessage(bottom.getText());
                }
            }
        });

        bottombox = new HBox();
        HBox.setHgrow(bottom, Priority.ALWAYS);
        bottombox.setPrefHeight(60);
        bottombox.setAlignment(Pos.CENTER);
        bottombox.setPadding(new Insets(0,20,0,20));
        bottombox.getChildren().addAll(bottom, send);
    }

    private void chatScreen() {
        chatfield = new ScrollPane();
        chatfield.setContent(userChat);
        chatfield.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatfield.setFitToHeight(true);
        chatfield.setFitToWidth(true);
        chatfield.setId("scroll");

        chatfield.setVvalue(chatfield.getVmax());
        chatfield.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(scrollToBottom){
                    chatfield.setVvalue(chatfield.getVmax());
                    scrollToBottom = false;
                }
            }
        });
    }

    private void userChat() {
        userChat = new VBox();
        userChat.setPadding(new Insets(10,10,10,10));
        userChat.setSpacing(10);
        userChat.setFillWidth(true);
    }

    public void receiveMessage(String textbox) {
        if(!textbox.equals("")) {
            HBox bubble = new HBox();
            bubble.setAlignment(Pos.TOP_LEFT);
            Text text = new Text(textbox);
            TextFlow textBubble = new TextFlow(text);
            textBubble.setStyle("-fx-background-color: #26A69A");
            messageOnScreen(bubble, textBubble, textbox);
        }
    }

    public void sendMessage(String textbox) {
        if(!textbox.equals("")) {
            HBox bubble = new HBox();
            bubble.setAlignment(Pos.TOP_RIGHT);
            Text text = new Text(textbox);
            TextFlow textBubble = new TextFlow(text);
            textBubble.setStyle("-fx-background-color: #F06292");
            messageOnScreen(bubble, textBubble, textbox);
            GUILauncher.sendMessage(textbox, match);
        }
    }

    private void messageOnScreen(HBox bubble, TextFlow textBubble, String textbox){
        textBubble.setMaxWidth(900);
        textBubble.setPadding(new Insets(10,10,10,10));
        textBubble.setId("textBubble");
        bubble.getChildren().add(textBubble);

        userChat.getChildren().add(bubble);
        bottom.setText("");
        scrollToBottom = true;
    }

    public User getMatch() {
        return this.match;
    }
}
