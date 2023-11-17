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
    public void handleReplyAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SendMailPopupUser.fxml"));
            Parent root = loader.load();

            SendMailPopupUser controller = loader.getController();

            Stage currentStage = (Stage) reply.getScene().getWindow();
            controller.setStage(currentStage); // Pass the current stage to the controller

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
