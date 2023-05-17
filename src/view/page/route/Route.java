package view.page.route;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import main.Main;
import view.page.*;
import view.widget.BackButton;
import view.widget.LoginButton;

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
		switch (c) {
			case "뒤로" -> goBack();
			case "로그인" -> goTo(new LoginPage(new BackButton(), null, true));
			case "로그아웃" -> goTo(new HomePage());
			case "회원가입" -> goTo(new RegisterPage(new BackButton(), null, true));
			case "마이페이지" -> goTo(new MyPage(new BackButton(), null, true));
			case "항공편조회" -> goTo(new SearchPage(new BackButton(), new LoginButton(), true));
			case "회원정보" -> goTo(new MyInfoPage(new BackButton(), null, true));
		}
	}
}
