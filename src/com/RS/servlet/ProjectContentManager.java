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
import com.RS.model.ProjectInfo;

/**
 * Servlet implementation class ProjectContentManager
 */
@WebServlet("/Modify")
public class ProjectContentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected static final String pendingProject = "theProject";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProjectContentManager() {
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
		AccessLeveled leveledProject = (AccessLeveled) request
		.getAttribute("PendingProject");

		if (leveledProject == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		ProjectInfo project = leveledProject.getProjectItem();
		long projectUID = project.getProjectUID();
		HttpSession session = request.getSession(false);

		if (projectUID < 0) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		} else {
				Object viewFlag = request.getAttribute("isView");
				String arguments = "?arg";

				if (viewFlag instanceof Boolean && ((boolean) viewFlag)) {
					session.setAttribute("Content", project.getViewer());
					arguments += "&isView=true";
				} else {
					if(!leveledProject.getLevel().isModifiable()){
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						return;
					}
					session.setAttribute("Content", project.getContent());
				}

				session.setAttribute(pendingProject, project);
				project.setModified();
				response.sendRedirect(response
				.encodeRedirectURL(project.getEditorPage()+arguments));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_NO_CONTENT);
		return;
	}


	@WebFilter(urlPatterns = { "/DashBoard" })
	public static class ContentFilter implements Filter{
		public void destroy() {

		}

		public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
			HttpServletRequest re = (HttpServletRequest) request;
			HttpSession session = re.getSession(false);
			Object content = session.getAttribute("Content");
			Object viewFlag = session.getAttribute("isView");
			
			if (content != null) {
				session.removeAttribute("Content");
			}
			
			if(viewFlag != null){
				session.removeAttribute("isView");
			}
			chain.doFilter(request, response);
		}

		public void init(FilterConfig fConfig) throws ServletException {

		}
	}
}
