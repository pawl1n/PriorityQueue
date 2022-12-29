package com.team19.priorityqueue;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * PriorityQueueController - класс панелі контролю пріорітетної черги
 * queue - черга
 * flowPane - Панель відображення черги
 * valueTextField - поле вводу для значення вузла
 * priorityTextField - поле вводу для пріорітетності вузла
 */
public class PriorityQueueController {
    private final PriorityQueue<QueueNode<String>> queue = new PriorityQueue<>();
    private QueueNode<String> selectedNode;

    @FXML
    private FlowPane flowPane;

    @FXML
    private TextField valueTextField;

    @FXML
    private TextField priorityTextField;

    /**
     * add - додає новий елемент в чергу, бере значення з полів в які інформацію вводить користувач
     * value - значення в середині вузла
     * priority - пріорітет вузла
     * queueNode - об'єкт черги
     * flowPane - панель відображення вузлів
     * queue - черга
     */
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

    /**
     * peek - метод знаходження  а виділення елемента з найбільшим пріорітетом
     */
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

    /**
     * poll - видаляє елемент з найбільшою пріорітетністю
     * queueNode - об'єкт черги
     */
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

    /**
     * size - метод знаходження розміру черги
     */
    @FXML
    private void size() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Size of priority queue");
        alert.setContentText("The size of priority queue is " + queue.size());

        alert.showAndWait();
    }

    /**
     * remove - метод видалення
     * selectedNode - вибраний елемент
     * queue - черга
     */
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

    /**
     * redrawNodes - метод перемальовування вузлів в момент змінення черги
     * queueNode - об'єкт черги
     * flowPane - панель, на якій знаходиться всі вузли
     */
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

    /**
     * removeNodeFromFlowPane - метод включае видалення елемента з панелі та реалізація анімації
     * node - елемент який видаляється
     *
     */
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