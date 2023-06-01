package model.dao;

import model.user.Admin;

public class AdminDAO extends DAO<Admin> {
	@Override
	protected String getFilepath() { return filepath + "admins.csv"; };
	
	@Override
	protected String getHeader() { return "uid;id;password;name"; }; 

	@Override
	protected Admin getConstructor(String[] csvList) {
		return new Admin(csvList);
	}

	@Override
	public void visualize() { 
		System.out.println("\nLoaded Admins(" + getObj().size() + "):"); 
		super.visualize(); 
	}
	
}