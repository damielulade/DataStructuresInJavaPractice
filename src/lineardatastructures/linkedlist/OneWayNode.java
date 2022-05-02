package lineardatastructures.linkedlist;

import lineardatastructures.CoalNode;

public class OneWayNode<T> extends CoalNode<T> {

  protected OneWayNode<T> next;

  public OneWayNode(T value, OneWayNode<T> next) {
    this.value = value;
    this.next = next;
  }

  public OneWayNode(T value) {
    this.value = value;
  }

  public void setNext(final OneWayNode<T> next) {
    this.next = next;
  }

  public OneWayNode<T> getNext() {
    return next;
  }
}
