package parallellinkedlists;

// there are no duplicates in sets
public interface DarkSet<T> {

  // add 'item' to the set;
  // return 'false' if 'item' is already in the set
  boolean add(T item);

  // remove 'item' from the set;
  // return 'false' if 'item' is not in the set
  boolean remove(T item);

  // is 'item' in the set?
  boolean contains(T item);

}
