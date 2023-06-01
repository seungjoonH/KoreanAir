package view.widget.form;

import global.DateUtil;
import model.user.Customer;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightInputForm extends JPanel {
    private JTextField idField = new JTextField(20);
    private JTextField airlineField = new JTextField(20);
    private JTextField depAirpField = new JTextField(20);
    private JTextField depGateNoField = new JTextField(20);
    private JTextField depDateField = new JTextField(20);
    private JTextField depTimeField = new JTextField(20);
    private JTextField desAirpField = new JTextField(20);
    private JTextField desGateNoField = new JTextField(20);
    private JTextField arrDateField = new JTextField(20);
    private JTextField arrTimeField = new JTextField(20);
    private JTextField airplaneField = new JTextField(20);

    public FlightInputForm() {
         buildUserInfoPanel();
    }

    public void setTexts(
        String id,
        String airline,
        String depAirp,
        String depGateNo,
        LocalDateTime depTime,
        String desAirp,
        String desGateNo,
        LocalDateTime arrTime,
        String airplane
    ) {
        idField.setText(id);
        airlineField.setText(airline);
        depAirpField.setText(depAirp);
        depGateNoField.setText(depGateNo);
        depDateField.setText(DateUtil.onlyDateToString(depTime));
        depTimeField.setText(DateUtil.onlyTimeToString(depTime));
        desAirpField.setText(desAirp);
        desGateNoField.setText(desGateNo);
        arrDateField.setText(DateUtil.onlyDateToString(arrTime));
        arrTimeField.setText(DateUtil.onlyTimeToString(arrTime));
        airplaneField.setText(airplane);
    }

    public List<String> getTexts() {
        List<String> list = new ArrayList<>();

        String depStr = depDateField.getText() + " " + depTimeField.getText();
        LocalDateTime departureTime = DateUtil.stringToTime(depStr);
        String arrStr = arrDateField.getText() + " " + arrTimeField.getText();
        LocalDateTime arrivalTime = DateUtil.stringToTime(arrStr);

        list.add(idField.getText());
        list.add(airlineField.getText());
        list.add(depAirpField.getText());
        list.add(desAirpField.getText());
        list.add(depGateNoField.getText());
        list.add(desGateNoField.getText());
        list.add(DateUtil.timeToString(departureTime));
        list.add(DateUtil.timeToString(arrivalTime));
        list.add(airplaneField.getText());

        return list;
    }

    private void buildUserInfoPanel() {
        Color fontColor = ThemeMode.getFontColor();

        JLabel idLabel = new CustomTextLabel("항공편 코드 :", fontColor);
        JLabel airlineLabel = new CustomTextLabel("항공사:", fontColor);
        JLabel depAirpLabel = new CustomTextLabel("출발지 :", fontColor);
        JLabel depGateNoLabel = new CustomTextLabel("출발 게이트 :", fontColor);
        JLabel depDateLabel = new CustomTextLabel("출발일(YYYY-MM-DD) :", fontColor);
        JLabel depTimeLabel = new CustomTextLabel("출발 시간(HH:MM) :", fontColor);
        JLabel desAirpLabel = new CustomTextLabel("도착지 :", fontColor);
        JLabel desGateNoLabel = new CustomTextLabel("도착 게이트 :", fontColor);
        JLabel arrDateNoLabel = new CustomTextLabel("도착일(YYYY-MM-DD) :", fontColor);
        JLabel arrTimeNoLabel = new CustomTextLabel("도착 시간(HH:MM) :", fontColor);
        JLabel airplaneLabel = new CustomTextLabel("항공기 :", fontColor);

        JPanel flightInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        flightInfoPanel.add(idLabel, c);
        c.gridy = 1; flightInfoPanel.add(airlineLabel, c);
        c.gridy = 2; flightInfoPanel.add(depAirpLabel, c);
        c.gridy = 3; flightInfoPanel.add(depGateNoLabel, c);
        c.gridy = 4; flightInfoPanel.add(depDateLabel, c);
        c.gridy = 5; flightInfoPanel.add(depTimeLabel, c);
        c.gridy = 6; flightInfoPanel.add(desAirpLabel, c);
        c.gridy = 7; flightInfoPanel.add(desGateNoLabel, c);
        c.gridy = 8; flightInfoPanel.add(arrDateNoLabel, c);
        c.gridy = 9; flightInfoPanel.add(arrTimeNoLabel, c);
        c.gridy = 10; flightInfoPanel.add(airplaneLabel, c);


        c.gridx = 1; c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        flightInfoPanel.add(idField, c);
        c.gridy = 1; flightInfoPanel.add(airlineField, c);
        c.gridy = 2; flightInfoPanel.add(depAirpField, c);
        c.gridy = 3; flightInfoPanel.add(depGateNoField, c);
        c.gridy = 4; flightInfoPanel.add(depDateField, c);
        c.gridy = 5; flightInfoPanel.add(depTimeField, c);
        c.gridy = 6; flightInfoPanel.add(desAirpField, c);
        c.gridy = 7; flightInfoPanel.add(desGateNoField, c);
        c.gridy = 8; flightInfoPanel.add(arrDateField, c);
        c.gridy = 9; flightInfoPanel.add(arrTimeField, c);
        c.gridy = 10; flightInfoPanel.add(airplaneField, c);
        c.gridx = 0; c.gridy = 9;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;

        setLayout(new BorderLayout());
        add(flightInfoPanel, BorderLayout.CENTER);
    }
    public boolean validateField() {
        String msg = "";
        boolean result = true;

        boolean depDateValid = true;
        depDateValid &= depDateField.getText().length() == 10;
        depDateValid &= depDateField.getText().matches("\\d{4}-\\d{2}-\\d{2}");
        System.out.println();
        result &= depDateValid;

        boolean arrDateValid = true;
        arrDateValid &= arrDateField.getText().length() == 10;
        arrDateValid &= arrDateField.getText().matches("\\d{4}-\\d{2}-\\d{2}");
        result &= arrDateValid;

        boolean depTimeValid = true;
        depTimeValid &= depTimeField.getText().length() == 5;
        depTimeValid &= depTimeField.getText().matches("\\d{2}:\\d{2}");
        result &= depTimeValid;

        boolean arrTimeValid = true;
        arrTimeValid &= arrTimeField.getText().length() == 5;
        arrTimeValid &= arrTimeField.getText().matches("\\d{2}:\\d{2}");
        result &= arrTimeValid;

        if (!depDateValid) msg = "YYYY-MM-DD 형식의 출발일을 입력하세요.";
        else if (!depTimeValid) msg = "HH:MM 형식의 출발시간을 입력하세요.";
        else if (!arrDateValid) msg = "YYYY-MM-DD 형식의 도착일을 입력하세요.";
        else if (!arrTimeValid) msg = "HH:MM 형식의 도착시간을 입력하세요.";

        if (!result) JOptionPane.showMessageDialog(this, msg);

        return result;
    }
}
