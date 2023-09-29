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
	private static Stack<Page> pages = new Stack<>();

	public static void push(Page page) { pages.push(page); }

	public static void goHome() {
		pages = new Stack<>();
		goTo(new HomePage());
	}
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
			case "뒤로": goBack(); Route.getThisPage().refresh(); break;
			case "로그인": goTo(new LoginPage(back)); break;
			case "로그아웃": goHome(); break;
			case "회원가입": goTo(new RegisterPage(back)); break;
			case "마이페이지": goTo(new MyPage(back)); break;
			case "항공편조회/예약":
			case "항공편조회/수정": goTo(new SearchPage(back, login)); break;
			case "회원정보": goTo(new MyInfoPage(back)); break;
			case "예약": goTo(new ReservationPage(back)); break;
			case "예약정보": goTo(new MyReservationPage(back)); break;
			case "항공편생성": goTo(new FlightCreatePage(back)); break;
			case "수정": goTo(new FlightModifyPage(back)); break;
		}
	}
}
