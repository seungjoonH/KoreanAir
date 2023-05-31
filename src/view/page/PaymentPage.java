package view.page;

import javax.swing.*;

import model.flight.Flight;
import model.user.Customer;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class PaymentPage extends Page {
	protected JTextField idField;
    protected JTextField passwordField;
    protected JTextField nameField;
    protected JComboBox<Customer.Sex> sexCombo;
    protected JTextField birthField;
    protected JTextField emailField;
    protected JTextField phoneField;
    protected JTextField passportNoField;

    protected JButton submitButton;
    
    private static int price;
    public static void setPrice(int p) { price = p; }
    
    private static int personnel;
    public static void setPersonnel(int p) { personnel = p; }
    
    private static String seatClass;
    public static void setSeatClass(String c) { seatClass = c; }
    
    
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
        idField = new JTextField(20);
        passwordField = new JPasswordField(20);
        nameField = new JTextField(20);
        sexCombo = new JComboBox<>(Customer.Sex.values());
        birthField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        passportNoField = new JTextField(20);
    }

    @Override
    protected String getTitle() { return "결제"; }

    @Override
    protected void buildContent() {
        // TODO
//        JPanel panel = new JPanel();
//        add(panel);
    	JLabel priceLabel = new JLabel("가격 : ");
        JLabel availableMaileageLabel = new JLabel("사용가능 : ");
        JLabel useMileageLabel = new JLabel("사용 : ");
        JLabel totalLabel = new JLabel("총액 : ");
        JLabel accumulatedLabel = new JLabel("적립 : ");
        
        JLabel price = new JLabel("10,000 (10,000 * 1)");
        JLabel availableMaileage = new JLabel("4,000");
        JLabel total = new JLabel("6,000");
        JLabel accumulated = new JLabel("300 (5%)");

        submitButton = new JButton("결제");
        submitButton.addActionListener(this);

        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 0; registerPanel.add(priceLabel, c);
        
        c.gridy = 1; registerPanel.add(new JLabel("--------"), c);
        
        c.gridy = 2; registerPanel.add(availableMaileageLabel, c);
        c.gridy = 3; registerPanel.add(useMileageLabel, c);
        c.gridy = 4; registerPanel.add(new JLabel("--------"), c);
        c.gridy = 5; registerPanel.add(totalLabel, c);
        c.gridy = 6; registerPanel.add(accumulatedLabel, c);

        c.gridx = 1; c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 0; registerPanel.add(price, c);
        c.gridy = 1; registerPanel.add(new JLabel("-------------------------------"), c);
        c.gridy = 2; registerPanel.add(availableMaileage, c);
        c.gridy = 3; registerPanel.add(nameField, c);
        c.gridy = 4; registerPanel.add(new JLabel("-------------------------------"), c);
        c.gridy = 5; registerPanel.add(total, c);
        c.gridy = 6; registerPanel.add(accumulated, c);

        c.gridx = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 7; registerPanel.add(submitButton, c);

        add(registerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
