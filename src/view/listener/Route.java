package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import view.page.*;
import view.page.my.*;
import view.widget.BackButton;

public class Route implements ActionListener {
	private static final Route r = new Route();
	private final Main m = Main.getMain();
	
	private Route() { backStack.add(new HomePage()); }
	public static Route getRoute() { return r; } 
	
	private final List<Page> backStack = new ArrayList<>();
	
	public void pushPage(Page back) { backStack.add(back); }
	public Page popPage() { return backStack.remove(backStack.size() - 1); }
	public Page peekPage() { return backStack.get(backStack.size() - 1); }
	
	public void goBack() { m.gotoPage(popPage()); }
	public void refresh() { m.gotoPage(m.getCurrentPage()); }

	public void goTo(Page page) {
		Page p = m.getCurrentPage();
		pushPage(p); m.gotoPage(page);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String c = e.getActionCommand();
		switch (c) {
			case "뒤로" -> goBack();
			case "로그인" -> goTo(new LoginPage(new BackButton(peekPage()), null, true));
			case "로그아웃" -> goTo(new HomePage());
			case "회원가입" -> goTo(new RegisterPage(new BackButton(peekPage()), null, true));
			case "마이페이지" -> goTo(new MyPage(new BackButton(peekPage()), null, true));
			case "항공편조회" -> goTo(new SearchPage(new BackButton(peekPage()), null, true));
			case "회원정보" -> goTo(new MyInfoPage(new BackButton(peekPage()), null, true));
		}
	}
}
