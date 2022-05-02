package lineardatastructures.panda.optionalexamples;

import java.util.Optional;

public class SafeEmpty<X> implements SafeList<X> {

  @Override
  public Optional<X> head() {
    return Optional.empty();
  }

  @Override
  public Optional<SafeList<X>> tail() {
    return Optional.empty();
  }

  @Override
  public boolean empty() {
    return true;
  }
}
