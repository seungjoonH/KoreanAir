package model.dao;

import model.Flight;

public class FlightDAO extends DAO<Flight> {
	private FlightDAO() {}
	private static FlightDAO dao = new FlightDAO();
	
	public static FlightDAO getDAO() { return dao; }

	@Override
	protected String getFilepath() { return filepath + "flights.csv"; };

	@Override
	protected String getHeader() {
		return "id;airline;depAirp;desAirp;depGateNo;desGateNo;depTime;arrTime";
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