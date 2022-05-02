package graphs;

import java.util.Set;

public class Main {

  private static final Graph graph = new Graph();

  public static void main(String[] args) {
    Node start = new Node(1);
    buildGraph(start);
    DepthFirstSearcher dfsMachine = new DepthFirstSearcher(graph);
    BreadthFirstSearcher bfsMachine = new BreadthFirstSearcher(graph);

    dfsMachine.topologicalSort();

//    int i = 1;
//    for (int distance : bfsMachine.shortestPath(start)) {
//      System.out.print(i + " = " + distance + "\n");
//      i++;
//    }
  }

  private static void buildGraph(Node startNode) {
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);
    Node node8 = new Node(8);
    graph.addNodes(Set.of(startNode, node2, node3, node4, node5, node6, node7, node8));
    Arc arcA = new Arc(startNode, node2);
    Arc arcB = new Arc(startNode, node8);
    Arc arcC = new Arc(node2, node3);
    Arc arcD = new Arc(node2, node4);
    Arc arcE = new Arc(node8, node4);
    Arc arcF = new Arc(node8, node7);
    Arc arcG = new Arc(node4, node5);
    Arc arcH = new Arc(node4, node6);
    Arc arcI = new Arc(node7, node6);
    graph.addArcs(Set.of(arcA, arcB, arcC, arcD, arcE, arcF, arcG, arcH, arcI));
  }

}
