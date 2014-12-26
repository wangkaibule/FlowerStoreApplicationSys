package com.RS.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.ResultSet;

/**
 * 
 * @author wangkaibule
 * the class holds a project infomation ,in order to add,modify,delete the specific 
 * project in database
 *
 */
public class ProjectItem {
	private String ProjectFileId;
	private ArrayList<TeamMemberInfo> memInfo;
	private String projectUID;
	private String projectSummary;
	private String subject;
	//private projectContent content;

	public ProjectItem() {
		ProjectFileId = "";
		memInfo = new ArrayList<TeamMemberInfo>();
	}
	
	public void fillInfoWithDBset(ResultSet set){
		//set.next()
	}
	
	public static Iterator<ProjectItem> getUsrProjectIterator(CurrentUserInformation info){
		return new ArrayList<ProjectItem>().iterator();
	}

	//getters and setters 
	public String getProjectFileId() {
		return ProjectFileId;
	}

	public void setProjectFileId(String projectFileId) {
		ProjectFileId = projectFileId;
	}
	
	public Iterator<TeamMemberInfo> getMemInfo() {
		return memInfo.iterator();
	}

	public void setMemInfo(TeamMemberInfo memInfo) {
		this.memInfo.add(memInfo);
	}

	public String getProjectUID() {
		return projectUID;
	}

	private void setProjectUID(String projectUID) {
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
	
	
}
