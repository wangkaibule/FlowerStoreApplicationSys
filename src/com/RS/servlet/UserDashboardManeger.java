package com.RS.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RS.model.CurrentUserInformation;
import com.RS.model.ProjectItem;

/**
 * Implementation manipulations to project files
 * 
 */
@WebServlet("/UserDashboardManeger")
public class UserDashboardManeger extends HttpServlet {
	private static final int RQ_NEW = 0;
	private static final int RQ_MODIFY = 1;
	private static final int RQ_DELETE = 2;
	private static final int RQ_PRINTSELECTION = 3;
	private static final int RQ_VIEWDETAIL = 4;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDashboardManeger() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestMethod = request.getParameter("method");

		if (requestMethod == null) {
			// TODO Auto-generated method stub
			HttpSession session = request.getSession(false);
			CurrentUserInformation UsrInfo = (CurrentUserInformation) session
					.getAttribute("UserInfomation");
			Iterator<ProjectItem> ProjectItems = ProjectItem
					.getUsrProjectIterator(UsrInfo);
			session.setAttribute("ProjectItems", ProjectItems);
			request.getRequestDispatcher("viewUserDashBoard").forward(request,
					response);
		} else {
			switch (Integer.parseInt(requestMethod)) {
			case RQ_DELETE:

			case RQ_MODIFY:

				/*
				 * TODO: . . .
				 */
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * implements the real modify manipulation .
		 */
		String requestMethod = request.getParameter("method");
		if (requestMethod != null) {
			if (Integer.parseInt(requestMethod) == RQ_MODIFY) {
				
			}
		}

	}

	// getters and setters
	public static int getRqNew() {
		return RQ_NEW;
	}

	public static int getRqModify() {
		return RQ_MODIFY;
	}

	public static int getRqDelete() {
		return RQ_DELETE;
	}

	public static int getRqPrintselection() {
		return RQ_PRINTSELECTION;
	}

	public static int getRqViewdetail() {
		return RQ_VIEWDETAIL;
	}

}
