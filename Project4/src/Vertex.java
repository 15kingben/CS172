import java.util.LinkedList;

public class Vertex {

	String id;
	double posx;
	double posy;
	String minTreeId;	
	LinkedList<Edge> edgeList;
	public Vertex(String id, double posx, double posy){
		this.id = id;
		this.posx = posx;
		this.posy = posy;
		edgeList = new LinkedList<Edge>();
		minTreeId = "-1";
	}
	
	public void insertEdge(Edge e){
		edgeList.add(e);
	}

	public static double getLength(Vertex v1, Vertex v2) {
		return Math.sqrt( Math.pow( (v1.posx - v2.posx) , 2) + Math.pow(v1.posy - v2.posy, 2));
	}
}
