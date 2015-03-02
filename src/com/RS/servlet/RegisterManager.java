package com.RS.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.RS.model.DBConnection;

/**
 * Servlet implementation class RegisterManager
 */
@WebServlet("/Register")
public class RegisterManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String regName = "RegName";
	private static final String regId = "RegID";
	private static final String regPassword = "RegPassword";
	private static final int statusUserNX = 0;
	private static final String formRealNameID="realName";
	private static final String formIDid="schoolID";
	private static final String formPWDid="pwd";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		getServletContext().setAttribute("Register", this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("RegisterPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("GB18030");
		String registerUserName = (String) request.getParameter(formRealNameID);
		String registerID = (String) request.getParameter(formIDid);
		String registerPassword = (String) request.getParameter(formPWDid);

		DB db = new DB();

		if (db.isValidRegister(registerUserName, registerID))
		{
			db.registerUser(registerID, registerPassword);
			response.sendRedirect("Login?status="+LoginManager.statusRegSuccessful);
			return;
		}
		else {
			response.sendRedirect("Register?status="+statusUserNX);
			return;
		}
	}

	private class DB extends DBConnection {

		boolean isValidRegister(String realName, String userId) {
			final String sql = "select memberRealName from memberInfo where memberID=?";
			try {
				PreparedStatement statement = getPool().getConnection().prepareStatement(sql);
				statement.setString(1, userId);
	
				statement.execute();
				ResultSet result = statement.getResultSet();
				if(result.first()){
					return result.getString(1).equals(realName);
				}else{
					return false;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		boolean registerUser(String userId, String pwd) {
			final String sql = "UPDATE `memberInfo` set memberPWD=? where memberID=?";
			int result = 0;

			try {
				PreparedStatement statement = getPool().getConnection()
				.prepareStatement(sql);
				statement.setString(2, userId);
				statement.setString(1, pwd);
				result = statement.executeUpdate();

				if (result > 0) {
					return true;
				} else {
					return false;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}

	public String getRegname() {
		return regName;
	}

	public String getRegid() {
		return regId;
	}

	public String getRegpassword() {
		return regPassword;
	}

	public int getStatusUserNX() {
		return statusUserNX;
	}

	public String getFormRealNameID() {
		return formRealNameID;
	}

	public String getFormIDid() {
		return formIDid;
	}

	public String getFormPWDid() {
		return formPWDid;
	}
}
