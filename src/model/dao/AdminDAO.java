package model.dao;

import model.Admin;

public class AdminDAO extends DAO<Admin> {
	
	private AdminDAO() {}
	private static AdminDAO dao = new AdminDAO();
	
	public static AdminDAO getDAO() { return dao; }

	@Override
	protected String getFilepath() { return filepath + "admins.csv"; };
	
	@Override
	protected String getHeader() { return "uid;id;password;name"; }; 

	@Override
	protected Admin getConstructor(String[] fields) {
		return new Admin(fields);
	}

	@Override
	public void visualize() { 
		System.out.println("\nLoaded Admins(" + getObj().size() + "):"); 
		super.visualize(); 
	}
	
}