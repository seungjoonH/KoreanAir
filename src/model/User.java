package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import main.Main;
import model.dao.AdminDAO;
import model.dao.CustomerDAO;
import model.dao.DAO;

public abstract class User implements CSVModel {
	private static User logged;
	protected static final DAO<Admin> adminDAO = AdminDAO.getDAO();
	protected static final DAO<Customer> customerDAO = CustomerDAO.getDAO();
	
	private String uid;
	private String id;
	private String password;
	private String name;
	
	public static void loadAll() throws IOException {
		adminDAO.loadCSV(); customerDAO.loadCSV();
	}

	public static boolean isLogged() { return logged != null; }
	public static boolean isLoggedUserAdmin() { return logged.isAdmin(); }
	public static User getUser() { return logged; }
	public static void setUser(User user) {
		user.uid = logged.uid; logged = user;
		customerDAO.update((Customer) logged);
	}

	public static LoginState login(String id, String pw) { 
		User foundUser = findUser(id);
		
		if (foundUser == null) { return LoginState.NO_MEM; }
		if (!foundUser.validPassword(pw)) { return LoginState.PW_INCRT; }
		
		logged = foundUser;
		return LoginState.SUCCESS;
	}
	
	public static void logout() { logged = null; }
	public static RegisterState register(User newcomer) {
		User foundUser = findUser(newcomer.id);
		
		if (foundUser != null) { return RegisterState.DUP_ID; }
		
		customerDAO.add((Customer) newcomer);
		
		return RegisterState.SUCCESS;
	}

	public static String getUid() { return logged.uid; }
	public static String getId() { return logged.id; }
	public static String getPassword() { return logged.password; }
	public static String getName() { return logged.name; }
	public static Sex getSex() { return ((Customer) logged).sex; }
	public static LocalDate getBirth() { return ((Customer) logged).birth; }
	public static String getEmail() { return ((Customer) logged).email; }
	public static String getPassportNo() { return ((Customer) logged).passportNo; }
	public static String getPhone() { return ((Customer) logged).phone; }
	public static int getMileagePoint() { return ((Customer) logged).mileagePoint; }

	private static User findUser(String id) {
		List<Admin> admins = adminDAO.getObj();
		List<Customer> customers = customerDAO.getObj();
		
		for (User a : admins) { if (a.id.equals(id)) return (Admin) a; }
		for (User c : customers) { if (c.id.equals(id)) return (Customer) c; }
		
		return null;
	}
	
	public static String getNextUid() {
		List<Customer> customers = customerDAO.getObj();
		
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
