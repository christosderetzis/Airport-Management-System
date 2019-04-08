import java.util.ArrayList;

public class CentralRegistry {
	private static ArrayList<Flight> flights = new ArrayList<Flight>();
	private static ArrayList<Airport> airports = new ArrayList<Airport>();
	
	
	
	public static ArrayList<Flight> getFlights() {
		return flights;
	}

	public static ArrayList<Airport> getAirports() {
		return airports;
	}

	public static void addAirport(Airport anAirport) {
		airports.add(anAirport);
	}
	
	public static void addFlight(Flight aFlight) {
		flights.add(aFlight);
		aFlight.getAirportA().AddAirport(aFlight.getAirportB());
		aFlight.getAirportA().AddCompany(aFlight.getCompany());
		aFlight.getAirportB().AddCompany(aFlight.getCompany());
	}
	
	public static Airport getLargestHub() {
		Airport max = airports.get(0);
		for(int i=0; i<airports.size();i++) {
			Airport anAirport = airports.get(i);
			if(anAirport.getConnectedAirports().size() > max.getConnectedAirports().size()) {
				max = anAirport;
			}
		}
		return max;
	}
	
	public static Flight getLongestFlight() {
		Flight max = flights.get(0);
		for(Flight f: flights) {
			if(f.getDuration() > max.getDuration()) {
				max = f;
			}
		}
		return max;
	}
	
	public static Airport getAirport(String cityName) {
		for(Airport a: airports) {
			if(a.getCity().equals(cityName)) {
				return a;
			}
		}
		return null;
	}
	
	public static String getDirectFlightsDetails(Airport a1, Airport a2) {
		String message = "DIRECT FLIGHTS DETAILS:" + System.lineSeparator();
		if(a1.isDirectlyConnectedTo(a2)) {
			int counter = 0;
			for(Flight f: flights) {
				if(f.getAirportA().getName().equals(a1.getName()) && f.getAirportB().getName().equals(a2.getName())) {
					counter++;
					message = message + "[" + counter + "]" + f.toString() + System.lineSeparator();
				}
			}
		}
		return message;
	}
	
	public static String getIndirectFlightsDetails(Airport a1, Airport a2) {
		String message="INDIRECT FLIGHTS through... " + System.lineSeparator();
		if(a1.isInDirectlyConnectedTo(a2)) {
			int counter=0;
			for(Airport a: airports) {
				if(a1.isDirectlyConnectedTo(a) && a.isDirectlyConnectedTo(a2)) {
					counter++;
					message = message + "[" + counter + "]" + a.getCity() + ", " + a.getCoded_name() + " Airport" + System.lineSeparator();
				}
			}
		}
		return message;
	}
	
	
}
