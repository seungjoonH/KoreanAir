package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import model.Airport;
import model.Flight;
import model.FlightFilter;
import model.PaymentAgency;

public class FlightManagementController {
	private static Vector<Airport> airports = new Vector<Airport>();
	private static Vector<Flight> flights = new Vector<Flight>();
	private static FlightFilter filter;
	
	public Vector<Flight> getFlights() { return flights; }
	public FlightFilter getFilter() { return filter; }
	public void setFlights(Vector<Flight> flights) { this.flights = flights; }
	public void setFilter(FlightFilter filter) { this.filter = filter; }
	
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
//	    System.out.println(String.join(" / ", list[2]));
//	    System.out.println(list.length);
	    return list;
	}
	
//	public static search(String ) {}
	
	public void showFilterForm() {}
	public void receiveFilteredInfo() {}
	public void showFlights() {}
	public void loadFlightsFromDB() {}
	public void showReservationForm() {}
	public void requestPayment(int price, PaymentAgency agency) {}
	public void saveFlightsToDB() {}
	public void showCompleteDialog() {}
}
