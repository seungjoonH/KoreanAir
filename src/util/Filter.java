package util;

import java.util.ArrayList;
import java.util.List;

import model.flight.Flight;

public abstract class Filter {

	protected List<Flight> flights;

	Filter(List<Flight> flights) { this.flights = flights; }
	
	protected abstract boolean found(Flight f, String keyword); 
	public List<Flight> search(String keyword) {
		List<Flight> list = new ArrayList<Flight>();
		for (Flight f : flights) { if (found(f, keyword)) list.add(f); }
		return list;
	}
}