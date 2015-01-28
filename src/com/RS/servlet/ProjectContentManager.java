package com.RS.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.RS.model.ProjectItem;
import com.RS.model.ProjectItemLib;

/**
 * Servlet implementation class ProjectContentManager
 */
@WebServlet("/ProjectContentManager")
public class ProjectContentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int pTypeApplication = 0;
	


	private static final int pTypeSummary = 1;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectContentManager() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProjectItem project = (ProjectItem)request.getAttribute("PendingProject");
		long projectUID = project.getProjectUID();
		int projectType = (int)request.getAttribute("PendingProjectType");
		
		if(projectUID< 0 ){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}else{
			switch (projectType) {
			case pTypeApplication:
				request.getRequestDispatcher("ApplicationContentViewer").forward(request, response);
				break;
			case pTypeSummary:
				request.getRequestDispatcher("SummaryContentViewer").forward(request, response);
				break;

			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//try to save the part of project that just posted,according the post parameter ProjectUID
		//I am thinking that no problems because of thread synchronization would happen.
		
		long projectUID = Long.parseLong(request.getParameter("ProjectUID"));
		int part = Integer.parseInt(request.getParameter("part"));
		
		ProjectItemLib lib = ProjectItemLib.getProjectLib();
		
		if(lib.update(projectUID, part)){
			response.sendError(HttpServletResponse.SC_OK);
			return;
		}else{
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}

	//getters 
	public static int getPtypeapplication() {
		return pTypeApplication;
	}

	public static int getPtypesummary() {
		return pTypeSummary;
	}

}
