package parallellinkedlists.finegrained;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import parallellinkedlists.DarkNode;

public class LockerNode<T> implements DarkNode<T> {

  private final Lock lock = new ReentrantLock();
  private T item;
  private int key;
  private LockerNode<T> next;

  public LockerNode(T item) {
    this.item = item;
  }

  public LockerNode() {
    this(null);
  }

  public void lock() {
    lock.lock();
  }

  public void unlock() {
    lock.unlock();
  }

  @Override
  public T item() {
    return item;
  }

  @Override
  public int key() {
    return key;
  }

  public LockerNode<T> next() {
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
    if (next instanceof LockerNode newNode) {
      this.next = newNode;
    }
  }


}
