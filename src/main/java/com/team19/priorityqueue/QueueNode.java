package com.team19.priorityqueue;

import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Objects;

/**
 * QueueNode - клас черги
 * value - значення вузла
 * priority - пріорітетність вузла
 * rectangle - тіло вузла
 * stackPane - панель відображення вузла
 */
public class QueueNode<T> implements Comparable<QueueNode<?>> {
    private final StackPane stackPane;
    private final T value;
    private final int priority;
    private final Rectangle rectangle;

    /**
     * value - значення вузла
     * priority - пріорітетність вузла
     * rectangle - об'єкт тіла вузла
     * stringValue - літерне значення вузла
     * valueText - значення вузла в потріному форматі для запису в тіло
     * priorityText - пріорітет вузла в потріному форматі для запису в тіло
     * stackPane - панель відображення вузлів
     */
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

    /**
     * rectangle - метод повернення обьекта тіла вузла
     */
    public Rectangle rectangle() {
        return rectangle;
    }
    /**
     * stackPane - метод повернення панель вузлів
     */
    public StackPane stackPane() {
        return stackPane;
    }
    /**
     * priority - метод повернення пріорітету вузла
     */
    private int priority() {
        return this.priority;
    }
    /**
     * equals - метод перевірки на еквалентність
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueNode<?> queueNode = (QueueNode<?>) o;
        return priority == queueNode.priority && Objects.equals(stackPane, queueNode.stackPane) && Objects.equals(value, queueNode.value) && Objects.equals(rectangle, queueNode.rectangle);
    }
    /**
     * hashCode - метод отримання індивідуального хеш-номеру для об'єкта
     */
    @Override
    public int hashCode() {
        return Objects.hash(stackPane, value, priority, rectangle);
    }
    /**
     * compareTo - метод порівняння пріорітетів двух вузлів
     */
    @Override
    public int compareTo(QueueNode<?> queueNode) {
        return Integer.compare(priority, queueNode.priority);
    }
    /**
     * compareTo - метод виведення на екран значення вузла
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
