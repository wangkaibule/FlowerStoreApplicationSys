package com.RS.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RS.model.AccessLeveled;
import com.RS.model.ApplicationProject;
import com.RS.model.ProjectInfo;

/**
 * Servlet implementation class ProjectContentManager
 */
@WebServlet("/Modify")
public class ProjectContentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected static final String pendingProject="theProject";
	private static final int RQ_EDITRETURN = 1;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectContentManager() {
		super();
		// TODO Auto-generated constructor stub

	}

	public void init(){
		getServletContext().setAttribute("ContentManager", this);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccessLeveled leveledProject = (AccessLeveled) request
		.getAttribute("PendingProject");

		if (leveledProject == null || !leveledProject.getLevel().isModifiable()) {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		ProjectInfo project = leveledProject.getProjectItem();
		long projectUID = project.getProjectUID();
		int projectType = project.getProjectType();
		HttpSession session = request.getSession(false);


		if (projectUID < 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			switch (projectType) {
			case ProjectInfo.projectTypeApplication:
				session.setAttribute("Content", project.getContent());
				session.setAttribute(pendingProject, project);
				project.setModified();
				response.sendRedirect(response.encodeRedirectURL("ApplicationEditor.jsp"));
				break;
			case ProjectInfo.projectTypeSummary:
				request.getRequestDispatcher("SummaryContentViewer").forward(
				request, response);
				break;

			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		String update = request.getParameter("update");
		
		if (update==null) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}
		HttpSession session = request.getSession(false);
		ProjectInfo project = (ProjectInfo)session.getAttribute(pendingProject);
		project.updateProject();
		return;
	}
	
	public int getProjectTypeApplication(){
		return ProjectInfo.projectTypeApplication;
	}
	
	public int getProjectTypeSummary(){
		return ProjectInfo.projectTypeSummary;
	}
	
	public int getProjectCategoryCreationTrain(){
		return ApplicationProject.getCategorycreationtrain();
	}
	
	public int getProjectCategoryBussinessTrain(){
		return ApplicationProject.getCategorybussinesstrain();
	}
	
	public int getProjectCategoryBussinessPractice(){
		return ApplicationProject.getCategorybussinesspractice();
	}
	
	public int getProjectCategoryUndefined(){
		return ApplicationProject.getCategoryUndefined();
	}
}
