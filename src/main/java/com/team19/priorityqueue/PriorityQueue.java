package com.team19.priorityqueue;

import java.util.*;

/**
 * Зпрощений варіант Java PriorityQueue, що зберігає дані у вигляді масиву а не дерева
 * T - тип елементів черги
 * nodes - вузли (елементи) черги
 * size - розмір черги
 */
public final class PriorityQueue<T> implements Iterable<T> {
   private T[] nodes;
   private int size = 0;

   /**
    * PriorityQueue - основний конструктор черги
    */
   @SuppressWarnings("unchecked")
   public PriorityQueue() {
      nodes = (T[]) new Object[10];
      size = 0;
   }

   /**
    * Додає елемент в чергу в залежності від пріоритету
    * @param element - елемент, що необхідно додати
    */
   @SuppressWarnings("unchecked")
   public void add(T element) {
      if (element == null)
         throw new NullPointerException("Value can't be null");

      grow();

      Comparable<? super T> key = (Comparable<? super T>) element;
      int i;
      for (i = size; i > 0; i--) {
         if (key.compareTo(nodes[i-1]) <= 0)
            break;
         nodes[i] = nodes[i-1];
      }
      nodes[i] = element;

      size++;
   }

   /**
    * Збільшує розмір черги, якщо вона вже заповнена
    */
   private void grow() {
      if (size == nodes.length) {
         nodes = Arrays.copyOf(nodes, (int) (nodes.length * 1.5));
      }
   }

   /**
    * Повертає розмір черги
    * @return розмір черги
    */
   public int size() {
      return size;
   }

   /**
    * Чистить чергу
    */
   @SuppressWarnings("unchecked")
   public void clear() {
      nodes = (T[]) new Object[10];
      size = 0;
   }

   /**
    * Повертає найпріорітетніший елемент
    * @return найпріорітетніший елемент
    */
   public T peek() {
      if (size == 0) return null;
      return nodes[0];
   }

   /**
    * Видаляє найпріорітетніший елемент
    * @return видалений елемент
    */
   public T poll() {
      if (size == 0) return null;

      T peek = nodes[0];
      remove(peek);

      return peek;
   }

   /**
    * Видаляє вибраний вузол
    * @param node вузол, що треба видалити
    */
   public void remove(T node) {
      int position;

      for (position = 0; position < size; position++) {
         if (nodes[position].equals(node)) {
            break;
         }
      }

      nodes[position] = null;

      for (int i = position + 1; i < size; i++) {
         nodes[i - 1] = nodes[i];
      }

      size--;
   }

   @Override
   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (int i = 0; i < size; i++) {
         result.append(nodes[i]);
         if (i != size-1) result.append(", ");
      }
      result.append("]");

      return result.toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PriorityQueue<?> that)) return false;

      return size == that.size && Arrays.equals(nodes, that.nodes);
   }

   @Override
   public int hashCode() {
      int result = Objects.hash(size);
      result = 31 * result + Arrays.hashCode(nodes);

      return result;
   }

   @Override
   public Iterator<T> iterator() {
      return new Itr();
   }

   /**
    * Клас, що є реалізує ітератор для елементів черги
    */
   private class Itr implements Iterator<T> {
      int cursor = 0;

      Itr() {}

      @Override
      public boolean hasNext() {
         return cursor < size;
      }

      @Override
      public T next() {
         if (!hasNext()) throw new NoSuchElementException();

         return nodes[cursor++];
      }
   }
}
