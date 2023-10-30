package com.example.pcos_journey;

import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NotificationPane extends StackPane {
    private final Label label;

    public NotificationPane() {
        label = new Label();
        getChildren().add(label);
        setOpacity(0);
    }

    public void setMessage(String message) {
        label.setText(message);
    }

    public void setBackground(Paint background) {
        label.setStyle("-fx-background-color: " + toCssColor(background) + "; -fx-background-radius: 5; -fx-padding: 10;");
    }

    private String toCssColor(Paint color) {
        if (color instanceof Color) {
            Color c = (Color) color;
            return String.format("#%02X%02X%02X",
                    (int) (c.getRed() * 255),
                    (int) (c.getGreen() * 255),
                    (int) (c.getBlue() * 255));
        }
        return "white";
    }

    public void setTextFill(Paint textFill) {
        label.setTextFill(textFill);
    }

    public void show() {
        setOpacity(1);
    }

    public void hide() {
        setOpacity(0);
    }

}


