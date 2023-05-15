package model.user;

public class Admin extends User {
	public Admin(String[] csv) { super(csv); }

	@Override
	public boolean isAdmin() { return true; }
}
