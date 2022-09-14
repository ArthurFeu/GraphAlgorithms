class Main {
  public static void main(String[] args) {
    Graph g1 = new Graph(3);

    g1.addEdge(0, 1, 1);
    g1.addEdge(0, 2, 6);
    g1.addEdge(2, 1, 3);
    g1.addEdge(1, 2, 4);
    g1.FloydWarshall(g1);

    // g1.addUnorientedEdge(1, 0, 1);
    // g1.addUnorientedEdge(0, 4, 1);
    // g1.addUnorientedEdge(4, 6, 1);
    // g1.addUnorientedEdge(6, 5, 1);
    // g1.addUnorientedEdge(6, 3, 1);
    // g1.addUnorientedEdge(3, 2, 1);
    // System.out.println(g1.DepthFirstSearch(6));
    // System.out.println(g1.isNonOriented());
    // System.out.println(g1.toString());
    // System.out.println(g1.recursiveDepthFirstSearch(6));

    // g1.addUnorientedEdge(7, 5, 1);
    // g1.addUnorientedEdge(7, 1, 1);
    // g1.addUnorientedEdge(7, 2, 1);
    // g1.addUnorientedEdge(5, 6, 1);
    // g1.addUnorientedEdge(1, 0, 1);
    // g1.addUnorientedEdge(1, 4, 1);
    // g1.addUnorientedEdge(2, 3, 1);
    // g1.addUnorientedEdge(6, 8, 1);
    // System.out.println(g1.BreathFirstSearch(7));
    // System.out.println(g1.connected());

    // Graph g2 = new Graph("graph1.txt");
    // System.out.println(g2);

    // g1.addEdge(0, 1, 1);
    // g1.addEdge(1, 0, 1);
    // g1.addEdge(0, 3, 1);
    // g1.addEdge(3, 2, 1);
    // g1.addEdge(3, 4, 2);
    // g1.addEdge(2, 1, 0);
    // System.out.println(g1.isNonOriented());
    // System.out.println(g1.toString());
    // System.out.println("Degree node 3: " + g1.degree(3));
    // System.out.println("Highest degree in the graph: " + g1.highestDegree());
    // System.out.println("Lowest degree in the graph: " + g1.lowestDegree());
    // System.out.println("Density of the graph: " + g1.density());
    // System.out.println("\nGraph's complement:\n\n" + g1.complement().toString());

  }
}