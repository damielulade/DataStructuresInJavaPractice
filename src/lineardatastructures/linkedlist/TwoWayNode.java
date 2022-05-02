package lineardatastructures.linkedlist;

import lineardatastructures.CoalNode;

public class TwoWayNode<T> extends CoalNode<T> {

  protected TwoWayNode<T> prev;
  protected TwoWayNode<T> next;

  public TwoWayNode(T value, TwoWayNode<T> next) {
    this.value = value;
    this.next = next;
  }

  public TwoWayNode(TwoWayNode<T> prev, T value) {
    this.prev = prev;
    this.value = value;
  }

  public TwoWayNode(T value, TwoWayNode<T> prev,
      TwoWayNode<T> next) {
    this.prev = prev;
    this.value = value;
    this.next = next;
  }

  public TwoWayNode(T value) {
    this.value = value;
  }

  public TwoWayNode<T> getNext() {
    return next;
  }

  public void setNext(final TwoWayNode<T> next) {
    this.next = next;
  }

  public TwoWayNode<T> getPrev() {
    return prev;
  }

  public void setPrev(TwoWayNode<T> prev) {
    this.prev = prev;
  }
}
