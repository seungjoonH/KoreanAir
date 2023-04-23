package model;

public class ExternalAirline extends Administrator {
	private String airlineName;
	
	public String getAirlineName() { return airlineName; }
	public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
	
	public void login() {}
	public void addFlight(Flight flight) {}
	public void updateFlight(Flight flight) {}
	public void deleteFlight(Flight flight) {}
}