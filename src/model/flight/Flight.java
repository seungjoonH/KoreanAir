package model.flight;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import global.DateUtil;
import model.CSVModel;
import model.dao.*;
import model.reservation.Reservation;

public class Flight implements CSVModel {
	private String id;
	private String airlineName;
	private Airport departure;
	private Airport destination;
	private int departureGateNo;
	private int destinationGateNo;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private Airplane airplane;


	public String getId() { return id; }
	public String getAirlineName() { return airlineName; }
	public Airport getDeparture() { return departure; }
	public Airport getDestination() { return destination; }
	public int getDepartureGateNo() { return departureGateNo; }
	public int getDestinationGateNo() { return destinationGateNo; }
	public LocalDateTime getDepartureTime() { return departureTime; }
	public LocalDateTime getArrivalTime() { return arrivalTime; }

	public List<Reservation> getReservations() {
		return ReservationFactory.getFactory().getReservationByFlight(id);
	}
	public int getRemainSeat(Airplane.SeatClass seatClass) {
		int seat = airplane.getSeat(seatClass);
		for (Reservation re : getReservations()) seat -= re.getSeat(seatClass);
		return seat;
	}

	public int getAllRemainSeat() {
		return (
			getRemainSeat(Airplane.SeatClass.FIRST)
			+ getRemainSeat(Airplane.SeatClass.BUSINESS)
			+ getRemainSeat(Airplane.SeatClass.ECONOMY)
		);
	}
	
	public Flight(String[] csv) { fromCSV(csv); }

	@Override
	public void fromCSV(String[] csvList) {
		final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm");
		
		int l = csvList.length, c = 0;
		if (l > c && !csvList[c].equals("")) id = csvList[c++];
		if (l > c && !csvList[c].equals("")) airlineName = csvList[c++];
		if (l > c && !csvList[c].equals("")) departure = AirportDAOFactory.getFactory().getAirport(csvList[c++]);
		if (l > c && !csvList[c].equals("")) destination = AirportDAOFactory.getFactory().getAirport(csvList[c++]);
		if (l > c && !csvList[c].equals("")) departureGateNo = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) destinationGateNo = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) departureTime = LocalDateTime.parse(csvList[c++], format);
		if (l > c && !csvList[c].equals("")) arrivalTime = LocalDateTime.parse(csvList[c++], format);
		if (l > c && !csvList[c].equals("")) airplane = AirplaneDAOFactory.getFactory().getAirplane(csvList[c]);
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
    		DateUtil.timeToString(arrivalTime),
			airplane.model,
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