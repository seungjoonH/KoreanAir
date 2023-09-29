package model.dao;

import model.flight.Airport;

public class AirportDAOFactory extends DAOFactory<Airport> {
    private static final AirportDAOFactory factory = new AirportDAOFactory();
    private AirportDAOFactory() {}
    public static AirportDAOFactory getFactory() { return factory; }

    protected AirportDAO create() { return new AirportDAO(); }

    public Airport getAirport(String code) {
        for (Airport ap : getList()) { if (ap.isSameCode(code)) return ap; }
        return null;
    }
}
