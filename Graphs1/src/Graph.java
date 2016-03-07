//@author Ben King
//DEPRECATED
import java.util.Random;

public class Graph {
	
	
	public static void main(String[] args){
		Graph graph = new Graph(100, false);
		
		Random r = new Random();
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(r.nextBoolean()){
					graph.insert(new Edge(i,j));
				}
			}
		}
		
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(r.nextBoolean()){
					graph.delete(new Edge(i,j));
				}
			}
		}
		
		System.out.println("Undirected Graph:");
		graph.show();
	
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(r.nextBoolean()){
					graph.insert(new Edge(i,j));
				}
			}
		}
		
		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				if(r.nextBoolean()){
					graph.delete(new Edge(i,j));
				}
			}
		}
		
		System.out.println("Directed Graph:");
		graph.show();
	
	
	}
	
	
	
	private int vertexCount, edgeCount;
	boolean directed; // false for undirected graphs, true for directed
	private boolean adj[][];

	public Graph(int numVertices, boolean isDirected) { 
		directed = isDirected;
		adj = new boolean[numVertices][numVertices];
		for(boolean[] i : adj)
			for(boolean j : i)
				j = false;
		vertexCount = numVertices;
	}

	public boolean isDirected() { 
		return directed;
	}

	public int vertices() {
		return vertexCount;// return the number of vertices }
	}

	public int edges() {
		return edgeCount;// return number of edges }
	}

	public void insert(Edge e) {
		adj[e.v][e.w] = true;
		if(!directed)
			adj[e.w][e.v] = true;// your code here }
	}

	public void delete(Edge e) {
		adj[e.v][e.w] = false;
		if(!directed)
			adj[e.w][e.v] = false;// your code here }
	}

	public boolean connected(int node1, int node2) {
		return adj[node1][node2];// are they connected? }
	}

	public AdjList getAdjList(int vertex) { // your code here }
		return new AdjArray(vertex);
	}

	public void show () {
		for (int s = 0; s < vertices(); s++) {
			System.out.print(s + ": ");
			AdjList A = getAdjList(s);
			for (int t = A.begin(); !A.end(); t = A.next()) // use of iterator
				System.out.print(t + " ");
			System.out.println();
		}
	}



	private class AdjArray implements AdjList {
		private int v; // what vertex we are interested in
		private int i; // so we can keep track of where we are
		public AdjArray(int v) {
			// write the code for the constructors
			// save the value of the vertex passed in
			// (that will be where the iterator starts)
			// start the “i” counter at negative one
			i = -1; this.v = v;
		}
		public int next() { // perhaps the trickiest method
			// use a for loop to advance the value of “i”
			// “for (++i; i < vertices(); i++)”
			// and search the appropriate row return the index
			// of the next true value found
			// “if (connected(v,i) == true) return i;”
			// if the loop completes without finding anything return -1
			for(++i; i < vertices(); i++){
				if(connected(v,i))
					return i;
			}
			return -1;
		}
		public int begin() {
			// reset “i” back to negative one
			// return the value of a call to “next”
			i = -1;
			return next();
		}
		public boolean end() {
			// if “i” is less than the number of vertices return false
			return i > vertices();
		}
	}
}