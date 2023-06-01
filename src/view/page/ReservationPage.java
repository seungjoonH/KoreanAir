package view.page;

import model.dao.ReservationDAOFactory;
import model.flight.Airplane;
import model.flight.Flight;
import model.reservation.Reservation;
import model.user.Customer;
import model.user.User;
import view.page.route.Route;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;
import view.widget.button.BackButton;
import view.widget.form.PassengerInputForm;
import view.widget.widget.FlightInfoWidget;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationPage extends Page implements ChangeListener {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Airplane.SeatClass[] classes = Airplane.SeatClass.values();

    private JSpinner numberOfPassengersSpinner;
    private JLabel remainingSeatsLabel;
    private JPanel userInfoPanel;
    private JComboBox<Airplane.SeatClass> seatClassComboBox;

    private static final int USERINFO_MAX = 10;

    private PassengerInputForm[] forms;

    private int passengerNumber = 1;
    private int selected = 0;
    private int remain = 0;

    @Override
    protected String getTitle() { return "예약"; }

    public ReservationPage() {
        super(null, null, true);
    }
    public ReservationPage(JComponent left) {
        super(left, null, true);
    }
    public ReservationPage(JComponent left, JComponent right) {
        super(left, right, true);
    }
    public ReservationPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    private static Flight flight;
    public static void setFlight(Flight f) { flight = f; }


    @Override
    protected void setInit() {
        super.setInit();
        forms = new PassengerInputForm[10];

        for (int i = 0; i < 10; i++) {
            forms[i] = new PassengerInputForm(i);
        }

        String name = User.getName();
        Customer.Sex sex = User.getSex();
        LocalDate birth = User.getBirth();
        String ppNo = User.getPassportNo();

        forms[0].setTexts(name, sex, birth, ppNo);
    }

    @Override
    protected void buildContent() {
        JPanel panel = new JPanel();
        panel.setBackground(ThemeMode.getBackgroundColor());
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Color fontColor = ThemeMode.getFontColor();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel seatPanel = new JPanel();

        seatClassComboBox = new JComboBox<>(classes);
        seatClassComboBox.addActionListener(this);

        selected = seatClassComboBox.getSelectedIndex();
        remain = flight.getRemainSeat(classes[selected]);

        JPanel passengerPanel = new JPanel();

        remainingSeatsLabel = new CustomTextLabel(String.valueOf(remain), fontColor);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        numberOfPassengersSpinner = new JSpinner(spinnerModel);
        numberOfPassengersSpinner.addChangeListener(this);

        seatPanel.setOpaque(false);
        seatPanel.add(new CustomTextLabel("좌석 클래스:", fontColor));
        seatPanel.add(seatClassComboBox);
        seatPanel.add(new CustomTextLabel("잔여석: ", fontColor));
        seatPanel.add(remainingSeatsLabel);
        passengerPanel.setOpaque(false);
        passengerPanel.add(new CustomTextLabel("인원 수:", fontColor));
        passengerPanel.add(numberOfPassengersSpinner);

        JButton paymentButton = new JButton("결제");
        paymentButton.addActionListener(new Route());
        paymentButton.addActionListener(this);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

        controlPanel.setOpaque(false);
        controlPanel.add(seatPanel);
        controlPanel.add(passengerPanel);
        controlPanel.add(paymentButton);

        JScrollPane userInfoScrollPane = new JScrollPane();
        userInfoPanel = new JPanel();

        loadPassengerNumber();

        userInfoPanel.setOpaque(false);
        userInfoScrollPane.setOpaque(false);
        userInfoScrollPane.setViewportView(userInfoPanel);

        contentPanel.setOpaque(false);
        contentPanel.add(new FlightInfoWidget(flight));
        contentPanel.add(controlPanel);
        contentPanel.add(userInfoScrollPane);

        panel.add(contentPanel);
        add(panel);
    }

    private JPanel buildUserInfoPanel(int index) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(ThemeMode.getBackgroundColor());
        GridBagConstraints c = new GridBagConstraints();

        Color fontColor = ThemeMode.getFontColor();

        JLabel userLabel = new CustomTextLabel("승객 정보 #" + (index + 1), 20, fontColor, Font.BOLD);
        JLabel nameLabel = new CustomTextLabel("이름 :", fontColor);
        JLabel sexLabel = new CustomTextLabel("성별 :", fontColor);
        JLabel birthLabel = new CustomTextLabel("생년월일(YYYY-MM-DD) :", fontColor);
        JLabel ppLabel = new CustomTextLabel("여권번호 :", fontColor);

        c.gridx = 0; c.gridy = 12;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(userLabel, c); c.gridy++;
        panel.add(nameLabel, c); c.gridy++;
        panel.add(sexLabel, c); c.gridy++;
        panel.add(birthLabel, c); c.gridy++;
        panel.add(ppLabel, c);

        c.gridx = 1; c.gridy = 13;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(forms[index].getNameField(), c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(forms[index].getSexComboBox(), c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(forms[index].getBirthField(), c);
        c.gridy++; panel.add(forms[index].getPpNoField(), c);

        return panel;
    }

    private void loadPassengerNumber() {
        passengerNumber = (int) numberOfPassengersSpinner.getValue();
        userInfoPanel.setLayout(new GridLayout(0, 3));
        userInfoPanel.removeAll();

        for (int i = 0; i < USERINFO_MAX; i++) {
            JPanel blankPanel = new JPanel();
            blankPanel.setBackground(ThemeMode.getBackgroundColor());
            if (i < passengerNumber) userInfoPanel.add(buildUserInfoPanel(i));
            else userInfoPanel.add(blankPanel);
        }
    }

    private List<List<String>> fieldToList() {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < passengerNumber; i++) {
            list.add(forms[i].getTexts());
        }
        return list;
    }

    public boolean validateField() {
        String msg = null;
        for (int i = 0; i < passengerNumber; i++) {
            String name = forms[i].getNameField().getText();
            String birth = forms[i].getBirthField().getText();
            String ppNo = forms[i].getPpNoField().getText();
            if (name.equals("")) msg = "이름을 입력해주세요.";
            else if (birth.equals("")) msg = "생년월일을 입력해주세요.";
            else if (!birth.matches("\\d{4}-\\d{2}-\\d{2}")) msg = "생년월일을 yyyy-mm-dd 형식에 맞게 입력해주세요.";
            else if (ppNo.equals("")) msg = "여권번호를 입력해주세요.";
            else if (!ppNo.matches("[A-Za-z]\\d{8}")) msg = "여권번호를 입력해주세요.";
            if (msg != null) break;
        }

        if (msg != null) JOptionPane.showMessageDialog(this, msg);
        return msg == null;
    }

    public void reserve() {
        if (!validateField()) return;

        String key = ReservationDAOFactory.getFactory().getLastKey();
        int id = Integer.parseInt(key) + 1;

        int selected = seatClassComboBox.getSelectedIndex();

        int[] seats = {0, 0, 0};
        seats[selected] = passengerNumber;

        List<List<String>> passengerInfos = fieldToList();

        String[] csvList = {
            String.valueOf(id),
            User.getUid(),
            flight.getId(),
            String.valueOf(seats[0]),
            String.valueOf(seats[1]),
            String.valueOf(seats[2]),
            Reservation.encodePassengerInfos(passengerInfos),
        };

        Reservation reservation = new Reservation(csvList);

        if (reservation.getRemainSeat() < passengerNumber) {
            JOptionPane.showMessageDialog(this, "좌석이 부족합니다");
            return;
        }
        PaymentPage.setReservation(reservation);
        Route.goTo(new PaymentPage(new BackButton()));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();

        if (c.equals("comboBoxChanged")) {
            selected = seatClassComboBox.getSelectedIndex();
            remain = flight.getRemainSeat(classes[selected]);
            remainingSeatsLabel.setText(String.valueOf(remain));
            Route.refresh();
        }
        else if (c.equals("결제")) { reserve(); }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        loadPassengerNumber();
        Route.refresh();
    }
}
