package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class dashboardUserController extends User{

    public Button logout;
    public Button Health;

    public Label welcome;
    public Button symptomButton;
    public Button weightButton;
    public Button DoctorButton;
    public Button gy;
    public ImageView home;
    public ListView <String> messageFrom;
    public Button FAQ;
    private String userEmail;
    public Button delete;

    public dashboardUserController() {
        super(null); // or however you want to handle this
    }

    public void initialize() {
        // Access the logged-in user from UserSession
        User loggedInUser = UserSession.getLoggedInUser();
        UserSession.getInstance().setUserEmail(loggedInUser.getUsername());
        if (loggedInUser != null) {
            // Display the username in the usernameLabel
            welcome.setText("Welcome " + loggedInUser.getUsername());
            userEmail=loggedInUser.getUsername();
            welcome.setVisible(true);
        }
        loadMessagefrom();
        messageFrom.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedReply = messageFrom.getSelectionModel().getSelectedItem();
                if (selectedReply != null) {
                    openPatientMessagePopup(selectedReply);
                }
            }
        });
        welcome.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                openProfile();
            }
        });
    }
    private void loadMessagefrom() {
        String userDirectoryPath = "E:\\Java\\PCOS_Journey\\src\\main\\java\\com\\example\\pcos_journey\\UserData\\"+userEmail;
        File userDirectory = new File(userDirectoryPath);
        String[] replies = userDirectory.list((dir, name) -> name.startsWith("reply_from_"));
        if (replies != null) {
            messageFrom.getItems().addAll(
                    Arrays.stream(replies)
                            .map(this::formatFileName)
                            .collect(Collectors.toList())
            );
        }
    }
    private String formatFileName(String fileName) {
        return fileName.replace("reply_from_", "Message from ")
                .replace("sent_mail_to_", "Sent mail to ")
                .replace("_", " ")
                .replace(".txt", "");
    }
    private void openPatientMessagePopup(String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeDoctorMessage.fxml"));
            Parent root = loader.load();

            SeeDoctorMessage controller = loader.getController();
            String filePath = "E:\\Java\\PCOS_Journey\\src\\main\\java\\com\\example\\pcos_journey\\UserData\\" + userEmail + "\\" + fileName.replace("Message from ", "reply_from_").replace(" ", "_") + ".txt";
            String messageContent = new String(Files.readAllBytes(Paths.get(filePath)));
            controller.setMessage(messageContent);
            Stage stage = new Stage();
            stage.setTitle("Doctor's Message");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void openProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myProfile.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Profile");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., show an error dialog)
        }
    }
    public void setLogout(ActionEvent event)
    {
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

    public void setSymptomButton(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("trackerlanding.fxml")); // Adjust the path
        try {
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)symptomButton.getScene().getWindow();
            Scene scene = new Scene(root);
            // If you have a stylesheet
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWeightButton(ActionEvent event) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WeightTracker.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) weightButton.getScene().getWindow();

            // Set the new content in the same window
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setHome(MouseEvent event) {
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
    public void setDoctorButton(ActionEvent event)
    {
        try {
            // Load the new FXML file for the popup
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeeMySentMails.fxml"));
            Parent root = loader.load();

            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Set the popup to block interaction with other windows
            popupStage.setTitle("Sent Messages");

            // Set the scene for the popup stage
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            // Show the popup window and wait for it to close
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onDelete(ActionEvent event) {
        deleteUserFolder(userEmail);
        UserSession.logout(); // Logout the user session
        setLogout(event);
    }

    private void deleteUserFolder(String userEmail) {
        String userFolderPath = "E:\\Java\\PCOS_Journey\\src\\main\\java\\com\\example\\pcos_journey\\UserData\\" + userEmail;
        File userFolder = new File(userFolderPath);
        deleteDirectory(userFolder);
    }

    private void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
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
