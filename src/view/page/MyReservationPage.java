package view.page;

import model.dao.ReservationDAOFactory;
import model.reservation.Reservation;
import model.user.Customer;
import model.user.User;
import util.logger.Logger;
import view.Window;
import view.page.route.Route;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;
import view.widget.form.PassengerInputForm;
import view.widget.widget.FlightInfoWidget;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyReservationPage extends Page implements ChangeListener {

    private List<JPanel> reservationsView;
    private static int index = 0;

    private JSpinner spinner;
    private List<Reservation> reservations;
    private JPanel reservationPanel;
    private JPanel userInfoPanel;
    private List<List<PassengerInputForm>> forms;

    private Color fontColor;

    public static int getIndex() { return index; }

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
        super.setInit();
        JButton deleteButton = new JButton("삭제");
        deleteButton.addActionListener(this);
        appbar.setRightWidget(deleteButton);

        index = 0;
        ReservationDAOFactory.getFactory().loadList();
        reservations = ReservationDAOFactory.getFactory().getReservationByUid(User.getUid());

        reservationsView = new ArrayList<>();

        for (Reservation reservation : reservations) {
            JPanel panel = new JPanel();
            panel.setOpaque(false);
            panel.add(new FlightInfoWidget(reservation.getFlight()));
            reservationsView.add(panel);
        }

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, reservations.size(), 1);
        spinner = new JSpinner(spinnerModel);
        Dimension d = spinner.getPreferredSize(); d.width = 100;
        spinner.setPreferredSize(d);
        spinner.addChangeListener(this);
        reservationPanel = new JPanel();
        userInfoPanel = new JPanel();
        forms = new ArrayList<>();
        setForms();

        reservationPanel.setOpaque(false);
        userInfoPanel.setOpaque(false);
        reservationPanel.removeAll();
        reservationPanel.add(reservationsView.get(index));
        loadPassengerNumber();
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ThemeMode.getBackgroundColor());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        fontColor = ThemeMode.getFontColor();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        spinnerPanel.setOpaque(false);
        spinnerPanel.add(new CustomTextLabel("예약번호: ", fontColor));
        spinnerPanel.add(spinner);

        JPanel reservationInfoPanel = new JPanel(new BorderLayout());
        reservationInfoPanel.setOpaque(false);
        reservationInfoPanel.add(new FlightInfoWidget(reservations.get(index).getFlight()), BorderLayout.CENTER);
        reservationInfoPanel.setPreferredSize(new Dimension(Window.WIDTH - 40, Window.HEIGHT / 4));

        JPanel userInfoScrollPane = new JPanel(new BorderLayout());
        JScrollPane pane = new JScrollPane(userInfoPanel);
        userInfoScrollPane.setOpaque(false);
        pane.setOpaque(false);
        userInfoScrollPane.add(pane, BorderLayout.CENTER);
        userInfoScrollPane.setPreferredSize(new Dimension(Window.WIDTH - 40, Window.HEIGHT / 2));

        contentPanel.setOpaque(false);
        contentPanel.add(spinnerPanel, BorderLayout.NORTH);
        contentPanel.add(reservationInfoPanel, BorderLayout.CENTER);
        contentPanel.add(userInfoScrollPane, BorderLayout.SOUTH);

        panel.add(contentPanel);

        add(panel);
    }

    private void loadPassengerNumber() {
        userInfoPanel.setLayout(new GridLayout(0, 3));
        userInfoPanel.removeAll();

        int size = reservations.get(index).getPassengerSize();

        for (int i = 0; i < 10; i++) {
            JPanel blankPanel = new JPanel();
            blankPanel.setBackground(ThemeMode.getBackgroundColor());
            if (i < size) userInfoPanel.add(buildUserInfoPanel(i));
            else userInfoPanel.add(blankPanel);
        }
    }

    private JPanel buildUserInfoPanel(int i) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(ThemeMode.getBackgroundColor());
        fontColor = ThemeMode.getFontColor();

        GridBagConstraints c = new GridBagConstraints();
        JLabel userLabel = new CustomTextLabel("승객 정보 #" + (i + 1), 20, fontColor, Font.BOLD);
        JLabel nameLabel = new CustomTextLabel("이름 :", fontColor);
        JLabel sexLabel = new CustomTextLabel("성별 :", fontColor);
        JLabel birthLabel = new CustomTextLabel("생년월일(YYYY-MM-DD) :", fontColor);
        JLabel ppLabel = new CustomTextLabel("여권번호 :", fontColor);

        c.gridx = 0; c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(userLabel, c); c.gridy++;
        panel.add(nameLabel, c); c.gridy++;
        panel.add(sexLabel, c); c.gridy++;
        panel.add(birthLabel, c); c.gridy++;
        panel.add(ppLabel, c);

        c.gridx = 1; c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(forms.get(index).get(i).getNameField(), c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(forms.get(index).get(i).getSexComboBox(), c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(forms.get(index).get(i).getBirthField(), c);
        c.gridy++; panel.add(forms.get(index).get(i).getPpNoField(), c);

        return panel;
    }

    void delete() {
        ReservationDAOFactory.getFactory().delete(reservations.get(index));
        JOptionPane.showMessageDialog(this, "삭제되었습니다!");
        Route.goBack();
        Logger.get().log("Reservation deleted: " + reservations.get(index).getKey());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        index = (int) spinner.getValue() - 1;
        reservationPanel.removeAll();
        reservationPanel.add(reservationsView.get(index));
        loadPassengerNumber();
        Route.refresh();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("삭제")) delete();
    }
}
