package model.dao;

import model.Customer;

public class CustomerDAO extends DAO<Customer> {
	private CustomerDAO() {}
	private static CustomerDAO dao = new CustomerDAO();
	
	public static CustomerDAO getDAO() { return dao; }

	@Override
	protected String getFilepath() { return filepath + "customers.csv"; };

	@Override
	protected String getHeader() {
		return "uid;id;password;name;sex;birth;passportNo;phone;email;mileagePoint";
	}
	
	@Override
	protected Customer getConstructor(String[] csvList) {
		return new Customer(csvList);
	}

	@Override
	public void visualize() { 
		System.out.println("\nLoaded Customers(" + getObj().size() + "):"); 
		super.visualize(); 
	}
}