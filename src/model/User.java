package model;

import main.Sex;

public class User {
	public static Sex[] sexs = { Sex.MALE, Sex.FEMALE };
	
	// attributes
	private String uid;
	private String id;
	private String name;
	private String password;
	
	// getters & setters
	public String getUid() { return uid; }
	public String getId() { return id; }
	public String getName() { return name; }
	public String getPassword() { return password; }
	public void setUid(String uid) { this.uid = uid; }
	public void setId(String id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setPassword(String password) { this.password = password; }
	
	public User(String[] csv) { fromCsv(csv); }
	public User(String uid, String id, String password, String name) {
		this.uid = uid;
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	// methods
	public void fromCsv(String[] csv) {
		int l = csv.length, c = 0;
		if (l > c) uid = csv[c++];
		if (l > c) id = csv[c++];
		if (l > c) password = csv[c++];
		if (l > c) name = csv[c++];
	}

	@Override
	public String toString() {
		return "uid: " + uid + "\n"
				+ "id: " + id + "\n"
				+ "password: " + password + "\n"
				+ "name: " + name + "\n";
	}
}
