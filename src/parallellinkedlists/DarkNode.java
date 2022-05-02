package parallellinkedlists;

public interface DarkNode<T> {

  T item();

  int key();

  DarkNode<T> next();

  void setItem(T item);

  void setKey(int key);

  void setNext(DarkNode<T> next);
}
