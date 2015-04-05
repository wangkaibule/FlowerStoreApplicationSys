package com.RS.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RS.model.AccessLeveled;
import com.RS.model.CurrentUserInformation;
import com.RS.model.ProjectInfo;

/**
 * Implementation manipulations to project files
 * 
 */
// TODO DASHBOARD viewer should help manager to identify the type of project by
// set the PendingProjectType request parameter.

@WebServlet("/DashBoard")
public class UserDashboardManager extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RQ_NEW = 0;
	private static final int RQ_MODIFY = 1;
	private static final int RQ_DELETE = 2;
	private static final int RQ_PRINTSELECTION = 3;
	private static final int RQ_VIEWDETAIL = 4;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDashboardManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		getServletContext().setAttribute("DashBoard", this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String strRequestMethod = request.getParameter("method");
		CurrentUserInformation info = (CurrentUserInformation) session
		.getAttribute("UserInformation");

		if (strRequestMethod == null) {

			request.setAttribute("usrProjectItems", info.getProjects());
			request.getRequestDispatcher("DashBoardPage.jsp").forward(request,
			response);

		} else {
			String strProjectUID = request.getParameter("ProjectUID");
			int method = Integer.parseInt(strRequestMethod);
			AccessLeveled pendingProject = null;

			if (strProjectUID != null) {
				long ProjectUID = Long.parseLong(strProjectUID);

				switch (method) {
				case RQ_DELETE:
					info.deleteProjectItem(ProjectUID);
					response.sendRedirect(response
					.encodeRedirectURL("DashBoard"));
					return;
				case RQ_MODIFY:
					pendingProject = info.getProjectItem(ProjectUID);
					request.setAttribute("PendingProject", pendingProject);
					request.getRequestDispatcher("Modify")
					.forward(request, response);
					return;
				case RQ_PRINTSELECTION:
					pendingProject = info.getProjectItem(ProjectUID);
					request.setAttribute("PendingProject", pendingProject);
					request.getRequestDispatcher("ProjectPrintManager")
					.forward(request, response);
					return;
				case RQ_VIEWDETAIL:
					pendingProject = info.getProjectItem(ProjectUID);
					request.setAttribute("PendingProject", pendingProject);
					request.setAttribute("isView", true);
					request.getRequestDispatcher("Modify").forward(
					request, response);
					return;
				default:
					response.sendError(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				switch (method) {
				case RQ_NEW:
					String strProjectType = request.getParameter("ProjectType");
					pendingProject = info.addProjectItem(strProjectType);

					response.sendRedirect(response
					.encodeRedirectURL("DashBoard"));

					return;
				}
				response.sendError(HttpServletResponse.SC_NO_CONTENT);
			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		CurrentUserInformation info = (CurrentUserInformation) session
		.getAttribute("UserInformation");
		String strRequestMethod = request.getParameter("method");
		String[] CheckedProjects = request
		.getParameterValues("CheckedProjects");

		if (strRequestMethod != null && CheckedProjects != null) {

			switch (Integer.parseInt(strRequestMethod)) {
			case RQ_DELETE:
				for (int i = 0; i < CheckedProjects.length; i++) {
					info.deleteProjectItem(Long.parseLong(CheckedProjects[i]));
				}
				response.sendRedirect(response.encodeRedirectURL("DashBoard"));

				return;
			case RQ_PRINTSELECTION:
				ArrayList<AccessLeveled> pendingProjects = new ArrayList<AccessLeveled>();
				AccessLeveled tempP = null;

				for (int i = 0; i < CheckedProjects.length; i++) {
					tempP = info.getProjectItem(Long
					.parseLong(CheckedProjects[i]));
					pendingProjects.add(tempP);
				}

				request.getRequestDispatcher("ProjectPrintManager").forward(
				request, response);
				return;
			default:
				response.sendError(HttpServletResponse.SC_NO_CONTENT);
				return;
			}

		} else {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

	}

	// getters and setters
	public int getRqNew() {
		return RQ_NEW;
	}

	public int getRqModify() {
		return RQ_MODIFY;
	}

	public int getRqDelete() {
		return RQ_DELETE;
	}

	public int getRqPrintselection() {
		return RQ_PRINTSELECTION;
	}

	public int getRqViewdetail() {
		return RQ_VIEWDETAIL;
	}
	
	public List<?> getProjectLists(){
		return ProjectInfo.getDefinedProjects();
	}
}
