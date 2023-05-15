package model;

public interface CSVModel {
	String getKey();
	void fromCSV(String[] csv);
	String[] toCSV();
}
