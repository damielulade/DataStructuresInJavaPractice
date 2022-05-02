package trees;

/*
We want to compare the elements of type T in the tree
to decide if it is less than or greater than.

The interface is mutable:
you get back a different tree when you apply functions.
 */
public interface BST<T extends Comparable<T>> {

  boolean add(T element);

  boolean remove(T element);

  boolean contains(T element);
}
