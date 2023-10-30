package com.example.pcos_journey;

import com.example.pcos_journey.UserSession;
import com.example.pcos_journey.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class loginController {
    public CheckBox doctorcheck;
    public CheckBox usercheck;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public Button signupbutton;
    @FXML
    public  Button loginbutton;
    public Button forgotpasswordbutton;
    public Label emptyusername;
    private boolean isAuthenticated(String enteredUsername, String enteredPassword, boolean isUserSelected, boolean isDoctorSelected) {
        // Construct the path to the user's folder
        String userFolderPath = "E:\\Java\\PCOS_Journey\\src\\main\\java\\com\\example\\pcos_journey\\UserData\\" + enteredUsername;

        // Check if the user's folder exists
        File userFolder = new File(userFolderPath);
        if (userFolder.exists() && userFolder.isDirectory()) {
            // User folder exists, indicating a valid user
            File userInfoFile=null;
            if(isUserSelected) userInfoFile = new File(userFolder, "USER_info.txt");
            else if(isDoctorSelected)  userInfoFile= new File(userFolder, "DR_info.txt");
            boolean checkboxAndFirstLineMatch = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(userInfoFile))) {
                String firstLine = reader.readLine();
                if (firstLine != null) {
                    if (isUserSelected && firstLine.equals("USER")) {
                        checkboxAndFirstLineMatch = true;
                    } else if (isDoctorSelected && firstLine.equals("DR")) {
                        checkboxAndFirstLineMatch = true;
                    }
                }

                if (checkboxAndFirstLineMatch) {
                    // Now, check the password
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("Password: ")) {
                            String storedPassword = line.substring("Password: ".length());
                            return storedPassword.equals(enteredPassword);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return false; // User folder does not exist or checkboxes don't match
    }


    public void setLoginbutton(ActionEvent event) {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        // Check if the username and password fields are empty
        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            emptyusername.setText("Please fill in all the fields.");
            emptyusername.setVisible(true);
            return;
        }

        // Check the selected checkboxes
        boolean isUserSelected = usercheck.isSelected();
        boolean isDoctorSelected = doctorcheck.isSelected();

        if (isUserSelected && isDoctorSelected) {
            emptyusername.setText("Please check one type of user.");
            emptyusername.setVisible(true);
            return;
        }

        // Load the appropriate dashboard based on the selected checkboxes
        String dashboardFxml;
        if (isUserSelected) {
            dashboardFxml = "dashboard_user.fxml";
        } else if (isDoctorSelected) {
            dashboardFxml = "dashboard_doctor.fxml";
        } else {
            // Neither user nor doctor is selected
            emptyusername.setText("Please check one type of user.");
            emptyusername.setVisible(true);
            return;
        }

        // If authentication is successful, proceed to the selected dashboard
        if (isAuthenticated(enteredUsername, enteredPassword, isUserSelected, isDoctorSelected)) {
            // Create a User object with the username
            User loggedInUser = new User(enteredUsername);

            // Set the logged-in user in the UserSession
            UserSession.setLoggedInUser(loggedInUser);

            // Load the selected dashboard
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(dashboardFxml));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1540, 790);
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage = new Stage();
            stage.setTitle("Dashboard");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } else {
            emptyusername.setText("Wrong credentials or user type. Please try again or click forgot password.");
            emptyusername.setVisible(true);
        }
    }



    public void setSignupbutton(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signup.fxml"));
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
    public void setForgotpasswordbutton(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forgot_password.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1540, 790);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage=new Stage();
        stage.setTitle("Forgot Password");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}