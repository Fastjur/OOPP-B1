package gui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class GUISideBarConstructor extends VBox {

    public GUISideBarConstructor() {
        super();

        Insets sideBarBtnInsets = new Insets(0, 0, 0, 20);
        Insets noPaddingInsets = new Insets(0);

        Button editProfileBtn = new Button();
        editProfileBtn.setPadding(sideBarBtnInsets);
        editProfileBtn.setMinSize(200, 50);
        editProfileBtn.setPrefSize(200, 50);
        editProfileBtn.setMaxSize(200, 50);
        editProfileBtn.setText("Edit Profile");
        editProfileBtn.setTextAlignment(TextAlignment.LEFT);
        editProfileBtn.getStyleClass().add("sidebarBtn");

        Button logoutBtn = new Button();
        logoutBtn.setPadding(sideBarBtnInsets);
        logoutBtn.setMinSize(200, 50);
        logoutBtn.setPrefSize(200, 50);
        logoutBtn.setMaxSize(200, 50);
        logoutBtn.setText("Logout");
        logoutBtn.setOnMouseClicked(e -> System.exit(0));
        logoutBtn.getStyleClass().add("sidebarBtn");

        super.setPadding(noPaddingInsets);
        super.setSpacing(0);
        super.getChildren().addAll(editProfileBtn, logoutBtn);
        super.setAlignment(Pos.TOP_LEFT);
        super.setId("sidebar");
    }
}
