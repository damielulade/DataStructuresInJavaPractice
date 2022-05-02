package lineardatastructures.panda.optionalexamples;

public interface SafeStack<X> {

  void push(X value);

  X pop();

  X peek();

  boolean empty();

}
