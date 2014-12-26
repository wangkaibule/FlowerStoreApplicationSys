package com.RS.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RS.model.*;

/**
 * Servlet implementation class LoginManager
 */
@WebServlet("/LoginManager")
public class LoginManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("LoginPage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext servContext = request.getServletContext();
		
		String formUserNameId = "UserN";
		String formPasswordId = "Password";
		
		String formUserName = (String)request.getParameter(formUserNameId);
		String formPassword = (String)request.getParameter(formPasswordId);
		
		CurrentUserInformation UsrInfo=null;
		DataBaseInterface DB = new DataBaseInterface();
		
		
		if(formPassword == null || formPassword.length()==0){
			if(formUserName == null || formUserName.length() == 0){
				response.sendRedirect("LoginPage?status=emptyUsrN");
			}else{
				UsrInfo = new CurrentUserInformation(formUserName);
				
			}
		}else{

			UsrInfo = new CurrentUserInformation();
			if(!DB.DBisValidUser(formUserName,formPassword)){
				response.sendRedirect("LoginPage?status=LoginErr");
			}else{
				UsrInfo = new CurrentUserInformation(formUserName, formPassword);
			}
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("UserInformation", UsrInfo);
		response.sendRedirect(response.encodeRedirectURL("/DashBoard"));
	}

}
