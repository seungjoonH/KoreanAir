package model;

public class Admin extends User {
	public Admin(String[] csv) { super(csv); }

	@Override
	public boolean isAdmin() { return true; }
}
