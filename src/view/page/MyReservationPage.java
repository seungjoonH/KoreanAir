package view.page;

import model.dao.ReservationFactory;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyReservationPage extends Page implements ChangeListener {

    private List<JPanel> reservationsView;
    private int index = 0;

    private JSpinner spinner;
    private List<Reservation> reservations;
    private JPanel reservationPanel;
    private JPanel userInfoPanel;
    private List<List<PassengerInputForm>> forms;

    protected MyReservationPage() {
        super(null, null, true);
    }
    public MyReservationPage(JComponent left) {
        super(left, null, true);
    }
    protected MyReservationPage(JComponent left, JComponent right) {
        super(left, right, true);
    }
    protected MyReservationPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected void setInit() {
        ReservationFactory.getFactory().loadList();
        reservations = ReservationFactory.getFactory().getReservationByUid(User.getUid());
        reservationsView = new ArrayList<>();

        for (Reservation reservation : reservations) {
            JPanel panel = new JPanel();
            panel.add(new FlightInfoWidget(reservation.getFlight()));
            reservationsView.add(panel);
        }

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, reservations.size() - 1, 1);
        spinner = new JSpinner(spinnerModel);
        Dimension d = spinner.getPreferredSize(); d.width = 100;
        spinner.setPreferredSize(d);
        spinner.addChangeListener(this);
        reservationPanel = new JPanel();
        userInfoPanel = new JPanel();
        forms = new ArrayList<>();
        setForms();
    }

    private void setForms() {
        for (Reservation reservation : reservations) {
            List<PassengerInputForm> formList = new ArrayList<>();
            for (int i = 0; i < reservation.getPassengerSize(); i++) {
                PassengerInputForm form = new PassengerInputForm(i);
                String name = reservation.getPassengerName(i);
                Customer.Sex sex = reservation.getPassengerSex(i);
                LocalDate birth = reservation.getPassengerBirth(i);
                String ppNo = reservation.getPassengerPassportNo(i);
                form.setTexts(name, sex, birth, ppNo);
                formList.add(form);
            }
            forms.add(formList);
        }
    }

    @Override
    protected String getTitle() { return "나의 예약정보"; }

    @Override
    protected void buildContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel contentPanel = new JPanel();

        JPanel spinnerPanel = new JPanel();
        spinnerPanel.add(spinner);

        reservationPanel.add(reservationsView.get(index));

        JScrollPane userInfoScrollPane = new JScrollPane();
        userInfoPanel = new JPanel();

        loadPassengerNumber();

        userInfoScrollPane.setViewportView(userInfoPanel);

        contentPanel.add(spinnerPanel);
        contentPanel.add(reservationPanel);
        contentPanel.add(userInfoScrollPane);

        panel.add(contentPanel);

        add(panel);
    }

    private void loadPassengerNumber() {
        userInfoPanel.setLayout(new GridLayout(0, 3));
        userInfoPanel.removeAll();

        for (int i = 0; i < reservations.get(index).getPassengerSize(); i++) {
            userInfoPanel.add(buildUserInfoPanel(i));
        }
    }

    private JPanel buildUserInfoPanel(int index) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel userLabel = new HeaderTypeLabel("고객 정보 #" + (index + 1), true);
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
        panel.add(forms.get(this.index).get(index).getNameField(), c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(forms.get(this.index).get(index).getSexComboBox(), c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(forms.get(this.index).get(index).getBirthField(), c);
        c.gridy++; panel.add(forms.get(this.index).get(index).getPpNoField(), c);

        return panel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        index = (int) spinner.getValue();
        reservationPanel.removeAll();
        reservationPanel.add(reservationsView.get(index));
        loadPassengerNumber();
        Route.refresh();
    }
}
