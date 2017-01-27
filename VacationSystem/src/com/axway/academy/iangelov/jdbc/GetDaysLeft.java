package com.axway.academy.iangelov.jdbc;

import java.sql.SQLException;

public class GetDaysLeft extends DataBaseOperations {
	private static GetDaysLeft gdl = null;

	public static GetDaysLeft getInstance(){
		if(gdl==null){
			gdl = new GetDaysLeft();
		}
		
		return gdl;
	}
	
	private GetDaysLeft(){
	}
	
	public String getDays(String name){
		String  output = "";
		super.connect();
		super.createStatement("select daysLeft,yearly from vacationdays where userName = ?");
		try {
			super.prep.setString(1, name);
			super.res = super.prep.executeQuery();
			while(res.next()){
				
				output += name + " have " + "<br/>" + res.getString("daysLeft") + " days left " + "<br/>" +  "  Yearly days: " + res.getString("yearly"); 
				output += "<br/>" + " days consumed: " + (res.getInt("yearly") - res.getInt("daysLeft"));
			}
		} catch (SQLException e) {
		System.out.println("Cannot get days from annual plan " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
		
		return output;
	}
}
