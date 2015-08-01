package com.RS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RS.model.ProjectInfo;

/**
 * Servlet implementation class TestInvalidUser
 */
@WebServlet("/exit")
public class InvalidUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InvalidUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String invalidUser = request.getParameter("invalidUser");
		if (invalidUser == null) {
			ProjectInfo project = (ProjectInfo) session
			.getAttribute(ProjectContentManager.pendingProject);
			project.updateProject();
			response.sendRedirect(response.encodeRedirectURL("./DashBoard"));
			return;
		} else {
			session.invalidate();
			response.sendRedirect("./Login");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_NO_CONTENT);
	}

}
