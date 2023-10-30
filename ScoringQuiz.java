package com.example.pcos_j;

/*import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//import java.awt.*;
import java.io.IOException;

public class ScoringQuiz extends Application {
    @FXML
    private Label scoreLabel;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ScoringQuiz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Quiz!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void setScore(int score) {
        //System.out.println(score);
        scoreLabel.setText("Your score: "+score);
    }
}*/


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoringQuiz {

    @FXML
    private Label scoreLabel;

    public void setScore(int score) {

        if(score == 0)
        {
            scoreLabel.setText("Congratulations! You are NOT diagnosed with PCOS.");
        }
        else if(score==4)
        {
            scoreLabel.setText("You HAVE PCOS. Please contact with a professional doctor for more information.");
        }
        else
        {
            scoreLabel.setText("You MIGHT have PCOS. Consult with a professional doctor");
        }

    }

    public void switchToScene3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("QuizDisclaimer.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

