package com.team19.priorityqueue;

import javafx.scene.control.Alert;

/**
 * Містить методи для виведення оповіщень
 * Допомагає уникнути повторень в коді
 */
public class AlertUtils {
    private AlertUtils() {}

    public static void showError(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showWarning(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showInformation(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
