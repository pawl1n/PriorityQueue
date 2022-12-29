package com.team19.priorityqueue;

import java.util.*;

public final class PriorityQueue<T> implements Iterable<T> {
   private T[] nodes;
   private int size = 0;

   @SuppressWarnings("unchecked")
   public PriorityQueue() {
      nodes = (T[]) new Object[10];
      size = 0;
   }

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

   private void grow() {
      if (size == nodes.length) {
         nodes = Arrays.copyOf(nodes, (int) (nodes.length * 1.5));
      }
   }

   public int size() {
      return size;
   }

   @SuppressWarnings("unchecked")
   public void clear() {
      nodes = (T[]) new Object[10];
      size = 0;
   }

   public T peek() {
      if (size == 0) return null;
      return nodes[0];
   }

   public T poll() {
      if (size == 0) return null;

      T peek = nodes[0];
      remove(peek);

      return peek;
   }

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
      return Arrays.toString(nodes);
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
