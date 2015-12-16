package gui.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUILauncher extends Application {
    @Override
    public void start(Stage PrimaryStage) throws Exception{

        BorderPane GUI = new BorderPane();
        GUI.setCenter(new GuiProfileConstructor());
        GUI.setLeft(new GUISideBarConstructor());
        Scene GUIScene = new Scene(GUI);
        PrimaryStage.setScene(GUIScene);
        GUIScene.getStylesheets().addAll("/gui/views/css/ProfileStyle.css","/gui/views/css/SideBarStyle.css");
        PrimaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
