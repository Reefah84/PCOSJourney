package com.example.pcos_journey;




//NEW


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Hormone_Tracker implements HormoneTracker {


    public Label labelside;
    public Label labeup;
    public ImageView homeButton;
    @FXML
    private MenuButton menuButton;

    @FXML
    private Label label;

    @FXML
    private MenuItem item1;

    @FXML
    private MenuItem item2;

    @FXML
    private MenuItem item3;

    @FXML
    private TextField value;

    @FXML
    private Label result;

    @FXML
    private Button submit;

    String enteredvalue;
    float valuee;
    public void initialize(ActionEvent actionEvent) {
        URL url = null;
        ResourceBundle resourceBundle = null;
        initialize(url,resourceBundle);
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        item1.setOnAction(event -> {
            label.setText("Testosteron Tracker:");

            submit.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    enteredvalue = value.getText();
                    System.out.println(enteredvalue);


                    if(!enteredvalue.isEmpty())
                    {
                        valuee = Float.parseFloat(value.getText());
                        //System.out.println(valuee);
                    }
                    else
                    {
                        //valuee = Float.parseFloat(enteredvalue);
                        //System.out.println(valuee);
                    }

                    if(valuee>5.71 && valuee<77)
                    {
                        result.setText("Your Testosteron level is in range. Reference Range is: 5.71-77 ng/dl");
                    }
                    else if(valuee<5.71)
                    {
                        result.setText("Your Testosteron level is low. Reference Range is: 5.71-77 ng/dl");
                    }
                    else
                    {
                        result.setText("Your Testosteron level is high. Reference Range is: 5.71-77 ng/dl");
                    }

                }

            });


        });

        item2.setOnAction(event -> {
            label.setText("Prolactin Tracker:");

            submit.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    enteredvalue = value.getText();
                    System.out.println(enteredvalue);


                    if(!enteredvalue.isEmpty())
                    {
                        valuee = Float.parseFloat(value.getText());
                        //System.out.println(valuee);
                    }
                    else
                    {
                        //valuee = Float.parseFloat(enteredvalue);
                        //System.out.println(valuee);
                    }

                    if(valuee > 1 && valuee < 25)
                    {
                        result.setText("Your Prolactin level is in range. Reference Range is: 1-25 ng/ml");
                    }
                    else if(valuee < 1)
                    {
                        result.setText("Your Prolactin level is low. Reference Range is: 1-25 ng/ml");
                    }
                    else
                    {
                        result.setText("Your Prolactin level is high. Reference Range is: 1-25 ng/ml");
                    }

                }

            });


        });

        item3.setOnAction(event -> {
            label.setText("TSH Tracker:");

            submit.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    enteredvalue = value.getText();
                    System.out.println(enteredvalue);


                    if(!enteredvalue.isEmpty())
                    {
                        valuee = Float.parseFloat(value.getText());
                        //System.out.println(valuee);
                    }
                    else
                    {
                        //valuee = Float.parseFloat(enteredvalue);
                        //System.out.println(valuee);
                    }

                    if(valuee > 0.35 && valuee < 5.50)
                    {
                        result.setText("Your TSH level is in range. Reference Range is: 0.35-5.50 uIU/ml");
                    }
                    else if(valuee < 0.35)
                    {
                        result.setText("Your TSH level is low. Reference Range is: 0.35-5.50 uIU/ml");
                    }
                    else
                    {
                        result.setText("Your TSH level is high. Reference Range is: 0.35-5.50 uIU/ml");
                    }

                }

            });


        });




    }
    public void setHomeButton(MouseEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage_attempt2.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error dialog)
        }
    }

    
}