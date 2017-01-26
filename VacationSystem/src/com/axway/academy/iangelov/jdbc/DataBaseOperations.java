package com.axway.academy.iangelov.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBaseOperations {
	private String url="jdbc:mysql://127.0.0.1:3306/vacationinfo";
	private String user ="root";
	private String password ="root";

	protected Connection connection = null;
	protected ResultSet res = null;
	protected PreparedStatement prep = null;
	
	private static String vacationDaysTable = "`id` INT NOT NULL AUTO_INCREMENT, "
					+ " `userName` varchar(45) NOT NULL,"
					+ " `daysLeft` int NOT NULL,"
				    + " `yearly` int NOT NULL,"
					+ " PRIMARY KEY (`ID`));";
				    
	private static String userregistrationTable =   "`ID` INT NOT NULL AUTO_INCREMENT,"
			  + "`userId` VARCHAR(45) NOT NULL,"
			  + "`userPwd` VARCHAR(45) NOT NULL,"
			  + "`userEmail` VARCHAR(45) NOT NULL,"
			  + "PRIMARY KEY (`ID`))";
	private static DataBaseOperations dbo = null;

	public static DataBaseOperations getInstance(){
		if(dbo == null){
			dbo = new DataBaseOperations();
		}
		return dbo;
	}

	protected DataBaseOperations(){
		createDatabase();
		createTable("vacationdays", vacationDaysTable);
		createTable("userregistration", userregistrationTable);
	}

	protected void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("Cannot establish connection to database " + e.getMessage());
			e.printStackTrace();
		}
	}

	protected void createStatement(String stmt){

		try {
			this.prep = this.connection.prepareStatement(stmt);
		} catch (SQLException e) {
			System.out.println("Cannot create statements" + e.getMessage());
			e.printStackTrace();
		}
	}

	protected void disconnect(){
		if(this.res != null){
			try {
				this.res.close();
			} catch (SQLException e) {
				System.out.println("Cannot close resultset");
				e.printStackTrace();
			}
			if(this.prep != null){
				try {
					this.prep.close();
				} catch (SQLException e) {
					System.out.println("Cannot close resultset" + e.getMessage());
					e.printStackTrace();
				}
				if(this.connection != null){
					try {
						this.connection.close();
					} catch (SQLException e) {
						System.out.println("Cannot close connection to databse" + e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void createDatabase(){
			this.connect();
			this.createStatement("Create database if not exists vacationinfo");
			try {
				this.prep.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Cannot create database " + e.getMessage());
				e.printStackTrace();
			}
			this.disconnect();
			
	}
	
	protected void createTable(String tableName, String values){
		this.connect();
		
		this.createStatement("CREATE TABLE IF NOT EXISTS " + tableName + " (" + values );
		try {
		int row = this.prep.executeUpdate();
		System.out.println(row + " rows updated");
		} catch (SQLException e) {
			System.out.println("Cannot create table " + tableName + " " + e.getMessage());
			e.printStackTrace();
		}
		this.disconnect();
	}

}
