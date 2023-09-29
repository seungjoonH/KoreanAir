package model.flight;

import model.CSVModel;

public class Airplane implements CSVModel {
    public enum SeatClass { FIRST, BUSINESS, ECONOMY }

    String model;
    int first;
    int business;
    int economy;

    @Override
    public String getKey() { return model; }

    public Airplane(String[] csv) { fromCSV(csv); }

    public int getSeat(SeatClass seatClass) {
        switch (seatClass) {
            case FIRST: return first;
            case BUSINESS: return business;
            case ECONOMY: return economy;
        };
        return -1;
    }

    @Override
    public void fromCSV(String[] csvList) {
        int l = csvList.length, c = 0;
        if (l > c && !csvList[c].equals("")) model = csvList[c++];
        if (l > c && !csvList[c].equals("")) first = Integer.parseInt(csvList[c++]);
        if (l > c && !csvList[c].equals("")) business = Integer.parseInt(csvList[c++]);
        if (l > c && !csvList[c].equals("")) economy = Integer.parseInt(csvList[c]);
    }

    @Override
    public String[] toCSV() {
        return new String[]{
            model, String.valueOf(first), String.valueOf(business), String.valueOf(economy),
        };
    }

    public boolean isSameModel(String model) {
        return this.model.equals(model);
    }
}
