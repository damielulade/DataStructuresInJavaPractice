package parallellinkedlists.optimistic;

import parallellinkedlists.DarkNode;

public class OptimistPosition<T> {

  // (pre, post)
  // (predecessor, current)
  // (predecessor, next)
  // Nic said it would be better named next
  // So, I called it pre and post to avoid confusion
  protected final DarkNode<T> pre;
  protected final DarkNode<T> post;

  public OptimistPosition(DarkNode<T> pre, DarkNode<T> post) {
    this.pre = pre;
    this.post = post;
  }
}
