public class Graph {
  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;

  public int getCountNodes() {
    return this.countNodes;
  }

  public int getCountEdges() {
    return this.countEdges;
  }

  public int[][] getAdjMatrix() {
    return this.adjMatrix;
  }

  public void addEdge(int source, int sink, int weight) {
    if (source < 0 || sink < 0 || source > this.countNodes - 1 ||
        sink > this.countNodes - 1) {
      System.out.println("\nERROR: Cannot add edge with this source or sink.\n-> SOURCE: " + source
          + " | SINK: " + sink + "\n");
      return;
    }
    if (weight <= 0) {
      System.out.println("\nERROR: Weight have to be greater than zero.\n-> SOURCE: " + source
          + " | SINK: " + sink + "\n");
    }

    this.adjMatrix[source][sink] = weight;
    this.countEdges++;
  }

  public int highestDegree() {
    int highest = 0;
    int aux = 0;
    for (int i = 0; i < this.adjMatrix.length; i++) {
      aux = this.degree(i);
      if (aux > highest)
        highest = aux;
    }
    return highest;
  }

  public Graph complement() {
    Graph complement = new Graph(this.adjMatrix.length);
    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix[i].length; j++) {
        if (this.adjMatrix[i][j] == 0 && i != j) {
          complement.addEdge(i, j, 1);
        }
      }
    }

    return complement;
  }

  public int lowestDegree() {
    int lowest = this.adjMatrix.length;
    int aux = 0;
    for (int i = 0; i < this.adjMatrix.length; i++) {
      aux = this.degree(i);
      if (aux < lowest)
        lowest = aux;
    }
    return lowest;
  }

  public int degree(int node) {
    int count = 0;
    for (int j = 0; j < this.adjMatrix.length; j++) {
      if (adjMatrix[node][j] != 0)
        count++;
    }
    return count;
  }

  public String toString() {
    String str = "";
    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix.length; j++) {
        str += this.adjMatrix[i][j] + "\t";
      }
      str += "\n";
    }
    return str;
  }

  public Graph(int countNodes) {
    this.countNodes = countNodes;
    this.adjMatrix = new int[countNodes][countNodes];
  }

  public boolean subGraph(Graph g2) {
    if (g2.countNodes > this.countNodes) {
      return false;
    }
    for (int i = 0; i < g2.adjMatrix.length; i++) {
      for (int j = 0; j < g2.adjMatrix[i].length; j++) {
        if (g2.adjMatrix[i][j] == 0 && this.adjMatrix[i][j] == 1) {
          return false;
        }
      }
    }
    return true;
  }

  public float density() {
    float edges = this.countEdges;
    float nodes = this.countNodes;
    float density = edges / ((nodes * nodes) - 1);
    return density;
  }
}