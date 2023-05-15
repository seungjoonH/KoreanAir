package model;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import global.DateUtil;
import model.dao.AirportDAO;
import model.dao.FlightDAO;

public class Flight implements CSVModel {
	private static final FlightDAO flightDAO = FlightDAO.getDAO();
	private static final AirportDAO airportDAO = AirportDAO.getDAO();

	public static void loadAll() throws IOException { flightDAO.loadCSV(); }

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
	
	public Flight(String[] csv) { fromCSV(csv); }

	@Override
	public void fromCSV(String[] csvList) {
		final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm");
		
		int l = csvList.length, c = 0;
		if (l > c && !csvList[c].equals("")) id = csvList[c++];
		if (l > c && !csvList[c].equals("")) airlineName = csvList[c++];
		if (l > c && !csvList[c].equals("")) departure = airportDAO.getAirport(csvList[c++]);
		if (l > c && !csvList[c].equals("")) destination = airportDAO.getAirport(csvList[c++]);
		if (l > c && !csvList[c].equals("")) departureGateNo = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) destinationGateNo = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) departureTime = LocalDateTime.parse(csvList[c++], format);
		if (l > c && !csvList[c].equals("")) arrivalTime = LocalDateTime.parse(csvList[c], format);
	}

	@Override
	public String[] toCSV() {
		return new String[]{
    		id, 
    		airlineName, 
    		departure.code, 
    		destination.code,
    		String.valueOf(departureGateNo),
    		String.valueOf(destinationGateNo),
    		DateUtil.timeToString(departureTime), 
    		DateUtil.timeToString(arrivalTime)
		}; 
	}
	
	public String[] toTableFormattedString() {
		return new String[]{
    		id, 
    		airlineName, 
    		departure.city, 
    		destination.city, 
    		DateUtil.timeToString(departureTime), 
    		DateUtil.timeToString(arrivalTime)
		}; 
	}

	@Override
	public String getKey() { return id; }
}