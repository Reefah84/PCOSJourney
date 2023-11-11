package com.example.pcos_journey;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.Objects;

public class loginController {
    public CheckBox doctorcheck;
    public CheckBox usercheck;
    public ImageView backHome;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public Button signupbutton;
    @FXML
    public  Button loginbutton;
    public Button forgotpasswordbutton;
    public Label emptyusername;
    public void setBackHome(MouseEvent event) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage_attempt2.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) backHome.getScene().getWindow();

            // Set the new content in the same window
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isAuthenticated(String enteredUsername, String enteredPassword, boolean isUserSelected, boolean isDoctorSelected) {
        // Construct the path to the user's folder
        String userFolderPath;
        if (isUserSelected) {
            userFolderPath = "E:\\Java\\PCOS_Journey\\src\\main\\java\\com\\example\\pcos_journey\\UserData\\" + enteredUsername;
        } else if (isDoctorSelected) {
            userFolderPath = "E:\\Java\\PCOS_Journey\\src\\main\\java\\com\\example\\pcos_journey\\DRData\\" + enteredUsername;
        } else {
            // Invalid user type
            return false;
        }
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
        // Check if the email is a valid Gmail address
        if (!enteredUsername.endsWith("@gmail.com")) {
            emptyusername.setText("Invalid email. Please provide a correct Gmail address.");
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
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(dashboardFxml));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage)loginbutton.getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
            stage.setScene(scene);
        } else {
            emptyusername.setText("Wrong credentials or user type. Please try again or click forgot password.");
            emptyusername.setVisible(true);
        }
    }



    public void setSignupbutton(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("signup.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage)signupbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
    }
    public void setForgotpasswordbutton(ActionEvent event)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forgot_password.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage)forgotpasswordbutton.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
    }
}