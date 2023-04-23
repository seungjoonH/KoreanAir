package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import main.Sex;
import model.Customer;
import model.Ticket;
import model.User;

public class UserManagementController {
	private static Vector<User> users = new Vector<User>();
	private static Vector<String> adminUids = new Vector<String>();
	
	public static void loadCustomers() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("database/customers.csv"));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(";");
            users.add(new User(fields));
        }
        br.close();
	}
	
	public static boolean isMember(String uid) { return getUser(uid) != null; }
	public static User getUser(String uid) {
		for (User user : users) { if (user.getUid() == uid) return user; }
		return null; 
	}
	public static User getUserById(String id) {
		for (User user : users) {
			if (user.getId().equals(id)) return user; 
		}
		return null; 
	}
	public void addCustomer(Customer customer) {}
	public boolean isAdmin(Customer customer) { return false; }
//	public Vector<Customer> getCustomers(Customer customer) { return new Vector(); }
//	public Customer addshowReservations(Customer customer) { return new Customer(); }
}