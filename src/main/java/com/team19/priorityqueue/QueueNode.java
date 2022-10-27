package com.team19.priorityqueue;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class QueueNode<T> {
    private final StackPane stackPane;
    private final T value;
    private final int priority;
    private final Circle circle;

    public QueueNode(T value, int priority) {
        circle = new Circle(20);
        circle.setFill(Color.WHITE);
        circle.setStrokeWidth(5);

        Text text = new Text(value.toString());
        stackPane = new StackPane(circle, text);

        this.value = value;
        this.priority = priority;
    }

    public Circle circle() {
        return circle;
    }

    public StackPane stackPane() {
        return stackPane;
    }

    private int priority() {
        return this.priority;
    }

    public int comparePriorityTo(QueueNode<T> queueNode) {
        return Integer.compare(priority, queueNode.priority());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
