import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Yassin on 09-Dec-15.
 */
public class GuiMain extends Application {
    @Override
    public void start(Stage PrimaryStage) throws Exception {
        URL location = getClass().getResource("GuiLogin.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            PrimaryStage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
        // Stage Settings
        PrimaryStage.setFullScreen(true);
        PrimaryStage.setMinHeight(600);
        PrimaryStage.setMinWidth(800);
        PrimaryStage.setMaxHeight(1080);
        PrimaryStage.setMaxWidth(1920);
        PrimaryStage.show();
    }

    //public static void main(String[] args) {
     //   launch(args);
   // }

    public static void showRegisterScene(){
       // URL location = getClass().getResource("GuiLogin.fxml");
        //FXMLLoader fxmlLoader = new FXMLLoader(location);
        //try {
         //   Parent root = fxmlLoader.load();
          //  Scene scene = new Scene(root);
           // PrimaryStage.setScene(scene);
        //} catch (IOException io) {
          //  io.printStackTrace();
        //}

    }

}
