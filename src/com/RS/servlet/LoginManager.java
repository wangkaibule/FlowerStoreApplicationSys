package com.RS.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.RS.model.CurrentUserInformation;
import com.RS.model.DBConnection;

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
		HttpSession session = request.getSession(false);
		if (session == null) {
			// TODO Auto-generated method stub
			request.getRequestDispatcher("LoginPage.jsp")
			.forward(request, response);
		}else{
			response.sendRedirect(response.encodeRedirectURL("DashBoard"));
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
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
					&& db.isUserNameExist(formUserRealName, formUserName)) {

				UsrInfo = new CurrentUserInformation(formUserName, false);
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
			Connection con =null;

			try {
				con = pool.getConnection();
				CallableStatement statement = con.prepareCall(sql);
				statement.registerOutParameter(3, Types.INTEGER);
				statement.setString(1, userID);
				statement.setString(2, userPWD);

				statement.execute();

				return statement.getBoolean(3);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				close(con);
			}
		}

		private boolean isUserNameExist(String realName, String userID) {
			final String sql = "select memberRealName from memberInfo where memberID = ? limit 1";
			ResultSet result = null;
			Connection con = null;

			try {
				con = pool.getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, userID);

				statement.execute();
				result = statement.getResultSet();

				if (result.first()) {
					return result.getString(1).equals(realName);
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				close(con);
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
