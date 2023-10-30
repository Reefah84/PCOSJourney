package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class OTPController {


    public Button resendOTP;
    public TextField OTP;
    public Button nextbutton;
    public Label incorrect;

    public boolean setOTP() {
        String otp=OTP.getText();
        if(otp.isEmpty()) return false;
        return true;
    }
    public void setNextbutton(ActionEvent event) {
        if(!setOTP()) {
            incorrect.setText("Please enter OTP in the field");
            incorrect.setVisible(true);
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("reset_password.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1540, 790);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Reset pass");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void setResendOTP() {
        incorrect.setText("Email resent");
        incorrect.setVisible(true);
    }
}