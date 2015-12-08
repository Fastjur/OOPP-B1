package Client;

import javafx.application.Application;
import javafx.stage.Stage;

public class SidebarGUI extends Application {

    @Override
    public void start(Stage PrimaryStage) throws Exception{
        PrimaryStage.setTitle("Sidebar-only version 1.0");
        PrimaryStage.setResizable(true);
        PrimaryStage.setMinHeight(600);
        PrimaryStage.setMinWidth(800);
        PrimaryStage.setMaxHeight(1080);
        PrimaryStage.setMaxWidth(1920);
        PrimaryStage.show();

    }
}
