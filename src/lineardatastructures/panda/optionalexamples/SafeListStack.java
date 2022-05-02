package lineardatastructures.panda.optionalexamples;

public class SafeListStack<X> implements SafeStack<X> {

  private SafeList<X> xs = new SafeEmpty<>();

  @Override
  public void push(X value) {
    xs = new SafeCons<>(value, xs);
  }

  @Override
  public X pop() {
    assert !xs.empty();
    X value = xs.head().get();
    xs = xs.tail().get();
    return value;
  }

  @Override
  public X peek() {
    assert !xs.empty();
    return xs.head().get();
  }

  @Override
  public boolean empty() {
    return xs.empty();
  }
}
