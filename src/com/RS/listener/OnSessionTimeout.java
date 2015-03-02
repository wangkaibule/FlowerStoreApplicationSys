package com.RS.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.RS.model.CurrentUserInformation;

/**
 * Application Lifecycle Listener implementation class OnSessionTimeout
 *
 */
@WebListener
public class OnSessionTimeout implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public OnSessionTimeout() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	Object o = event.getValue();
    	if (o instanceof CurrentUserInformation) {
    		((CurrentUserInformation)o).onDestroy();
		}
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
