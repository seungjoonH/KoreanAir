package model;

public class Airport {
	String code;
	String country;
	String city;
	
	public Airport(String[] csv) { fromCsv(csv); }
	
	public void fromCsv(String[] csv) {
		int l = csv.length, c = 0;
		if (l > c) code = csv[c++];
		if (l > c) country = csv[c++];
		if (l > c) city = csv[c++];
	}
	
	public String getCode() { return code; }
	public String getCountry() { return country; }
	public String getCity() { return city; }
	public void setCode(String code) { this.code = code; }
	public void setCountry(String country) { this.country = country; }
	public void setCity(String city) { this.city = city; }
}