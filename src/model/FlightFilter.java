package model;

import java.time.LocalDateTime;

public class FlightFilter {
	private String airlineName;
	private Airport departure;
	private Airport destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	
	public String getAirlineName() { return airlineName; }
	public Airport getDeparture() { return departure; }
	public Airport getDestination() { return destination; }
	public LocalDateTime getDepartureTime() { return departureTime; }
	public LocalDateTime getArrivalTime() { return arrivalTime; }
	public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
	public void setDeparture(Airport departure) { this.departure = departure; }
	public void setDestination(Airport destination) { this.destination = destination; }
	public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
	public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; } 
	
	public String getQuery() { return ""; }
}
