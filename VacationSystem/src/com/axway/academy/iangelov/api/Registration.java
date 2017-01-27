package com.axway.academy.iangelov.api;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axway.academy.iangelov.jdbc.AddUser;
//import com.axway.academy.iangelov.jdbc.PrintTable;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final String regex_email = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	private static final long serialVersionUID = 1L;
	private AddUser  au = AddUser.getInstance();
	//private PrintTable pt = PrintTable.getInstance();
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		
		String userName = request.getParameter("userId");
		String userPass = request.getParameter("pass");
		String userEmail = request.getParameter("email");
		
		if(userName == ""){
			request.setAttribute("errorMessage", "Username cannot be empty");
			request.getRequestDispatcher("/Register.jsp").forward(request,response);
		}
		if(userPass == ""){
			request.setAttribute("errorMessage", "password cannot be empty");
			request.getRequestDispatcher("/Register.jsp").forward(request,response);
		}
		if(userEmail == ""){
			request.setAttribute("errorMessage", "email cannot be empty");
			request.getRequestDispatcher("/Register.jsp").forward(request,response);
		}
		
		if(!userEmail.matches(regex_email)){
			request.setAttribute("errorMessage", "Email is no valid");
			request.getRequestDispatcher("/Register.jsp").forward(request,response);
			
			//days for annual vacations of user have to be managed other way;
		}else if(au.addNewAccount(userName, userPass, userEmail,45)){
			
			request.getSession().setAttribute("userId", userName);
			request.setAttribute("userId", userName);
			request.getRequestDispatcher("/Registered.jsp").forward(request, response);
		}
		else{
			out.println("This user allready exist");
			request.setAttribute("errorMessage", "This user allready exists");
			request.getRequestDispatcher("/Register.jsp").forward(request,response);
			out.close();
			
		


			//doGet(request, response);
		}
	}
}
