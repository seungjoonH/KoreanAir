package model.dao;

import model.CSVModel;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class DAOFactory<T extends CSVModel> {
    // 모든 데이터 로드
    public static void loadAll() {
        CustomerDAOFactory.getFactory().loadList();
        AdminDAOFactory.getFactory().loadList();
        AirportDAOFactory.getFactory().loadList();
        FlightDAOFactory.getFactory().loadList();
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
    protected DAO<T> getDAO() { return create(); }

    protected abstract DAO<T> create();
}