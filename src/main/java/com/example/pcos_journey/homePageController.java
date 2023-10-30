package com.example.pcos_journey;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class homePageController {

    public Button LoginButton;
    public MenuButton Health;
    public MenuButton allDoctors;
    public Button News;
    public Button quiz;
    public Button doctorList;
    public Button symptomTrack;
    public void setLoginButton(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1540, 790);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage=new Stage();
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
