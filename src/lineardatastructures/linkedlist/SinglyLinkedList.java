package lineardatastructures.linkedlist;

import lineardatastructures.CoalList;

public class SinglyLinkedList<T> implements CoalList<T> {

  private OneWayNode<T> head;
  private int size;

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public T get(int pos) {
    assert pos >= 0 && pos < size;
    if (pos == 0) {
      return head.getValue();
    }
    OneWayNode<T> cursor = head;
    for (int i = 0; i < pos; i++) {
      cursor = cursor.getNext();
    }
    return cursor.getValue();
  }

  @Override
  public void add(T value) {
    if (head == null) {
      head = new OneWayNode<>(value);
    } else {
      OneWayNode<T> cursor = head;
      while (cursor.getNext() != null) {
        cursor = cursor.getNext();
      }
      cursor.setNext(new OneWayNode<>(value));
    }
    size++;
  }

  @Override
  public void add(int pos, T value) {
    assert pos >= 0 && pos <= size;
    if (pos == 0) {
      OneWayNode<T> prevNode = head;
      head = new OneWayNode<>(value, prevNode);
    } else {
      OneWayNode<T> cursor = head;
      for (int i = 0; i < pos - 1; i++) {
        cursor = cursor.getNext();
      }
      OneWayNode<T> newNode = new OneWayNode<>(value, cursor.getNext());
      cursor.setNext(newNode);
    }
    size++;
  }

  @Override
  public T remove(int pos) {
    assert !isEmpty() && pos < size;

    final T result = get(pos);

    if (pos == 0) {
      head = head.getNext();
    } else {
      OneWayNode<T> cursor = head;
      for (int i = 0; i < pos - 1; i++) {
        cursor = cursor.getNext();
      }
      cursor.setNext(cursor.getNext().getNext());
    }
    size--;

    return result;
  }

  /**
   * Tortoise and the hare solution to checking if a linked list is circular.
   */
  public boolean hasCycle() {
    if (head != null && head.getNext() != null) {
      OneWayNode<T> tortoise = head;
      OneWayNode<T> hare = head.getNext();
      while (hare != null && hare.getNext() != null) {
        if (tortoise == hare) {
          return true;
        }
        tortoise = tortoise.getNext();
        hare = hare.getNext().getNext();
      }
    }
    return false;
  }
}
