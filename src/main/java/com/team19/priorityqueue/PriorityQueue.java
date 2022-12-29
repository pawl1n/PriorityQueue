package com.team19.priorityqueue;

import java.util.*;

/**
 * PriorityQueue - клас пріорітетної черги
 * nodes - вузли
 * size - розмір черги
 */
public final class PriorityQueue<T> implements Iterable<T> {
   private T[] nodes;
   private int size = 0;
   /**
    * PriorityQueue - конструктор створення черги
    * nodes - вузли
    * size - розмір черги
    */
   @SuppressWarnings("unchecked")
   public PriorityQueue() {
      nodes = (T[]) new Object[10];
      size = 0;
   }
   /**
    * add - метод створення додавання вузла в чергу, включае створення самого вузла, надання йому пріорітету,
    * розміщення в черзі
    * nodes - вузли
    * size - розмір черги
    * value - значення вузла
    */
   @SuppressWarnings("unchecked")
   public void add(T value) {
      if (value == null)
         throw new NullPointerException("Value can't be null");

      grow();

      Comparable<? super T> key = (Comparable<? super T>) value;
      int i;
      for (i = size; i > 0; i--) {
         if (key.compareTo(nodes[i-1]) <= 0)
            break;
         nodes[i] = nodes[i-1];
      }
      nodes[i] = value;

      size++;
   }

   /**
    * grow - метод
    */
   private void grow() {
      if (size == nodes.length) {
         nodes = Arrays.copyOf(nodes, (int) (nodes.length * 1.5));
      }
   }
   /**
    * size - метод, який повертає розмір черги
    */
   public int size() {
      return size;
   }
   /**
    * clear - метод, який чистить чергу
    */
   @SuppressWarnings("unchecked")
   public void clear() {
      nodes = (T[]) new Object[10];
      size = 0;
   }
   /**
    * clear - метод, який знаходить найпріорітетний вузол
    */
   public T peek() {
      if (size == 0) return null;
      return nodes[0];
   }
   /**
    * clear - метод, який видаляє найпріорітетний вузол
    */
   public T poll() {
      if (size == 0) return null;

      T peek = nodes[0];
      remove(peek);

      return peek;
   }
   /**
    * remove - метод, який видаляє вибраний вузол
    * position - позиція вузла
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
   /**
    * toString - метод, який виводить значення вузла на екран
    */
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
   /**
    * equals - метод перевірки на еквалентність
    */
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PriorityQueue<?> that)) return false;
      return size == that.size && Arrays.equals(nodes, that.nodes);
   }
   /**
    * hashCode - метод отримання індивідуального хеш-номеру для об'єкта
    */
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
