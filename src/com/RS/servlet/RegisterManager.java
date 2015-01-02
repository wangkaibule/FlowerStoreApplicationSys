package com.RS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.RS.model.DataBaseInterface;


/**
 * Servlet implementation class RegisterManager
 */
@WebServlet("/RegisterManager")
public class RegisterManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("RegisterPage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String registerUserName = (String)request.getParameter("RegName");
		String registerID = (String)request.getParameter("RegID");
		String registerPassword = (String)request.getParameter("RegPassword");
		
		DataBaseInterface dbi = new DataBaseInterface();
		
		if(dbi.isUserNameExit(registerUserName, registerID))
		{
			response.sendRedirect("RegisterPage?status=UsrNameExit");
		}
		else{
			dbi.executeUpdate(registerUserName, registerID, registerPassword);
			response.sendRedirect("LoginPage?status=RegSuccess");
		}
	}

}
