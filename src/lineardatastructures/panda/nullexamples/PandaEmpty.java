package lineardatastructures.panda.nullexamples;

public class PandaEmpty<X> implements PandaList<X> {

  @Override
  public X head() {
    return null;
  }

  @Override
  public PandaList<X> tail() {
    return null;
  }

  @Override
  public boolean empty() {
    return true;
  }
}
