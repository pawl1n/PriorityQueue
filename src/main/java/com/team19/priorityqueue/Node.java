package com.team19.priorityqueue;

import javafx.scene.layout.StackPane;

public record Node<T>(StackPane stackPane, T value, int priority) implements Comparable<Node<T>> {

    @Override
    public int compareTo(Node node) {
        return Integer.compare(priority, node.priority());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
