package model.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import global.Global;

public class Customer extends User {
	public enum Sex { MALE, FEMALE }

	protected Sex sex;
	protected LocalDate birth;
	protected String passportNo;
	protected String phone;
	protected String email;
	protected int mileagePoint;

	public int getPoint() { return mileagePoint; }

	public Customer(String[] csv) {
		super(csv);
		fromCSV(csv);
	}

	@Override
	public boolean isAdmin() { return false; }
	

	@Override
	public String toString() {
		return super.toString() + ("\nsex: " + sex.name()
			+ "\nbirth: " + birth.toString()
			+ "\npassportNo: " + passportNo
			+ "\nphone: " + phone
			+ "\nemail: " + email
			+ "\nmileagePoint: " + mileagePoint
		);
	}
	
	public Sex parseSex(String str) { return Global.sexs[Integer.parseInt(str)]; }
	public LocalDate parseBirth(String str) { 
		return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	public int parseMileagePoint(String str) { return Integer.parseInt(str); }

	@Override
	public void fromCSV(String[] csvList) {
		super.fromCSV(csvList);
		int l = csvList.length, c = 4;
		if (l > c && !csvList[c].equals("")) sex = parseSex(csvList[c++]);
		if (l > c && !csvList[c].equals("")) birth = parseBirth(csvList[c++]);
		if (l > c && !csvList[c].equals("")) passportNo = csvList[c++];
		if (l > c && !csvList[c].equals("")) phone = csvList[c++];
		if (l > c && !csvList[c].equals("")) email = csvList[c++];
		if (l > c && !csvList[c].equals("")) mileagePoint = parseMileagePoint(csvList[c]);
	}

	@Override
	public String[] toCSV() {
		String[] list1 = super.toCSV();
		String[] list2 = { 
			String.valueOf(sex.ordinal()), 
			birth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
			passportNo, phone, email,
			String.valueOf(mileagePoint)
		};
		String[] values = new String[list1.length + list2.length];
		
		System.arraycopy(list1, 0, values, 0, list1.length);
		System.arraycopy(list2, 0, values, list1.length, list2.length);
		
		return values;
	}
}
