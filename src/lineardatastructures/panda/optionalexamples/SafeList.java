package lineardatastructures.panda.optionalexamples;

import java.util.Optional;

public interface SafeList<X> {

  Optional<X> head();

  Optional<SafeList<X>> tail();

  boolean empty();

}
