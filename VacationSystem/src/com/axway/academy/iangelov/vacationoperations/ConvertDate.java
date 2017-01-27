package com.axway.academy.iangelov.vacationoperations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
String date = null;

public ConvertDate(){}

public java.sql.Date formatToDate(String inputDate){
	
	Date format = null;
	try {
		format = new SimpleDateFormat("dd-MMM-yyyy").parse(inputDate);
	} catch (ParseException e1) {
		System.out.println("Cannot Format Date " + e1.getMessage());
		e1.printStackTrace();
	}
	java.sql.Date input = null;
	  input = new java.sql.Date(format.getTime());
	return input;
	
}
}
