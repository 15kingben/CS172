import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

import javax.swing.JFrame;

public class MonroeCounty {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input= new Scanner(new File("monroe-county.tab"));
		Graph g = new Graph(20000);
		JFrame frame = new JFrame("MonroeCounty");
		GraphicsPanel panel = new GraphicsPanel(g);
		
		
		
		while(input.hasNext()){
			String type = input.next();
			String id = input.next();
			if(type.equals("i")){ 
				double x = Double.parseDouble(input.next());
				x *= (1.5);
				double y = Double.parseDouble(input.next());
				y*=1.5;
				
				
				
				g.insertVertex(id, x, y);
				panel.addElement(x, y, 8.0);
			}else{
				String v1 = input.next();
				String v2 = input.next();
				g.insertEdge(id, v1, v2);
				panel.addElement(g.verts.get(v1).posx, g.verts.get(v1).posy,g.verts.get(v2).posx, g.verts.get(v2).posy);
				//System.out.println(g.verts.get(v1).posx + " " +g.verts.get(v1).posy + " " +g.verts.get(v2).posx + " " + g.verts.get(v2).posy);
			}
		}
		
		//PriorityQueue<Vertex> heap;
		//heap.a(g.verts);
		g.minSpanTree();
		System.out.println("Minimum Spanning Tree Edges by ID: ");
		for(Edge e : g.minTree){
			System.out.println(e.id);
			double x1 = g.verts.get(e.v).posx;
			double x2 = g.verts.get(e.w).posx;
			double y1 = g.verts.get(e.v).posy;
			double y2 = g.verts.get(e.w).posy;
			panel.minTree.add(new Line2D.Double(x1, y1, x2, y2));
		}
		
		//g.shortestPath("i26759940", "i221703754");
		
		/*for(Vertex v : g.minPath){
			if(v == null)
				break;
			double x1 = g.verts.get(v.id).posx;
			double y1 = g.verts.get(v.id).posy;
			panel.minPath.add(new Ellipse2D.Double(x1, y1, 5.0, 5.0));
		}*/
		
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		
		
	}

}
