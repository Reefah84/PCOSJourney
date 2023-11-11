package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class dashboardUserController {

    public Button logout;
    public MenuButton healthbutton;
    public MenuButton alldoctor;
    public Button news;
    public Label welcome;
    public Button symptomButton;
    public Button weightButton;
    public Button DoctorButton;
    public VBox notification;

    public void initialize() {
        // Access the logged-in user from UserSession
        User loggedInUser = UserSession.getLoggedInUser();

        if (loggedInUser != null) {
            // Display the username in the usernameLabel
            welcome.setText("Welcome " + loggedInUser.getUsername());
            welcome.setVisible(true);
        }
    }
    public void setLogout(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homepage_attempt2.fxml")); // Adjust the path
        try {
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)logout.getScene().getWindow();
            Scene scene = new Scene(root);
            // If you have a stylesheet
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
