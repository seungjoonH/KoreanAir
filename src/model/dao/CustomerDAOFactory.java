package model.dao;

import model.user.Customer;
import model.user.User;

public class CustomerDAOFactory extends DAOFactory<Customer> {
    private static final CustomerDAOFactory factory = new CustomerDAOFactory();
    private CustomerDAOFactory() {}
    public static CustomerDAOFactory getFactory() { return factory; }

    @Override
    public void loadList() {
        super.loadList();
        User.updateLogged();
    }

    @Override
    protected CustomerDAO create() { return new CustomerDAO(); }
}
