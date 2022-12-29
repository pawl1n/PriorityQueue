package com.team19.priorityqueue;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Objects;

/**
 * Вузол черги
 * value - значення вузла
 * priority - пріорітетність вузла
 * rectangle - тіло вузла
 * stackPane - панель відображення вузла
 */
public class QueueNode<T> implements Comparable<QueueNode<?>> {
    private final StackPane stackPane;
    private final int priority;
    private final Rectangle rectangle;

    /**
     * Конструктор вузла
     * value - значення вузла
     * priority - пріорітетність вузла
     * rectangle - об'єкт тіла вузла
     * stringValue - літерне значення вузла
     * valueText - значення вузла в потріному форматі для запису в тіло
     * priorityText - пріорітет вузла в потріному форматі для запису в тіло
     * stackPane - панель відображення вузлів
     */
    public QueueNode(T value, int priority) {
        rectangle = new Rectangle(80,40, Color.WHITE);
        rectangle.setStrokeWidth(5);

        String stringValue = value.toString();
        stringValue = stringValue.length() > 8 ? stringValue.substring(0, 5) + "..." : stringValue;

        Text priorityText = new Text(String.valueOf(priority));
        TextFlow priorityTextFlow = new TextFlow(priorityText);
        priorityTextFlow.prefHeight(0);
        priorityTextFlow.prefWidth(0);
        priorityTextFlow.setPadding(new Insets(3, 0, 0, 5));

        Text valueText = new Text(stringValue);
        stackPane = new StackPane(rectangle, valueText, priorityTextFlow);

        this.priority = priority;
    }

    /**
     * Повертає обьект тіла вузла
     */
    public Rectangle rectangle() {
        return rectangle;
    }

    /**
     * Повертає панель вузлів
     */
    public StackPane stackPane() {
        return stackPane;
    }

    /**
     * Повертає пріоритет вузла
     */
    private int priority() {
        return this.priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueNode<?> queueNode = (QueueNode<?>) o;

        return priority == queueNode.priority && Objects.equals(stackPane, queueNode.stackPane) &&
                Objects.equals(rectangle, queueNode.rectangle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stackPane, priority, rectangle);
    }

    /**
     * Порівнює пріоритети вузлів
     * @param queueNode об'єкт, з яким необхідно порівняти
     * @return результат порівняння пріоритетів
     */
    @Override
    public int compareTo(QueueNode<?> queueNode) {
        return Integer.compare(priority, queueNode.priority);
    }

    @Override
    public String toString() {
        return "QueueNode{" +
                "stackPane=" + stackPane +
                ", priority=" + priority +
                ", rectangle=" + rectangle +
                '}';
    }
}
