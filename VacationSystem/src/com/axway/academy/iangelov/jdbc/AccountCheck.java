package com.axway.academy.iangelov.jdbc;

import java.sql.SQLException;

public class AccountCheck extends DataBaseOperations {
	
	private static AccountCheck ac = null;
	
	private AccountCheck(){
	
	}
	
	public static AccountCheck getInstance(){
		if(ac == null){
			ac = new AccountCheck();
		}
		return ac;
	}
	
	public boolean checkIfUserExist(String name,String pass){
		
		super.connect();
		super.createStatement("select * from userregistration where userId = ? AND userPwd = ?");
		try {
			super.prep.setString(1, name);
			super.prep.setString(2, pass);
			
			super.res = super.prep.executeQuery();
			
			if(res.next()){
				super.disconnect();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

}
