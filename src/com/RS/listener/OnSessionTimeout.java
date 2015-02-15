package com.RS.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.RS.model.CurrentUserInformation;

/**
 * Application Lifecycle Listener implementation class OnSessionTimeout
 *
 */
@WebListener
public class OnSessionTimeout implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public OnSessionTimeout() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event)  { 
    	HttpSession session = (HttpSession) event.getSession();
    	
    	CurrentUserInformation info = (CurrentUserInformation) session.getAttribute("UserInformation");
    	
    }
	
}
