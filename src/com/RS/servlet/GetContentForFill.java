package com.RS.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.RS.socket.SocketFillerServer;

/**
 * Servlet implementation class GetContentForFill
 * servlet for manager app 
 */
@WebServlet("/appGetContentForFill")
public class GetContentForFill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetContentForFill() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method == null) {
			response.sendError(HttpServletResponse.SC_NO_CONTENT);
			return;
		}
		if (method.equals("content")) {
			String date = request.getParameter("startTime");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			int count = 0;
			try {
				d = formatter.parse(date);
			} catch (ParseException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,
				"Date format error, the correct format is yyyy-MM-dd");
				return;
			}
			SocketFillerServer filler = new SocketFillerServer();
			filler.setStartYear(d);
			count = filler.getCount();
			Thread th = new Thread(filler);
			th.start();
			response.setContentType("text/html");
			new DataOutputStream(response.getOutputStream()).writeInt(count);
			return;
		} else if (method.equals("notify")) {
			NotificationOnLoginPage notify = new NotificationOnLoginPage();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(notify.getContent());
			return;
		} else if (method.equals("notifySet")) {
			NotificationOnLoginPage notify = new NotificationOnLoginPage();
			String content = request.getParameter("content");
			notify.setContent(content);
			response.sendError(HttpServletResponse.SC_OK);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
	}

}
