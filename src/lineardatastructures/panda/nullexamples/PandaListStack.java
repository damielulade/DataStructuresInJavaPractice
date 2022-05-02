package lineardatastructures.panda.nullexamples;

public class PandaListStack<X> implements PandaStack<X> {

  private PandaList<X> xs = new PandaEmpty<>();

  @Override
  public void push(X value) {
    xs = new PandaCons<>(value, xs);
  }

  @Override
  public X pop() {
    assert !xs.empty();
    X value = xs.head();
    xs = xs.tail();
    return value;
  }

  @Override
  public X peek() {
    assert !xs.empty();
    return xs.head();
  }

  @Override
  public boolean empty() {
    return xs.empty();
  }
}
