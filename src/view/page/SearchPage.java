package view.page;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.flight.Flight;
import model.dao.FlightDAO;
import util.AirlineFilter;
import util.DateFilter;
import util.DepartureFilter;
import util.DestinationFilter;
import view.listener.Route;
import view.widget.FlightTable;

public class SearchPage extends Page implements ActionListener {
	@Serial
	private static final long serialVersionUID = 1L;

	@Override
	protected String getTitle() { return "검색"; }

	private JPanel panel;
	private JPanel searchBarPanel;
	private JScrollPane tablePane;

	private JTextField airlineField;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;
	private JTextField fromField;
	private JTextField toField;

	private List<Flight> flights;

	public SearchPage(JComponent left, JComponent right, boolean displayTitle) {
		super(left, right, displayTitle);
	}

	@Override
	public void build() {
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		buildPanel(true, true);
	}

	private void buildPanel(boolean buildSearch, boolean buildTable) {
		panel.removeAll();

		if (buildSearch) buildSearchBar();
		if (buildTable) buildTable();
		panel.add(searchBarPanel, BorderLayout.NORTH);
		panel.add(tablePane, BorderLayout.CENTER);
		add(panel);
	}

	private void buildSearchBar() {
		JPanel airlinePanel = new JPanel();
		airlinePanel.setLayout(new BoxLayout(airlinePanel, BoxLayout.X_AXIS));

		JLabel airlineLabel = new JLabel("항공사:");
		airlineField = new JTextField(10);
		airlinePanel.add(airlineLabel);
		airlinePanel.add(airlineField);

		JPanel depPanel = new JPanel();
		depPanel.setLayout(new BoxLayout(depPanel, BoxLayout.X_AXIS));

		JLabel depLabel = new JLabel("출발일:");
		depPanel.add(depLabel);

		yearField = new JTextField(4);
		JLabel yearLabel = new JLabel("년");
		monthField = new JTextField(2);
		JLabel monthLabel = new JLabel("월");
		dayField = new JTextField(2);
		JLabel dayLabel = new JLabel("일");

		depPanel.add(yearField);
		depPanel.add(yearLabel);
		depPanel.add(monthField);
		depPanel.add(monthLabel);
		depPanel.add(dayField);
		depPanel.add(dayLabel);

		JPanel firstPanel = new JPanel(new GridLayout(2, 1));
		firstPanel.add(airlinePanel);
		firstPanel.add(depPanel);

		JPanel fromPanel = new JPanel();
		fromPanel.setLayout(new BoxLayout(fromPanel, BoxLayout.X_AXIS));

		JLabel fromLabel = new JLabel("출발지:");
		fromField = new JTextField(10);
		fromPanel.add(fromLabel);
		fromPanel.add(fromField);

		JPanel toPanel = new JPanel();
		toPanel.setLayout(new BoxLayout(toPanel, BoxLayout.X_AXIS));

		JLabel toLabel = new JLabel("도착지:");
		toField = new JTextField(10);
		toPanel.add(toLabel);
		toPanel.add(toField);

		JPanel secondPanel = new JPanel(new GridLayout(2, 1));
		secondPanel.add(fromPanel);
		secondPanel.add(toPanel);

		JPanel passengerPanel = new JPanel();
		passengerPanel.setLayout(new BoxLayout(passengerPanel, BoxLayout.X_AXIS));
		JLabel passengerLabel = new JLabel("잔여석:");

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		passengerPanel.add(passengerLabel);
		passengerPanel.add(spinner);

		JPanel classPanel = new JPanel();
		classPanel.setLayout(new BoxLayout(classPanel, BoxLayout.X_AXIS));
		JLabel classLabel = new JLabel("좌석등급:");

		String[] seatClasses = { "이코노미", "비지니스", "퍼스트" };
		DefaultComboBoxModel<String> seatModel = new DefaultComboBoxModel<>(seatClasses);
		JComboBox<String> seatCombo = new JComboBox<>(seatModel);
		classPanel.add(classLabel);
		classPanel.add(seatCombo);

		JPanel thirdPanel = new JPanel(new GridLayout(2, 1));
		thirdPanel.add(passengerPanel);
		thirdPanel.add(classPanel);

		JButton submitButton = new JButton("검색");
		submitButton.addActionListener(this);

		JPanel searchPanel = new JPanel(new GridLayout(1, 3, 10, 0));

		searchPanel.add(firstPanel);
		searchPanel.add(secondPanel);
		searchPanel.add(thirdPanel);

		searchBarPanel = new JPanel();
		searchBarPanel.setLayout(new BoxLayout(searchBarPanel, BoxLayout.X_AXIS));
		searchBarPanel.add(searchPanel);
		searchBarPanel.add(submitButton);
	}
	private void buildTable() {
		filterFlights();
		FlightTable table = new FlightTable(flights);

		tablePane = new JScrollPane(table);
	}

	private void filterFlights() {
		String year = yearField.getText();
		String month = monthField.getText();
		String day = dayField.getText();
		if (year.equals("")) year = "####";
		if (month.equals("")) month = "##";
		if (day.equals("")) day = "##";

		String date = year + "-" + month + "-" + day;


		flights = FlightDAO.getDAO().getObj();
		flights = new AirlineFilter(flights).search(airlineField.getText());
		flights = new DepartureFilter(flights).search(fromField.getText());
		flights = new DestinationFilter(flights).search(toField.getText());
		flights = new DateFilter(flights).search(date);
	}

	public void search() {
		Route r = Route.getRoute();
		buildPanel(false, true);
		r.refresh();

		int searched = flights.size();
		String msg = "총 " + searched + "개의 결과가 검색되었습니다.";
		JOptionPane.showMessageDialog(this, msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		if (c.equals("검색")) search();
	}
}
