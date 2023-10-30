package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class forgotpasswordController {

    public Button sendotp;
    public TextField enteremail;
    public Label valid;

    public void setSendotp(ActionEvent event)
    {
        if(!checkEmail()) valid.setVisible(true);
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OTP_code.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1540, 790);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("OTP code");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
    public boolean checkEmail()
    {
        String email=enteremail.getText();
        if(email.isEmpty())
        {
            return false;
        }
        else return true;
    }
}