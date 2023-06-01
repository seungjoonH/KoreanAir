package view.widget.widget;

import global.DateUtil;
import model.flight.Flight;
import view.Window;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FlightInfoWidget extends JPanel {
    public FlightInfoWidget(Flight flight) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(view.Window.WIDTH - 40, Window.HEIGHT / 4));
        panel.setBackground(ThemeMode.getBackgroundColor());

        Color fontColor = ThemeMode.getFontColor();

        JPanel headPanel = new JPanel();
        JLabel headerTypeLabel = new CustomTextLabel("항공편 정보", 20, fontColor, Font.BOLD);
        headerTypeLabel.setPreferredSize(new Dimension(Window.WIDTH - 40, 30));
        headPanel.setOpaque(false);
        headPanel.add(headerTypeLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();

        JLabel flightCodeLabel = new CustomTextLabel("항공편 코드 : " + flight.getId(), fontColor);
        JLabel airlineLabel = new CustomTextLabel("항공사 : " + flight.getAirlineName(), fontColor);

        JLabel flightDepartureLabel = new CustomTextLabel("Departure", 20, fontColor, Font.BOLD);
        JLabel departureCountryLabel = new CustomTextLabel("출발지 :", fontColor);
        JLabel departureAirportLabel = new CustomTextLabel("출발 공항 :", fontColor);
        JLabel departureGateLabel = new CustomTextLabel("출발 게이트 :", fontColor);
        JLabel departureTimeLabel = new CustomTextLabel("출발 일시 :", fontColor);

        JLabel departureCountryData = new CustomTextLabel(flight.getDeparture().getCountry(), fontColor);
        JLabel departureAirportData = new CustomTextLabel(flight.getDeparture().getKey(), fontColor);
        JLabel departureGateData = new CustomTextLabel(String.valueOf(flight.getDepartureGateNo()), fontColor);
        JLabel departureTimeData = new CustomTextLabel(DateUtil.timeToString(flight.getDepartureTime()), fontColor);

        JLabel flightDestinationLabel = new CustomTextLabel("Destination", 20, fontColor, Font.BOLD);
        JLabel destinationCountryLabel = new CustomTextLabel("도착지 :", fontColor);
        JLabel destinationAirportLabel = new CustomTextLabel("도착 공항 :", fontColor);
        JLabel destinationGateLabel = new CustomTextLabel("도착 게이트 :", fontColor);
        JLabel destinationTimeLabel = new CustomTextLabel("도착 일시 :", fontColor);

        JLabel destinationCountryData = new CustomTextLabel(flight.getDestination().getCountry(), fontColor);
        JLabel destinationAirportData = new CustomTextLabel(flight.getDestination().getKey(), fontColor);
        JLabel destinationGateData = new CustomTextLabel(String.valueOf(flight.getDestinationGateNo()), fontColor);
        JLabel destinationTimeData = new CustomTextLabel(DateUtil.timeToString(flight.getArrivalTime()), fontColor);

        contentPanel.setOpaque(false);

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

        contentPanel.setBorder(new LineBorder(ThemeMode.getFontColor()));

        panel.add(headPanel);
        panel.add(contentPanel);

        add(panel);
    }
}
