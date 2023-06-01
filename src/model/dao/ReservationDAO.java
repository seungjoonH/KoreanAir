package model.dao;

import model.reservation.Reservation;

public class ReservationDAO extends DAO<Reservation> {
	@Override
	protected String getFilepath() { return filepath + "reservations.csv"; };

	@Override
	protected String getHeader() {
		return "id;uid;flight;first;business;economy;passengerInfos";
	}
	
	@Override
	protected Reservation getConstructor(String[] csvList) {
		return new Reservation(csvList);
	}

	@Override
	public void visualize() { 
		System.out.println("\nLoaded Reservations(" + getObj().size() + "):");
		super.visualize(); 
	}
}