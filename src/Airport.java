import java.util.ArrayList;

public class Airport {
	private String name;
	private String coded_name;
	private String city;
	private String country;
	private ArrayList<String> Companies = new ArrayList<String>();
	private ArrayList<Airport> ConnectedAirports = new ArrayList<Airport>();
	
	public Airport(String name, String coded_name, String city, String country) {
		this.name = name;
		this.coded_name = coded_name;
		this.city = city;
		this.country = country;
	}
	
	public String getName() {
		return name;
	}



	public String getCoded_name() {
		return coded_name;
	}



	public String getCity() {
		return city;
	}



	public String getCountry() {
		return country;
	}



	public ArrayList<String> getCompanies() {
		return Companies;
	}



	public ArrayList<Airport> getConnectedAirports() {
		return ConnectedAirports;
	}



	public void AddAirport(Airport a) {
		this.ConnectedAirports.add(a);
		a.getConnectedAirports().add(this);
	}
	
	public void AddCompany(String company) {
		this.Companies.add(company);
	}
	
	public boolean isDirectlyConnectedTo(Airport anAirport) {
		for(Airport A: ConnectedAirports) {
			if(A.getCoded_name().equals(anAirport.getCoded_name())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isInDirectlyConnectedTo(Airport anAirport) {
		for(Airport A: ConnectedAirports) {
			for(Airport airport: A.getConnectedAirports()) {
				if(airport.getCoded_name().equals(anAirport.getCoded_name()) && !airport.equals(this)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public ArrayList<Airport> getCommonConnections(Airport anAirport){
		ArrayList<Airport> commonAirports = new ArrayList<Airport>();
		for(Airport A: this.ConnectedAirports) {
			for(Airport B: anAirport.getConnectedAirports()) {
				if(A.equals(B)) {
					commonAirports.add(A);
				}
			}
		}
		
		return commonAirports;
	}
	
	public void printCompanies() {
		for(String c: this.Companies) {
			System.out.println(c);
		}
	}
	
	
	
	
	
	
	
}
