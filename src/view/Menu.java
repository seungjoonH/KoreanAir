package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import controller.FlightManagementController;
import main.Main;
import main.Pages;

public class Menu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton logButton;

	public Menu() {
		JPanel sizedBox = new JPanel();
		sizedBox.setPreferredSize(new Dimension(10, 0));

		JPanel panel = new JPanel();

		JLabel profileLabel = new JLabel();

		if (Main.isLogged())
			profileLabel = new JLabel(Main.getUser().getName() + " 님 환영합니다");

		ImageIcon logo = new ImageIcon("./asset/logo.png");
		Image resizedImage = logo.getImage().getScaledInstance(200, 25, Image.SCALE_DEFAULT);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));

		JPanel appbarPanel = new JPanel(new BorderLayout());
		logButton = new JButton(Main.isLogged() ? "로그아웃" : "로그인");
		logButton.addActionListener(this);

		appbarPanel.add(profileLabel, BorderLayout.WEST);
		appbarPanel.add(logoLabel, BorderLayout.CENTER);

		appbarPanel.add(logButton, BorderLayout.EAST);

		JPanel fromPanel = new JPanel();
		fromPanel.setLayout(new BoxLayout(fromPanel, BoxLayout.X_AXIS));

		JLabel fromLabel = new JLabel("출발지:");
		JTextField fromField = new JTextField(10);
		fromPanel.add(fromLabel);
		fromPanel.add(fromField);

		JPanel toPanel = new JPanel();
		toPanel.setLayout(new BoxLayout(toPanel, BoxLayout.X_AXIS));

		JLabel toLabel = new JLabel("도착지:");
		JTextField toField = new JTextField(10);
		toPanel.add(toLabel);
		toPanel.add(toField);

		JPanel airportPanel = new JPanel(new GridLayout(2, 1));

		airportPanel.add(fromPanel);
		airportPanel.add(toPanel);

		JPanel depPanel = new JPanel();
		depPanel.setLayout(new BoxLayout(depPanel, BoxLayout.X_AXIS));

		JLabel depLabel = new JLabel("출발시각:");
		JTextField depField = new JTextField(10);
		depPanel.add(depLabel);
		depPanel.add(depField);

		JPanel arrPanel = new JPanel();
		arrPanel.setLayout(new BoxLayout(arrPanel, BoxLayout.X_AXIS));

		JLabel arrLabel = new JLabel("도착시각:");
		JTextField arrField = new JTextField(10);
		arrPanel.add(arrLabel);
		arrPanel.add(arrField);

		JPanel timePanel = new JPanel(new GridLayout(2, 1));

		timePanel.add(depPanel);
		timePanel.add(arrPanel);

		JPanel passengerPanel = new JPanel();
		passengerPanel.setLayout(new BoxLayout(passengerPanel, BoxLayout.X_AXIS));
		JLabel passengerLabel = new JLabel("예약수:");

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

		JPanel pcPanel = new JPanel(new GridLayout(2, 1));
		pcPanel.add(passengerPanel);
		pcPanel.add(classPanel);

//        JPanel buttonPanel = new JPanel();
		JButton submitButton = new JButton("검색");
//        buttonPanel.add(submitButton);

		JPanel searchPanel = new JPanel(new GridLayout(1, 3, 10, 0));

		searchPanel.add(airportPanel);
		searchPanel.add(timePanel);
		searchPanel.add(pcPanel);

		JPanel searchBarPanel = new JPanel();
		searchBarPanel.setLayout(new BoxLayout(searchBarPanel, BoxLayout.X_AXIS));
		searchBarPanel.add(searchPanel);
		searchBarPanel.add(submitButton);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		listPanel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT - 50));

		String[] columnNames = { "코드", "항공사", "출발지", "도착지", "출발시각", "도착시각" };

		String[][] data = FlightManagementController.getFlightsList();
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		listPanel.add(scrollPane);

		panel.setLayout(new BorderLayout());
		panel.add(appbarPanel, BorderLayout.NORTH);
		panel.add(searchBarPanel, BorderLayout.CENTER);
		panel.add(listPanel, BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		Main.getFrame().add(panel);
		Main.getFrame().pack();
		Main.getFrame().setResizable(false);
		Main.getFrame().setVisible(true);
		Main.getFrame().setLocationRelativeTo(null);
		Main.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void logout() {
		Main.setUser(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("로그인"))
			Main.changeLevel(Pages.LOGIN);
		else if (e.getActionCommand().equals("로그아웃")) {
			logout();
			Main.changeLevel(Pages.MENU);
		}
	}
}
