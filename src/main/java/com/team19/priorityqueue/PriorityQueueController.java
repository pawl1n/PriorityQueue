package com.team19.priorityqueue;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PriorityQueueController {
    private final PriorityQueue<QueueNode<String>> queue = new PriorityQueue<>();
    private QueueNode<String> selectedNode;

    @FXML
    private FlowPane flowPane;

    @FXML
    private TextField valueTextField;

    @FXML
    private TextField priorityTextField;

    @FXML
    private void add() {
        String value = valueTextField.getText();

        int priority;
        try {
            priority = Integer.parseInt(priorityTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid number passed as priority");
            alert.setContentText("Enter valid number in priority field");

            alert.showAndWait();
            return;
        }

        QueueNode<String> queueNode = new QueueNode<>(value, priority);
        queue.add(queueNode);
        flowPane.getChildren().add(queueNode.stackPane());

        queueNode.stackPane().setOnMouseClicked(event -> {
            selectedNode = queueNode;
            redrawNodes();
        });

        redrawNodes();
    }

    @FXML
    private void peek() {
        QueueNode<String> peek = queue.peek();
        if (peek == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Priority queue is empty");
            alert.setContentText("Add some elements before getting peek");

            alert.showAndWait();
            return;
        }
        peek.rectangle().setStroke(Color.RED);
    }

    @FXML
    private void poll() {
        QueueNode<String> queueNode = queue.poll();

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
        for (QueueNode<String> queueNode : queue) {
            flowPane.getChildren().remove(queueNode.stackPane());
        }

        for (QueueNode<String> queueNode : queue) {
            StackPane stackPane = queueNode.stackPane();

            if (selectedNode != null && queueNode == selectedNode) {
                queueNode.rectangle().setStroke(Color.web("F2AE3A"));
            } else {
                queueNode.rectangle().setStroke(Color.web("F2DF3A"));
            }

            flowPane.getChildren().add(stackPane);
        }
    }

    private void removeNodeFromFlowPane(QueueNode<String> node) {
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