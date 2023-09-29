package view.page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.*;

import global.DateUtil;
import model.dao.FlightDAOFactory;
import model.dao.ReservationDAOFactory;
import model.flight.Airplane;
import model.flight.Flight;
import view.page.route.Route;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;

public class FlightDetailPage extends Page {
        
	private static Flight flight;
    public static void setFlight(Flight f) { flight = f; }

    public static boolean delete() {
        boolean reserved = ReservationDAOFactory.getFactory().getReservationByFlight(flight.getKey()).size() > 0;
        if (reserved) return false;
        FlightDAOFactory.getFactory().delete(flight);
        Route.goBack();
        Route.getThisPage().refresh();
        return true;
    }

    public FlightDetailPage(JComponent left) {
        super(left, null, true);
    }
    public FlightDetailPage(JComponent left, JComponent right) {
        super(left, right, true);
    }
    public FlightDetailPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected String getTitle() { return "항공편 상세보기"; }

    @Override
    protected void buildContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ThemeMode.getBackgroundColor());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel sizedBoxW = new JPanel();
        sizedBoxW.setPreferredSize(new Dimension(750, 0));
        sizedBoxW.setOpaque(false);

        JPanel flightPanel = new JPanel(new BorderLayout());
        JPanel codePanel = new JPanel(new BorderLayout());
        JPanel airlinePanel = new JPanel(new BorderLayout());
        JPanel firstPanel = new JPanel(new BorderLayout());
        JPanel businessPanel = new JPanel(new BorderLayout());
        JPanel economyPanel = new JPanel(new BorderLayout());
        JPanel soldOutPanel = new JPanel(new BorderLayout());

        JLabel codeLabel1 = new CustomTextLabel("항공편 코드", 20, Font.BOLD);
        JLabel codeLabel2 = new CustomTextLabel(flight.getId(), 20, Font.BOLD);
        codePanel.add(codeLabel1, BorderLayout.NORTH);
        codePanel.add(codeLabel2, BorderLayout.SOUTH);
        codePanel.setPreferredSize(new Dimension(170, 50));
        codePanel.setBackground(Color.WHITE);

        JLabel airlineLabel1 = new CustomTextLabel("항공사", 20, Font.BOLD);
        JLabel airlineLabel2 = new CustomTextLabel(flight.getAirlineName(), 20, Font.BOLD);
        airlinePanel.add(airlineLabel1, BorderLayout.NORTH);
        airlinePanel.add(airlineLabel2, BorderLayout.SOUTH);
        airlinePanel.setPreferredSize(new Dimension(170, 50));
        airlinePanel.setBackground(Color.WHITE);

        int first = flight.getRemainSeat(Airplane.SeatClass.FIRST);
        int business = flight.getRemainSeat(Airplane.SeatClass.BUSINESS);
        int economy = flight.getRemainSeat(Airplane.SeatClass.ECONOMY);

        JLabel firstLabel1 = new CustomTextLabel("잔여석 (퍼스트)", 20, Font.BOLD);
        JLabel firstLabel2 = new CustomTextLabel(String.valueOf(first), 20, Font.BOLD);
        firstPanel.add(firstLabel1, BorderLayout.NORTH);
        firstPanel.add(firstLabel2, BorderLayout.SOUTH);
        firstPanel.setPreferredSize(new Dimension(170, 50));
        firstPanel.setBackground(Color.WHITE);

        JLabel businessLabel1 = new CustomTextLabel("잔여석 (비지니스)", 20, Font.BOLD);
        JLabel businessLabel2 = new CustomTextLabel(String.valueOf(business), 20, Font.BOLD);
        businessPanel.add(businessLabel1, BorderLayout.NORTH);
        businessPanel.add(businessLabel2, BorderLayout.SOUTH);
        businessPanel.setPreferredSize(new Dimension(170, 50));
        businessPanel.setBackground(Color.WHITE);

        JLabel economyLabel1 = new CustomTextLabel("잔여석 (이코노미)", 20, Font.BOLD);
        JLabel economyLabel2 = new CustomTextLabel(String.valueOf(economy), 20, Font.BOLD);
        economyPanel.add(economyLabel1, BorderLayout.NORTH);
        economyPanel.add(economyLabel2, BorderLayout.SOUTH);
        economyPanel.setPreferredSize(new Dimension(170, 50));
        economyPanel.setBackground(Color.WHITE);

        JLabel soldOutLabel = new CustomTextLabel("매진", 20, Font.BOLD);
        soldOutPanel.add(soldOutLabel, BorderLayout.CENTER);
        soldOutPanel.setPreferredSize(new Dimension(170, 50));
        soldOutPanel.setBackground(new Color(255, 115, 115));

        JPanel flightInfoPanel = new JPanel(new GridLayout(0, 5, 20, 20));

        flightInfoPanel.setOpaque(false);
        flightInfoPanel.add(codePanel);
        flightInfoPanel.add(airlinePanel);
        if (first > 0) flightInfoPanel.add(firstPanel);
        if (business > 0) flightInfoPanel.add(businessPanel);
        if (economy > 0) flightInfoPanel.add(economyPanel);
        if (first + business + economy == 0) flightInfoPanel.add(soldOutPanel);

        flightPanel.setOpaque(false);
        flightPanel.add(flightInfoPanel, BorderLayout.WEST);
        flightPanel.add(sizedBoxW, BorderLayout.CENTER);
        flightPanel.add(sizedBoxW, BorderLayout.EAST);

        JPanel depPanel = buildPanel(0, flight);
        JPanel arrPanel = buildPanel(1, flight);

        ImageIcon airplane = new ImageIcon("./asset/airplane.png");
        Image resizedImage = airplane.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));

        panel.add(flightPanel, BorderLayout.NORTH);
        panel.add(depPanel, BorderLayout.WEST);
        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(arrPanel, BorderLayout.EAST);

        add(panel, BorderLayout.CENTER);
    }

    private JPanel buildPanel(int type, Flight flight) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        Color fontColor = ThemeMode.getFontColor();

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

        JLabel headLabel = new CustomTextLabel(type == 0 ? "출발" : "도착", 20, fontColor, Font.BOLD);

        panel.add(headLabel);
        panel.add(new JLabel());

        for (String str : strs[type]) panel.add(new CustomTextLabel(str, 20, fontColor));

        panel.setPreferredSize(new Dimension(300, 100));
        panel.setOpaque(false);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
