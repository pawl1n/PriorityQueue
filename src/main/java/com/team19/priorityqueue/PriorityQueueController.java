package com.team19.priorityqueue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueController {
    private final PriorityQueue<Node<Integer>> queue = new PriorityQueue<>(Node::compareTo);
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
        Circle circle = new Circle(20);
        circle.setFill(Color.WHITE);
        circle.setStrokeWidth(5);

        int val = random.nextInt(10);

        Text text = new Text(Integer.valueOf(val).toString());
        StackPane stackPane = new StackPane(circle, text);

        queue.add(new Node<>(stackPane, val, val));
        System.out.println(queue);
        flowPane.getChildren().add(stackPane);

        redrawNodes();
    }


    @FXML
    private void peek() {
        System.out.println(queue.peek());
    }

    @FXML
    private void poll() {
        Node<Integer> node = queue.poll();
        if (node != null) {
            flowPane.getChildren().remove(node.stackPane());
        }
        redrawNodes();
        System.out.println(queue);
    }

    private void redrawNodes() {
        for (Node<Integer> node : queue) {
            flowPane.getChildren().remove(node.stackPane());
        }

        for (Node<Integer> node : queue) {
            StackPane stackPane = node.stackPane();
            Circle circle = (Circle) stackPane.getChildren().get(0);
            if (flowPane.getChildren().size() == 0) {
                circle.setStroke(Color.RED);
            } else {
                circle.setStroke(Color.web("F2DF3A"));
            }
            flowPane.getChildren().add(stackPane);
        }
    }

}