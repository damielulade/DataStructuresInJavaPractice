package parallellinkedlists.coarsegrained;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import parallellinkedlists.sequentialaccess.SequentialSet;

/*
Uses a lock to ensure that at most one thread at a time is operating on the structure
+ it is clearly causing no race conditions or deadlocks
+ if contention is low, it is quite efficient
- but access to the set is essentially sequential, so no parallelism
- if contention is high, it is quite slow

It is not possible to execute find without locking as the list
may be modified between a thread performing find and acquiring the lock.
 */
public class CoarseSet<T> extends SequentialSet<T> {

  private final Lock lock = new ReentrantLock();

  @Override
  public boolean contains(T item) {
    lock.lock();
    try {
      return super.contains(item);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean add(T item) {
    lock.lock();
    try {
      return super.add(item);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean remove(T item) {
    lock.lock();
    try {
      return super.remove(item);
    } finally {
      lock.unlock();
    }
  }
}
