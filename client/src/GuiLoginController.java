import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Application;

import static javafx.application.Application.launch;


/**
 * Created by Yassin on 09-Dec-15.
 */
public class GuiLoginController {

    @FXML
    private Button RegisterBtn;

    //RegisterBtn.setOnAction(ActionEvent event);

    private void registerBtnAction(ActionEvent event){
        GuiMain.showRegisterScene();

    }



}
