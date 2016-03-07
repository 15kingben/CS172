Ben King
bking11@u.rochester.edu
No Partner

In this lab I implemented Dijsktra's algorithm for shortest weighted path using my previous adjacency list implementation of a graph. The program runs in O(Nlogn) time, because it does a constant time operation on each vertex, (if the graph is fairly sparse), and finds the next vertex in O(logn) time thanks to the use of a priority queue. While the program does not crash when a negative weight is used, the results are not guaranteed correct because the algorithm does not assume negative weights. 