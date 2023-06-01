package util.filter;

import model.flight.Airport;
import model.flight.Flight;

import java.util.List;

public class DestinationFilter extends Filter {
    public DestinationFilter(List<Flight> flights, String keyword) { super(flights, keyword); }

    protected boolean found(Flight f) {
        Airport destination = f.getDestination();
        String desString = "";
        if (destination != null) desString = destination.toString();
        return desString.toLowerCase().contains(keyword.toLowerCase());
    }
}
