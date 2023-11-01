package com.example.newpcos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseHEALTH implements Initializable {
    @FXML
    private Button pcos;

    @FXML
    void pcosBtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) pcos.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("basePCOS.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    @FXML
    private Button fertility;
    @FXML
    void fertilitybtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) fertility.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("baseFertility.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

    }
    @FXML
    private Button diet;
    @FXML
    void dietbtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) diet.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("baseDiet.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

    }
    @FXML
    private Button ment;


    @FXML
    void mentbtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) ment.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mentalHealth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
