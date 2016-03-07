import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Template from Professor Ferguson CSC 171
public class GraphicsPanel extends JPanel{

	ArrayList<Shape> elements;
	ArrayList<Line2D.Double> minTree;
	ArrayList<Ellipse2D.Double> minPath;
	JButton minTreeButton;
	boolean drawTree = true;
	boolean drawPath = true;
	Graph graph;
	
	public GraphicsPanel(Graph graph){
		this.graph = graph;
		elements = new ArrayList<>();
		minTree = new ArrayList<>();
		minPath = new ArrayList<>();
		minTreeButton = new JButton("Toggle Minimum Spanning Tree");
		GraphicsPanel g = this;
		//JLabel enterX = new JLabel("Start Vertex id:");
		//JLabel entery = new JLabel("Start Vertex id:");
		JTextField textX = new JTextField("Start Vertex id");
		JTextField textY = new JTextField("End Vertex id:");
		JButton buttonPath = new JButton("Calculate Shortest Path");
		buttonPath.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				minPath.clear();
				boolean ret = graph.shortestPath(textX.getText(), textY.getText());
				if(!ret)
					JOptionPane.showMessageDialog(null, "No Path Found");
				
				for(Vertex v : graph.minPath){
					if(v == null)
						break;
					double x1 = graph.verts.get(v.id).posx;
					double y1 = graph.verts.get(v.id).posy;
					minPath.add(new Ellipse2D.Double(x1, y1, 5.0, 5.0));
				}
				
				
				g.repaint();
			}
			
		});
		JButton pathToggle = new JButton("Toggle Shortest Path");
		pathToggle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				drawPath = !drawPath;
				g.repaint();
			}
			
		});
		
		
		minTreeButton.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				drawTree = !drawTree;
				g.repaint();
			}
		});
		this.add(textX);
		this.add(textY);
		this.add(buttonPath);
		this.add(pathToggle);
		this.add(minTreeButton);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		for(Shape s : elements){
			if(s==null)
				continue;
			if(s instanceof Line2D.Double){
				Line2D.Double l = (Line2D.Double)s;
				g.drawLine((int)l.x1, (int)l.y1, (int)l.x2, (int)l.y2);
			}else{
				Ellipse2D.Double e = (Ellipse2D.Double)s;
				g.fillOval((int)(e.x - e.width/2.0), (int)(e.y - e.height/2.0), (int)e.width, (int)e.height);
			}
		}
		
		g.setColor(Color.RED);
		if(drawTree){
		for(Line2D.Double s : minTree){
			if(s==null)
				continue;
			g.drawLine((int)s.x1, (int)s.y1, (int)s.x2, (int)s.y2);
			
		}};
		
		g.setColor(Color.green);
		if(drawPath){
			int prevX = -1, prevY = -1;
			for(Ellipse2D.Double e : minPath){
				g.fillOval((int)(e.x - e.width/2.0), (int)(e.y - e.height/2.0), (int)e.width, (int)e.height);
				if(prevX != -1){
					g.drawLine(prevX, prevY, (int)e.x, (int)e.y);
				}
				prevX = (int)e.x; prevY = (int)e.y;
			}
		}
	}  

	public void addElement(Double x1, Double y1, Double x2, Double y2){
		elements.add(new Line2D.Double(x1, y1, x2, y2));
	}
	
	public void addElement(Double x, Double y, Double radius){
		elements.add(new Ellipse2D.Double(x,y,radius/2, radius/2));
	}
	
	public void printMinimumSpanningTree(){
		
	}
	
}
