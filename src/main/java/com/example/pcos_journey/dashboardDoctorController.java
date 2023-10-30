package com.example.pcos_journey;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardDoctorController {

    public Button logout;
    public MenuButton healthbutton;
    public MenuButton alldoctor;
    public Button news;
    public Label welcome;
    public VBox notification;

    public void initialize() {
        // Access the logged-in user from UserSession
        User loggedInUser = UserSession.getLoggedInUser();

        if (loggedInUser != null) {
            // Display the username in the usernameLabel
            welcome.setText("Welcome Dr" + loggedInUser.getUsername());
            welcome.setVisible(true);
        }
    }
    public void setLogout(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepage_attempt2.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1540, 790);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage=new Stage();
        stage.setTitle("homepage");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
