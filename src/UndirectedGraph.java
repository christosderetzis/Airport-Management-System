import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class UndirectedGraph {
	// Create an undirected graph with Airports as nodes and Flights as edges
	private Graph<Airport,Flight> g = new UndirectedSparseMultigraph<Airport,Flight>();
	
	public UndirectedGraph() {
		
		
		for(Flight f: CentralRegistry.getFlights()) {
			// check if there is not an edge between two airports
			if(g.findEdge(f.getAirportA(), f.getAirportB()) == null) {
				g.addEdge(f, f.getAirportA(), f.getAirportB(),EdgeType.UNDIRECTED);
			}
		}
	}
	
	public Graph<Airport,Flight> getGraph(){
		return g;
	}
	
}
