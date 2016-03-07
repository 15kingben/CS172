//@author Ben King
// Of course, we need an edge class
public class Edge {
	public final int v, w; // an edge from v to w

	public int weight;
	
	public Edge(int v, int w, int weight) { 
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
}