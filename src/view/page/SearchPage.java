package view.page;

import java.awt.*;
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

import model.dao.FlightDAOFactory;
import model.flight.Airplane;
import model.flight.Flight;
import util.*;
import view.page.route.Route;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;
import view.widget.FlightTable;

public class SearchPage extends Page {
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
	private JSpinner seatSpinner;
	private JComboBox<Airplane.SeatClass> seatCombo;

	private List<Flight> flights;

	public SearchPage() {
		super(null, null, true);
	}
	public SearchPage(JComponent left) {
		super(left, null, true);
	}
	public SearchPage(JComponent left, JComponent right) {
		super(left, right, true);
	}
	public SearchPage(JComponent left, JComponent right, boolean displayTitle) {
		super(left, right, displayTitle);
	}

	@Override
	protected void buildContent() {
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setBackground(ThemeMode.getBackgroundColor());
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
		Color fontColor = ThemeMode.getFontColor();

		JPanel airlinePanel = new JPanel();
		airlinePanel.setLayout(new BoxLayout(airlinePanel, BoxLayout.X_AXIS));

		JLabel airlineLabel = new CustomTextLabel("항공사:", fontColor);
		airlineField = new JTextField(10);

		airlinePanel.setOpaque(false);
		airlinePanel.add(airlineLabel);
		airlinePanel.add(airlineField);

		JPanel depPanel = new JPanel();
		depPanel.setLayout(new BoxLayout(depPanel, BoxLayout.X_AXIS));

		JLabel depLabel = new CustomTextLabel("출발일:", fontColor);
		depPanel.add(depLabel);

		yearField = new JTextField(4);
		JLabel yearLabel = new CustomTextLabel("년", fontColor);
		monthField = new JTextField(2);
		JLabel monthLabel = new CustomTextLabel("월", fontColor);
		dayField = new JTextField(2);
		JLabel dayLabel = new CustomTextLabel("일", fontColor);

		depPanel.setOpaque(false);
		depPanel.add(yearField);
		depPanel.add(yearLabel);
		depPanel.add(monthField);
		depPanel.add(monthLabel);
		depPanel.add(dayField);
		depPanel.add(dayLabel);

		JPanel firstPanel = new JPanel(new GridLayout(2, 1));
		firstPanel.setOpaque(false);
		firstPanel.add(airlinePanel);
		firstPanel.add(depPanel);

		JPanel fromPanel = new JPanel();
		fromPanel.setLayout(new BoxLayout(fromPanel, BoxLayout.X_AXIS));

		JLabel fromLabel = new CustomTextLabel("출발지:", fontColor);
		fromField = new JTextField(10);
		fromPanel.setOpaque(false);
		fromPanel.add(fromLabel);
		fromPanel.add(fromField);

		JPanel toPanel = new JPanel();
		toPanel.setLayout(new BoxLayout(toPanel, BoxLayout.X_AXIS));

		JLabel toLabel = new CustomTextLabel("도착지:", fontColor);
		toField = new JTextField(10);
		toPanel.setOpaque(false);
		toPanel.add(toLabel);
		toPanel.add(toField);

		JPanel secondPanel = new JPanel(new GridLayout(2, 1));
		secondPanel.setOpaque(false);
		secondPanel.add(fromPanel);
		secondPanel.add(toPanel);

		JPanel seatPanel = new JPanel();
		seatPanel.setLayout(new BoxLayout(seatPanel, BoxLayout.X_AXIS));
		JLabel passengerLabel = new CustomTextLabel("잔여석:", fontColor);

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		seatSpinner = new JSpinner(spinnerModel);
		seatPanel.setOpaque(false);
		seatPanel.add(passengerLabel);
		seatPanel.add(seatSpinner);

		JPanel classPanel = new JPanel();
		classPanel.setLayout(new BoxLayout(classPanel, BoxLayout.X_AXIS));
		JLabel classLabel = new CustomTextLabel("좌석등급:", fontColor);

		seatCombo = new JComboBox<>(Airplane.SeatClass.values());
		seatCombo.setSelectedIndex(Airplane.SeatClass.ECONOMY.ordinal());
		classPanel.setOpaque(false);
		classPanel.add(classLabel);
		classPanel.add(seatCombo);

		JPanel thirdPanel = new JPanel(new GridLayout(2, 1));
		thirdPanel.setOpaque(false);
		thirdPanel.add(seatPanel);
		thirdPanel.add(classPanel);

		JButton clearButton = new JButton("초기화");
		clearButton.addActionListener(this);
		JButton submitButton = new JButton("검색");
		submitButton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(clearButton);
		buttonPanel.add(submitButton);

		JPanel searchPanel = new JPanel(new GridLayout(1, 3, 10, 0));

		searchPanel.setOpaque(false);
		searchPanel.add(firstPanel);
		searchPanel.add(secondPanel);
		searchPanel.add(thirdPanel);

		searchBarPanel = new JPanel();
		searchBarPanel.setOpaque(false);
		searchBarPanel.setLayout(new BoxLayout(searchBarPanel, BoxLayout.X_AXIS));
		searchBarPanel.add(searchPanel);
		searchBarPanel.add(buttonPanel);
	}
	private void buildTable() {
		filterFlights();
		FlightTable table = new FlightTable(flights);

		tablePane = new JScrollPane(table);
	}

	private String getDateKeyword() {
		String year = yearField.getText();
		String month = monthField.getText();
		String day = dayField.getText();

		year = year.equals("") ? "####" : String.format("%04d", Integer.parseInt(year));
		month = month.equals("") ? "##" : String.format("%02d", Integer.parseInt(month));
		day = day.equals("") ? "##" : String.format("%02d", Integer.parseInt(day));

		return year + "-" + month + "-" + day;
	}

	private String getRemainSeatKeyword() {
		return seatSpinner.getValue() + ";" + seatCombo.getSelectedIndex();
	}

	private void filterFlights() {
		flights = FlightDAOFactory.getFactory().getList();
		flights = new AirlineFilter(flights, airlineField.getText()).search();
		flights = new DepartureFilter(flights, fromField.getText()).search();
		flights = new DestinationFilter(flights, toField.getText()).search();
		flights = new DateFilter(flights, getDateKeyword()).search();
		flights = new RemainSeatFilter(flights, getRemainSeatKeyword()).search();
	}

	public void clear() {
		buildPanel(true, false);
		Route.refresh();
	}
	public void search() {
		buildPanel(false, true);
		Route.refresh();

		int searched = flights.size();
		String msg = "총 " + searched + "개의 결과가 검색되었습니다.";
		JOptionPane.showMessageDialog(this, msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		if (c.equals("초기화")) clear();
		else if (c.equals("검색")) search();
	}
}
