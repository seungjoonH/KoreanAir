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

	public List<T> getObj() { return objs; }
	public void loadCSV() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(getFilepath()));
        String line = br.readLine();
        objs = new ArrayList<T>();

        while ((line = br.readLine()) != null) {
            String[] csvList = line.split(";");
            objs.add(getConstructor(csvList));
        }
        br.close();
	}
	
	public void saveCSV() throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(getFilepath()));
		
        pw.println(getHeader());
        for (T obj : objs) pw.println(String.join(";", obj.toCSV()));

        pw.close();
	}
	
	public void add(T obj) {
		objs.add(obj);
		try { saveCSV(); } 
		catch (IOException e) { e.printStackTrace(); }
	}

	public void update(T obj) {
		ListIterator<T> iterator = objs.listIterator();
		while (iterator.hasNext()) {
			T o = iterator.next();
			if (o.getKey().equals(obj.getKey())) { iterator.set(obj); break; }
		}

		try { saveCSV(); }
		catch (IOException e) { e.printStackTrace(); }
	}
	
	public void visualize() { for (T obj : objs) { System.out.println(obj); } }
	
}


