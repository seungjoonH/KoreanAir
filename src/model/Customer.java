package model;

import java.time.LocalDate;
import java.util.Vector;

import main.Sex;

public class Customer extends User {
	// attributes
	private Sex sex;
	private LocalDate birth;
	private String passportNo;
	private Vector<Ticket> reservations;
	private int mileagePoint;

	// getters & setters
	public Sex getSex() { return sex; }
	public LocalDate getBirth() { return birth; }
	public String getPassportNo() { return passportNo; }
	public Vector<Ticket> getReservations() { return reservations; }
	public int getMileagePoint() { return mileagePoint; }
	public void setSex(Sex sex) { this.sex = sex; }
	public void setBirth(LocalDate birth) { this.birth = birth; }
	public void setPassportNo(String passportNo) { this.passportNo = passportNo; }
	public void setMileagePoint(int mileagePoint) { this.mileagePoint = mileagePoint; }
	public void setReservations(Vector<Ticket> reservations) { this.reservations = reservations; }
	
	// Constructor
	public Customer(
			String uid, 
			String id, 
			String password, 
			String name, 
			Sex sex, 
			LocalDate birth, 
			String passportNo, 
			int mileagePoint,
			Vector<Ticket> reservations 
	) {
		super(uid, id, password, name);
		this.sex = sex;
		this.birth = birth;
		this.passportNo = passportNo;
		this.mileagePoint = mileagePoint;
		this.reservations = reservations;
	}

	// methods
	@Override
	public void login() {}
	@Override
	public void showFlights() {}
	
	public void shareReservations(String uid, Ticket ticket) {}
	public void addReservation(Ticket ticket) {}
	public void addMileage(int value) {}
	public void useMileage(int value) {}
	
	@Override 
	public String toString() {
		return super.toString();
	}
	
}
