package view.widget.widget;

import global.DateUtil;
import model.flight.Flight;
import view.Window;
import view.widget.HeaderTypeLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FlightInfoWidget extends JPanel {
    public FlightInfoWidget(Flight flight) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(view.Window.WIDTH, Window.HEIGHT / 4));

        JPanel headPanel = new JPanel();
        headPanel.add(new HeaderTypeLabel("항공편 정보", true));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JLabel flightCodeLabel = new JLabel("항공편 코드 : " + flight.getId());
        JLabel airlineLabel = new JLabel("항공사 : " + flight.getAirlineName());

        JLabel flightDepartureLabel = new HeaderTypeLabel("Departure", false);
        JLabel departureCountryLabel = new JLabel("출발지 :");
        JLabel departureAirportLabel = new JLabel("출발 공항 :");
        JLabel departureGateLabel = new JLabel("출발 게이트 :");
        JLabel departureTimeLabel = new JLabel("출발 일시 :");

        JLabel departureCountryData = new JLabel(flight.getDeparture().getCountry());
        JLabel departureAirportData = new JLabel(flight.getDeparture().getKey());
        JLabel departureGateData = new JLabel(String.valueOf(flight.getDepartureGateNo()));
        JLabel departureTimeData = new JLabel(DateUtil.timeToString(flight.getDepartureTime()));

        JLabel flightDestinationLabel = new HeaderTypeLabel("Destination", false);
        JLabel destinationCountryLabel = new JLabel("도착지 :");
        JLabel destinationAirportLabel = new JLabel("도착 공항 :");
        JLabel destinationGateLabel = new JLabel("도착 게이트 :");
        JLabel destinationTimeLabel = new JLabel("도착 일시 :");

        JLabel destinationCountryData = new JLabel(flight.getDestination().getCountry());
        JLabel destinationAirportData = new JLabel(flight.getDestination().getKey());
        JLabel destinationGateData = new JLabel(String.valueOf(flight.getDestinationGateNo()));
        JLabel destinationTimeData = new JLabel(DateUtil.timeToString(flight.getArrivalTime()));

        c.gridx = 0; c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = .5;
        contentPanel.add(flightCodeLabel, c); c.gridy++;
        contentPanel.add(airlineLabel, c); c.gridy++;
        contentPanel.add(new JLabel(" "), c);

        c.gridx = 1; c.gridy = 0;
        c.gridwidth = 2;
        contentPanel.add(flightDepartureLabel, c); c.gridy++;
        c.gridwidth = 1;
        contentPanel.add(departureCountryLabel, c); c.gridy++;
        contentPanel.add(departureAirportLabel, c); c.gridy++;
        contentPanel.add(departureGateLabel, c); c.gridy++;
        contentPanel.add(departureTimeLabel, c);

        c.gridx = 2; c.gridy = 1;
        contentPanel.add(departureCountryData, c); c.gridy++;
        contentPanel.add(departureAirportData, c); c.gridy++;
        contentPanel.add(departureGateData, c); c.gridy++;
        contentPanel.add(departureTimeData, c);

        c.gridx = 3; c.gridy = 0;
        c.gridwidth = 2;
        contentPanel.add(flightDestinationLabel, c); c.gridy++;
        c.gridwidth = 1;
        contentPanel.add(destinationCountryLabel, c); c.gridy++;
        contentPanel.add(destinationAirportLabel, c); c.gridy++;
        contentPanel.add(destinationGateLabel, c); c.gridy++;
        contentPanel.add(destinationTimeLabel, c);

        c.gridx = 4; c.gridy = 1;
        contentPanel.add(destinationCountryData, c); c.gridy++;
        contentPanel.add(destinationAirportData, c); c.gridy++;
        contentPanel.add(destinationGateData, c); c.gridy++;
        contentPanel.add(destinationTimeData, c); c.gridy++;

        contentPanel.setBorder(new LineBorder(Color.BLACK));

        add(headPanel);
        add(contentPanel);
    }
}
