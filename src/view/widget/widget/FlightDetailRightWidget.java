package view.widget.widget;

import model.user.User;
import view.page.FlightDetailPage;
import view.page.route.Route;
import view.page.theme.ThemeMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class FlightDetailRightWidget extends JPanel implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    public FlightDetailRightWidget() {
        boolean isAdmin = User.isLoggedUserAdmin();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton button1;
        JButton button2;

        if (isAdmin) {
            button1 = new JButton("수정");
            button2 = new JButton("삭제");
            button1.addActionListener(new Route());
            button2.addActionListener(this);

            panel.add(button1);
            panel.add(button2);
        }
        else {
            button1 = new JButton("예약");
            button1.addActionListener(User.isLogged() ? new Route() : this);

            panel.add(button1);
        }

        panel.setOpaque(false);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        String msg = null;

        if (c.equals("예약")) msg = "먼저 로그인 해주세요!";
        if (c.equals("삭제")) {
            if (FlightDetailPage.delete())  msg = "삭제되었습니다.";
            else msg = "예약자가 있어 삭제할 수 없습니다.";
        }

        if (msg != null) JOptionPane.showMessageDialog(Route.getThisPage(), msg);
    }
}
