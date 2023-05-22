package view.widget.button;

import model.user.User;
import view.page.route.Route;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class ReserveButton extends JButton implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    public ReserveButton() {
        super("예약");
        addActionListener(User.isLogged() ? new Route() : this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        String msg = null;
        if (c.equals("예약")) msg = "먼저 로그인 해주세요!";
        if (msg == null) return;
        JOptionPane.showMessageDialog(Route.getThisPage(), msg);

    }
}
