import java.awt.Dimension;

import javax.swing.*;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class UndirectedGraphFrame extends JFrame{
	
	private JPanel panel = new JPanel();
	private JTextField txt = new JTextField(40);
	
	public UndirectedGraphFrame() {
		// create an instance of UndirectedGraph class
		UndirectedGraph ug = new UndirectedGraph();
		
		// visualization of the undirected graph
		Layout<Airport,Flight> layout = new CircleLayout(ug.getGraph());
		layout.setSize(new Dimension(300,300));
		
		BasicVisualizationServer<Airport,Flight> vv = new BasicVisualizationServer<Airport,Flight>(layout);
		vv.setPreferredSize(new Dimension(350,350));
		
		//set name of the city as a label to each node
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller() {
			 public String transform(Object v) {

                 return ((Airport)v).getCity();
             }
		});	
		
		// Diameter of a graph
		DistanceStatistics dist = new DistanceStatistics();
		double diameter = dist.diameter(ug.getGraph());
		txt.setText("Diameter = " + diameter);
				
		this.setContentPane(panel);
		this.setTitle("City Airport Connections Network");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(460, 420);
		
		panel.add(vv);
		panel.add(txt);
		
		
	}

}
