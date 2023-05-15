package util;

import model.flight.Airport;
import model.flight.Flight;

import java.util.List;

public class DestinationFilter extends Filter {
    public DestinationFilter(List<Flight> flights) { super(flights); }

    protected boolean found(Flight f, String keyword) {
        Airport destination = f.getDeparture();
        return destination.toString().toLowerCase().contains(keyword.toLowerCase());
    }
}
