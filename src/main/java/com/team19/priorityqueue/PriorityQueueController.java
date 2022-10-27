package com.team19.priorityqueue;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueController {
    private final PriorityQueue<QueueNode<Integer>> queue = new PriorityQueue<>(QueueNode::comparePriorityTo);
    private final Random random = new Random();
    private QueueNode<Integer> selectedNode;

    @FXML
    private FlowPane flowPane;

    @FXML
    private void add() {
        int val = random.nextInt(10);

        QueueNode<Integer> queueNode = new QueueNode<>(val, val);
        queue.add(queueNode);
        flowPane.getChildren().add(queueNode.stackPane());

        queueNode.stackPane().setOnMouseClicked(event -> {
            selectedNode = queueNode;
            redrawNodes();
        });

        queueNode.stackPane().getStyleClass().add("stackPane");
        queueNode.circle().getStyleClass().add("circle");

        redrawNodes();
    }

    @FXML
    private void peek() {
        QueueNode<Integer> peek = queue.peek();
        if (peek == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Priority queue is empty");
            alert.setContentText("Add some elements before getting peek");

            alert.showAndWait();
            return;
        }
        peek.circle().setStroke(Color.RED);
    }

    @FXML
    private void poll() {
        QueueNode<Integer> queueNode = queue.poll();

        if (queueNode == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Priority queue is empty");
            alert.setContentText("Add some elements before removing");

            alert.showAndWait();
            return;
        }

        removeNodeFromFlowPane(queueNode);
    }

    @FXML
    private void size() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Size of priority queue");
        alert.setContentText("The size of priority queue is " + queue.size());

        alert.showAndWait();
    }

    @FXML
    private void remove() {
        if (selectedNode == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Node is not selected");
            alert.setContentText("Select a node before removing");

            alert.showAndWait();
            return;
        }

        queue.remove(selectedNode);
        removeNodeFromFlowPane(selectedNode);

        selectedNode = null;
    }

    private void redrawNodes() {
        for (QueueNode<Integer> queueNode : queue) {
            flowPane.getChildren().remove(queueNode.stackPane());
        }

        for (QueueNode<Integer> queueNode : queue) {
            StackPane stackPane = queueNode.stackPane();

            if (selectedNode != null && queueNode == selectedNode) {
                queueNode.circle().setStroke(Color.web("F2AE3A"));
            } else {
                queueNode.circle().setStroke(Color.web("F2DF3A"));
            }

            flowPane.getChildren().add(stackPane);
        }
    }

    private void removeNodeFromFlowPane(QueueNode<Integer> node) {
        if (node == null) return;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200));
        scaleTransition.setNode(node.stackPane());
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(0);
        scaleTransition.setToY(0);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);
        scaleTransition.play();

        scaleTransition.setOnFinished(event -> {
            flowPane.getChildren().remove(node.stackPane());
            redrawNodes();
        });
    }

}