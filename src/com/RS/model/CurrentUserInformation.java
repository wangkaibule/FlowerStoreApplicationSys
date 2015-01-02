package com.RS.model;

import java.util.ArrayList;

import com.RS.model.level.Modifiable;
import com.RS.model.level.Removable;

public class CurrentUserInformation implements java.io.Serializable {

	private String userId;
	private String userName;
	private boolean inProgress;
	private ArrayList<AccessLeveled> projects;

	public CurrentUserInformation(String userId, String password) {
		projects = new ArrayList<AccessLeveled>();
		new DataBaseInterface();

		
		this.userId = userId;
		this.userName = DataBaseInterface.getUserName(userId);
		this.inProgress = DataBaseInterface.getUserProgressStatus(userId);
		DataBaseInterface.getCurrentUserProjectItems(userId, password, projects);
	}

	public CurrentUserInformation(String userId) {

		projects = new ArrayList<AccessLeveled>();
		this.userId = userId;
		this.userName = DataBaseInterface.getUserName(userId);
		DataBaseInterface.getCurrentUserProjectItems(userId, projects);
		

	}

	public boolean deleteProjectItem(long projectUID) {
		AccessLeveled[] projectArray = (AccessLeveled[]) projects.toArray();
		boolean isSuccess = false;
		new DataBaseInterface();

		for (int i = 0; i < projectArray.length; i++) {
			if (projectArray[i].getProjectUID() == projectUID) {
				if (projectArray[i].getLevel().isRemovable()) {
					isSuccess = DataBaseInterface.deleteProjectItem(projectUID);
					break;
				}
			}
		}

		return isSuccess;
	}

	public AccessLeveled addProjectItem() {
		AccessLeveled newItem = new ProjectItem();
		
		newItem = new Modifiable(newItem);
		newItem = new Removable(newItem);
		projects.add(newItem);
		return newItem;		
	}

	// make this class to be able to become a JavaBean

	// getters
	public String getUserName() {

		return userName;
	}

	public String getUserId() {

		return userId;
	}

	public ProjectItem[] getProjects() {
		return (ProjectItem[])projects.toArray();
	}

	public AccessLeveled getProjectItem(long ProjectUID) {
		ProjectItem[] projectArray = (ProjectItem[])projects.toArray();
		
		for(int i=0;i<projectArray.length;i++){
			if(projectArray[i].getProjectUID() == ProjectUID){
				return (AccessLeveled)projectArray[i];
			}
		}
		return null;
	}

	public boolean isInProgress() {
		return inProgress;
	}
	
	// setters ... actually I don't want any others outside of this class to
	// modify anything.

}
