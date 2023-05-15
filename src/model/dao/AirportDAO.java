package model.dao;

import model.Airport;

public class AirportDAO extends DAO<Airport> {
	private AirportDAO() {}
	private static AirportDAO dao = new AirportDAO();
	
	public static AirportDAO getDAO() { return dao; }

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
	
	public Airport getAirport(String code) {
		for (var ap : getObj()) { if (ap.isSameCode(code)) return ap; }
		return null;
	}
}