package com.team19.priorityqueue;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Objects;

public class QueueNode<T> implements Comparable<QueueNode<?>> {
    private final StackPane stackPane;
    private final T value;
    private final int priority;
    private final Rectangle rectangle;

    public QueueNode(T value, int priority) {
        rectangle = new Rectangle(80,40);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(5);

        String stringValue = value.toString();
        stringValue = stringValue.length() > 8 ? stringValue.substring(0, 5) + "..." : stringValue;

        Text valueText = new Text(stringValue);
        Text priorityText = new Text(String.valueOf(priority));

        TextFlow priorityTextFlow = new TextFlow(priorityText);
        priorityTextFlow.prefHeight(0);
        priorityTextFlow.prefWidth(0);
        priorityTextFlow.setPadding(new Insets(3, 0, 0, 5));

        stackPane = new StackPane(rectangle, valueText, priorityTextFlow);
        stackPane.prefHeight(0);
        stackPane.prefWidth(0);

        this.value = value;
        this.priority = priority;
    }

    public Rectangle rectangle() {
        return rectangle;
    }

    public StackPane stackPane() {
        return stackPane;
    }

    private int priority() {
        return this.priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueNode<?> queueNode = (QueueNode<?>) o;
        return priority == queueNode.priority && Objects.equals(stackPane, queueNode.stackPane) && Objects.equals(value, queueNode.value) && Objects.equals(rectangle, queueNode.rectangle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stackPane, value, priority, rectangle);
    }

    @Override
    public int compareTo(QueueNode<?> queueNode) {
        return Integer.compare(priority, queueNode.priority);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
