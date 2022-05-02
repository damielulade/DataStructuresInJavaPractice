package lineardatastructures.panda.nullexamples;

// state: xs : [X]
public interface PandaList<X> {

  // pre:     xs != []
  // post:    xs == xs0 (xs is equal to xs zero: the list is unchanged)
  // return:  head xs
  X head();

  // pre:     xs != []
  // post:    xs == xs0
  // return:  head xs
  PandaList<X> tail();

  // post:    xs == xs0
  // return:  xs === []
  boolean empty();

}
