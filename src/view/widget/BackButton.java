package view.widget;

import javax.swing.JButton;

import view.page.Page;
import view.listener.Route;

public class BackButton extends JButton {
	private static final long serialVersionUID = 1L;

	public BackButton(Page back) {
		super("뒤로");
		Route r = Route.getRoute();
		r.pushPage(back);
		addActionListener(r);
	}
}
