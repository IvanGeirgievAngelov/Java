package com.axway.academy.iangelov.jdbc;

import java.sql.SQLException;
import java.time.LocalDate;

public class NewVacation extends DataBaseOperations {
	private static NewVacation nv = null;

	public static NewVacation getInstance(){
		if(nv == null){
			nv = new NewVacation();
		}
		return nv;
	}

	private NewVacation(){
	}

	public boolean RegisterVacation(String name, java.sql.Date startDate, java.sql.Date endDate, long difference){
		int daysLeft = getDaysForUser(name);
		int duration = calculateDurations(startDate,endDate);
		
		if(daysLeft - duration >= 0){
			super.connect();
			super.createStatement("insert into vacations" + name + "(name, startDate, endDate, continues,status) values(?,?,?,?,?)");
			//we remove used days from all days for this user;
			try {
				super.prep.setString(1, name);
				super.prep.setDate(2, startDate);
				super.prep.setDate(3, endDate);
				super.prep.setInt(4, duration);
				super.prep.setInt(5, 0);
				super.prep.executeUpdate();
				minusDays(name,(daysLeft-duration));
				super.disconnect();
				return true;
			} catch (SQLException e) {
				System.out.println("Cannot create vaccation " + e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}

	public long userDaysleft(java.sql.Date begin,java.sql.Date end){

		long difference = (begin.getTime() - end.getTime())/86400000;
		return difference;
	}
	
	public long calculateDays(java.sql.Date start, java.sql.Date end){
		
		return ((start.getTime() - end.getTime())/(24 * 60 * 60 * 1000));
	}

	private int calculateDurations(java.sql.Date start, java.sql.Date end){
		int duration = 0;
		LocalDate s = start.toLocalDate();
		LocalDate e = end.toLocalDate();

		for(LocalDate i = s; i.isBefore(e); i = i.plusDays(1)){
			if(i.getDayOfWeek().toString().equals("SUNDAY") || i.getDayOfWeek().toString().equals("SATURDAY")){
			}
			else {
				duration++;
			}
		}
		return duration;
	}

	public int getDaysForUser(String name){
		super.connect();
		super.createStatement("select daysLeft from vacationDays where username = ?");
		int daysLeft = -1;
		try {
			super.prep.setString(1, name);
			super.res = super.prep.executeQuery();
			while(super.res.next()){
				daysLeft = super.res.getInt("daysLeft");
			}
			super.disconnect();
		} catch (SQLException e) {
			System.out.println("cannot take days from vacationDays " + e.getMessage());
			e.printStackTrace();
		}
		return daysLeft;
	}
	
	private void minusDays(String name, int days){
		super.connect();
		super.createStatement("update vacationdays set daysleft = ? where username = ?");
	
		try {
			super.prep.setInt(1, days);
			super.prep.setString(2, name);
			super.prep.executeUpdate();
			super.disconnect();
		} catch (SQLException e) {
			System.out.println("Cannot update vacationdays table " + e.getMessage());
			e.printStackTrace();
		}
	}
}
