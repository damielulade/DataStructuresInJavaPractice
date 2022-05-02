package parallellinkedlists.optimistic;

import parallellinkedlists.DarkNode;
import parallellinkedlists.DarkSet;

/*
+ threads operating on disjoint portions of the list can operate in parallel
+ when validation often succeeds, there is much less locking involved than in FineSet
- it may lead to starvation:
  in the unlikely event that other threads keeps removing or adding pre/post,
  a thread may need to retry its operation several times
- if traversing the list twice without locking
  is not significantly faster than traversing the list once with locking,
  OptimisticSet has no clear advantage over FineSet

Operations work as follows:
1. find the term's position in the list normally, without locking
2. lock the position's nodes pre and post
3. validate the position while locking:
   a. if the position is valid, perform the operation while locking, then release
   b. if the position is invalid, release locks and repeat the process from scratch

This works well when validation is often successful (not many retries)

If a node following post is modified:
validation is not affected because it only goes up until post

If a node 'n' is removed before pre is removed:
validation succeeds even if it goes through n, since n still leads back to pre

If a node is added before pre:
Validation succeeds even if it skips over the node

The problem is adding volatile is that it removes concurrency, and it is expensive
 */
public class OptimisticSet<T> implements DarkSet<T> {

  private final ReadWriteNode<T> head;
  private final ReadWriteNode<T> tail;

  public OptimisticSet() {
    head = new ReadWriteNode(Integer.MIN_VALUE);
    tail = new ReadWriteNode(Integer.MAX_VALUE);
    head.setNext(tail);
  }

  OptimistPosition<T> find(DarkNode<T> start, int key) {
    DarkNode<T> pre;
    DarkNode<T> post;
    post = start;
    do {
      pre = post;
      post = post.next();
    } while (post.key() < key);
    return new OptimistPosition<>(pre, post);
  }

  // is pre reachable from the head?
  // does pre still point to post?
  private boolean valid(DarkNode<T> pre, DarkNode<T> post) {
    DarkNode<T> node = head;
    while (node.key() <= pre.key()) {
      if (node == pre) {
        return pre.next() == post;
      }
      node = node.next();
    }
    return false;
  }

  @Override
  public boolean add(T item) {
    ReadWriteNode<T> node = new ReadWriteNode<>(item);
    do {
      OptimistPosition<T> where = find(head, node.key());
      ReadWriteNode<T> pre = (ReadWriteNode<T>) where.pre;
      ReadWriteNode<T> post = (ReadWriteNode<T>) where.post;
      pre.lock();
      post.lock();
      try {
        if (valid(pre, post)) {
          /* check no duplicate and rewire */
        }
      } finally {
        pre.unlock();
        post.unlock();
      }
    } while (true);
  }

  @Override
  public boolean remove(T item) {
    ReadWriteNode<T> node = new ReadWriteNode<>(item);
    do {
      OptimistPosition<T> where = find(head, node.key());
      ReadWriteNode<T> pre = (ReadWriteNode<T>) where.pre;
      ReadWriteNode<T> post = (ReadWriteNode<T>) where.post;
      pre.lock();
      post.lock();
      try {
        if (valid(pre, post)) {
          /* check element in and rewire */
        }
      } finally {
        pre.unlock();
        post.unlock();
      }
    } while (true);
  }

  @Override
  public boolean contains(T item) {
    ReadWriteNode<T> node = new ReadWriteNode<>(item);
    do {
      OptimistPosition<T> where = find(head, node.key());
      ReadWriteNode<T> pre = (ReadWriteNode<T>) where.pre;
      ReadWriteNode<T> post = (ReadWriteNode<T>) where.post;
      pre.lock();
      post.lock();
      try {
        if (valid(pre, post)) {
          return post.key() == node.key();
        }
      } finally {
        pre.unlock();
        post.unlock();
      }
    } while (true);
  }
}
