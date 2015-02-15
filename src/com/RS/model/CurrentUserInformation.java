package com.RS.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.RS.model.level.Modifiable;
import com.RS.model.level.Removable;

public class CurrentUserInformation {

	// TODO MANAGER wants to loose the limitation about the number of projects
	// of a user.
	private String						userId;
	private String						userName;
	private boolean						inProgress;
	private boolean 					loggedin;
	private ArrayList<AccessLeveled>	projects;

	public CurrentUserInformation(String userId, String password) {
		projects = new ArrayList<AccessLeveled>();
		new DataBaseInterface();

		this.userId = userId;
		this.userName = DataBaseInterface.getUserName(userId);
		this.inProgress = DataBaseInterface.getUserProgressStatus(userId);
		loggedin = true;
		ProjectItemLib lib = ProjectItemLib.getProjectLib();
		lib.getUserProjects(userId,password,projects);
	}

	public CurrentUserInformation(String userId) {

		projects = new ArrayList<AccessLeveled>();
		this.userId = userId;
		this.userName = DataBaseInterface.getUserName(userId);
		loggedin = false;
		ProjectItemLib lib = ProjectItemLib.getProjectLib();
		lib.getUserProjects(userId, null, projects);
	}

	public boolean deleteProjectItem(long projectUID) {
		Iterator<AccessLeveled> tempProjects= projects.iterator();
		boolean isSuccess = false;

		for (int i = 0;  tempProjects.hasNext(); i++) {
			AccessLeveled project = tempProjects.next();

			if (project.getProjectUID() == projectUID) {
				if (project.getLevel().isRemovable()) {
					projects.remove(i);
					ProjectItemLib lib = ProjectItemLib.getProjectLib();
					isSuccess = lib.deleteProject(projectUID);
					break;
				}
			}
		}

		return isSuccess;
	}

	public AccessLeveled addProjectItem(int projectType) {
		AccessLeveled newItem = null; 

		ProjectItemLib lib = ProjectItemLib.getProjectLib();
		ProjectInfo project = lib.createProject(projectType);

		project.fillAuthorInfo(new TeamMemberInfo(userId));
		newItem = new Modifiable(project);
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

	public ArrayList<AccessLeveled> getProjects() {
		return projects;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public AccessLeveled getProjectItem(long ProjectUID) {
		Iterator<AccessLeveled> projectArray = projects.iterator();
		AccessLeveled tempProject =null;

		for (int i = 0; projectArray.hasNext(); i++) {
			tempProject = projectArray.next();
			if (tempProject.getProjectUID() == ProjectUID) {
				return tempProject;
			}
		}
		return null;
	}

	public boolean isInProgress() {
		return inProgress;
	}
	
	public void onDestroy(){
		for(AccessLeveled project:projects){
			project.getProjectItem().updateModification();
		}
	}
}
