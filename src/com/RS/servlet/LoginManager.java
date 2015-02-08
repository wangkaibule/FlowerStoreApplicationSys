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
@WebServlet("/Login")
public class LoginManager extends HttpServlet {
	private static final long	serialVersionUID	= 1L;

	private static final String		formUserNameId		= "UserN";
	private static final String		formPasswordId		= "Password";
	private static final String		formTypeId			= "formType";
	private static final String		formRealNameId		= "realName";
	private static final int			formTypeManage		= 1;
	private static final int			formTypeViewer		= 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		try {
			super.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getServletContext().setAttribute("LoginManager", this);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("LoginPage.jsp")
		.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String formUserName = request.getParameter(formUserNameId);
		String strFormType = request.getParameter(formTypeId);

		CurrentUserInformation UsrInfo = null;

		if (strFormType == null) {
			request.getRequestDispatcher("LoginPage.jsp").forward(request,
			response);
			return;
		}

		int formType = Integer.parseInt(strFormType);
		switch (formType) {
		case formTypeManage:

			String formPassword = request.getParameter(formPasswordId);

			if (formPassword != null && formUserName != null
			&& DataBaseInterface.DBisValidUser(formUserName, formPassword)) {
				UsrInfo = new CurrentUserInformation(formUserName, formPassword);
			} else {
				request.setAttribute("invalidPWD", true);
				request.getRequestDispatcher("LoginPage.jsp").forward(request,
				response);
				return;
			}
			break;

		case formTypeViewer:
			String formUserRealName = request.getParameter(formRealNameId);

			if (formUserName != null && formUserRealName != null
			&& DataBaseInterface
			.isUserNameExit(formUserRealName, formUserName)) {

				UsrInfo = new CurrentUserInformation(formUserName);
			} else {
				request.setAttribute("invalidID", true);
				request.getRequestDispatcher("LoginPage.jsp").forward(request,
				response);
				return;
			}
			break;

		default:
			request.getRequestDispatcher("LoginPage.jsp").forward(request,
			response);
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("UserInformation", UsrInfo);
		response.sendRedirect(response.encodeRedirectURL("./DashBoard"));
		return;
	}

	public String getFormusernameid() {
		return formUserNameId;
	}

	public String getFormpasswordid() {
		return formPasswordId;
	}

	public String getFormrealnameid() {
		return formRealNameId;
	}

	public String getFormtypeid() {
		return formTypeId;
	}

	public int getFormtypemanage() {
		return formTypeManage;
	}

	public int getFormtypeviewer() {
		return formTypeViewer;
	}
}
