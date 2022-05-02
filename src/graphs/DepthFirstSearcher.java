package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepthFirstSearcher {

  private int[] topSort;
  private int index;
  private final Map<Node, Node> childParentMap = new HashMap<>();
  private final List<Node> result;
  private Graph graph;

  public DepthFirstSearcher(Graph graph) {
    this.graph = graph;
    result = new ArrayList<>();
  }

  public void setGraph(Graph graph) {
    this.graph = graph;
  }

  public List<Node> getCurrentResult() {
    return result;
  }

  /**
   * Original depth-first search algorithm:
   *
   * A boolean array is used to represent visited nodes
   * It uses the provided start node.
   */
  public List<Node> dfs(Node start) {
    result.clear();
    boolean[] visited = new boolean[graph.getNumberOfNodes()];
    recursiveDFS(start, visited);
    return result;
  }

  /**
   * This is a recursive algorithm.
   *
   * 1. Visit a node and add it to the list of results/print it.
   * 2. For every adjacent node (in numerical order):
   *    If you have not visited the adjacent node:
   *    A. Set the adjacent node as the child of the parent node.
   *    B. Recursively apply DFS on the adjacent node.
   */
  private void recursiveDFS(Node node, boolean[] visited) {
    visited[node.getValue() - 1] = true;
    result.add(node);
    for (Node adjNode : graph.getAdjacentNodes(node)) {
      if (!visited[adjNode.getValue() - 1]) {
        childParentMap.put(adjNode, node);
        recursiveDFS(adjNode, visited);
      }
    }
  }

  /**
   * DFS algorithm redesigned to test for cycles and return arcs that produce cycles.
   */
  public Arc testForCycles(Node start) {
    result.clear();
    boolean[] visited = new boolean[graph.getNumberOfNodes()];
    return cycleDFS(start, visited);
  }

  /**
   * Recursive algorithm.
   *
   * 1. Visit a node and add it to the list of results/print it.
   * 2. For every adjacent node (in numerical order):
   *    A. If you have visited the adjacent node, and it isn't the assigned child of the main node:
   *       You have found a cycle. Return the arc connecting the two nodes.
   *    B. If you have not visited the adjacent node:
   *       I.  Set the adjacent node as the child of the main node.
   *       II. Get the recursive pair for the adjacent node.
   *           If a pair was found, return that pair.
   * Return a null pair if every adjacent node for that node has been checked
   */
  private Arc cycleDFS(Node node, boolean[] visited) {
    visited[node.getValue() - 1] = true;
    result.add(node);
    for (Node adjNode : graph.getAdjacentNodes(node)) {
      if (visited[adjNode.getValue() - 1] && childParentMap.get(node) != adjNode) {
        return graph.returnArc(node, adjNode);
      }
      if (!visited[adjNode.getValue() - 1]) {
        childParentMap.put(adjNode, node);
        Arc pair = cycleDFS(adjNode, visited);
        if (pair != null) {
          return pair;
        }
      }
    }
    return null;
  }

  /**
   * DFS algorithm adapted for topological sort
   *
   * A boolean array is used to represent visited nodes
   * It uses the provided start node.
   */
  public void topologicalSort() {
    index = graph.getNumberOfNodes() - 1;
    topSort = new int[graph.getNumberOfNodes()];
    boolean[] entered = new boolean[graph.getNumberOfNodes()];
    boolean[] exited = new boolean[graph.getNumberOfNodes()];
    List<Node> nodes = graph.nodes.stream().sorted().collect(Collectors.toList());
    for (Node node : nodes) {
      if (!entered[node.getValue() - 1]) {
        dfsTS(node, entered, exited);
      }
    }
    for (int node : topSort) {
      System.out.print(node + " ");
    }
  }


  private void dfsTS(Node node, boolean[] entered, boolean[] exited) {
    entered[node.getValue() - 1] = true;
    for (Node adjNode : graph.getAdjacentNodes(node)) {
      if (entered[adjNode.getValue() - 1]) {
        if (!exited[adjNode.getValue() - 1]) {
          return;
        }
      } else {
        childParentMap.put(adjNode, node);
        dfsTS(adjNode, entered, exited);
      }
    }
    exited[node.getValue() - 1] = true;
    topSort[index] = node.getValue();
    index--;
  }
}
