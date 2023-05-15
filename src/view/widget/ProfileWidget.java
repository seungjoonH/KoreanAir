package view.widget;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.user.User;

public class ProfileWidget extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ProfileWidget() {
		if (!User.isLogged()) return;
		String name = User.getName();
		String admin = User.isLoggedUserAdmin() ? " 관리자" : "";
		add(new JLabel(name + admin + "님 환영합니다"));
	}
}
