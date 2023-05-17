package util;

import model.flight.Flight;

import java.util.List;

public class AirlineFilter extends Filter {
    public AirlineFilter(List<Flight> flights, String keyword) { super(flights, keyword); }

    protected boolean found(Flight f) {
        return f.getAirlineName().toLowerCase().contains(keyword.toLowerCase());
    }
}