package com.RS.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.RS.model.TeamMemberInfo;
import com.RS.model.project.application.AppProjectContent;
import com.RS.model.project.application.AppProjectContent.Content;
import com.RS.model.project.application.AppProjectContent.Content.MemberInfo;
import com.RS.model.project.application.ApplicationProject;

/**
 * Servlet implementation class AppProjectPostReciever
 */
@WebServlet("/AppProjectReciever")
public class AppProjectPostReciever extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppProjectPostReciever() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		if (title == null) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
		} else {
			if (title.equals("memberInfoUpdate")) {
				String id = request.getParameter("id");
				String index = request.getParameter("index");
				Content.Builder content = (Content.Builder) request.getSession(
				false).getAttribute("Content");
				ApplicationProject project = (ApplicationProject) request
				.getSession(false).getAttribute(
				ProjectContentManager.pendingProject);

				List<MemberInfo> list = content.getMembersList();
				for (MemberInfo info : list) {
					if (info.getStudentID().equals(id)) {
						response.sendError(HttpServletResponse.SC_NO_CONTENT);
						return;
					}
				}

				TeamMemberInfo newInfo = new TeamMemberInfo(id);
				Content.MemberInfo.Builder builder = newInfo.builder();

				if (builder != null) {
					content.addMembers(builder);
					project.addMember(id);
				} else {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}

				request.setAttribute("id", id);
				request.setAttribute("profession", newInfo.profession);
				request.setAttribute("department", newInfo.department);
				request.setAttribute("name", newInfo.name);
				request.setAttribute("nextIndex", index);

				request.getRequestDispatcher("formPieces/memberInfoPiece.jsp")
				.forward(request, response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String field = request.getParameter("name");
		String value = request.getParameter("value");
		boolean isSuccess = true;
		AppProjectContent.Content.Builder content = (AppProjectContent.Content.Builder) request
		.getSession(false).getAttribute("Content");

		if (title == null) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

		if (title.equals("teacherUpdate")) {
			isSuccess = invoke(field, value, content.getTeacherBuilder(),
			response);
		} else if (title.equals("tutorUpdate")) {
			isSuccess = invoke(field, value, content.getCompanyTutorBuilder(),
			response);
		} else if (title.equals("memberInfoUpdate")) {
			int index = Integer.parseInt(request.getParameter("index"));
			List<Content.MemberInfo> list = content.getMembersList();
			if (list.size() - 1 < index) {
				// replaced by get method
			} else {
				Content.MemberInfo.Builder builder = content
				.getMembersBuilder(index);
				isSuccess = invoke(field, value, builder, response);
			}
		} else if (title.equals("memberInfoDelete")) {
			String strIndex = request.getParameter("index");
			int index;
			ApplicationProject project = (ApplicationProject) request
			.getSession(false).getAttribute(
			ProjectContentManager.pendingProject);

			index = Integer.parseInt(strIndex);
			project.deleteTeamMember(index);
		}

		if (isSuccess) {
			response.sendError(HttpServletResponse.SC_OK);
		}
		return;
	}

	private boolean invoke(String field, String value, Object builder,
	HttpServletResponse response) throws IOException {
		try {
			field = field.substring(0, 1).toUpperCase() + field.substring(1);
			Method method = builder.getClass().getMethod("set" + field,
			String.class);
			method.invoke(builder, value);

		} catch (NoSuchMethodException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			return false;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
