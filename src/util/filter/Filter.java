package util.filter;

import java.util.ArrayList;
import java.util.List;

import model.flight.Flight;

public abstract class Filter {

	protected List<Flight> flights;
	protected String keyword;

	Filter(List<Flight> flights, String keyword) {
		this.flights = flights;
		this.keyword = keyword;
	}
	
	protected abstract boolean found(Flight f);
	public List<Flight> search() {
		List<Flight> list = new ArrayList<Flight>();
		for (Flight f : flights) { if (found(f)) list.add(f); }
		return list;
	}
}