package lineardatastructures;

public interface CoalList<T> {

  int size();

  boolean isEmpty();

  T get(int pos);

  void add(T value);

  void add(int pos, T value);

  T remove(int pos);

}
