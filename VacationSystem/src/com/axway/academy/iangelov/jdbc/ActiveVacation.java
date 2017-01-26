package com.axway.academy.iangelov.jdbc;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActiveVacation extends DataBaseOperations {

	private static ActiveVacation av = null;
	
	private ActiveVacation(){
		
	}
	
	public static ActiveVacation getInstance(){
		if(av == null){
			av = new ActiveVacation();
		}
		return av;
	}
	
	public String getActiveVacations(String name){
		String output = "";
		super.connect();
		super.createStatement("select * from vacations" + name + " where curdate() between startdate and enddate ");
		try {
			super.res = super.prep.executeQuery();
			while(res.next()){
				output += ("ID : " + super.res.getInt(1)
				+ " StartDate " + res.getString(3) 
				+ " EndDate " + res.getString(4)  
				+ " Duration " + res.getInt(5) 
				+ "<br/></br/>"
				+ "you have left " + calculateDaysLeft(res.getString(3),res.getString(4)) + " days from your vaation" );
			}
		} catch (SQLException e) {
			System.out.println("Cannot execute query " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
		return output;
	}
	
	private long calculateDaysLeft(String begin, String end){
		
		SimpleDateFormat formater = new SimpleDateFormat("YYYY-mm-dd");
		Date beg = null;
		Date en = null;
		try {
			beg =	formater.parse(begin);
			en = formater.parse(end);
		} catch (ParseException e) {
		System.out.println("Cannot parse date " + e.getMessage());
			e.printStackTrace();
		}
		return (en.getTime() - beg.getTime())/10000;
	}
}
