package view.widget.button;

import javax.swing.JButton;

import view.page.Page;
import view.page.route.Route;

public class BackButton extends JButton {
	private static final long serialVersionUID = 1L;

	public BackButton() {
		super("뒤로");
		addActionListener(new Route());
	}
}
