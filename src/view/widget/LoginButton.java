package view.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.*;

import model.user.User;
import view.page.HomePage;
import view.page.route.Route;

public class LoginButton extends JButton implements ActionListener {
	@Serial
	private static final long serialVersionUID = 1L;

	public LoginButton() {
		super(User.isLogged() ? "로그아웃" : "로그인");
		addActionListener(new Route());
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		String msg = null;
		if (c.equals("로그아웃")) msg = "로그아웃 되었습니다!";
		if (msg == null) return;
		JOptionPane.showMessageDialog(Route.getThisPage(), msg);
		User.logout();
	}
}
