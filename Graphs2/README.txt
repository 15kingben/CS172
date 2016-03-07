Ben King
bking11@u.rochester.edu
No Partner

In this lab I implemented an unweighted shortest path algorithm for graphs, and a function to parse directed and undirected graphs from a file. The Algorithm we used utilizes shortcuts thanks to the fact that all the edges are unweighted, and the assumption that the graph is fairly full. It uses 2 for loops which have a running time of O(N^2), with the exact result depending on the number of connections. If i used a priority queue the algorithm could be done in O(NlogN) time.