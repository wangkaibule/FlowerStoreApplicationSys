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

import com.RS.model.AppProjectContent;
import com.RS.model.AppProjectContent.Content;

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
		//response.sendError(HttpServletResponse.SC_NO_CONTENT);
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String field = request.getParameter("name");
		String value = request.getParameter("value");
		boolean isSuccess = true;
		AppProjectContent.Content.Builder content = (AppProjectContent.Content.Builder) request
		.getSession(false).getAttribute("Content");

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
				Content.MemberInfo.Builder builder = Content.MemberInfo
				.newBuilder();
				builder.setName("新成员")
				.setDepartment("-")
				.setProfession("-")
				.setStudentID("-")
				.setResponsibility("-")
				.setTel("-");

				content.addMembers(builder);
			} else {
				Content.MemberInfo.Builder builder = content.getMembersBuilder(index);
				isSuccess = invoke(field, value, builder, response);
			}
		}

		if (isSuccess) {
			response.sendError(HttpServletResponse.SC_OK);
		}
		return;
	}

	private boolean invoke(String field, String value, Object builder,
	HttpServletResponse response) throws IOException {
		try {
			field = field.substring(0,1).toUpperCase()+field.substring(1);
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
