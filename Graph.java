import java.io.File;
import java.util.*;


public class Graph {
    private int vertexCt;  // Number of vertices in the graph.
    private int[][] capacity;  // Adjacency  matrix
    private int[][] residual; // residual matrix
    private int[][] edgeCost; // cost of edges in the matrix
    private String graphName;  //The file from which the graph was created.
    private int totalFlow; // total achieved flow
    private int source = 0; // start of all paths
    private int sink; // end of all paths

    public Graph(String fileName) {
        this.vertexCt = 0;
        source  = 0;
        this.graphName = "";
        makeGraph(fileName);
    }

    /* 
     * Methods to test if graph is being created properly
     */

    public int getVertexCt() {
        return vertexCt;
    }

    public int getCappacity(int i, int j) {
        return capacity[i][j];
    }

    public int getEdgeCost(int i, int j) {
        return edgeCost[i][j];
    }

    /**
     * Method to add an edge
     *
     * @param source      start of edge
     * @param destination end of edge
     * @param cap         capacity of edge
     * @param weight      weight of edge, if any
     * @return edge created
     */
    private boolean addEdge(int source, int destination, int cap, int weight) {
        if (source < 0 || source >= vertexCt) return false;
        if (destination < 0 || destination >= vertexCt) return false;
        capacity[source][destination] = cap;
        residual[source][destination] = cap;
        edgeCost[source][destination] = weight;
        edgeCost[destination][source] = -weight;
        return true;
    }

    /**
     * Method to get a visual of the graph
     *
     * @return the visual
     */
    public String printMatrix(String label, int[][] m) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n " + label+ " \n     ");
        for (int i=0; i < vertexCt; i++)
            sb.append(String.format("%5d", i));
        sb.append("\n");
        for (int i = 0; i < vertexCt; i++) {
            sb.append(String.format("%5d",i));
            for (int j = 0; j < vertexCt; j++) {
                sb.append(String.format("%5d",m[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Method to make the graph
     *
     * @param filename of file containing data
     */
    private void makeGraph(String filename) {
        try {
            graphName = filename;
            System.out.println("\n****Find Flow " + filename);
            Scanner reader = new Scanner(new File(filename));
            vertexCt = reader.nextInt();
            capacity = new int[vertexCt][vertexCt];
            residual = new int[vertexCt][vertexCt];
            edgeCost = new int[vertexCt][vertexCt];

            // Initialize all matrices to 0
            for (int i = 0; i < vertexCt; i++) {
                for (int j = 0; j < vertexCt; j++) {
                    capacity[i][j] = 0;
                    residual[i][j] = 0;
                    edgeCost[i][j] = 0;
                }
            }

            // If weights, need to grab them from file
            while (reader.hasNextInt()) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                int cap = reader.nextInt();
                int weight = reader.nextInt();
                if (!addEdge(v1, v2, cap, weight))
                    throw new Exception();
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sink = vertexCt - 1;
        System.out.println( printMatrix("Edge Cost" , edgeCost));
    }

    public boolean hasAugmentingPath(int s, int t){
        int[] pred = new int[vertexCt];

        int[] cost = new int[vertexCt];
        for (int i = 0; i < vertexCt; i++){
            pred[i] = -1;
            cost[i] = 20_000; // code broke when I used Integer.MAX_VALUE
        }
        cost[s] = 0;

        for (int i = 0; i < vertexCt; i++) {
            for (int u = 0; u < vertexCt; u++){
                for (int v = 0; v < vertexCt; v++){
                    //since edgcost can be 0, we need to check if the edge exists with capacity which is always greater than 0
                    //when edge is present. then we check if the cost of the edge is less than the cost of the vertex.
                    if (capacity[u][v] > 0 && (cost[u] + edgeCost[u][v]) < cost[v]){
                        cost[v] = cost[u] + edgeCost[u][v];
                        pred[v] = u;
                    }
                }
            }
        }

        // Check for negative cycles
        for (int u = 0; u < vertexCt; u++) {
            for (int v = 0; v < vertexCt; v++) {
                if (capacity[u][v] > 0 && (cost[u] + edgeCost[u][v]) < cost[v]) {
                    System.out.println("Negative cycle detected. No finite augmenting path exists.");
                    return false;
                }
            }
        }
        System.out.println("Cost: " + cost[t]);
        System.out.println("Pred: " + Arrays.toString(pred));

        return !(pred[t] == -1);
    }
 


    public void minCostMaxFlow(){
        System.out.println( printMatrix("Capacity", capacity));
        //findWeightedFlow();
        System.out.println(printMatrix("Residual", residual));
        System.out.println("Has Augmenting Path: " + hasAugmentingPath(0, vertexCt-1));
        //finalEdgeFlow();  
    }

    public static void main(String[] args) {
        String[] files = {"match0.txt", "match1.txt",  "match2.txt"}; // "match3.txt", "match4.txt", "match5.txt","match6.txt", "match7.txt", "match8.txt", "match9.txt"};
        for (String fileName : files) {
            //Add the folder path for simplicity
            fileName = "InputFIles/" + fileName;

            Graph graph = new Graph(fileName);
            graph.minCostMaxFlow();

        }
    }
}