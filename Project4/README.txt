Ben King
bking11@u.rochester.edu
No Partner

In this project I took a list of about 10k vertices and 10k edges and drew them to a screen, and provided buttons for drawing the minimum spaninng tree and draw the shortest path to two arbitrary vertices. It also prints the shortest path and Minimum Spanning Tree to the console. I used an adjacency list implementation because an adjacency matrix would waste lots of space in memory as the graph is quite sparse. 

Running Time: 
The minimum spanning tree take O ( E log V), where E is the number of edges and V is the number of Vertices. This is because I sort the edges using java's default sort, which is nlogn, and I use a priority Queue to iterate through the edges which takes several runs of nlogn time. The overall algorithm visits each edge only once, and the bubble down operation takes logn time, so the dominating running time is nlogn.

Dijkstra's shortest path algorithm visits each vertex once and calculates every edge which is connected by a path to the start vertex. This is O (E) running time, times long n for access to the priority queue that was used, so nlogn running time once again. While E can theoretically be as large as V^2, this is not the case in this graph as most vertices have only 2-4 incident edges.

The graph contains  5 class files, the main method being in MonroeCounty.java.