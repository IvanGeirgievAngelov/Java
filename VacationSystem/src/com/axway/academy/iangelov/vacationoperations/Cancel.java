package com.axway.academy.iangelov.vacationoperations;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axway.academy.iangelov.jdbc.CancelVacations;
import com.axway.academy.iangelov.jdbc.ViewRegisteredVacations;
/**
 * Servlet implementation class CancelVacation
 */
@WebServlet("/Cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CancelVacations cv  = CancelVacations.getInstance();
    private ViewRegisteredVacations view = ViewRegisteredVacations.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cancel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getSession().getAttribute("userId");
		if(cv.cancelVacations(request.getParameter("ID"),name)){
			request.setAttribute("errorMessage","vacation successfully canceled ");
			request.getRequestDispatcher("/ViewRegisteredVacations.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
