package model;

import java.time.LocalDateTime;
import java.util.Vector;

public class Flight {
	private Vector<Seat> seats;
	private String airlineName;
	private Airport departure;
	private Airport destination;
	private int departureGateNo;
	private int destinationGateNo;
	LocalDateTime departureTime;
	LocalDateTime arrivalTime;
	
	public Vector<Seat> getSeats() { return seats; }
	public String getAirlineName() { return airlineName; }
	public Airport getDeparture() { return departure; }
	public Airport getDestination() { return destination; }
	public int getDepartureGateNo() { return departureGateNo; }
	public int getDestinationGateNo() { return destinationGateNo; }
	public LocalDateTime getDepartureTime() { return departureTime; }
	public LocalDateTime getArrivalTime() { return arrivalTime; }
	public void setSeats(Vector<Seat> seats) { this.seats = seats; }
	public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
	public void setDeparture(Airport departure) { this.departure = departure; }
	public void setDestination(Airport destination) { this.destination = destination; }
	public void setDepartureGateNo(int departureGateNo) { this.departureGateNo = departureGateNo; }
	public void setDestinationGateNo(int destinationGateNo) { this.destinationGateNo = destinationGateNo; }
	public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
	public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
	
	public int getDuration() {
		return 0;
	}
}
