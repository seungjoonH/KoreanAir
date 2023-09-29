package view.page;

import model.dao.FlightDAOFactory;
import model.flight.Airplane;
import model.flight.Flight;
import util.logger.Logger;

import javax.swing.*;

public class FlightModifyPage extends FlightEditPage {
    private static Flight flight;

    public static void setFlight(Flight f) { flight = f; }

    public FlightModifyPage() {
        super();
    }
    public FlightModifyPage(JComponent left) {
        super(left);
    }
    public FlightModifyPage(JComponent left, JComponent right) {
        super(left, right);
    }
    public FlightModifyPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected void setInit() {
        super.setInit();
        form.setTexts(
            flight.getId(),
            flight.getAirlineName(),
            flight.getDeparture().getKey(),
            String.valueOf(flight.getDepartureGateNo()),
            flight.getDepartureTime(),
            flight.getDestination().getKey(),
            String.valueOf(flight.getDestinationGateNo()),
            flight.getArrivalTime(),
            flight.getAirplane().getKey(),
            flight.getPrice(Airplane.SeatClass.FIRST),
            flight.getPrice(Airplane.SeatClass.BUSINESS),
            flight.getPrice(Airplane.SeatClass.ECONOMY)
        );
    }

    @Override
    protected String getButtonText() { return "수정"; }

    @Override
    protected void submit() {
        FlightDetailPage.setFlight(extractFlight());
        FlightDAOFactory.getFactory().update(extractFlight());
        Logger.get().log("Flight modified: " + extractFlight().getKey());
    }

    @Override
    protected String getTitle() { return "항공편 수정"; }
}
