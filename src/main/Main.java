package main;

import model.dao.DAOFactory;
import view.Window;

public class Main {
	private static Window thisWindow;
	public static void build() { thisWindow.build(); }

	public static void main(String[] args) {
		DAOFactory.loadAll();
		thisWindow = new Window();
		build();
	}
}