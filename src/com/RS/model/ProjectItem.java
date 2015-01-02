package com.RS.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.ResultSet;

/**
 * 
 * the class holds a project information ,
 * in order to add,
 * modify,delete the specific 
 * project in database
 *
 */
public class ProjectItem implements AccessLeveled{
	 protected ArrayList<TeamMemberInfo> memInfo;
	 protected long projectUID;
	 protected String subject;
	//private projectContent content;

	public ProjectItem() {
		//FIXME new ProjectItem should be created only during specific time range.
		
		//TODO if in some conditions I don't want to create a real new projectItem(not in the right time ,etc.) I should set the projectUID to -1L .
		  
		projectUID = 0;
		memInfo = new ArrayList<TeamMemberInfo>();
		subject ="";
	}
	
	public void fillInfoWithDBset(ResultSet set){
		//set.next()
	}
	
	public static Iterator<ProjectItem> getUsrProjectIterator(CurrentUserInformation info){
		return new ArrayList<ProjectItem>().iterator();
	}

	
	public static ProjectItem createProject(){
		return new ProjectItem();
		//TODO:fill this place with real code
	}
	
	public static ProjectItem getProject(long ProjectUID){
		return new ProjectItem();
		//TODO:fill this place with real code
	}
	
	public  boolean DeleteProject(long ProjectUID){
		return true;
	}
	
	//getters and setters 

	
	public Iterator<TeamMemberInfo> getMemInfo() {
		return memInfo.iterator();
	}

	public void setMemInfo(TeamMemberInfo memInfo) {
		this.memInfo.add(memInfo);
	}

	public long getProjectUID() {
		return projectUID;
	}

	private void setProjectUID(long projectUID) {
		this.projectUID = projectUID;
	}

	public String getProjectSummary() {
		return projectSummary;
	}

	public void setProjectSummary(String projectSummary) {
		this.projectSummary = projectSummary;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public AccessLevel getLevel() {
		
		return new AccessLevel(this);
	}

	@Override
	public ProjectItem getProjectItem() {
		return this;
	}
	
	
}
