package model;

public interface CSVModel {
	String getKey();
	void fromCSV(String[] csvList);
	String[] toCSV();
}
