package model.dao;

import model.user.Customer;

public class CustomerDAO extends DAO<Customer> {
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