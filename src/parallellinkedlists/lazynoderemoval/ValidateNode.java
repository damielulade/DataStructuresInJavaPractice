package parallellinkedlists.lazynoderemoval;

import parallellinkedlists.optimistic.ReadWriteNode;

public class ValidateNode<T> extends ReadWriteNode<T> {

  private volatile boolean valid;

  public ValidateNode(T item) {
    super(item);
  }

  public boolean valid() {
    return valid;
  }

  public void setValid() {
    this.valid = true;
  }

  public void setInvalid() {
    this.valid = false;
  }
}
