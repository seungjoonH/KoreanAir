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




public class MyReservation extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public MyReservation(Flight flight) {
		
		// 실제로는 MyReservation(Flight flight, Customer customer)로 받아와야할 것 같습니다.
		//그냥 레이아웃을 위해 하드코딩
		
		// 처음부터 다 레이아웃을 만들려고 했으나 이해 부족으로 잘 못만들게되었어서 사실상 재활용했습니다...죄송합니다ㅠㅠ
		
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    JPanel sizedBoxW = new JPanel();
	    JPanel sizedBoxH = new JPanel();
	    sizedBoxW.setPreferredSize(new Dimension(750, 0));
	    sizedBoxH.setPreferredSize(new Dimension(0, 50));
	    
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
	    JPanel reservedNumPanel = new JPanel(new BorderLayout());
	    JPanel namePanel = new JPanel(new BorderLayout());
	    JPanel reservedOKPanel = new JPanel(new BorderLayout());
	    JPanel ticketNumPanel = new JPanel(new BorderLayout());
	    
	    JLabel codeLabel1 = buildLabel("항공편 코드", true);
	    JLabel codeLabel2 = buildLabel(flight.getId(), false);
	    codePanel.add(codeLabel1, BorderLayout.NORTH);
	    codePanel.add(codeLabel2, BorderLayout.SOUTH);
	    codePanel.setPreferredSize(new Dimension(150, 70));
	    codePanel.setBackground(Color.WHITE);
	    
	    JLabel airlineLabel1 = buildLabel("항공사", true);
	    JLabel airlineLabel2 = buildLabel(flight.getAirlineName(), false);
	    airlinePanel.add(airlineLabel1, BorderLayout.NORTH);
	    airlinePanel.add(airlineLabel2, BorderLayout.SOUTH);
	    airlinePanel.setPreferredSize(new Dimension(150, 70));
	    airlinePanel.setBackground(Color.WHITE);

	    JLabel ticketNumLabel1 = buildLabel("예약한 개수", true);
	    JLabel ticketNumLabel2 = buildLabel("1", false);
	    ticketNumPanel.add(ticketNumLabel1, BorderLayout.NORTH);
	    ticketNumPanel.add(ticketNumLabel2, BorderLayout.SOUTH);
	    ticketNumPanel.setPreferredSize(new Dimension(150, 70));
	    ticketNumPanel.setBackground(Color.WHITE);
	    
	    JLabel reservedNumLabel1 = buildLabel("예약번호", true);
	    JLabel reservedNumLabel2 = buildLabel("12345678", false);
	    reservedNumPanel.add(reservedNumLabel1, BorderLayout.NORTH);
	    reservedNumPanel.add(reservedNumLabel2, BorderLayout.SOUTH);
	    reservedNumPanel.setPreferredSize(new Dimension(150, 70));
	    reservedNumPanel.setBackground(Color.WHITE);
	    
	    JLabel reservedOKLabel1 = buildLabel("예약 상태", true);
	    JLabel reservedOKLabel2 = buildLabel("예약 완료", false);
	    reservedOKPanel.add(reservedOKLabel1, BorderLayout.NORTH);
	    reservedOKPanel.add(reservedOKLabel2, BorderLayout.SOUTH);
	    reservedOKPanel.setPreferredSize(new Dimension(150, 70));
	    reservedOKPanel.setBackground(Color.WHITE);
	    
	    JLabel nameLabel1 = buildLabel("예약자명", true);
	    JLabel nameLabel2 = buildLabel("홍길동", false);
	    namePanel.add(nameLabel1, BorderLayout.NORTH);
	    namePanel.add(nameLabel2, BorderLayout.SOUTH);
	    namePanel.setPreferredSize(new Dimension(150, 70));
	    namePanel.setBackground(Color.WHITE);
	    
	    
	    JPanel flightInfoPanel = new JPanel(new GridLayout(0, 6, 20, 20));
	    
	    
	    flightInfoPanel.add(namePanel);
	    flightInfoPanel.add(codePanel);
	    flightInfoPanel.add(airlinePanel);
	    flightInfoPanel.add(reservedNumPanel);
	    flightInfoPanel.add(reservedOKPanel);
	    flightInfoPanel.add(ticketNumPanel);
	    
	    flightPanel.add(sizedBoxH, BorderLayout.NORTH);
	    flightPanel.add(flightInfoPanel, BorderLayout.WEST);
	    flightPanel.add(sizedBoxW, BorderLayout.CENTER);
	    flightPanel.add(sizedBoxW, BorderLayout.EAST);
	    
	    JPanel depPanel = buildPanel(0, flight);
	    JPanel arrPanel = buildPanel(1, flight);
//	    depPanel.setBackground(new Color(153, 220, 255));
//	    arrPanel.setBackground(new Color(153, 220, 255));

	    ImageIcon airplane = new ImageIcon("./asset/airplane.png");
		Image resizedImage = airplane.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
		
	    contentPanel.add(flightPanel, BorderLayout.NORTH);
	    contentPanel.add(depPanel, BorderLayout.WEST);
	    contentPanel.add(imageLabel, BorderLayout.CENTER);
	    contentPanel.add(arrPanel, BorderLayout.EAST);
//	    contentPanel.setBackground(new Color(153, 220, 255));
	    
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
	    
	    panel.setPreferredSize(new Dimension(200, 100));
	    
	    return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("뒤로")) Main.gotoPage(new Search());
	}
	
	
}
