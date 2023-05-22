package model.dao;

import model.flight.Airplane;
import model.flight.Flight;

public class AirplaneDAO extends DAO<Airplane> {
	@Override
	protected String getFilepath() { return filepath + "airplanes.csv"; };

	@Override
	protected String getHeader() {
		return "model;first;business;economy";
	}
	
	@Override
	protected Airplane getConstructor(String[] csvList) {
		return new Airplane(csvList);
	}

	@Override
	public void visualize() { 
		System.out.println("\nLoaded Airplanes(" + getObj().size() + "):");
		super.visualize(); 
	}
}