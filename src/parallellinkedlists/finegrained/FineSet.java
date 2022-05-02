package parallellinkedlists.finegrained;

import parallellinkedlists.DarkSet;

/*
We start locking on the head, and we pass the lock along the chain of nodes.
+ threads operating on disjoint portions of the list can operate in parallel
+ no deadlocks thanks to the global locking order
- one thread prevents another thread from operating in parallel
- hand-over-hand protocol is quite slow

It is not enough to lock only the pre, we have to lock the pre and next.

Always keeping at least one node locked prevents interference between threads.

Locking two nodes at once is sufficient to prevent problems with conflicting operations:
One node cannot overtake another that is further out

Locks are acquired by all threads in the same order, thus avoiding deadlocks.

It is not possible to execute find without locking as the list
may be modified between a thread performing find and acquiring the lock.
 */
public class FineSet<T> implements DarkSet<T> {

  private final LockerNode<T> head;
  private final LockerNode<T> tail;

  public FineSet() {
    head = new LockerNode(Integer.MIN_VALUE);
    tail = new LockerNode(Integer.MAX_VALUE);
    head.setNext(tail);
  }

  FinePosition<T> find(LockerNode<T> start, int key) {
    LockerNode<T> pre;
    LockerNode<T> post;
    pre = start;
    pre.lock();
    post = start.next();
    post.lock();
    // hand over hand
    // unlock pre, move the hand over post, lock the node after post
    while (post.key() < key) {
      pre.unlock();
      pre = post;
      post = post.next();
      post.lock();
    }
    return new FinePosition<>(pre, post);
  }

  @Override
  public boolean add(T item) {
    LockerNode<T> node = new LockerNode<>(item);
    LockerNode<T> pre = null;
    LockerNode<T> post = null;
    try {
      FinePosition<T> where = find(head, node.key());
      pre = where.pre;
      post = where.post;
      if (where.post.key() == node.key()) {
        return false;
      } else {
        node.setNext(where.post);
        where.pre.setNext(node);
        return true;
      }
    } finally {
      pre.unlock();
      post.unlock();
    }
  }

  @Override
  public boolean remove(T item) {
    LockerNode<T> node = new LockerNode<>(item);
    LockerNode<T> pre = null;
    LockerNode<T> post = null;
    try {
      FinePosition<T> where = find(head, node.key());
      pre = where.pre;
      post = where.post;
      if (where.post.key() > node.key()) {
        return false;
      } else {
        where.pre.setNext(where.post.next());
        return true;
      }
    } finally {
      pre.unlock();
      post.unlock();
    }
  }

  @Override
  public boolean contains(T item) {
    LockerNode<T> node = new LockerNode<>(item);
    LockerNode<T> pre = null;
    LockerNode<T> post = null;
    try {
      FinePosition<T> expectedPosition = find(head, node.key());
      pre = expectedPosition.pre;
      post = expectedPosition.post;
      return expectedPosition.post.key() == node.key();
    } finally {
      pre.unlock();
      post.unlock();
    }
  }
}
