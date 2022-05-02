package lineardatastructures.linkedlist;

import lineardatastructures.CoalList;

public class OrderedLinkedList<T extends Comparable<T>> implements CoalList<T> {

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

  /**
   * Worst case complexity is O(n^2).
   */
  @Override
  public void add(T value) {
    final OneWayNode<T> newNode = new OneWayNode<>(value);

    // add at front if null or smaller
    if (head == null || value.compareTo(head.getValue()) < 0) {
      newNode.setNext(head);
      head = newNode;
    } else {
      OneWayNode<T> cursor = head;
      // add after the predecessor (cursor)
      // if there is nothing after cursor or value is not bigger than the successor
      while (cursor.getNext() != null && value.compareTo(cursor.getNext().getValue()) > 0) {
        cursor = cursor.getNext();
      }
      newNode.setNext(cursor.getNext());
      cursor.setNext(newNode);
    }
    size++;
  }

  @Override
  public void add(int pos, T value) {
    // unsupported method.
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
}
