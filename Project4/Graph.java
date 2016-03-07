import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

//@author Ben King
//New Graph class using linked list implementation
public class Graph {

	boolean isDirected;
	int vertexCount;
	int edgeCount;
	
	HashMap<String, Vertex> verts;
	HashMap<String, Integer> idtoindex;
	ArrayList<Vertex> vertsArray;
	ArrayList<Edge> edgeArray;
	ArrayList<Edge> minTree;
	ArrayList<Vertex> minPath;
	
	
	public Graph(int size){
		verts = new HashMap<String, Vertex>(size);
		vertsArray = new ArrayList<>();
		edgeArray = new ArrayList<>();
		idtoindex = new HashMap<>(size*2);
		minTree = new ArrayList<>(size*2);
		minPath = new ArrayList<>();
	}
	
	public boolean isDirected(){
		return isDirected;
	}
	
	public int getVertexCount(){
		return vertexCount;
	}
	
	public int edgeCount(){
		return edgeCount;
	}
	
	public void insertVertex(String id, double posx, double posy){
		verts.put(id , new Vertex(id, posx, posy));
		vertsArray.add(new Vertex(id, posx, posy));
		idtoindex.put(id, vertexCount++);
	}
	
	public void insertEdge(String edgeId, String intId1, String intId2){
		edgeCount++;
		verts.get(intId1).insertEdge(new Edge(intId1, intId2, edgeId, verts));
		verts.get(intId2).insertEdge(new Edge(intId2, intId1, edgeId, verts));
		edgeArray.add(new Edge(intId1, intId2, edgeId, verts));
		//edgeArray.add(new Edge(intId2, intId1, edgeId, verts));
	}
	
	public boolean isConnected(Vertex v1, Vertex v2){
		for(Edge e : v1.edgeList){
			if(e.w == v2.id){
				return true;
			}
		}
		return false;
	}
	
	
	
	public boolean shortestPath(String startId, String endId){		
		
		if(verts.get(startId) == null || verts.get(endId) == null){
			return false;
		}
		
		HashMap<String, vWrapper> d = new HashMap<>();
		PriorityQueue<vWrapper> q = new PriorityQueue<>(new Comparator<vWrapper>(){
			@Override
			public int compare(vWrapper arg0, vWrapper arg1) {
				if(arg0.distance > arg1.distance)
					return 1;
				if(arg0.distance == arg1.distance)
					return 0;
				return -1;
			}});
		
		
		for(Vertex v : vertsArray){
			vWrapper wrap = new vWrapper(verts.get(v.id));
			d.put(v.id, wrap);
			q.add(wrap);
		}
		
		vWrapper v = d.get(startId);
		v.distance = 0;
		v.known = true;
		
		for(Edge e : v.vertex.edgeList){
			vWrapper wrap = new vWrapper(verts.get(e.w));
			wrap.distance = e.length;
			wrap.path = v;
			q.remove(wrap);
			q.add(wrap);
		}
		
		while(!q.isEmpty()){
			vWrapper next = q.poll();
			next.known = true;
			//System.out.println(next.vertex.id);
			for(Edge e : next.vertex.edgeList){
				vWrapper wrap = d.get(e.w);
				if(next.distance + e.length < wrap.distance){
					wrap.distance = next.distance + e.length;
					wrap.path = next;
					q.remove(wrap);
					q.add(wrap);
				}
			}
		}
		
		
		
		vWrapper p = d.get(endId);
		if(p.path == null)
			return false;
		minPath.add(p.vertex);
		while(p.path != null){
			
			minPath.add(p.path.vertex);
			System.out.println(p.path.vertex.id);
			p = p.path;
		}
		return true;
		
		
	}
	
	
	
	class vWrapper{
		Vertex vertex;
		boolean known;
		double distance;
		vWrapper path;
		vWrapper(Vertex v){
			this.vertex = v;
			known = false;
			distance = Double.MAX_VALUE;
			path = null;
		}
	}
	
	
	public void minSpanTree(){
		int[] tree = new int[vertexCount];
		int treeCount = 0;
		int edgeCount = 0;
		int skipCount = 0;
		edgeArray.sort(null);
		//for(Edge e : edgeArray)
		//	System.out.println(e.id + " " + e.length);
		
		for(int i = 0; i < tree.length; i++){
			tree[i] = -1;
		}
		
		while(treeCount < vertexCount){
			if(edgeCount >= edgeArray.size())
				break;
			Edge e = edgeArray.get(edgeCount++);
			
			
			
			
			
			int v = idtoindex.get(e.v);
			int w = idtoindex.get(e.w);
			/*if(tree[v] == -1 && tree[w] == -1){
				tree[w] = tree[v];
			}else if(tree[v] == -1){
				tree[v] = find(w, tree);
			}else if(tree[w] == -1){
				tree[w] = find(v, tree);*/
			
			if(find(v, tree) != find(w, tree) ){
				 union(find(v,tree) , find(w,tree),tree);
			}else{
				skipCount++;
				continue;
			}
			treeCount++;
			minTree.add(e);
		}
		
	}
	
	public int find(int index, int[] tree){
		if(tree[index] < 0){
			return index;
		}
		return (tree[index] = find(tree[index], tree));
	}
	
	public void union(int v, int w, int[] tree){
		//while(tree[w] != -1){
		//	w = tree[w];
		//	System.out.println("Union; " + w);
		//}
		
		tree[w] = v;
	}
}
