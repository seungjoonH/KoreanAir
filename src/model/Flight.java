package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import controller.FlightManagementController;
import main.DateUtil;

public class Flight {
	private String id;
	private String airlineName;
	private Airport departure;
	private Airport destination;
	private int departureGateNo;
	private int destinationGateNo;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;

	private Vector<Seat> seats;
	
	public String getId() { return id; }
	public String getAirlineName() { return airlineName; }
	public Airport getDeparture() { return departure; }
	public Airport getDestination() { return destination; }
	public int getDepartureGateNo() { return departureGateNo; }
	public int getDestinationGateNo() { return destinationGateNo; }
	public LocalDateTime getDepartureTime() { return departureTime; }
	public LocalDateTime getArrivalTime() { return arrivalTime; }
	public Vector<Seat> getSeats() { return seats; }
	public void setId(String id) { this.id = id; }
	public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
	public void setDeparture(Airport departure) { this.departure = departure; }
	public void setDestination(Airport destination) { this.destination = destination; }
	public void setDepartureGateNo(int departureGateNo) { this.departureGateNo = departureGateNo; }
	public void setDestinationGateNo(int destinationGateNo) { this.destinationGateNo = destinationGateNo; }
	public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
	public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
	public void setSeats(Vector<Seat> seats) { this.seats = seats; }
	
	public Flight(String[] csv) { fromCsv(csv); }
	public void fromCsv(String[] csv) {
		int l = csv.length, c = 0;
		if (l > c) id = csv[c++];
		if (l > c) airlineName = csv[c++];
		if (l > c) departure = FlightManagementController.getAirport(csv[c++]);
		if (l > c) destination = FlightManagementController.getAirport(csv[c++]);
		if (l > c) departureGateNo = Integer.parseInt(csv[c++]);
		if (l > c) destinationGateNo = Integer.parseInt(csv[c++]);
		if (l > c) departureTime = LocalDateTime.parse(csv[c++], DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"));
		if (l > c) arrivalTime = LocalDateTime.parse(csv[c++], DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"));
	}
	
	public String[] toStrList() {
	    return new String[]{
    		id, 
    		airlineName, 
    		departure.city, 
    		destination.city, 
    		DateUtil.timeToString(departureTime), 
    		DateUtil.timeToString(arrivalTime)
		};
	}
	
	public int getDuration() { return 0; }
}