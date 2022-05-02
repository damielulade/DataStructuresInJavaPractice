package lineardatastructures.linkedlist;

import lineardatastructures.CoalList;

public class DoublyLinkedList<T> implements CoalList<T> {

  private TwoWayNode<T> head;
  private TwoWayNode<T> tail;
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
    assert pos < size;
    if (pos == 0) {
      return head.getValue();
    }
    if (pos == size - 1) {
      return tail.getValue();
    }
    TwoWayNode<T> cursor;
    if (pos <= size / 2) {
      cursor = head;
      for (int i = 0; i < pos; i++) {
        cursor = cursor.getNext();
      }
    } else {
      cursor = tail;
      for (int i = 0; i < size - pos; i++) {
        cursor = cursor.getPrev();
      }
    }
    return cursor.getValue();
  }

  @Override
  public void add(T value) {
    if (head == null) {
      TwoWayNode<T> newNode = new TwoWayNode<>(value);
      head = newNode;
      tail = newNode;
    } else if (head == tail) {
      tail = new TwoWayNode<>(head, value);
      head.setNext(tail);
    } else {
      TwoWayNode<T> newNode = new TwoWayNode<>(tail, value);
      tail.setNext(newNode);
      tail = newNode;
    }
    size++;
  }

  @Override
  public void add(int pos, T value) {
    assert pos >= 0 && pos <= size;
    if (head == null) {
      TwoWayNode<T> newNode = new TwoWayNode<>(value);
      head = newNode;
      tail = newNode;
    } else if (pos == 0) {
      TwoWayNode<T> previousHead = head;
      head = new TwoWayNode<>(value, previousHead);
      previousHead.setPrev(head);
    } else if (pos == size) {
      TwoWayNode<T> newNode = new TwoWayNode<>(tail, value);
      tail.setNext(newNode);
      tail = newNode;
    } else if (pos <= size / 2) {
      TwoWayNode<T> cursor = head;
      for (int i = 0; i < pos - 1; i++) {
        cursor = cursor.getNext();
      }
      TwoWayNode<T> newNode = new TwoWayNode<>(value, cursor,
          cursor.getNext());
      cursor.getNext().setPrev(newNode);
      cursor.setNext(newNode);
    } else {
      TwoWayNode<T> cursor = tail;
      for (int i = 0; i < size - pos + 1; i++) {
        cursor = cursor.getPrev();
      }
      TwoWayNode<T> newNode = new TwoWayNode<>(value, cursor.getPrev(),
          cursor);
      cursor.getPrev().setNext(newNode);
      cursor.setPrev(newNode);
    }
    size++;
  }

  @Override
  public T remove(int pos) {
    assert !isEmpty() && pos < size;

    T result = get(pos);

    if (pos == 0) {
      head = head.getNext();
    } else if (pos <= size / 2) {
      TwoWayNode<T> cursor = head;
      for (int i = 0; i < pos - 1; i++) {
        cursor = cursor.getNext();
      }
      cursor.setNext(cursor.getNext().getNext());
      cursor.getNext().setPrev(cursor);
    } else {
      TwoWayNode<T> cursor = tail;
      for (int i = 0; i < size - pos + 1; i++) {
        cursor = cursor.getPrev();
      }
      cursor.setPrev(cursor.getPrev().getPrev());
      cursor.getPrev().setNext(cursor);
    }

    size--;
    return result;
  }

  public void join(DoublyLinkedList<T> other) {
    if (other.isEmpty()) {
      return;
    } else if (isEmpty()) {
      head = other.head;
      tail = other.tail;
    } else {
      tail.setNext(other.head);
      other.head.setPrev(tail);
      tail = other.tail;
    }
  }
}
