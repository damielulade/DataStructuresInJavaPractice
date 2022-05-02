package graphs;

import java.util.Objects;

public class Arc {

  private final Node start;
  private final Node end;
  private int degree;

  public Arc(Node start, Node end) {
    this.start = start;
    this.end = end;
  }

  public Arc(Node start, Node end, int degree) {
    this.start = start;
    this.end = end;
    this.degree = degree;
  }

  public boolean isStartNode(Node node) {
    return start == node;
  }

  public boolean isEndNode(Node node) {
    return end == node;
  }

  public Node getStart() {
    return start;
  }

  public Node getEnd() {
    return end;
  }

  public void setDegree(int degree) {
    this.degree = degree;
  }

  @Override
  public String toString() {
    return "(" + start + ", " + end + ')';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Arc arc = (Arc) o;
    return degree == arc.degree && Objects.equals(start, arc.start)
        && Objects.equals(end, arc.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end, degree);
  }
}
