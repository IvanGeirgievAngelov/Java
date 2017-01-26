package com.axway.academy.iangelov.jdbc;

import java.sql.SQLException;

public class CancelVacations extends DataBaseOperations {
	
	private static CancelVacations cv = null;
	
	private CancelVacations(){
		
	}
	
	public static CancelVacations getInstance(){
		if (cv == null){
			cv = new CancelVacations();
		}
		return cv;
	}
	
	public boolean cancelVacations(String ID,String name){
	
		returnBackDays(name,ID);
		super.connect();
		super.createStatement("DELETE FROM vacations" + name + " where id = ?");
		try {
		
			super.prep.setString(1, ID);
			super.prep.executeUpdate();
			super.disconnect();
			return true;
		} catch (SQLException e) {
			System.out.println("Cannot execute query " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
		return false;
	}
	

	private void returnBackDays(String name,String id){
		int vacation = getVacationDays(id,name);
		super.connect();
		super.createStatement("update vacationdays set daysLeft = daysLeft + ? where username =  ?");
		try {
			super.prep.setString(2, name);
			super.prep.setString(1, String.valueOf(vacation));
			int row = super.prep.executeUpdate();
			System.out.println(row + " rows updated");
		} catch (SQLException e) {
			System.out.println("Cannot set  Days back " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
	}
	
	private int getVacationDays(String id, String name){
		int result = 0;
		super.connect();
		super.createStatement("select continues from vacations" + name + " where id = ?");
		try {
			super.prep.setString(1, id);
			super.res = super.prep.executeQuery();
			while(res.next()){
				result = super.res.getInt("continues");
			}
			super.disconnect();
			return result;
		} catch (SQLException e) {
			System.out.println("Cannot get days for this vacations " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
		return 0;
	}

}
