package com.RS.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import com.RS.model.AccessLeveled;

/**
 * Servlet Filter implementation class ModifiedFilter
 */
@WebFilter(urlPatterns = { "/Modify" })
public class ModifiedFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ModifiedFilter() {
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

		AccessLeveled project = (AccessLeveled) request
		.getAttribute("PendingProject");
		if (project != null) {
			project.getProjectItem().setModified();
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
