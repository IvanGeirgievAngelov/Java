package com.axway.academy.iangelov.jdbc;
import java.sql.SQLException;

public class ViewRegisteredVacations extends DataBaseOperations {

	private static ViewRegisteredVacations vrv = null;

	public static ViewRegisteredVacations getInstance(){
		if(vrv == null){
			return new ViewRegisteredVacations();
		}
		return vrv;
	}
	private ViewRegisteredVacations(){
	}

	public String getVacations(String name){
		String output = "";
		super.connect();
		super.createStatement("select * from vacations"+ name + " where name = ?");
		try {
			super.prep.setString(1, name);
			super.res = super.prep.executeQuery();
				while(res.next()){
					output +=("ID: " 
					+ super.res.getInt(1) 
					+ " Name: " 
					+ super.res.getString(2) 
					+ " StartDate: " 
					+ super.res.getString(3)
					+ " EndDate: " 
					+ super.res.getString(4) 
					+ " continue: " 
					+ super.res.getInt(5));
					output += "<br/>";
				}
		} catch (SQLException e) {
			System.out.println("Cannot print vacations for user " + name + " " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
		return output;
	}

}

