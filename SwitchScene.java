package com.example.pcos_j;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;

public class SwitchScene {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button yesButton1;
    @FXML
    private Button noButton1;
    @FXML
    private Button yesButton2;
    @FXML
    private Button noButton2;
    @FXML
    private Button yesButton3;
    @FXML
    private Button noButton3;
    @FXML
    private Button yesButton4;
    @FXML
    private Button noButton4;




    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Quiz_Disclaimer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("quizpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*public void switchToScene3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("QuizDisclaimer.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    //int correct_guesses = 0;
    //char answer;


    @FXML

    private Button submitButton;

    private int score = 0;

    public void initialize(URL location, ResourceBundle resources)
    {
        yesButton1.setOnAction(event -> handleAnswer(event, true));
        noButton1.setOnAction(event -> handleAnswer(event, false));
        yesButton2.setOnAction(event -> handleAnswer(event, true));
        noButton2.setOnAction(event -> handleAnswer(event, false));
        yesButton3.setOnAction(event -> handleAnswer(event, true));
        noButton3.setOnAction(event -> handleAnswer(event, false));
        yesButton4.setOnAction(event -> handleAnswer(event, true));
        noButton4.setOnAction(event -> handleAnswer(event, false));
    }

    private void handleAnswer(ActionEvent event, boolean isYes)
    {
        if(isYes)
        {
            //System.out.println("hi");
            score++;
        }

        System.out.println(score);
    }

    public void SubmitButton(ActionEvent event) throws IOException {
        //root = FXMLLoader.load(getClass().getResource("ScoringQuiz.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoringQuiz.fxml"));
        Parent root = loader.load();
        ScoringQuiz scorequiz = loader.getController();
        //ScoringQuiz scorequiz;
        scorequiz.setScore(score);

        /*Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();*/

        // Show the result page
        // Example:
        /*Stage resultStage = new Stage();
        Scene scene = new Scene(root);
        resultStage.setScene(scene);
        resultStage.show();*/

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
