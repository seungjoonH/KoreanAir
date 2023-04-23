package model;

public class Ticket {
	// attributes
	private String id;
	private Customer customer;
	private Seat seat;
	private boolean checkedIn;
	private String barcodeNo;
	private int price;
	private int usedMileage;
	
	// getters & setters
	public String getId() { return id; }
	public Customer getCustomer() { return customer; }
	public Seat getSeat() { return seat; }
	public boolean isCheckedIn() { return checkedIn; }
	public String getBarcodeNo() { return barcodeNo; }
	public int getPrice() { return price; }
	public int getUsedMileage() { return usedMileage; }
	public void setId(String id) { this.id = id; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	public void setSeat(Seat seat) { this.seat = seat; }
	public void setCheckedIn(boolean checkedIn) { this.checkedIn = checkedIn; }
	public void setBarcodeNo(String barcodeNo) { this.barcodeNo = barcodeNo; }
	public void setPrice(int price) { this.price = price; }
	public void setUsedMileage(int usedMileage) { this.usedMileage = usedMileage; }
	
	public int getCost() { return 0; }
}
