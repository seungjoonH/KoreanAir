package util.filter;

import model.flight.Airplane;
import model.flight.Flight;

import java.util.List;

public class RemainSeatFilter extends Filter {
    public RemainSeatFilter(List<Flight> flights, String keyword) { super(flights, keyword); }

    protected boolean found(Flight f) {
        String[] keywords = keyword.split(";");
        int reserve = Integer.parseInt(keywords[0]);
        int seatClassIndex = Integer.parseInt(keywords[1]);
        int remain = f.getRemainSeat(Airplane.SeatClass.values()[seatClassIndex]);
        return remain >= reserve;
    }
}
