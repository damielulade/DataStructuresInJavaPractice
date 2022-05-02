package trees.arraybased;

import java.util.Arrays;
import trees.BST;

/*
+ fast 'contains' and 'add' functions
- unless resizing is required. this may be a problem
- potentially a lot of memory wasted because of pre-allocation
* concurrency?

Left child at position (2 * i) + 1
Right child at position (2 * i) + 2
Parent at position (i - 1) div 2
 */
public class ArrayBST<T extends Comparable<T>> implements BST<T> {

  private static final int DEFAULT_ARRAY_SIZE = 1024;
  private final T[] elements;
  private int size;

  public ArrayBST(int capacity) {
    elements = (T[]) new Comparable[capacity];
    size = 0;
  }

  public ArrayBST() {
    this(DEFAULT_ARRAY_SIZE);
  }

  public static void main(String[] args) {
    ArrayBST<Integer> bst = new ArrayBST<>((int) Math.pow(2, 4) - 1);
    bst.add(14);
    bst.add(11);
    bst.add(17);
    bst.add(9);
    bst.add(13);
    bst.add(12);
    bst.add(18);

    System.out.println(bst);
  }

  private int getLeftChildIndex(int root) {
    return root * 2 + 1;
  }

  private int getRightChildIndex(int root) {
    return root * 2 + 2;
  }

  private int getParentIndex(int child) {
    return (child - 1) / 2;
  }

  @Override
  public boolean add(T element) {
    return addIterative(0, element);
  }

  // self-made
  // tested using main function and is equal to recursive func.
  private boolean addIterative(int root, T element) {
    int pos = root;
    while (true) {
      if (pos >= elements.length) {
        throw new ArrayIndexOutOfBoundsException(
            "Position " + pos + " is beyond the size of the elements.");
      }
      if (elements[pos] == null) {
        elements[pos] = element;
        size++;
        return true;
      } else {
        if (element.compareTo(elements[pos]) == 0) {
          return false;
        } else if (element.compareTo(elements[pos]) < 0) {
          pos = getLeftChildIndex(pos);
        } else {
          pos = getRightChildIndex(pos);
        }
      }
    }
  }

  // given by the notes
  private boolean addRecursive(int pos, T element) {
    if (pos >= elements.length) {
      throw new ArrayIndexOutOfBoundsException(
          "Position " + pos + " is beyond the sie of the elements.");
    }
    // if there is no element at this position...
    if (elements[pos] == null) {
      elements[pos] = element;
      size++;
      return true;
    } else {
      /*
      If the element at this position is the same as the one we want to add;
      Else, if the element we add is smaller than the one at POS;
      Otherwise... (our element is greater than the element at POS)
       */
      if (element.compareTo(elements[pos]) == 0) {
        return false;
      } else if (element.compareTo(elements[pos]) < 0) {
        int leftChildPos = getLeftChildIndex(pos);
        return addRecursive(leftChildPos, element);
      } else {
        int rightChildPos = getRightChildIndex(pos);
        return addRecursive(rightChildPos, element);
      }
    }
  }

  @Override
  public boolean remove(T element) {
    return false;
  }

  @Override
  public boolean contains(T element) {
    return contains(0, element);
  }

  private boolean contains(int pos, T element) {
    if (pos >= elements.length) {
      return false;
    }
    if (elements[pos] == null) {
      return false;
    } else {
      if (element.compareTo(elements[pos]) == 0) {
        return true;
      } else if (element.compareTo(elements[pos]) < 0) {
        int leftChildPos = getLeftChildIndex(pos);
        return contains(leftChildPos, element);
      } else {
        int rightChildPos = getRightChildIndex(pos);
        return contains(rightChildPos, element);
      }
    }
  }

  @Override
  public String toString() {
    return Arrays.toString(elements);
  }

}
