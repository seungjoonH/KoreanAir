package model;

public class Administrator extends User {
	// constructors
	public Administrator(String[] csv) { super(csv); }
	public Administrator(String uid, String id, String password, String name) {
		super(uid, id, password, name);
	}
}
