package util;

import model.Airport;
import model.Flight;

import java.util.List;

public class DepartureFilter extends Filter {
    public DepartureFilter(List<Flight> flights) { super(flights); }

    protected boolean found(Flight f, String keyword) {
        Airport departure = f.getDeparture();
        return departure.toString().contains(keyword);
    }
}