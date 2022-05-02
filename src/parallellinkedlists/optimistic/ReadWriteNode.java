package parallellinkedlists.optimistic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import parallellinkedlists.DarkNode;
import parallellinkedlists.finegrained.LockerNode;


/*
Volatile attributes are stored directly in the main memory
(no local cache used by a thread)
This ensures every thread accesses the most up-to-date value of the variable.

Memory operations on volatile attributes cannot be reordered
(guaranteeing memory consistency at the price of performance)

When a thread 'u' reads a volatile value written by 't',
the values of all variables that were visible to t BEFORE writing the volatile variable
become visible to u.
 */
public class ReadWriteNode<T> implements DarkNode<T> {

  private T item;
  private int key;
  private volatile ReadWriteNode<T> next;
  private final Lock lock = new ReentrantLock();

  public ReadWriteNode(T item) {
    this.item = item;
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

  @Override
  public void setNext(DarkNode<T> next) {
    if (next instanceof ReadWriteNode newNode) {
      this.next = newNode;
    }
  }
}
