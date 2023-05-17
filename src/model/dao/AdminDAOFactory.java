package model.dao;

import model.user.Admin;
import model.user.User;

import java.util.List;

public class AdminDAOFactory extends DAOFactory<Admin> {
    private static final AdminDAOFactory factory = new AdminDAOFactory();
    private AdminDAOFactory() {}
    public static AdminDAOFactory getFactory() { return factory; }

    @Override
    public void loadList() {
        super.loadList();
        User.updateLogged();
    }
    @Override
    protected AdminDAO create() { return new AdminDAO(); }
}
