package model.dao;

import model.flight.Airplane;

public class AirplaneDAOFactory extends DAOFactory<Airplane> {
    private static final AirplaneDAOFactory factory = new AirplaneDAOFactory();
    private AirplaneDAOFactory() {}
    public static AirplaneDAOFactory getFactory() { return factory; }

    protected AirplaneDAO create() { return new AirplaneDAO(); }

    public Airplane getAirplane(String model) {
        for (Airplane ap : getList()) { if (ap.isSameModel(model)) return ap; }
        return null;
    }
}
