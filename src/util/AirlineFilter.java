package util;

import model.flight.Flight;

import java.util.List;

public class AirlineFilter extends Filter {
    public AirlineFilter(List<Flight> flights) { super(flights); }

    protected boolean found(Flight f, String keyword) {
        return f.getAirlineName().toLowerCase().contains(keyword.toLowerCase());
    }
}