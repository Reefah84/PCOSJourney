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

public class homePageController {

    public Button LoginButton;
    public MenuButton Health;
    public MenuButton allDoctors;
    public Button News;
    public Button quiz;
    public Button doctorList;
    public Button symptomTrack;
    public void setLoginButton(ActionEvent event) throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();

// Get the current stage (window)
        Stage stage = (Stage)LoginButton.getScene().getWindow();

// Set the new content in the same window
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
    public void setQuiz(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz_Disclaimer.fxml"));
        Parent root = loader.load();

        // Get the current stage
        Stage stage = (Stage) quiz.getScene().getWindow();

        // Set the new content in the same window
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
