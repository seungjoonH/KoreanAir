package view.page.route;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import main.Main;
import view.page.*;
import view.widget.button.BackButton;
import view.widget.button.LoginButton;

import javax.swing.*;

public class Route implements ActionListener {
	private static final Stack<Page> pages = new Stack<>();

	public static void push(Page page) { pages.push(page); }

	public static void goBack() {
		pages.pop(); Main.build();
	}
	public static void goTo(Page page) {
		pages.push(page); Main.build();
	}
	public static void refresh() {
		Page current = getThisPage();
		pages.pop(); pages.push(current);
		Main.build();
	}
	public static Page getThisPage() { return pages.peek(); }

	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		JButton back = new BackButton();
		JButton login = new LoginButton();

		switch (c) {
			case "뒤로" -> goBack();
			case "로그인" -> goTo(new LoginPage(back));
			case "로그아웃" -> goTo(new HomePage());
			case "회원가입" -> goTo(new RegisterPage(back));
			case "마이페이지" -> goTo(new MyPage(back));
			case "항공편조회/예약", "항공편조회/수정" -> goTo(new SearchPage(back, login));
			case "회원정보" -> goTo(new MyInfoPage(back));
			case "예약" -> goTo(new ReservationPage(back));
			case "예약정보" -> goTo(new MyReservationPage(back));
		}
	}
}
