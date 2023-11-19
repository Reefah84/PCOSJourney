package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WeightTrackerController {

    public ImageView home;
    public TextField weight;
    public TextField height;
    public Label BMIresult;
    public Label Advice;
    public Button submit;
    public void initialize() {
        String userEmail = UserSession.getInstance().getUserEmail();
        // Use userEmail to load user-specific data
    }
    public void calculateBMI(ActionEvent event) {
        try {
            float weightValue = Float.parseFloat(weight.getText());
            float heightValue = Float.parseFloat(height.getText()) / 100; // Convert cm to meters
            if (weightValue <= 0 || heightValue <= 0) {
                throw new IllegalArgumentException();
            }

            float bmi = weightValue / (heightValue * heightValue);
            BMIresult.setText(String.format("Your BMI is: %.2f", bmi));
            giveAdvice(bmi);
        } catch (NumberFormatException e) {
            BMIresult.setText("Please enter correct values");
            Advice.setText("");
            BMIresult.setVisible(true);
        } catch (IllegalArgumentException e) {
            BMIresult.setText("Please enter positive values");
            Advice.setText("");
            BMIresult.setVisible(true);
        }
    }

    private void giveAdvice(float bmi) {
        if (bmi < 18.5) {
            Advice.setText("Underweight: Try to gain some weight for a healthy BMI.");
            Advice.setVisible(true);
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            Advice.setText("Normal: Keep up the good work for a healthy BMI.");
            Advice.setVisible(true);
        } else if (bmi >= 25 && bmi <= 29.9) {
            Advice.setText("Overweight: Consider diet and exercise to reach a healthier BMI.");
            Advice.setVisible(true);
        } else {
            Advice.setText("Obesity: It's important to consult with a healthcare provider for guidance.");
            Advice.setVisible(true);
        }
    }

    public void setBackHome(MouseEvent event) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard_user.fxml"));
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
}
