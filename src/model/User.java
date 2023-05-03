package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import main.Sex;

public class User {
	public static Sex[] sexs = { Sex.MALE, Sex.FEMALE };
	
	// attributes
	private String uid;
	private String id;
	private String name;
	private String password;
	private Sex sex;
	private LocalDate birth;
	private String passportNo;
	private String phone;
	private String email;
	private Vector<Ticket> reservations;
	private int mileagePoint;

	// getters & setters
	public String getUid() { return uid; }
	public String getId() { return id; }
	public String getName() { return name; }
	public String getPassword() { return password; }
	public Sex getSex() { return sex; }
	public LocalDate getBirth() { return birth; }
	public String getPassportNo() { return passportNo; }
	public String getPhone() { return phone; }
	public String getEmail() { return email; }
	public Vector<Ticket> getReservations() { return reservations; }
	public int getMileagePoint() { return mileagePoint; }
	public void setUid(String uid) { this.uid = uid; }
	public void setId(String id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setPassword(String password) { this.password = password; }
	public void setSex(Sex sex) { this.sex = sex; }
	public void setBirth(LocalDate birth) { this.birth = birth; }
	public void setPassportNo(String passportNo) { this.passportNo = passportNo; }
	public void setPhone(String phone) { this.phone = phone; }
	public void setEmail(String email) { this.email = email; }
	public void setMileagePoint(int mileagePoint) { this.mileagePoint = mileagePoint; }
	public void setReservations(Vector<Ticket> reservations) { this.reservations = reservations; }
	
	// Constructor
	public User(String[] csv) { fromCsv(csv); }
	public User(
			String uid, 
			String id, 
			String password, 
			String name, 
			Sex sex, 
			LocalDate birth, 
			String passportNo, 
			String phone, 
			String email, 
			int mileagePoint,
			Vector<Ticket> reservations 
	) {
		this.uid = uid;
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.passportNo = passportNo;
		this.phone = phone;
		this.email = email;
		this.mileagePoint = mileagePoint;
		this.reservations = reservations;
	}
	
	public void fromCsv(String[] csv) {
		int l = csv.length, c = 0;
		if (l > c) uid = csv[c++];
		if (l > c) id = csv[c++];
		if (l > c) password = csv[c++];
		if (l > c) name = csv[c++];
		if (l > c) sex = sexs[Integer.parseInt(csv[c++])];
		if (l > c) birth = LocalDate.parse(csv[c++], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (l > c) passportNo = csv[c++];
		if (l > c) phone = csv[c++];
		if (l > c) email = csv[c++];
		if (l > c) mileagePoint = Integer.parseInt(csv[c++]);
	}
	
	public String toCsv() {
		String sexStr = "";
		if (sex == Sex.MALE) sexStr = "0";
		else if (sex == Sex.FEMALE) sexStr = "1";
		
		String[] values = {
			uid, id, password, name, sexStr, 
			birth.toString(), passportNo, phone, 
			email, String.valueOf(mileagePoint) 
		};
		
		return String.join(";", values);
	}
	
	// methods
	public void shareReservations(String uid, Ticket ticket) {}
	public void addReservation(Ticket ticket) {}
	public void addMileage(int value) {}
	public void useMileage(int value) {}
	
}
