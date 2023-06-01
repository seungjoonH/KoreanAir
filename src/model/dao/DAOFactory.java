package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import model.CSVModel;

public abstract class DAOFactory<T extends CSVModel> {
    // 모든 데이터 로드
    public static void loadAll() {
        CustomerDAOFactory.getFactory().loadList();
        AdminDAOFactory.getFactory().loadList();
        AirportDAOFactory.getFactory().loadList();
        AirplaneDAOFactory.getFactory().loadList();
        FlightDAOFactory.getFactory().loadList();
        ReservationDAOFactory.getFactory().loadList();
    }

    private List<T> list = new ArrayList<>();

    public List<T> getList() { return list; }
    public void loadList() {
        DAO<T> dao = getDAO();
        dao.loadCSV();
        list = dao.getObj();
    }
    public void saveList() {
        DAO<T> dao = getDAO();
        dao.setObj(list);
        dao.saveCSV();
    }
    public void add(T obj) { list.add(obj); saveList(); }
    public void update(T obj) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T o = iterator.next();
            if (o.getKey().equals(obj.getKey())) { iterator.set(obj); break; }
        }
        saveList();
    }
    public void delete(T obj) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T o = iterator.next();
            if (o.getKey().equals(obj.getKey())) { iterator.remove(); break; }
        }
        saveList();
    }
    protected DAO<T> getDAO() { return create(); }

    protected abstract DAO<T> create();

    public String getLastKey() { return list.get(list.size() - 1).getKey(); }
}