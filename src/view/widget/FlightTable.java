package view.widget;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;
import java.util.List;

import javax.swing.JTable;

import main.Main;
import model.flight.Flight;
import view.page.FlightDetailPage;
import view.page.Page;

public class FlightTable extends JTable implements MouseListener {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final String[] columns = { "코드", "항공사", "출발지", "도착지", "출발시각", "도착시각" };

	private final List<Flight> flights;

	public FlightTable(List<Flight> flights) {
		super(castData(flights), columns);
		this.flights = flights;
		setDefaultEditor(Object.class, null);
		addMouseListener(this);
	}

	private static String[][] castData(List<Flight> flights) {
		String[][] data = new String[flights.size()][];
		for (int i = 0; i < flights.size(); i++) {
			data[i] = flights.get(i).toTableFormattedString();
		}
		return data;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			Main m = Main.getMain();
			Page p = m.getCurrentPage();
			FlightDetailPage.setFlight(flights.get(getSelectedRow()));
			m.gotoPage(new FlightDetailPage(new BackButton(p), null, true));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}