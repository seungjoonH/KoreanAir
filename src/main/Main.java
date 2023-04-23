package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import controller.FlightManagementController;
import controller.UserManagementController;
import model.User;
import view.Login;
import view.Menu;

public class Main {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 600;
	private static JFrame jframe;
	private static User loggedUser;
	
	public static JFrame getFrame() { return jframe; }
	public static void setUser(User user) { loggedUser = user; }
	public static User getUser() { return loggedUser; }
	public static boolean isLogged() { return getUser() != null; }
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		jframe = new JFrame();
		
		UserManagementController.loadCustomers();
		FlightManagementController.loadAirports();
		FlightManagementController.loadFlights();
		
		changeLevel(Pages.MENU);

		jframe.setTitle("Korean Air App");
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
	}

	public static void changeLevel(Pages page) {
		jframe.getContentPane().removeAll();
		switch (page) {
		case MENU: jframe.getContentPane().add(new Menu()); break;
		case LOGIN: jframe.getContentPane().add(new Login()); break;
		}
		
		jframe.revalidate();
		jframe.repaint();
	}
}