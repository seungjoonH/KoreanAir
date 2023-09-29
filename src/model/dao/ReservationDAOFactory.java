package model.dao;

import model.reservation.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAOFactory extends DAOFactory<Reservation> {
    private static final ReservationDAOFactory factory = new ReservationDAOFactory();
    private ReservationDAOFactory() {}
    public static ReservationDAOFactory getFactory() { return factory; }

    protected ReservationDAO create() { return new ReservationDAO(); }

    public Reservation getReservationById(String id) {
        for (Reservation re : getList()) { if (re.isSameId(id)) return re; }
        return null;
    }

    public List<Reservation> getReservationByFlight(String flight) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation re : getList()) { if (re.isSameFlight(flight)) reservations.add(re); }
        return reservations;
    }

    public List<Reservation> getReservationByUid(String uid) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation re : getList()) { if (re.isSameUid(uid)) reservations.add(re); }
        return reservations;
    }
}
