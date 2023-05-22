package model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import model.CSVModel;

public abstract class DAO<T extends CSVModel> {
	private List<T> objs = new ArrayList<>();
	protected String filepath = "./database/";
	protected abstract String getFilepath();
	protected abstract String getHeader();
	
	protected abstract T getConstructor(String[] csvList);

	List<T> getObj() { return objs; }
	void setObj(List<T> objs) { this.objs = objs; }

	public void loadCSV() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(getFilepath()));
			String line = br.readLine();

			if (!line.equals(getHeader())) {
				System.out.println("[ERROR] " + getFilepath() + " file open cannot be finished");
				System.out.println("Header does not exist!");
				System.exit(1);
			}
			objs = new ArrayList<T>();

			while ((line = br.readLine()) != null) {
				String[] csvList = line.split(";");
				objs.add(getConstructor(csvList));
			}
			br.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void saveCSV() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(getFilepath()));

			pw.println(getHeader());
			for (T obj : objs) pw.println(String.join(";", obj.toCSV()));

			pw.close();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void visualize() { for (T obj : objs) { System.out.println(obj); } }
	
}


