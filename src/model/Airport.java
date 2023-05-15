package model;

import model.dao.AirportDAO;

import java.io.IOException;

public class Airport implements CSVModel {

	private static final AirportDAO airportDAO = AirportDAO.getDAO();
	public static void loadAll() throws IOException { airportDAO.loadCSV(); }

	String code;
	String country;
	String city;

	public String getCity() { return city; }
	
	public Airport(String[] csv) { fromCSV(csv); }

	public void fromCSV(String[] csvList) {
		int l = csvList.length, c = 0;
		if (l > c && !csvList[c].equals("")) code = csvList[c++];
		if (l > c && !csvList[c].equals("")) country = csvList[c++];
		if (l > c && !csvList[c].equals("")) city = csvList[c++];
	}
	
	public String[] toCSV() {
		return new String[]{ code, country, city }; 
	}
	
	public boolean isSameCode(String code) {
		return this.code.equals(code);
	}
	
	@Override
	public String toString() {
		return code + ", " + country + ", " + city;
	}

	@Override
	public String getKey() { return code; }
}