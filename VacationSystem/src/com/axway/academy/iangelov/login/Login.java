package com.axway.academy.iangelov.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.axway.academy.iangelov.encryption.EncryptPassword;
import com.axway.academy.iangelov.jdbc.AccountCheck;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountCheck ac = AccountCheck.getInstance();
	private EncryptPassword ep = EncryptPassword.getInstance();
    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uName = request.getParameter("userName");
		String uPass = request.getParameter("userPass");
		String encryptedPass = ep.encrypt(uPass);
		if(ac.checkIfUserExist(uName, encryptedPass) && uName != "" && uPass != ""){
			request.getSession().setAttribute("userId",uName);
			request.setAttribute("userId", uName);
			request.getRequestDispatcher("/Registered.jsp").forward(request,response);
		}
		else{
			request.setAttribute("errorMessage", "Incorrect username or password");
			request.getRequestDispatcher("/Login.jsp").forward(request,response);
		}
	}

}
