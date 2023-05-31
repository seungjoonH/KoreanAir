package model.reservation;

import model.CSVModel;
import model.dao.CustomerDAOFactory;
import model.dao.FlightDAOFactory;
import model.flight.Airplane;
import model.flight.Flight;
import model.user.Customer;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reservation implements CSVModel {
    public static String encodePassengerInfos(List<List<String>> list) {
        List<String> strInfoList = new ArrayList<>();
        for (List<String> strList : list) strInfoList.add(String.join("#", strList));
        return String.join(",", strInfoList);
    }
    public static List<List<String>> decodePassengerInfos(String string) {
        List<List<String>> list = new ArrayList<>();
        List<String> strInfoList = new ArrayList<>(Arrays.asList(string.split(",")));
        for (String str : strInfoList) list.add(Arrays.asList(str.split("#")));
        return list;
    }

    private String id;
    private String uid;
    private String flight;
    private int first;
    private int business;
    private int economy;
    private List<List<String>> passengerInfos = new ArrayList<>();

    @Override
    public String getKey() { return id; }

    public int getSeat() { return getSeat(getSeatClass()); }

    public int getSeat(Airplane.SeatClass seatClass) {
        return switch (seatClass) {
            case FIRST -> first;
            case BUSINESS -> business;
            case ECONOMY -> economy;
        };
    }

    public int getRemainSeat() {
        return getFlight().getRemainSeat(getSeatClass());
    }

    public Airplane.SeatClass getSeatClass() {
        Airplane.SeatClass seatClass = Airplane.SeatClass.ECONOMY;
        if (first > 0) seatClass = Airplane.SeatClass.FIRST;
        if (business > 0) seatClass = Airplane.SeatClass.BUSINESS;
        return seatClass;
    }

    public int getPassengerSize() {
        return passengerInfos.size();
    }
    public String getPassengerName(int index) {
        return passengerInfos.get(index).get(0);
    }
    public Customer.Sex getPassengerSex(int index) {
        int sexIndex = Integer.parseInt(passengerInfos.get(index).get(1));
        return Customer.Sex.values()[sexIndex];
    }
    public LocalDate getPassengerBirth(int index) {
        return LocalDate.parse(passengerInfos.get(index).get(2));
    }
    public String getPassengerPassportNo(int index) {
        return passengerInfos.get(index).get(3);
    }

    public Reservation(String[] csvList) { fromCSV(csvList); }

    public Customer getCustomer() {
        List<Customer> customers = CustomerDAOFactory.getFactory().getList();
        for (Customer customer : customers) { if (customer.getKey().equals(uid)) return customer; }
        return null;
    }
    public Flight getFlight() {
        List<Flight> flights = FlightDAOFactory.getFactory().getList();
        for (Flight f : flights) { if (f.getKey().equals(flight)) return f; }
        return null;
    }

    public int getPrice() {
        return getFlight().getPrice(getSeatClass());
    }

    @Override
    public void fromCSV(String[] csvList) {
        int l = csvList.length, c = 0;
        passengerInfos = new ArrayList<>();

        if (l > c && !csvList[c].equals("")) id = csvList[c++];
        if (l > c && !csvList[c].equals("")) uid = csvList[c++];
        if (l > c && !csvList[c].equals("")) flight = csvList[c++];
        if (l > c && !csvList[c].equals("")) first = Integer.parseInt(csvList[c++]);
        if (l > c && !csvList[c].equals("")) business = Integer.parseInt(csvList[c++]);
        if (l > c && !csvList[c].equals("")) economy = Integer.parseInt(csvList[c++]);
        if (l > c && !csvList[c].equals("")) passengerInfos = decodePassengerInfos(csvList[c]);
    }

    @Override
    public String[] toCSV() {
        return new String[]{
            id, uid, flight,
            String.valueOf(first),
            String.valueOf(business),
            String.valueOf(economy),
            encodePassengerInfos(passengerInfos),
        };
    }

    public boolean isSameId(String id) { return this.id.equals(id); }
    public boolean isSameFlight(String flight) { return this.flight.equals(flight); }
    public boolean isSameUid(String uid) { return this.uid.equals(uid); }
}
