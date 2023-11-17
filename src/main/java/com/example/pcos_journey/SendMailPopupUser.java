package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SendMailPopupUser {

    public TextField user;
    public Button backhome;
    public Button sendButton;
    public TextArea message;
    public Label errorMessage;
    private String doctorEmail;
    // Variables to store message and user email
    private String userMessage;
    private String userEmail;

    // Method to initialize the popup with the doctor's email
    public void initialize(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private boolean sendEmail(String message, String subject, String to, String from) {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pcosjourney220@gmail.com", "voklbqljusokjqef");
            }
        });
        session.setDebug(true);
        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            m.setText(message);
            Transport.send(m);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean storeMessage(String message, String userEmail, String doctorEmail) {
        // Doctor's message file path
        File doctorMessageFile = new File("E:/Java/PCOS_Journey/src/main/java/com/example/pcos_journey/DRData/" + doctorEmail + "/message_from_" + userEmail + ".txt");

        // User's sent file path
        File userSentFile = new File("E:/Java/PCOS_Journey/src/main/java/com/example/pcos_journey/UserData/" + userEmail + "/sent_mail_to_" + doctorEmail + ".txt");

        // Message for doctor's file
        String messageForDoctor = "User Email: " + userEmail + "\nMessage: " + message;

        // Message for user's file
        String messageForUser = "Doctor Email: " + doctorEmail + "\nMessage: " + message;

        try {
            // Write messages to respective files
            if (!writeToFile(doctorMessageFile, messageForDoctor) || !writeToFile(userSentFile, messageForUser)) {
                errorMessage.setText("Failed to store the message. Try again.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage.setText("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }


    private boolean writeToFile(File file, String content) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    private void handleSendButtonAction(ActionEvent event) throws IOException {
        // Add your logic to send the message here
        userMessage = message.getText();
        userEmail = user.getText();
        String doctorFolder = "E:/Java/PCOS_Journey/src/main/java/com/example/pcos_journey/DRData/" + doctorEmail;
        String userFolder = "E:/Java/PCOS_Journey/src/main/java/com/example/pcos_journey/UserData/" + userEmail;

        File doctorDir = new File(doctorFolder);
        File userDir = new File(userFolder);

        if (!doctorDir.exists() || !userDir.exists()) {
            errorMessage.setText("Please login or signup first.");
            return;
        }
        String Subject="You received a new mail from the user "+userEmail;
        if(sendEmail(userMessage,Subject,doctorEmail,"pcosjourney220@gmail.com"))
        {
            if (storeMessage(userMessage, userEmail, doctorEmail)) {
                File repliedToFile = new File(doctorFolder + "/replied_to_" + userEmail + ".txt");
                if (writeToFile(repliedToFile, "Replied to " + userEmail + "\nMessage: " + userMessage)) {
                    // Close the popup window after sending the message
                    if (stage != null) {
                        stage.close();
                    }
                } else {
                    errorMessage.setText("Failed to store the reply. Try again.");
                }
            }

        }
        System.out.println("Error in sending mail");
    }
    @FXML
    private void handleGoBackButtonAction(ActionEvent event) {
        if (stage != null) {
            stage.close();
        } else {
            // Handle the case where stage is not set
            Stage currentStage = (Stage) backhome.getScene().getWindow();
            if (currentStage != null) {
                currentStage.close();
            }
        }
    }
}