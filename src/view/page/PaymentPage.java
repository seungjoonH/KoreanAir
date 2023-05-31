package view.page;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import global.NumberUtil;
import model.dao.ReservationFactory;
import model.reservation.Reservation;
import model.user.User;
import view.page.route.Route;
import view.widget.CustomTextLabel;
import view.widget.button.BackButton;

import java.awt.*;
import java.awt.event.ActionEvent;

class NoStringFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string.matches("\\d+")) { super.insertString(fb, offset, string, attr); }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("\\d+")) { super.replace(fb, offset, length, text, attrs); }
    }
}


public class PaymentPage extends Page implements DocumentListener {
    private JTextField mileageField;
    private JPanel paymentPanel;
    private JLabel priceText;
    private JLabel availableMileageText;
    private JLabel totalText;
    private JLabel accumulatedText;

    private String text = "0";
    private int price;
    private int passengers;
    private int totalPrice;
    private int availableMileage;
    private int discountedPrice;
    private int accumulatedMileage;

    private static Reservation reservation;

    public static void setReservation(Reservation res) {
        reservation = res;
    }

    public PaymentPage() {
        super(null, null, true);
    }
    public PaymentPage(JComponent left) {
        super(left, null, true);
    }
    public PaymentPage(JComponent left, JComponent right) {
        super(left, right, true);
    }
    public PaymentPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected void setInit() {
        mileageField = new JTextField(20);
        mileageField.setText(text);
        ((javax.swing.text.AbstractDocument) mileageField.getDocument())
                .setDocumentFilter(new NoStringFilter());
        mileageField.getDocument().addDocumentListener(this);
        JButton purchaseButton = new JButton("결제");
        purchaseButton.addActionListener(this);
        appbar.setRightWidget(purchaseButton);
    }

    @Override
    protected String getTitle() { return "결제"; }

    @Override
    protected void buildContent() {
        buildPaymentPanel();
        add(paymentPanel, BorderLayout.CENTER);
    }

    void buildPaymentPanel() {
        paymentPanel = new JPanel(new GridBagLayout());

        JLabel priceLabel = new CustomTextLabel("가격 : ", 20);
        JLabel availableMileageLabel = new CustomTextLabel("사용가능 : ", 20);
        JLabel useMileageLabel = new CustomTextLabel("사용 : ", 20);
        JLabel totalLabel = new CustomTextLabel("총액 : ", 20);
        JLabel accumulatedLabel = new CustomTextLabel("적립 : ", 20);

        double rate = 1.2 * (3 - reservation.getSeatClass().ordinal()) * .01;

        String text = mileageField.getText();
        if (text.equals("")) text = "0";

        price = reservation.getPrice();
        passengers = reservation.getPassengerSize();
        totalPrice = price * passengers;
        availableMileage = Math.min(reservation.getCustomer().getPoint(), totalPrice);
        discountedPrice = totalPrice - Integer.parseInt(text);
        accumulatedMileage = (int) (discountedPrice * rate);

        String priceStr = NumberUtil.toDecimalFormat(price);
        String ttPriceStr = NumberUtil.toDecimalFormat(totalPrice);
        String avMileageStr = NumberUtil.toDecimalFormat(availableMileage);
        String dcMileageStr = NumberUtil.toDecimalFormat(discountedPrice);
        String accMileageStr = NumberUtil.toDecimalFormat(accumulatedMileage);

        priceText = new CustomTextLabel(ttPriceStr + " = ( " + priceStr + " * " + passengers + " ) 원", 20);
        availableMileageText = new CustomTextLabel(avMileageStr + " 포인트", 20);
        totalText = new CustomTextLabel(dcMileageStr + " 원", 20);
        accumulatedText = new CustomTextLabel("+ " + accMileageStr + " ( " + NumberUtil.doubleRound(rate * 100, 2) + " %) 포인트", 20);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 0, 5, 0);

        c.gridy = 0; paymentPanel.add(priceLabel, c);

        c.gridy = 1; paymentPanel.add(new JLabel("--------------"), c);
        c.gridy = 2; paymentPanel.add(new CustomTextLabel("마일리지", 20, Font.BOLD), c);
        c.gridy = 3; paymentPanel.add(availableMileageLabel, c);
        c.gridy = 4; paymentPanel.add(useMileageLabel, c);
        c.gridy = 5; paymentPanel.add(new JLabel("--------------"), c);
        c.gridy = 6; paymentPanel.add(totalLabel, c);
        c.gridy = 7; paymentPanel.add(accumulatedLabel, c);

        c.gridx = 1; c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 0; paymentPanel.add(priceText, c);
        c.gridy = 1; paymentPanel.add(new JLabel("-----------------------------------------"), c);
        c.gridy = 2; paymentPanel.add(new JLabel());
        c.gridy = 3; paymentPanel.add(availableMileageText, c);
        c.gridy = 4; paymentPanel.add(mileageField, c);
        c.gridy = 5; paymentPanel.add(new JLabel("-----------------------------------------"), c);
        c.gridy = 6; paymentPanel.add(totalText, c);
        c.gridy = 7; paymentPanel.add(accumulatedText, c);
    }

    @Override
    public void refresh() {
        String msg = null;
        text = mileageField.getText();
        if (text.equals("")) text = "0";

        int point = Integer.parseInt(text);
        if (point > 100000) {
            msg = "마일리지는 100,000 포인트 이상으로 사용 불가능합니다.";
            text = "100000";
        }
        else if (point > availableMileage) {
            msg = "사용가능 마일리지를 범위를 벗어납니다.";
            text = String.valueOf(availableMileage);
        }

        if (msg != null) {
            paymentPanel.remove(mileageField);
            JOptionPane.showMessageDialog(this, msg);
            GridBagConstraints c = new GridBagConstraints();
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 1; c.gridy = 4; setInit();
            paymentPanel.add(mileageField, c);
        }

        price = reservation.getPrice();
        passengers = reservation.getPassengerSize();
        totalPrice = price * passengers;
        availableMileage = Math.min(reservation.getCustomer().getPoint(), totalPrice);
        discountedPrice = totalPrice - Integer.parseInt(text);
        double rate = 1.2 * (3 - reservation.getSeatClass().ordinal()) * .01;
        int accumulatedMileage = (int) (discountedPrice * rate);

        String avMileageStr = NumberUtil.toDecimalFormat(availableMileage);
        String dcMileageStr = NumberUtil.toDecimalFormat(discountedPrice);
        String accMileageStr = NumberUtil.toDecimalFormat(accumulatedMileage);

        availableMileageText.setText(avMileageStr);
        totalText.setText(dcMileageStr);
        accumulatedText.setText("+ " + accMileageStr + " ( " + NumberUtil.doubleRound(rate * 100, 2) + " %) 포인트");

        revalidate();
        repaint();
    }

    void purchase() {
        String msg = NumberUtil.toDecimalFormat(discountedPrice) + "원\n결제가 완료되었습니다.";
        JOptionPane.showMessageDialog(this, msg);
        msg = "마일리지 " + NumberUtil.toDecimalFormat(accumulatedMileage) + " 포인트 적립!";
        JOptionPane.showMessageDialog(this, msg);
        User.useMileagePoint(Integer.parseInt(mileageField.getText()));
        User.earnMileagePoint(accumulatedMileage);
        ReservationFactory.getFactory().add(reservation);

        Route.goHome();
        Route.goTo(new MyPage(new BackButton()));
        Route.goTo(new MyReservationPage(new BackButton()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals("결제")) purchase();
    }
    @Override
    public void insertUpdate(DocumentEvent e) { refresh(); }
    @Override
    public void removeUpdate(DocumentEvent e) { refresh(); }
    @Override
    public void changedUpdate(DocumentEvent e) {}
}
