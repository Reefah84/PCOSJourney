package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SeePatientMessage {
    public AnchorPane patientmessage;
    public Label message;
    public Button reply;
    public Button back;

    public void setMessage(String messageContent) {
        message.setText(messageContent);
        message.setVisible(true);
    }
    private String userEmail;

    // ... [other methods]
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void handleReplyAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sendMailPopupUser.fxml"));
            Parent root = loader.load();

            SendMailPopupUser sendMailController = loader.getController();
            sendMailController.setUserEmail(userEmail); // Pass the extracted user email to the SendMailPopupUser controller

            Stage currentStage = (Stage) reply.getScene().getWindow();
            currentStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleBackAction(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }
}
