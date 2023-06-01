package util.filter;

import model.flight.Airport;
import model.flight.Flight;

import java.util.List;

public class DepartureFilter extends Filter {
    public DepartureFilter(List<Flight> flights, String keyword) { super(flights, keyword); }

    protected boolean found(Flight f) {
        Airport departure = f.getDeparture();
        String depString = "";
        if (departure != null) depString = departure.toString();
        return depString.toLowerCase().contains(keyword.toLowerCase());
    }
}