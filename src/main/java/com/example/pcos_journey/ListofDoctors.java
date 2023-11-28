package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ListofDoctors {

    public Button LoginButton;
    public Button Health;
    public MenuButton allDoctors;
    public ImageView home;
    public Button gy;
    public Button FAQ;
    @FXML
    private ListView<String> doctorsListView;
    private Map<String, String> doctorEmailsMap = new HashMap<>();
    public void initialize() {
        updateLoginButton();
        loadDoctorsList();
    }
    private void updateLoginButton() {
        if (UserSession.getInstance().isUserLoggedIn()) {
            User loggedInUser = UserSession.getLoggedInUser();
            if (loggedInUser.isUser()) {
                LoginButton.setText("USER");
                LoginButton.setOnAction(this::openDashboardUser);
            } else if (loggedInUser.isDoctor()) {
                LoginButton.setText("DOCTOR");
                LoginButton.setOnAction(this::openDashboardDoctor);
            }
        } else {
            LoginButton.setText("LOGIN");
            LoginButton.setOnAction(this::openLogin);
        }
    }

    private void openDashboardUser(ActionEvent event) {
        navigateTo("dashboard_user.fxml", event);
    }

    private void openDashboardDoctor(ActionEvent event) {
        navigateTo("dashboard_doctor.fxml", event);
    }

    private void openLogin(ActionEvent event) {
        navigateTo("login.fxml", event);
    }

    private void navigateTo(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
    private void loadDoctorsList() {
        File drDataDir = new File("E:/Java/PCOS_Journey/src/main/java/com/example/pcos_journey/DRData");
        String[] doctorEmails = drDataDir.list();

        if (doctorEmails != null) {
            Arrays.stream(doctorEmails).forEach(email -> {
                String formattedName = formatDoctorName(email);
                doctorsListView.getItems().add(formattedName);
                doctorEmailsMap.put(formattedName, email); // Store the original email
            });
        }
    }

    private String formatDoctorName(String email) {
        String namePart = email.split("@")[0]; // Split at '@' and take the first part
        namePart = namePart.replaceAll("\\d", ""); // Remove numbers
        return "Dr " + namePart; // Add 'Dr' prefix
    }
    @FXML
    private void handleDoctorSelection() {
        String selectedDoctor = doctorsListView.getSelectionModel().getSelectedItem();
        if (selectedDoctor != null) {
            try {
                // Extract doctor email from the selected item
                String doctorEmail = doctorEmailsMap.get(selectedDoctor);
                //System.out.println("Calling from list of doctors "+doctorEmail);
                // Load SendMailPopupUser FXML and pass the doctor email
                FXMLLoader loader = new FXMLLoader(getClass().getResource("sendMailPopupUser.fxml"));
                Parent root = loader.load();

                SendMailPopupUser controller = loader.getController();
                controller.initialize(doctorEmail); // Pass the extracted doctor email

                // Show the new stage
                Stage stage = new Stage();
                stage.setTitle(selectedDoctor);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception
            }
        }
    }
    private String extractEmailFromName(String doctorName) {
        // Assuming doctorName is in the format "Dr FirstNameLastName"
        if (doctorName.startsWith("Dr ")) {
            doctorName = doctorName.substring(3); // Remove "Dr " prefix
        }
        return doctorName.replaceAll("\\s+", "") + "@gmail.com"; // Replace spaces and append "@gmail.com"
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
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chatBot.fxml"));
        Parent root = fxmlLoader.load();

// Get the current stage (window)
        Stage stage = (Stage)FAQ.getScene().getWindow();
// Set the new content in the same window
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("button.css")).toExternalForm());
        stage.setScene(scene);

    }
}
