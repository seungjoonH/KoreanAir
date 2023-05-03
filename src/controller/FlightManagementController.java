package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Vector;

import model.Airport;
import model.Flight;
import model.FlightFilter;

public class FlightManagementController {
	private static Vector<Airport> airports = new Vector<Airport>();
	private static Vector<Flight> flights = new Vector<Flight>();
	private static FlightFilter filter;
	
	public static Vector<Flight> getFlights() { return flights; }
	public static FlightFilter getFilter() { return filter; }
	public static void setFlights(Vector<Flight> f) { flights = f; }
	public static void setFilter(FlightFilter f) { filter = f; }
	
	public static Airport getAirport(String code) {
		for (Airport ap : airports) {
			if (code.equals(ap.getCode())) return ap;
		}
		return null;
	}
	
	public static void loadAirports() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("database/airports.csv"));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(";");
            airports.add(new Airport(fields));
        }

        br.close();
	}
	
	public static void loadFlights() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("database/flights.csv"));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(";");
            flights.add(new Flight(fields));
        }
        br.close();
	}
	
	public static Flight getFlight(String id) {
		for (Flight flight : flights) {
			if (id.equals(flight.getId())) return flight;
		}
		return null;
	}
	
	public static String[][] getFlightsList() {
	    String[][] list = new String[flights.size()][];
	    for (int i = 0; i < flights.size(); i++) {
	        list[i] = flights.get(i).toStrList();
	    }
	    return list;
	}
	
	public static Vector<Flight> getFiltFlights() {
		Vector<Flight> fs = new Vector<Flight>();
		
		for (Flight flight : flights) {
			boolean satisfied = true;
			LocalDateTime depDate = flight.getDepartureTime();
			String year = String.valueOf(depDate.getYear());
			String month = String.valueOf(depDate.getMonthValue());
			String day = String.valueOf(depDate.getDayOfMonth());
			
			Airport depAirp = flight.getDeparture();
			String dep = depAirp.toString(); 

			Airport desAirp = flight.getDestination();
			String des = desAirp.toString(); 
			
			if (!filter.getAirlineName().equals("")) satisfied &= flight.getAirlineName().contains(filter.getAirlineName());
			if (!filter.getYear().equals("")) satisfied &= year.equals(filter.getYear());
			if (!filter.getMonth().equals("")) satisfied &= month.equals(filter.getMonth());
			if (!filter.getDay().equals("")) satisfied &= day.equals(filter.getDay());
			if (!filter.getDeparture().equals("")) satisfied &= dep.contains(filter.getDeparture());
			if (!filter.getArrival().equals("")) satisfied &= des.contains(filter.getArrival());
			
			if (satisfied) fs.add(flight);
		}
		
		return fs;
	}
	
	public static String[][] getFiltFlightsList() {
		Vector<Flight> fs = getFiltFlights();
		String[][] list = new String[fs.size()][];
	    for (int i = 0; i < fs.size(); i++) {
	        list[i] = fs.get(i).toStrList();
	    }
	    return list;
	}
}
