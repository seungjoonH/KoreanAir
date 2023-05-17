package model.dao;

import model.flight.Airport;

public class AirportDAO extends DAO<Airport> {
	@Override
	protected String getFilepath() { return filepath + "airports.csv"; };

	@Override
	protected String getHeader() {
		return "code;country;city";
	}
	
	@Override
	protected Airport getConstructor(String[] csvList) {
		return new Airport(csvList);
	}

	@Override
	public void visualize() { 
		System.out.println("\nLoaded Airports(" + getObj().size() + "):"); 
		super.visualize(); 
	}
}