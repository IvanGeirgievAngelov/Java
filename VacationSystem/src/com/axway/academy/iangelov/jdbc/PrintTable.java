package com.axway.academy.iangelov.jdbc;

import java.sql.SQLException;

public class PrintTable extends DataBaseOperations {
	
	private static PrintTable pt;
	
	private PrintTable(){};
	
	public static PrintTable getInstance(){
		if(pt == null){
			pt = new PrintTable();
		}
		return pt;
	}
	
	public void print(String tableName){
		super.connect();
		super.createStatement("select * from " + tableName);
		try {
			super.res = super.prep.executeQuery();
			while(res.next()){
				System.out.print("ID: " + res.getInt("ID"));
				System.out.print(" Name: " + res.getString("userId"));
				System.out.print(" Pass: " + res.getString("userPwd"));
				System.out.println(" Emai: " + res.getString("userEmail"));
			}
			super.disconnect();
		} catch (SQLException e) {
			System.out.println("Cannot print table " + e.getMessage());
			e.printStackTrace();
		}
		super.disconnect();
	}
}
