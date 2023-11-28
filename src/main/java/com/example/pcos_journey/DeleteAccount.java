package com.example.pcos_journey;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteAccount extends dashboardUserController{
    public Button delete;
    public Button back;
    private String userEmail;

    public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
    }
    public void onDeleteAccount(ActionEvent event) {
            deleteUserFolder(userEmail);
            UserSession.logout(); // Logout the user session
            setLogout(event);
            closePopup();
            redirectToLogin();
    }

    // Existing setLogout function
    public void setLogout(ActionEvent event) {
        // Your existing logic to switch to the login screen
        closePopup();
    }
    private void redirectToLogin() {
            try {
                Stage stage = (Stage) delete.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                stage.setScene(new Scene(loader.load()));
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exceptions
            }
        }

        public void onGoBack(ActionEvent event){
            closePopup();
        }

        private void closePopup() {
            Stage stage = (Stage) back.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }
        }

}
