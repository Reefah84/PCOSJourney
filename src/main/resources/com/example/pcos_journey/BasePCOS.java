/*package com.example.newpcos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BasePCOS {
    @FXML
    private Button pback;

    @FXML
    void pbackbtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) pback.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("baseHEALTH.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

    }
}*/

package com.example.newpcos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BasePCOS {
    @FXML
    private Button pback;

    @FXML
    private TitledPane titledPane1;

    @FXML
    private TitledPane titledPane2;

    @FXML
    private TitledPane titledPane3;

    @FXML
    private TitledPane titledPane4;

    @FXML
    void initialize() {
        // Ensure all TitledPanes are initially closed
        titledPane1.setExpanded(false);
        titledPane2.setExpanded(false);
        titledPane3.setExpanded(false);
        titledPane4.setExpanded(false);
    }

    @FXML
    void pbackbtn(MouseEvent event) throws IOException {
        Stage stage = (Stage) pback.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("baseHEALTH.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}



