module com.example.pcos_journey {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;


    opens com.example.pcos_journey to javafx.fxml;
    exports com.example.pcos_journey;
}