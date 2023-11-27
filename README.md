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


