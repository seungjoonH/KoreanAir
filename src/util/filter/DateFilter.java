package util.filter;

import model.flight.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class DateFilter extends Filter {
    public DateFilter(List<Flight> flights, String keyword) { super(flights, keyword); }

    protected boolean found(Flight f) {
        LocalDateTime date = f.getDepartureTime();
        String year = keyword.substring(0, 4);
        String month = keyword.substring(5, 7);
        String day = keyword.substring(8, 10);

        boolean ret = true;

        ret &= year.equals("####") || Integer.parseInt(year) == date.getYear();
        ret &= month.equals("##") || Integer.parseInt(month) == date.getMonthValue();
        ret &= day.equals("##") || Integer.parseInt(day) == date.getDayOfMonth();
        return ret;
    }
}