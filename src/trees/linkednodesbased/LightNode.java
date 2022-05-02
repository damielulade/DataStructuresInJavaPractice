package trees.linkednodesbased;

/*
Could have been made as a private class within LinkedNodesBST
through which all attributes would be private.

Here, they have to be protected in order to preserve
the calling method used by the tree functions (no getters).
 */
public class LightNode<T> {

  protected T element;
  protected LightNode<T> left;
  protected LightNode<T> right;

  public LightNode(T element) {
    this.element = element;
  }

  @Override
  public String toString() {
    return "N[" + element + "]";
  }

}
