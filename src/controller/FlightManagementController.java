package controller;

import java.util.Vector;

import model.Flight;
import model.FlightFilter;
import model.PaymentAgency;

public class FlightManagementController {
	private Vector<Flight> flights;
	private FlightFilter filter;
	
	public Vector<Flight> getFlights() { return flights; }
	public FlightFilter getFilter() { return filter; }
	public void setFlights(Vector<Flight> flights) { this.flights = flights; }
	public void setFilter(FlightFilter filter) { this.filter = filter; }
	
	public void showFilterForm() {}
	public void receiveFilteredInfo() {}
	public void showFlights() {}
	public void loadFlightsFromDB() {}
	public void showReservationForm() {}
	public void requestPayment(int price, PaymentAgency agency) {}
	public void saveFlightsToDB() {}
	public void showCompleteDialog() {}
}
