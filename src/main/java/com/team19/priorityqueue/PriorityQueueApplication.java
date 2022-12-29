package com.team19.priorityqueue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Основний клас JavaFX програми
 */
public class PriorityQueueApplication extends Application {

    /**
     * Відкриває вікно програми та встановлює його параметри
     * @param stage сцена JavaFX додатку
     */
    @Override
    public void start(Stage stage) {
        Scene scene;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PriorityQueueApplication.class.getResource("main-view.fxml"));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            AlertUtils.showError("Cannot load main view", e.getMessage());
            return;
        }

        stage.setTitle("PriorityQueue");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}