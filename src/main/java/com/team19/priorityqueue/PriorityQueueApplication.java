package com.team19.priorityqueue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PriorityQueueApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PriorityQueueApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PriorityQueue");
        stage.setScene(scene);
        stage.show();

        stage.setMinHeight(500);
        stage.setMinWidth(850);
        stage.setMaxHeight(500);
        stage.setMaxWidth(850);

        stage.setResizable(false);

    }

    public static void main(String[] args) {
        launch();
    }
}