package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ListofDoctors {

    public Button LoginButton;
    public MenuButton Health;
    public MenuButton allDoctors;
    @FXML
    private ListView<String> doctorsListView;

    public void initialize() {
        loadDoctorsList();
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
            doctorsListView.getItems().addAll(
                    Arrays.stream(doctorEmails)
                            .map(this::formatDoctorName)
                            .collect(Collectors.toList())
            );
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
                // Load the new FXML (e.g., DoctorDetails.fxml)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard_doctor.fxml"));
                Parent root = loader.load();

                // Create a new Stage for the Doctor Details window
                Stage stage = new Stage();
                stage.setTitle(selectedDoctor); // Optional: Set title to doctor's name
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exception (e.g., show an error message)
            }
        }
    }
}
