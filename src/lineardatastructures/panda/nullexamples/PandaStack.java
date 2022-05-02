package lineardatastructures.panda.nullexamples;

public interface PandaStack<X> {

  void push(X value);

  X pop();

  X peek();

  boolean empty();

}
