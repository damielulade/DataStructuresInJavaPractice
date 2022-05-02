package lineardatastructures.panda.optionalexamples;

import java.util.Optional;

public class SafeCons<X> implements SafeList<X> {

  private final X head;
  private final SafeList<X> tail;

  public SafeCons(X head, SafeList<X> tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public Optional<X> head() {
    return Optional.of(head);
  }

  @Override
  public Optional<SafeList<X>> tail() {
    return Optional.of(tail);
  }

  @Override
  public boolean empty() {
    return false;
  }
}
