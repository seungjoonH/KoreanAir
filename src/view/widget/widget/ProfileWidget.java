package view.widget.widget;

import javax.swing.JPanel;

import model.user.User;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;

import java.awt.*;

public class ProfileWidget extends JPanel {
		
	public ProfileWidget() {
		if (!User.isLogged()) return;
		String name = User.getName();
		String admin = User.isLoggedUserAdmin() ? " 관리자" : "";
		Color fontColor = ThemeMode.getFontColor();
		add(new CustomTextLabel(name + admin + "님 환영합니다", fontColor));
	}
}
