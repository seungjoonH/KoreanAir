package model.user;

import java.time.LocalDate;
import java.util.List;

import model.CSVModel;
import model.dao.*;
import util.logger.Logger;
import view.page.LoginPage;
import view.page.RegisterPage;

public abstract class User implements CSVModel {
	/* STATIC */
	private static User logged;

	public static List<? extends User> getDAOList() {
		if (isLoggedUserAdmin()) return AdminDAOFactory.getFactory().getList();
		return CustomerDAOFactory.getFactory().getList();
	}

	public static boolean isLogged() { return logged != null; }
	public static boolean isLoggedUserAdmin() {
		if (!isLogged()) return false;
		return logged.isAdmin();
	}
	public static void updateLogged() {
		if (!isLogged()) return;
		List<? extends User> users = getDAOList();
		for (User user : users) { if (user.uid.equals(logged.uid)) logged = user; }
	}

	public static User getUser() { return logged; }
	public static void setUser(User user) {
		user.uid = logged.uid; logged = user;
		if (user.isAdmin()) AdminDAOFactory.getFactory().update((Admin) logged);
		CustomerDAOFactory.getFactory().update((Customer) logged);
	}

	public static LoginPage.LoginState login(String id, String pw) {
		User foundUser = findUser(id);
		
		if (foundUser == null) { return LoginPage.LoginState.NO_MEM; }
		if (!foundUser.validPassword(pw)) { return LoginPage.LoginState.PW_INCRT; }
		
		logged = foundUser;
		return LoginPage.LoginState.SUCCESS;
	}
	
	public static void logout() {
		Logger.get().log("Logged out"); logged = null;
	}
	public static RegisterPage.RegisterState register(User newcomer) {
		User foundUser = findUser(newcomer.id);
		
		if (foundUser != null) { return RegisterPage.RegisterState.DUP_ID; }

		CustomerDAOFactory.getFactory().add((Customer) newcomer);
		
		return RegisterPage.RegisterState.SUCCESS;
	}

	/* NON-STATIC */

	private String uid;
	private String id;
	private String password;
	private String name;

	public static String getUid() { return logged.uid; }
	public static String getId() { return logged.id; }
	public static String getPassword() { return logged.password; }
	public static String getName() { return logged.name; }
	public static Customer.Sex getSex() { return ((Customer) logged).sex; }
	public static LocalDate getBirth() { return ((Customer) logged).birth; }
	public static String getEmail() { return ((Customer) logged).email; }
	public static String getPassportNo() { return ((Customer) logged).passportNo; }
	public static String getPhone() { return ((Customer) logged).phone; }
	public static int getMileagePoint() { return ((Customer) logged).mileagePoint; }

	public static void earnMileagePoint(int amount) {
		((Customer) logged).mileagePoint += amount;
		CustomerDAOFactory.getFactory().update((Customer) logged);
	}
	public static void useMileagePoint(int amount) {
		((Customer) logged).mileagePoint -= amount;
		CustomerDAOFactory.getFactory().update((Customer) logged);
	}

	private static User findUser(String id) {
		List<Admin> admins = AdminDAOFactory.getFactory().getList();
		List<Customer> customers = CustomerDAOFactory.getFactory().getList();
		
		for (User a : admins) { if (a.id.equals(id)) return (Admin) a; }
		for (User c : customers) { if (c.id.equals(id)) return (Customer) c; }
		
		return null;
	}
	
	public static String getNextUid() {
		List<Customer> customers = CustomerDAOFactory.getFactory().getList();
		
		int maxUid = 0;		
		for (User c : customers) {
			maxUid = Math.max(Integer.parseInt(c.uid), maxUid); 
		}
		
		return String.valueOf(maxUid + 1);
	}
	
	// Constructor
	public User(String[] csv) { fromCSV(csv); }
	
	public String getHeader() {
		return "uid;id;password;name;sex;birth;passportNo;phone;email;mileagePoint";
	}
	
	public void fromCSV(String[] csvList) {
		int l = csvList.length, c = 0;
		if (l > c && !csvList[c].equals("")) uid = csvList[c++];
		if (l > c && !csvList[c].equals("")) id = csvList[c++];
		if (l > c && !csvList[c].equals("")) password = csvList[c++];
		if (l > c && !csvList[c].equals("")) name = csvList[c];
	}
	
	public String[] toCSV() {
		return new String[]{ uid, id, password, name };
	}	
	
	@Override
	public String toString() {
		return ("\nuid: " + uid
			+ "\nid: " + id
			+ "\npassword: " + password
			+ "\nname: " + name
		);
	}

	public abstract boolean isAdmin();
	@Override
	public String getKey() { return uid; }
	public boolean validPassword(String pw) { return password.equals(pw); }
}
