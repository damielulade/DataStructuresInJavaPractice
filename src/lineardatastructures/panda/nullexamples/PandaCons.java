package lineardatastructures.panda.nullexamples;

public class PandaCons<X> implements PandaList<X> {

  private final X head;
  private final PandaList<X> tail;

  public PandaCons(X head, PandaList<X> tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public X head() {
    return head;
  }

  @Override
  public PandaList<X> tail() {
    return tail;
  }

  @Override
  public boolean empty() {
    return false;
  }
}
