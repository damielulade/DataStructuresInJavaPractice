package parallellinkedlists.finegrained;

public class FinePosition<T> {

  // (pre, post)
  // (predecessor, current)
  // (predecessor, next)
  // Nic said it would be better named next
  // So, I called it pre and post to avoid confusion
  protected final LockerNode<T> pre;
  protected final LockerNode<T> post;

  public FinePosition(LockerNode<T> pre, LockerNode<T> post) {
    this.pre = pre;
    this.post = post;
  }
}
