package view.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.user.User;
import view.listener.Route;

public class LoginButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;

	public LoginButton() {
		super(User.isLogged() ? "로그아웃" : "로그인");
		addActionListener(Route.getRoute());
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		if (c.equals("로그아웃")) User.logout();
	}
}
