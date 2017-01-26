package com.axway.academy.iangelov.vacationsystem;

import java.io.IOException;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axway.academy.iangelov.vacationoperations.*;
import com.axway.academy.iangelov.jdbc.NewVacation;


@WebServlet("/RegisterNewVacation")
public class RegisterNewVacation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String name = (String) request.getSession().getAttribute("userId");
		
		Date today = new Date();
		java.sql.Date date = new java.sql.Date(today.getTime());

		ConvertDate cd = new ConvertDate();
		NewVacation nv = NewVacation.getInstance();
		
		String sDate = (request.getParameter("day") + "-" + request.getParameter("month") + "-" + request.getParameter("year"));
		String eDate = (request.getParameter("sday") + "-" + request.getParameter("smonth") + "-" + request.getParameter("syear"));
	
		java.sql.Date startDate = cd.formatToDate(sDate);
		java.sql.Date endDate = cd.formatToDate(eDate);
	
		long difference = nv.calculateDays(startDate, endDate);
		long userDaysleft = nv.getDaysForUser(name);
		
		
		//add check to see if vacationsdays are allready registered;
		
		if((nv.calculateDays(startDate, date)) < 0 ){
			request.setAttribute("userId", name);
			request.setAttribute("errorMessage", "start date is before current date");
			request.getRequestDispatcher("/RegisterNewVacation.jsp").forward(request, response);
		}else if(nv.calculateDays(startDate,endDate) > 0){
			request.setAttribute("errorMessage", "End date is before start date ");
			request.getRequestDispatcher("/RegisterNewVacation.jsp").forward(request, response);
		} else if(-1 * difference > userDaysleft) {
			request.setAttribute("errorMessage", "You have not enough days left");
			request.getRequestDispatcher("/RegisterNewVacation.jsp").forward(request, response);
			
		} else if(nv.RegisterVacation(name, startDate, endDate, difference)){
			request.setAttribute("errorMessage", "vacation successfullyAdded");
			request.getRequestDispatcher("/Registered.jsp").forward(request, response);
		}
		else{
			request.setAttribute("errorMessage","vacation was not added, try again");
			request.getRequestDispatcher("/RegisterNewVacation.jsp").forward(request,response);
		}
	}
}
