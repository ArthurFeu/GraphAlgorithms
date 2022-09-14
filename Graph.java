import java.io.*;
import java.util.*;

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

  public void addUnorientedEdge(int source, int sink, int weight) {
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
    this.adjMatrix[sink][source] = weight;
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

  public ArrayList<Integer> BreathFirstSearch(int s) {
    int desc[] = new int[this.countNodes];
    desc[s] = 1;
    ArrayList<Integer> r = new ArrayList<Integer>();
    ArrayList<Integer> q = new ArrayList<Integer>();
    q.add(s);
    r.add(s);

    while (q.size() > 0) {
      int u = q.remove(0);

      for (int node = 0; node < this.adjMatrix[u].length; ++node) {
        if (this.adjMatrix[u][node] != 0) {
          if (desc[node] == 0) {
            q.add(node);
            r.add(node);
            desc[node] = 1;
          }
        }
      }
    }

    return r;
  }

  public ArrayList<Integer> DepthFirstSearch(int s) {
    int desc[] = new int[this.countNodes];
    ArrayList<Integer> r = new ArrayList<Integer>();
    ArrayList<Integer> q = new ArrayList<Integer>();
    Stack<Integer> S = new Stack<Integer>();
    desc[s] = 1;

    q.add(s);
    r.add(s);
    S.push(s);
    int flag = 0;
    while (S.size() != 0) {
      flag = 0;
      int u = S.peek();
      for (int node = 0; node < this.adjMatrix[u].length; ++node) {
        if (this.adjMatrix[u][node] != 0 && desc[node] == 0) {
          flag++;
          S.push(node);
          r.add(node);
          desc[node] = 1;
          break;
        }
      }
      if (flag == 0) {
        S.pop();
      }
    }

    return r;
  }

  public boolean connected() {
    return this.BreathFirstSearch(0).size() == this.countNodes;
  }

  public Graph(String fileName) throws IOException {
    File file = new File(fileName);
    FileReader reader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(reader);

    // Read header
    String[] line = bufferedReader.readLine().split(" ");
    this.countNodes = (Integer.parseInt(line[0]));
    int fileLines = (Integer.parseInt(line[1]));

    // Create and fill adjMatrix with read edges
    this.adjMatrix = new int[this.countNodes][this.countNodes];
    for (int i = 0; i < fileLines; ++i) {
      String[] edgeInfo = bufferedReader.readLine().split(" ");
      int source = Integer.parseInt(edgeInfo[0]);
      int sink = Integer.parseInt(edgeInfo[1]);
      int weight = Integer.parseInt(edgeInfo[2]);
      addEdge(source, sink, weight);
    }
    bufferedReader.close();
    reader.close();
  }

  public boolean isNonOriented() {
    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = i + 1; j < this.adjMatrix[i].length; j++) {
        if (this.adjMatrix[i][j] != this.adjMatrix[j][i]) {
          return false;
        }
      }
    }
    return true;
  }

  public void recursiveAuxDFS(int u, int desc[], ArrayList<Integer> r) {
    desc[u] = 1;
    r.add(u);
    for (int node = 0; node < this.adjMatrix[u].length; ++node) {
      if (this.adjMatrix[u][node] != 0) {
        if (desc[node] == 0) {
          recursiveAuxDFS(node, desc, r);
        }
      }
    }

  }

  public ArrayList<Integer> recursiveDepthFirstSearch(int s) {
    int desc[] = new int[this.countNodes];
    for (int v = 0; v < this.countNodes; v++) {
      desc[v] = 0;
    }
    ArrayList<Integer> r = new ArrayList<Integer>();
    recursiveAuxDFS(s, desc, r);
    return r;
  }

  public void FloydWarshall(Graph g) {
    int infinite = 999999999;
    int dist[][] = new int[g.countNodes][g.countNodes];
    int pred[][] = new int[g.countNodes][g.countNodes];
    for (int i = 0; i < g.countNodes - 1; i++) {
      for (int j = 0; j < g.countNodes - 1; j++) {
        if (i == j) {
          dist[i][j] = 0;
        } else if (g.adjMatrix[i][j] == 0) {
          dist[i][j] = g.adjMatrix[i][j];
          pred[i][j] = i;
        } else {
          dist[i][j] = infinite;
          pred[i][j] = -1;
        }
      }
    }

    for (int k = 0; k < g.countNodes - 1; k++) {
      for (int i = 0; i < g.countNodes - 1; i++) {
        for (int j = 0; j < g.countNodes - 1; j++) {
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
            pred[i][j] = pred[k][j];
          }
        }
      }
    }

    String str = "";
    for (int i = 0; i < g.countNodes; i++) {
      for (int j = 0; j < g.countNodes; j++) {
        str += pred[i][j] + "\t";
      }
      str += "\n";
    }
    System.out.println(str);

    str = "";
    for (int i = 0; i < g.countNodes; i++) {
      for (int j = 0; j < g.countNodes; j++) {
        str += pred[i][j] + "\t";
      }
      str += "\n";
    }
    System.out.println(str);

  }
}