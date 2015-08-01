package com.RS.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.RS.model.CurrentUserInformation;

/**
 * Servlet Filter implementation class ValidateLoginFilter
 */
@WebFilter(urlPatterns = { "/*" }, filterName = "isLoginFilter", initParams = { @WebInitParam(name = "patterns", value = "(.*)/pic/.*,(.*)/Login,(.*)/Register,(.*)/js/.*,.*/css/.*,.*/appGetContentForFill") })
public class ValidateLoginFilter implements Filter {
	private static String[] patterns;

	/**
	 * Default constructor.
	 */
	public ValidateLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
	FilterChain chain) throws IOException, ServletException {

		String url = ((HttpServletRequest) request).getRequestURL().toString();
		boolean isMatch = false;
		if (patterns != null) {

			for (String pattern : patterns) {
				isMatch |= url.matches(pattern);
			}
		}
		if (!isMatch) {
			HttpSession session = ((HttpServletRequest) request)
			.getSession();
			if (!(session.getAttribute("UserInformation") instanceof CurrentUserInformation)) {

				((HttpServletResponse) response).sendError(
				HttpServletResponse.SC_UNAUTHORIZED, "Re-login please!!");
				session.invalidate();
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		patterns = fConfig.getInitParameter("patterns").split(",");
	}

}
