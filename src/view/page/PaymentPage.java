package view.page;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PaymentPage extends Page {
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
    protected String getTitle() { return "결제"; }

    @Override
    protected void buildContent() {
        // TODO
        JPanel panel = new JPanel();
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
