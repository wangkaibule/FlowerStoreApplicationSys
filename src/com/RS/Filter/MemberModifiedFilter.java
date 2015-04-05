package com.RS.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.RS.model.ProjectInfo;
import com.RS.model.project.application.ApplicationProject;

/**
 * Servlet Filter implementation class MemberModifiedFilter
 */
@WebFilter(urlPatterns={"/AppProjectReciever"})
public class MemberModifiedFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MemberModifiedFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(request instanceof HttpServletRequest){
			HttpServletRequest re = (HttpServletRequest) request;
			ProjectInfo project = (ProjectInfo)re.getSession(false).getAttribute("theProject");
			String title = re.getParameter("title");

			if(project instanceof ApplicationProject&&title!=null&&(title.equals("memberInfoUpdate"))){
				((ApplicationProject)project).setMemberModified();
			}
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
