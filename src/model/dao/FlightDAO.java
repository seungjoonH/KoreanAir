package model.dao;

import model.flight.Flight;

public class FlightDAO extends DAO<Flight> {
	@Override
	protected String getFilepath() { return filepath + "flights.csv"; };

	@Override
	protected String getHeader() {
		return "id;airline;depAirp;desAirp;depGateNo;desGateNo;depTime;arrTime;airplane;firstPrice;businessPrice;economyPrice";
	}
	
	@Override
	protected Flight getConstructor(String[] csvList) {
		return new Flight(csvList);
	}

	@Override
	public void visualize() { 
		System.out.println("\nLoaded Flights(" + getObj().size() + "):"); 
		super.visualize(); 
	}
}