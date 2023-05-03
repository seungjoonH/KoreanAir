package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.FlightManagementController;
import main.Main;
import model.Flight;
import model.FlightFilter;

public class Search extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTextField airlineField;
	private JTextField yearField;
	private JTextField monthField;
	private JTextField dayField;
	private JTextField fromField;
	private JTextField toField;

	public Search() {
		JPanel panel = new JPanel();

		JPanel sizedBox = new JPanel();
		sizedBox.setPreferredSize(new Dimension(10, 0));

		ImageIcon logo = new ImageIcon("./asset/logo.png");
		Image resizedImage = logo.getImage().getScaledInstance(200, 25, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));

		JPanel appbarPanel = new JPanel(new BorderLayout());
		JButton logButton = new JButton(Main.isLogged() ? "로그아웃" : "로그인");
		logButton.addActionListener(this);

		JPanel backButtonPanel = new JPanel(new BorderLayout());
		JButton backButton = new JButton("뒤로");
		backButton.addActionListener(this);
		backButtonPanel.add(backButton, BorderLayout.WEST);
		
		appbarPanel.add(backButtonPanel, BorderLayout.WEST);
		appbarPanel.add(logoLabel, BorderLayout.CENTER);
		appbarPanel.add(logButton, BorderLayout.EAST);

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

		JPanel searchBarPanel = new JPanel();
		searchBarPanel.setLayout(new BoxLayout(searchBarPanel, BoxLayout.X_AXIS));
		searchBarPanel.add(searchPanel);
		searchBarPanel.add(submitButton);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		listPanel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT - 50));

		String[] columnNames = { "코드", "항공사", "출발지", "도착지", "출발시각", "도착시각" };

		String[][] data;
		
		FlightFilter filter = FlightManagementController.getFilter();
		if (filter == null) data = FlightManagementController.getFlightsList();
		else data = FlightManagementController.getFiltFlightsList();
		
		JTable table = new JTable(data, columnNames);
		
		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent event) {
		        if (event.getClickCount() == 2) {
		            int row = table.getSelectedRow();
		            Flight flight = FlightManagementController.getFlight(data[row][0]);
		            Main.gotoPage(new FlightDetail(flight));
		        }
		    }
		});
		
		table.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(table);
		listPanel.add(scrollPane);

		panel.setLayout(new BorderLayout());
		panel.add(appbarPanel, BorderLayout.NORTH);
		panel.add(searchBarPanel, BorderLayout.CENTER);
		panel.add(listPanel, BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		add(panel);
	}
	
	public void filt() {
		FlightFilter filter = new FlightFilter();
		
		filter.setAirlineName(airlineField.getText());
		filter.setYear(yearField.getText());
		filter.setMonth(monthField.getText());
		filter.setDay(dayField.getText());
		filter.setDeparture(fromField.getText());
		filter.setArrival(toField.getText());
		
		FlightManagementController.setFilter(filter);
	}

	public void logout() { Main.setUser(null); }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("뒤로")) Main.gotoPage(new Home());
		else if (e.getActionCommand().equals("로그인")) Main.gotoPage(new Login());
		else if (e.getActionCommand().equals("로그아웃")) { logout(); Main.gotoPage(new Search()); }
		else if (e.getActionCommand().equals("검색")) { filt(); Main.gotoPage(new Search()); }
	}
}
