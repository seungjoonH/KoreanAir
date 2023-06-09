package model.flight;

import java.time.LocalDateTime;
import java.util.List;

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
	private int firstPrice;
	private int businessPrice;
	private int economyPrice;


	public String getId() { return id; }
	public String getAirlineName() { return airlineName; }
	public Airport getDeparture() { return departure; }
	public Airport getDestination() { return destination; }
	public int getDepartureGateNo() { return departureGateNo; }
	public int getDestinationGateNo() { return destinationGateNo; }
	public LocalDateTime getDepartureTime() { return departureTime; }
	public LocalDateTime getArrivalTime() { return arrivalTime; }
	public Airplane getAirplane() { return airplane; }
	public int getPrice(Airplane.SeatClass seatClass) {
		return switch (seatClass) {
			case FIRST -> firstPrice;
			case BUSINESS -> businessPrice;
			case ECONOMY -> economyPrice;
		};
	}

	public List<Reservation> getReservations() {
		return ReservationDAOFactory.getFactory().getReservationByFlight(id);
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
		int l = csvList.length, c = 0;
		if (l > c && !csvList[c].equals("")) id = csvList[c++];
		if (l > c && !csvList[c].equals("")) airlineName = csvList[c++];
		if (l > c && !csvList[c].equals("")) departure = AirportDAOFactory.getFactory().getAirport(csvList[c++]);
		if (l > c && !csvList[c].equals("")) destination = AirportDAOFactory.getFactory().getAirport(csvList[c++]);
		if (l > c && !csvList[c].equals("")) departureGateNo = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) destinationGateNo = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) departureTime = DateUtil.stringToTime(csvList[c++]);
		if (l > c && !csvList[c].equals("")) arrivalTime = DateUtil.stringToTime(csvList[c++]);
		if (l > c && !csvList[c].equals("")) airplane = AirplaneDAOFactory.getFactory().getAirplane(csvList[c++]);
		if (l > c && !csvList[c].equals("")) firstPrice = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) businessPrice = Integer.parseInt(csvList[c++]);
		if (l > c && !csvList[c].equals("")) economyPrice = Integer.parseInt(csvList[c]);
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
			String.valueOf(airplane.getKey()),
			String.valueOf(firstPrice),
			String.valueOf(businessPrice),
			String.valueOf(economyPrice),
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