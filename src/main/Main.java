package main;

import java.io.IOException;
import java.io.Serial;

import javax.swing.JFrame;

import global.Global;
import model.Airport;
import model.Flight;
import model.User;
import view.page.HomePage;
import view.page.Page;

public class Main extends JFrame {
	@Serial
	private static final long serialVersionUID = 1L;
	private static final Main m = new Main();
	public static int WIDTH;
	public static int HEIGHT;

	private Main() { initSet(); }
	public static Main getMain() { return m; }

	private Page page;
	public Page getCurrentPage() { return page; }

	private void initSet() {
		final String title = Global.appTitle;
		WIDTH = Global.windowSize.width;
		HEIGHT = Global.windowSize.height;

		setTitle(title);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		try { User.loadAll(); Airport.loadAll(); Flight.loadAll(); }
		catch (IOException e) { e.printStackTrace(); }
	}

	private void build() {
		getContentPane().removeAll();
		getContentPane().add(page);
		revalidate();
		repaint();
	}

	public void gotoPage(Page page) { this.page = page; build(); }

	public static void main(String[] args) {
		Main m = Main.getMain();
		m.gotoPage(new HomePage());
	}
}