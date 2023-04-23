package model;

public class Seat {
	// attributes
	int row;
	int column;
	int seatClass;
	Ticket ticket;
	
	// getters & setters
	public int getRow() { return row; }
	public int getColumn() { return column; }
	public int getSeatClass() { return seatClass; }
	public Ticket getTicket() { return ticket; }
	public void setRow(int row) { this.row = row; }
	public void setColumn(int column) { this.column = column; }
	public void setSeatClass(int seatClass) { this.seatClass = seatClass; }
	public void setTicket(Ticket ticket) { this.ticket = ticket; }
}
