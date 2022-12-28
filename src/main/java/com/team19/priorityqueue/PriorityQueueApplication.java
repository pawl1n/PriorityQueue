package com.team19.priorityqueue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PriorityQueueApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PriorityQueueApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PriorityQueue");
        stage.setScene(scene);
        stage.show();

        stage.setMinHeight(550);
        stage.setMinWidth(860);
        stage.setMaxHeight(550);
        stage.setMaxWidth(860);

        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}