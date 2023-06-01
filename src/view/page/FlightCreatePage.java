package view.page;

import model.dao.FlightDAOFactory;
import util.logger.Logger;

import javax.swing.*;

public class FlightCreatePage extends FlightEditPage {

    public FlightCreatePage() {
        super();
    }
    public FlightCreatePage(JComponent left) {
        super(left);
    }
    public FlightCreatePage(JComponent left, JComponent right) {
        super(left, right);
    }
    public FlightCreatePage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected String getButtonText() { return "생성"; }

    @Override
    protected void submit() {
        FlightDetailPage.setFlight(extractFlight());
        FlightDAOFactory.getFactory().add(extractFlight());
        Logger.get().log("Flight created: " + extractFlight().getKey());
    }

    @Override
    protected String getTitle() { return "항공편 생성"; }
}
