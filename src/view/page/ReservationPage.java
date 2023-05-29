package view.page;

import model.dao.ReservationFactory;
import model.flight.Airplane;
import model.flight.Flight;
import model.reservation.Reservation;
import model.user.Customer;
import model.user.User;
import view.page.route.Route;
import view.widget.HeaderTypeLabel;
import view.widget.form.PassengerInputForm;
import view.widget.widget.FlightInfoWidget;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationPage extends Page implements ActionListener, ChangeListener {
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
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel seatPanel = new JPanel();

        seatClassComboBox = new JComboBox<>(classes);
        seatClassComboBox.addActionListener(this);

        selected = seatClassComboBox.getSelectedIndex();
        remain = flight.getRemainSeat(classes[selected]);

        JPanel passengerPanel = new JPanel();

        remainingSeatsLabel = new JLabel(String.valueOf(remain));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        numberOfPassengersSpinner = new JSpinner(spinnerModel);
        numberOfPassengersSpinner.addChangeListener(this);

        seatPanel.add(new JLabel("좌석 클래스:"));
        seatPanel.add(seatClassComboBox);
        seatPanel.add(new JLabel("잔여석: "));
        seatPanel.add(remainingSeatsLabel);
        passengerPanel.add(new JLabel("인원 수:"));
        passengerPanel.add(numberOfPassengersSpinner);

        JButton reservationButton = new JButton("예약");
        reservationButton.addActionListener(this);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

        controlPanel.add(seatPanel);
        controlPanel.add(passengerPanel);
        controlPanel.add(reservationButton);

        JScrollPane userInfoScrollPane = new JScrollPane();
        userInfoPanel = new JPanel();

        loadPassengerNumber();

        userInfoScrollPane.setViewportView(userInfoPanel);

        contentPanel.add(new FlightInfoWidget(flight));
        contentPanel.add(controlPanel);
        contentPanel.add(userInfoScrollPane);

        panel.add(contentPanel);
        add(panel);
    }

    private JPanel buildUserInfoPanel(int index) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel userLabel = new HeaderTypeLabel("승객 정보 #" + (index + 1), true);
        JLabel nameLabel = new JLabel("이름 :");
        JLabel sexLabel = new JLabel("성별 :");
        JLabel birthLabel = new JLabel("생년월일(YYYY-MM-DD) :");
        JLabel ppLabel = new JLabel("여권번호 :");

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
            if (i < passengerNumber) userInfoPanel.add(buildUserInfoPanel(i));
            else userInfoPanel.add(new JPanel());
        }
    }

    private List<List<String>> fieldToList() {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < passengerNumber; i++) {
            list.add(forms[i].getTexts());
        }
        return list;
    }

    public void reserve() {
        String key = ReservationFactory.getFactory().getLastKey();
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
        ReservationFactory.getFactory().add(reservation);
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
        else if (c.equals("예약")) {
            reserve();
            String msg = "예약이 완료되었습니다";
            JOptionPane.showMessageDialog(this, msg);
            Route.goBack();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        loadPassengerNumber();
        Route.refresh();
    }
}
