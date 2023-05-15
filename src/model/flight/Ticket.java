package model.flight;

import model.user.Customer;
import model.user.User;

public class Ticket {
	private String id;
	private Customer customer;
	private Seat seat;
	private boolean checkedIn;
	private String barcodeNo;
	private int price;
	private int usedMileage;
}
