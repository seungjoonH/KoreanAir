package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Vector;

import main.Main;
import model.User;

public class UserManagementController {
	private static Vector<User> users = new Vector<User>();
	private static Vector<String> adminUids = new Vector<String>();
	
	public static String getNextUid() {
		return String.valueOf(Integer.parseInt(users.lastElement().getUid()) + 1);
	}
	
	public static void loadCustomers() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("database/customers.csv"));
        String line = br.readLine();
        users = new Vector<User>();

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(";");
            users.add(new User(fields));
        }
        br.close();
	}
	
	public static void saveCustomers() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("database/customers.csv"));
        pw.println("uid;id;password;name;sex;birth;passportNo;phone;email;mileagePoint");
        for (User user : users) pw.println(user.toCsv());

        pw.close();
        loadCustomers();
	}
	
	private static void remove(String uid) {
		int index = 0;
		for (User user : users) {
			if (uid.equals(user.getUid())) break;
			index++;
		}
		System.out.println(index);
		users.remove(index);
	}
	
	public static void loadAdminUids() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("database/admins.csv"));
        String line = br.readLine();
        
        adminUids = new Vector<String>(Arrays.asList(line.split(";")));
        br.close();
	}

	public static boolean isMember(String uid) { return getUser(uid) != null; }
	public static User getUser(String uid) {
		for (User customer : users) { 
			if (customer.getUid().equals(uid)) return customer; 
		}
		return null; 
	}
	public static User getUserById(String id) {
		for (User user : users) {
			if (user.getId().equals(id)) return user; 
		}
		return null; 
	}
	public static boolean isAdmin(String id) {
		return adminUids.contains(getUserById(id).getUid());
	}
	
	public static void addUser(User user) {
		user.setUid(getNextUid());
		users.add(user);
		try { saveCustomers(); }
		catch (IOException e) {}
	}
	
	public static void updateUser(User user) {
		String uid = Main.getUser().getUid();
		remove(uid);
		user.setUid(uid);
		users.add(user);
		try { saveCustomers(); }
		catch (IOException e) {}
	}
}