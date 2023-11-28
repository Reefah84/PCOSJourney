package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class tracker {

    public Button FAQ;
    public ImageView home;
    public Button logout;
    public Button Health;
    public Button gy;
    public Button hormoneButton;
    public Button medicine;
    private String userEmail;
    public void initialize() {
        // Access the logged-in user from UserSession
        User loggedInUser = UserSession.getLoggedInUser();
        UserSession.getInstance().setUserEmail(loggedInUser.getUsername());
        if (loggedInUser != null) {
            // Display the username in the usernameLabel
            userEmail = loggedInUser.getUsername();
        }
    }
    public void setHome(MouseEvent mouseEvent) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage_attempt2.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) home.getScene().getWindow();

            // Set the new content in the same window
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLogout(ActionEvent event) {
        UserSession.logout();
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

    public void setHormoneButton(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HormoneTracker.fxml")); // Adjust the path
        try {
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)hormoneButton.getScene().getWindow();
            Scene scene = new Scene(root);
            // If you have a stylesheet
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void setMedicine(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("medicineTracker.fxml")); // Adjust the path
        try {
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)medicine.getScene().getWindow();
            Scene scene = new Scene(root);
            // If you have a stylesheet
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public void setFAQ(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatBot.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)FAQ.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
        stage.setScene(scene);
    }

    public void setHealth(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("baseHEALTH.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)Health.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
        stage.setScene(scene);
    }
}
