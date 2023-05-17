package model.dao;

import model.flight.Flight;

public class FlightDAOFactory extends DAOFactory<Flight> {
    private static final FlightDAOFactory factory = new FlightDAOFactory();
    private FlightDAOFactory() {}
    public static FlightDAOFactory getFactory() { return factory; }

    protected FlightDAO create() { return new FlightDAO(); }
}
