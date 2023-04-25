package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.DateUtil;
import main.Main;
import model.Flight;

public class FlightDetail extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
				
	public FlightDetail(Flight flight) {
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    JPanel headPanel = new JPanel(new BorderLayout());

	    JPanel backButtonPanel = new JPanel(new BorderLayout());
	    JButton backButton = new JButton("뒤로");
	    backButton.addActionListener(this);
	    backButtonPanel.add(backButton, BorderLayout.WEST);

	    JPanel reserveButtonPanel = new JPanel(new BorderLayout());
	    JButton reserveButton = new JButton("예약");
	    reserveButton.addActionListener(this);
	    reserveButtonPanel.add(reserveButton, BorderLayout.EAST);

	    JLabel titleLabel = new JLabel("항공편 정보", SwingConstants.CENTER);
	    titleLabel.setFont(titleLabel.getFont().deriveFont(20f));

	    headPanel.add(backButtonPanel, BorderLayout.WEST);
	    headPanel.add(titleLabel, BorderLayout.CENTER);
	    headPanel.add(reserveButtonPanel, BorderLayout.EAST);

	    JPanel contentPanel = new JPanel(new BorderLayout());
	    
	    JPanel flightPanel = new JPanel(new BorderLayout());
	    JPanel codePanel = new JPanel(new BorderLayout());
	    JPanel airlinePanel = new JPanel(new BorderLayout());
	    JPanel remainPanel = new JPanel(new BorderLayout());
	    JPanel sizedBoxW = new JPanel();
	    JPanel sizedBoxH = new JPanel();
	    
	    JLabel codeLabel1 = buildLabel("항공편 코드", true);
	    JLabel codeLabel2 = buildLabel(flight.getId(), false);
	    codePanel.add(codeLabel1, BorderLayout.NORTH);
	    codePanel.add(codeLabel2, BorderLayout.SOUTH);
	    codePanel.setPreferredSize(new Dimension(100, 50));
	    codePanel.setBackground(Color.WHITE);
	    
	    JLabel airlineLabel1 = buildLabel("항공사", true);
	    JLabel airlineLabel2 = buildLabel(flight.getAirlineName(), false);
	    airlinePanel.add(airlineLabel1, BorderLayout.NORTH);
	    airlinePanel.add(airlineLabel2, BorderLayout.SOUTH);
	    airlinePanel.setPreferredSize(new Dimension(300, 50));
	    airlinePanel.setBackground(Color.WHITE);

	    JLabel remainLabel1 = buildLabel("잔여석", true);
	    JLabel remainLabel2 = buildLabel("1", false);
	    remainPanel.add(remainLabel1, BorderLayout.NORTH);
	    remainPanel.add(remainLabel2, BorderLayout.SOUTH);
	    remainPanel.setPreferredSize(new Dimension(300, 50));
	    remainPanel.setBackground(Color.WHITE);
	    
	    JPanel flightInfoPanel = new JPanel(new GridLayout(0, 3, 20, 20));
	    
	    sizedBoxW.setPreferredSize(new Dimension(750, 0));
	    sizedBoxH.setPreferredSize(new Dimension(0, 25));

	    flightInfoPanel.add(codePanel);
	    flightInfoPanel.add(airlinePanel);
	    flightInfoPanel.add(remainPanel);
	    
	    flightPanel.add(sizedBoxH, BorderLayout.NORTH);
	    flightPanel.add(flightInfoPanel, BorderLayout.WEST);
	    flightPanel.add(sizedBoxW, BorderLayout.CENTER);
	    flightPanel.add(sizedBoxW, BorderLayout.EAST);
	    flightPanel.add(sizedBoxH, BorderLayout.SOUTH);
	    
	    JPanel depPanel = buildPanel(0, flight);
	    JPanel arrPanel = buildPanel(1, flight);
	    depPanel.setBackground(new Color(153, 220, 255));
	    arrPanel.setBackground(new Color(153, 220, 255));
	    
	    ImageIcon airplane = new ImageIcon("./asset/airplane.png");
		Image resizedImage = airplane.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
		
	    contentPanel.add(flightPanel, BorderLayout.NORTH);
	    contentPanel.add(depPanel, BorderLayout.WEST);
	    contentPanel.add(imageLabel, BorderLayout.CENTER);
	    contentPanel.add(arrPanel, BorderLayout.EAST);
	    contentPanel.setBackground(new Color(153, 220, 255));
	    
	    panel.add(headPanel, BorderLayout.NORTH);
	    panel.add(contentPanel, BorderLayout.CENTER);
	    panel.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
	    
	    add(panel, BorderLayout.CENTER);
	}
	
	private JLabel buildLabel(String str, boolean bold) {
		JLabel label = new JLabel(str);
		label.setFont(label.getFont().deriveFont(20f));
		if (bold) label.setFont(label.getFont().deriveFont(Font.BOLD));
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label;
	}
	
	private JPanel buildPanel(int type, Flight flight) {
		JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
		
		String[][] strs = { {
			"공항", flight.getDeparture().getCity(),
			"게이트", flight.getDepartureGateNo() + "번",
			"날짜", DateUtil.onlyDateToString(flight.getDepartureTime()),
			"시간", DateUtil.onlyTimeToString(flight.getDepartureTime())
		}, {
			"공항", flight.getDestination().getCity(),
			"게이트", flight.getDestinationGateNo() + "번",
			"날짜", DateUtil.onlyDateToString(flight.getArrivalTime()),
			"시간", DateUtil.onlyTimeToString(flight.getArrivalTime())
		} };
		
		JLabel headLabel = buildLabel(type == 0 ? "출발" : "도착", true);
	    
	    panel.add(headLabel);
	    panel.add(new JLabel());

	    for (String str : strs[type]) panel.add(buildLabel(str, false));
	    
	    panel.setPreferredSize(new Dimension(300, 100));
	    
	    return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("뒤로")) Main.gotoPage(new Menu());
	}
	
	
}