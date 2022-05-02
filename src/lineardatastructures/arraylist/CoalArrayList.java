package lineardatastructures.arraylist;

import java.util.stream.IntStream;
import lineardatastructures.CoalList;

public class CoalArrayList<T> implements CoalList<T> {

  public static final int DEFAULT_INITIAL_SIZE = 100;
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
  private T[] elements;
  private int size;

  public CoalArrayList(int initialSize) {
    this.size = 0;
    this.elements = (T[]) new Object[initialSize];
  }

  public CoalArrayList() {
    this(DEFAULT_INITIAL_SIZE);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) {
      throw new OutOfMemoryError();
    }
    return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isNotFull() {
    return size != DEFAULT_INITIAL_SIZE;
  }

  @Override
  public T get(int pos) {
    if (!isEmpty() && pos >= 0 && pos <= size) {
      return elements[pos];
    }
    return null;
  }

  @Override
  public void add(int pos, T value) {
    if (isNotFull() && pos >= 0 && pos <= size) {
      shiftSubArrayRight(pos);
      elements[pos] = value;
      size++;
    }
  }

  @Override
  public void add(T value) {
    if (isNotFull()) {
      elements[size] = value;
      size++;
    }
  }

  @Override
  public T remove(int pos) {
    if (!isEmpty() && pos >= 0 && pos <= size) {
      T result = elements[pos];
      shiftSubArrayLeft(pos);
      size--;
      return result;
    }
    return null;
  }

  private void shiftSubArrayLeft(int pos) {
    IntStream.range(pos, size).forEach(i -> elements[i] = elements[i + 1]);
  }

  private void shiftSubArrayRight(int pos) {
    if (size + 1 - pos >= 0) {
      System.arraycopy(elements, pos, elements, pos + 1, size + 1 - pos);
    }
  }

  private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elements.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity < minCapacity) {
      newCapacity = minCapacity;
    }
    if (newCapacity > MAX_ARRAY_SIZE) {
      newCapacity = hugeCapacity(minCapacity);
    }
    T[] temp = (T[]) new Object[newCapacity];
    System.arraycopy(elements, 0, temp, 0, elements.length);
    elements = temp;
  }

}
