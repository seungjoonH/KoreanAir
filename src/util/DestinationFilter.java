package util;

import model.Airport;
import model.Flight;

import java.util.List;

public class DestinationFilter extends Filter {
    public DestinationFilter(List<Flight> flights) { super(flights); }

    protected boolean found(Flight f, String keyword) {
        Airport destination = f.getDeparture();
        return destination.toString().contains(keyword);
    }
}
