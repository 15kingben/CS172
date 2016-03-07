import java.util.HashMap;

public class Edge implements Comparable <Edge> {

	public String v;
	public String w;
	public double length;
	String id;
	
	public Edge(String v, String w, String id, HashMap<String, Vertex> verts){
		this.id = id;
		this.v = v;
		this.w = w;
		length = Vertex.getLength(verts.get(v), verts.get(w));
	}
	
	public int compareTo(Edge e){
		if(e.length == length)
			return 0;
		else if(length > e.length)
			return 1;
		else
			return -1;
	}
	
	@Override
	public boolean equals(Object e){
		if(e instanceof Object){
		if(((Edge)e).id.equals(id))
			return true;
		return false;
		}
		return false;
	}
}
