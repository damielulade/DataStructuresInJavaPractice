package parallellinkedlists.sequentialaccess;

import parallellinkedlists.DarkNode;
import parallellinkedlists.DarkSet;

// this is not thread-safe
// threads can easily interfere with each other's operations
// leaving the set in an inconsistent state
public class SequentialSet<T> implements DarkSet<T> {

  protected DarkNode<T> head;
  protected DarkNode<T> tail;

  public SequentialSet() {
    head = new SeqNode(Integer.MIN_VALUE);
    tail = new SeqNode(Integer.MAX_VALUE);
    head.setNext(tail);
  }

  // works in ascending order of key
  // returns when it finds a position where the post node's key is >= given key
  SeqPosition<T> find(DarkNode<T> start, int key) {
    DarkNode<T> pre;
    DarkNode<T> post;
    post = start;
    do {
      pre = post;
      post = post.next();
    } while (post.key() < key);
    return new SeqPosition<>(pre, post);
  }

  // if the insertion point already has a value with that key, return false.
  @Override
  public boolean add(T item) {
    SeqNode<T> node = new SeqNode<>(item);
    SeqPosition<T> where = find(head, node.key());
    if (where.post.key() == node.key()) {
      return false;
    } else {
      node.setNext(where.post);
      where.pre.setNext(node);
      return true;
    }
  }

  // if the post's key is greater than the item's key
  // it is not present in the list at all
  // if not, the where function makes sure post is the item to remove.
  @Override
  public boolean remove(T item) {
    DarkNode<T> node = new SeqNode<>(item);
    SeqPosition<T> where = find(head, node.key());
    if (where.post.key() > node.key()) {
      return false;
    } else {
      where.pre.setNext(where.post.next());
      return true;
    }
  }

  @Override
  public boolean contains(T item) {
    int itemKey = item.hashCode();
    SeqPosition<T> expectedPosition = find(head, itemKey);
    return expectedPosition.post.key() == itemKey;
  }
}
