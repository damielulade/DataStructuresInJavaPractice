package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreadthFirstSearcher {

  private final Map<Node, Node> childParentMap = new HashMap<>();
  private final List<Node> result;
  private Graph graph;

  public BreadthFirstSearcher(Graph graph) {
    this.graph = graph;
    result = new ArrayList<>();
  }

  public void setGraph(Graph graph) {
    this.graph = graph;
  }

  public Map<Node, Node> getChildParentMap() {
    return childParentMap;
  }

  private List<Node> bfs(Node start) {
    result.clear();
    boolean[] visited = new boolean[graph.getNumberOfNodes()];
    Deque<Node> deque = new ArrayDeque<>();
    Node node;

    visited[start.getValue() - 1] = true;
    result.add(start);
    deque.addLast(start);
    while (!deque.isEmpty()) {
      node = deque.removeFirst();
      for (Node adjNode : graph.getAdjacentNodes(node)) {
        if (!visited[adjNode.getValue() - 1]) {
          visited[adjNode.getValue() - 1] = true;
          result.add(adjNode);
          childParentMap.put(adjNode, node);
          deque.addLast(adjNode);
        }
      }
    }
    return result;
  }

  public int[] shortestPath(Node start) {
    result.clear();
    boolean[] visited = new boolean[graph.getNumberOfNodes()];
    int[] distance = new int[graph.getNumberOfNodes()];
    Deque<Node> deque = new ArrayDeque<>();
    Node node;

    deque.addLast(start);
    visited[start.getValue() - 1] = true;
    while (!deque.isEmpty()) {
      node = deque.removeFirst();
      for (Node adjNode : graph.getAdjacentNodes(node)) {
        if (!visited[adjNode.getValue() - 1]) {
          visited[adjNode.getValue() - 1] = true;
          childParentMap.put(adjNode, node);
          distance[adjNode.getValue() - 1] = distance[node.getValue() - 1] + 1;
          deque.addLast(adjNode);
        }
      }
    }
    return distance;
  }
}
