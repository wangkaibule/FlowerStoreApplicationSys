package com.RS.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.RS.model.*;

/**
 * Servlet implementation class LoginManager
 */
@WebServlet("/Login")
public class LoginManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String formUserNameId = "UserN";
	private static final String formPasswordId = "Password";
	private static final String formTypeId = "formType";
	private static final String formRealNameId = "realName";
	private static final int formTypeManage = 1;
	private static final int formTypeViewer = 0;
	public static final int statusRegSuccessful = 1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
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
		DB db = new DB();

		switch (formType) {
		case formTypeManage:

			String formPassword = request.getParameter(formPasswordId);

			if (formPassword != null && formUserName != null
			&& db.isValidUser(formUserName, formPassword)) {
				UsrInfo = new CurrentUserInformation(formUserName, true);
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
			&& db
			.isUserNameExist(formUserRealName, formUserName)) {

				UsrInfo = new CurrentUserInformation(formUserName,false);
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

	private static class DB extends DBConnection {
		private DataSource pool = null;

		DB() {
			pool = getPool();
		}

		private boolean isValidUser(String userID, String userPWD) {
			final String sql = "call isValidUser(?,?,?)";

			try {
				CallableStatement statement = pool.getConnection().prepareCall(
				sql);
				statement.registerOutParameter(3, Types.INTEGER);
				statement.setString(1, userID);
				statement.setString(2, userPWD);

				statement.execute();

				return statement.getBoolean(3);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		private boolean isUserNameExist(String realName, String userID) {
			final String sql = "select count(*) from memberInfo where memberRealName=? and memberID = ? limit 1";
			ResultSet result = null;

			try {
				PreparedStatement statement = pool.getConnection()
				.prepareStatement(sql);
				statement.setString(1, realName);
				statement.setString(2, userID);

				statement.execute();
				result = statement.getResultSet();

				return result.first();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
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

	public int getStatusRegSuccessful() {
		return statusRegSuccessful;
	}
}
