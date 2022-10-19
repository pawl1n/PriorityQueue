package com.team19.priorityqueue;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueController {
    private final PriorityQueue<QueueNode<Integer>> queue = new PriorityQueue<>(QueueNode::compareTo);
    private final Random random = new Random();

    @FXML
    private FlowPane flowPane;

    @FXML
    private Button addButton;
    @FXML
    private Button peekButton;
    @FXML
    private Button pollButton;
    @FXML
    private Button sizeButton;
    @FXML
    private Button removeButton;

    @FXML
    private void add() {

        int val = random.nextInt(10);

        QueueNode<Integer> queueNode = new QueueNode<>(val, val);
        queue.add(queueNode);
        System.out.println(queue);
        flowPane.getChildren().add(queueNode.stackPane());

        redrawNodes();
    }


    @FXML
    private void peek() {
        System.out.println(queue.peek());
    }

    @FXML
    private void poll() {
        QueueNode<Integer> queueNode = queue.poll();

        if (queueNode != null) {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200));
            scaleTransition.setNode(queueNode.stackPane());
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(0);
            scaleTransition.setToY(0);
            scaleTransition.setCycleCount(1);
            scaleTransition.setAutoReverse(false);
            scaleTransition.play();

            scaleTransition.setOnFinished(event -> {
                flowPane.getChildren().remove(queueNode.stackPane());
                redrawNodes();
            });
        }

        System.out.println(queue);
    }

    private void redrawNodes() {
        for (QueueNode<Integer> queueNode : queue) {
            flowPane.getChildren().remove(queueNode.stackPane());
        }

        for (QueueNode<Integer> queueNode : queue) {
            StackPane stackPane = queueNode.stackPane();

            queueNode.circle().setStroke(Color.web("F2DF3A"));
            flowPane.getChildren().add(stackPane);
        }

        QueueNode<Integer> peek = queue.peek();
        if (peek != null) {
            peek.circle().setStroke(Color.RED);
        }
    }

}