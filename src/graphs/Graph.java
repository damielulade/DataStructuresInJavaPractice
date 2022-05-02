package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

  protected final Set<Node> nodes;
  protected final Set<Arc> arcs;

  public Graph(Set<Node> nodes, Set<Arc> arcs) {
    this.nodes = nodes;
    this.arcs = arcs;
  }

  public Graph() {
    this.nodes = new HashSet<>();
    this.arcs = new HashSet<>();
  }

  public int getNumberOfNodes() {
    return nodes.size();
  }

  public int getNumberOfArcs() {
    return arcs.size();
  }

  public Set<Arc> getArcs() {
    return arcs;
  }

  public Set<Node> getNodes() {
    return nodes;
  }

  public Arc returnArc(Node start, Node end) {
    for (Arc arc : arcs) {
      if (arc.isStartNode(start) && arc.isEndNode(end)) {
        return arc;
      }
    }
    return null;
  }

  public void addArcs(Set<Arc> arcSet) {
    arcs.addAll(arcSet);
  }

  public void addNodes(Set<Node> nodeSet) {
    nodes.addAll(nodeSet);
  }

  public List<Node> getAdjacentNodes(Node node) {
    List<Node> result = new ArrayList<>();
    for (Arc arc : arcs) {
      if (arc.isEndNode(node)) {
        result.add(arc.getStart());
      } else if (arc.isStartNode(node)) {
        result.add(arc.getEnd());
      }
    }
    result = result.stream().sorted().collect(Collectors.toList());
    return result;
  }

  public int getDegree(Node node) {
    int result = 0;
    for (Arc arc : arcs) {
      if (arc.isEndNode(node) || arc.isStartNode(node)) {
        result++;
      }
    }
    return result;
  }

  public boolean isSubgraph(Graph graph) {
    return nodes.containsAll(graph.getNodes()) && arcs.containsAll(graph.getArcs());
  }

  public void printAdjacencyMatrix() {
    int[][] matrix = makeAdjacencyMatrix();
    StringBuilder string = new StringBuilder();
    for (int[] row : matrix) {
      string.append('(');
      for (int val : row) {
        string.append(" ").append(val);
      }
      string.append(" )\n");
    }
    System.out.println(string);
  }

  private void printAdjacencyList() {
    int sizeOfGraph = getNumberOfArcs();
    StringBuilder string = new StringBuilder();
    List<Node>[] list = new List[sizeOfGraph];
    for (Node node : nodes) {
      int row = node.getValue();
      List<Node> adjacentNodes = getAdjacentNodes(node);
      list[row] = adjacentNodes;
    }
    for (int i = 0; i < sizeOfGraph; i++) {
      string.append(i);
      for (Node node : list[i]) {
        string.append(" -> ").append(node.getValue());
      }
      string.append("\n");
    }
    System.out.println(string);
  }

  private int[][] makeAdjacencyMatrix() {
    int sizeOfGraph = getNumberOfArcs();
    int[][] matrix = new int[sizeOfGraph][sizeOfGraph];
    for (Node node : nodes) {
      int row = node.getValue();
      for (Arc arc : arcs) {
        if (arc.isStartNode(node)) {
          int col = arc.getEnd().getValue();
          matrix[row][col] = matrix[row][col] + 1;
        }
        if (arc.isEndNode(node)) {
          int col = arc.getStart().getValue();
          matrix[row][col] = matrix[row][col] + 1;
        }
      }
    }
    return matrix;
  }
}
