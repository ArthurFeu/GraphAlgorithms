class Main {
  public static void main(String[] args) {
    Graph g1 = new Graph(4);
    g1.addEdge(0, 1, 1);
    g1.addEdge(1, 0, 1);
    g1.addEdge(0, 3, 1);
    g1.addEdge(3, 0, 1);

    g1.addEdge(3, 4, 2);
    g1.addEdge(2, 1, 0);

    System.out.println(g1.toString());
    System.out.println("Degree node 3: " + g1.degree(3));
    System.out.println("Highest degree in the graph: " + g1.highestDegree());
    System.out.println("Lowest degree in the graph: " + g1.lowestDegree());
    System.out.println("Density of the graph: " + g1.density());

    System.out.println("\nGraph's complement:\n\n" + g1.complement().toString());

  }
}