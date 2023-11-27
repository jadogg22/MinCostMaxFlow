# Program 7 Min Cost Max FLow
We be learning and now implementing graphs.

## Part 1 - getting started

- [X] Draw a picture (for your own use) of what each graph looks like.
- [X] Get all files reading in the Program.

## Part 2 - Find cheapest path using Bellman Ford.

Using the graph you input in Part 1, find a cheapest path (in terms of cost) from the source to the sink.
While Dijkstra’s algorithm seems like a good one to use, we are going to use Bellman Ford (as we will
have negative edges in the future.

**psuedo code from doc**
```JAVA
private boolean hasAugmentingCheapestPath(FlowNetwork G, int s, int t) {
    clear pred
    set costs to high value;
    cost[s]=0
    for (int i = 0; i < vertexCt; i++) {
        for (int u = 0; u < vertexCt; u++) {
            for (int v = 0; v < vertexCt; v++) {
                if ( the edge from [u,v] exists and creates a cheaper path ) {
                    cost[v] = cost[u] + edgeCost[u][v];
                    pred[v] = u;
                }
            }
        }
    }   
    return pred[t] exists;
}
```

- [X] Implement bellman ford algorythm.
- [X] test to make sure that it is finding cheepest path

## Part 3: Maximum Match

In the mathematical field of graph theory, a bipartite graph (or bigraph) is a graph whose
vertices can be divided into two sets such that each edge connects a vertex in each group.
A matching in a Bipartite Graph is a set of the edges chosen in such a way that no two edges
share an endpoint. A maximum matching is a matching of maximum size (maximum number
of edges). There can be more than one maximum matchings for a given Bipartite Graph.

here is what this algoyrthm is trying to solve

1. The most amount of people have a book.
2. maxamize their happyness with the "cost" function



