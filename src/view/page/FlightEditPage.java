package view.page;

import model.flight.Flight;
import view.page.route.Route;
import view.page.theme.ThemeMode;
import view.widget.form.FlightInputForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class FlightEditPage extends Page {
    protected FlightInputForm form;
    protected JButton getSubmitButton() {
        JButton button = new JButton(getButtonText());
        button.addActionListener(this);
        return button;
    }

    protected FlightEditPage() {
        super(null, null, true);
        appbar.setRightWidget(getSubmitButton());
    }
    protected FlightEditPage(JComponent left) {
        super(left, null, true);
        appbar.setRightWidget(getSubmitButton());
    }
    protected FlightEditPage(JComponent left, JComponent right) {
        super(left, right, true);
    }
    protected FlightEditPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected void setInit() {
        super.setInit();
        form = new FlightInputForm();
    }

    @Override
    protected void buildContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ThemeMode.getBackgroundColor());

        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.add(form, BorderLayout.CENTER);
        formPanel.setOpaque(false);
        panel.add(formPanel, BorderLayout.CENTER);
        add(panel);
    }

    protected Flight extractFlight() {
        return new Flight(form.getTexts().toArray(new String[0]));
    }

    protected abstract String getButtonText();
    protected abstract void submit();

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        String msg = null;
        if (c.equals(getButtonText())) {
            if (!form.validateField()) return;
            submit(); msg = "항공편이 정상적으로 " + getButtonText() + " 되었습니다.";
        }

        JOptionPane.showMessageDialog(this, msg);
        Route.goBack();
        Route.getThisPage().refresh();
    }
}
