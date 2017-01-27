package com.axway.academy.iangelov.jdbc;

import java.sql.SQLException;

import com.axway.academy.iangelov.encryption.EncryptPassword;

public class AddUser extends DataBaseOperations {
	
	private EncryptPassword ep = EncryptPassword.getInstance();
	private static AddUser addUser = null;
	
	private static String values = "`ID` INT NOT NULL AUTO_INCREMENT, "
			+" `name` VARCHAR(45) NOT NULL,"
		    +" `startDate` DATE NOT NULL,"
		    +" `endDate` DATE NOT NULL,"
		    +" `continues` INT NOT NULL,"
		    +" `status` INT NOT NULL,"
		    +" PRIMARY KEY (`ID`));";
	
	
	private AddUser(){};

	
	public static AddUser getInstance(){
		if(addUser == null){
			addUser = new AddUser();
		}
		return addUser;
	}
	
	
	private void usersAddDays(String name, int days){
		super.connect();
		super.createStatement("insert into vacationinfo.vacationdays (userName,daysLeft,yearly) values  (" + '"' + name +'"' + "," + '"' + days + '"' + "," + '"' + days + '"' + ")"); 
		try {
			super.prep.executeUpdate();
			System.out.println("successfully added dayss");
			super.disconnect();
		} catch (SQLException e) {
			System.out.println("Cannot add days to this user " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
	}
	
	
	public boolean addNewAccount(String name , String pass, String email, int days){

		super.createTable("vacations" + name, values);
		if(!ifUserExist(name)){
		usersAddDays(name,days);
		super.connect();
		super.createStatement("INSERT INTO userregistration (userId,userPwd,userEmail) values(?,?,?)");
		try {
			super.prep.setString(1, name);
			super.prep.setString(2, ep.encrypt(pass));
			super.prep.setString(3, email);
			super.prep.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Cannot execute query " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
		return true;
		}
		else {
			System.out.println("This user allready exist");
			return false;
		}
	}
	
	private boolean ifUserExist(String name){
		super.connect();
		//super.createStatement("select * from userregistration where userId = " + '"' + name + '"');
		super.createStatement("select * from userregistration where userId= ? ");
		try {
			super.prep.setString(1, name);
			super.res = super.prep.executeQuery();
			
			if(super.res.next()){
				return true;
			}
		} catch (SQLException e) {
			//System.out.println("Cannot set this value " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
		return false;
	
	}
}
