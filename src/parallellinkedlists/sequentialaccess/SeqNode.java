package parallellinkedlists.sequentialaccess;

import parallellinkedlists.DarkNode;

public class SeqNode<T> implements DarkNode<T> {

  private T item;
  private int key;
  private DarkNode<T> next;

  public SeqNode(T item) {
    this.item = item;
  }

  @Override
  public T item() {
    return item;
  }

  @Override
  public int key() {
    return key;
  }

  @Override
  public DarkNode<T> next() {
    return next;
  }

  @Override
  public void setItem(T item) {
    this.item = item;
  }

  @Override
  public void setKey(int key) {
    this.key = key;
  }
  public void setNext(DarkNode<T> next) {
    this.next = next;
  }
}
