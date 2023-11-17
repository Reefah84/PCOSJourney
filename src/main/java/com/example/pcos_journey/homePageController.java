package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class homePageController {

    public Button LoginButton;
    public MenuButton Health;

    public Button quiz;
    public Button doctorList;
    public Button symptomTrack;
    public MenuItem nutrition;
    public MenuItem fertile;
    public MenuItem fit;
    public MenuItem Mental;
    public Button gy;

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
    public void showListOfDoctors(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListofDoctors.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) gy.getScene().getWindow();

            // Set the new content in the same window
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    public void setNutrition(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("baseDiet.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) nutrition.getParentPopup().getOwnerWindow();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    public void setSymptomTrack(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HormoneTracker.fxml")); // Adjust the path
        try {
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) symptomTrack.getScene().getWindow();
            Scene scene = new Scene(root);
            // If you have a stylesheet
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
