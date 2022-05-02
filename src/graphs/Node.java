package graphs;

public class Node implements Comparable {

  private final int value;

  public Node(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public int compareTo(Object o) {
    if (this == o) {
      return 0;
    }
    if (o == null || getClass() != o.getClass()) {
      return 0;
    }
    Node node = (Node) o;
    return value - node.value;
  }
}
