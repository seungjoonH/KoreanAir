package model;

public class FlightFilter {
	private String airlineName;
	private String year;
	private String month;
	private String day;
	private String departure;
	private String arrival;
	private int remain;
	private String seatClass;

	public String getAirlineName() { return airlineName; }
	public String getYear() { return year; }
	public String getMonth() { return month; }
	public String getDay() { return day; }
	public String getDeparture() { return departure; }
	public String getArrival() { return arrival; }
	public int getRemain() { return remain; }
	public String getSeatClass() { return seatClass; }
	
	public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
	public void setYear(String year) { this.year = year; }
	public void setMonth(String month) { this.month = month; }
	public void setDay(String day) { this.day = day; }
	public void setDeparture(String departure) { this.departure = departure; }
	public void setArrival(String arrival) { this.arrival = arrival; }
	public void setRemain(int remain) { this.remain = remain; }
	public void setSeatClass(String seatClass) { this.seatClass = seatClass; }
	
	public String getQuery() { return ""; }
}
