package com.RS.model;

import java.util.ArrayList;
import java.util.Iterator;

//TODO ALL ProjectItem operates should use this class.

/*
 * Project will be saved in database as many parts.
 * the first parts (no. 0 part) should be the title of projects and the list of team members
 * */
public class ProjectItemLib {
	private static ProjectItemLib singleton = null;
	private ArrayList<ProjectItem> projectList;

	private ProjectItemLib() {

	}

	// TODO ProjectLib should be created by application when application is
	// started.
	public static ProjectItemLib getProjectLib() {
		if (singleton == null) {
			singleton = new ProjectItemLib();
			return singleton;
		} else {
			return singleton;
		}
	}

	private boolean removeFromList(ProjectItem item) {
		return projectList.remove(item);

	}
	
	public boolean removeFromList(int index){
		if(projectList.remove(index)!=null){
			return true;
		}else{
			return false;
		}
	}

	private ProjectItem findProject(long projectUID) {
		Iterator<ProjectItem> i = projectList.iterator();
		ProjectItem item = null;

		while (i.hasNext()) {
			item = i.next();
			if (item.getProjectUID() == projectUID) {
				break;
			}
		}

		return item;
	}
	
	private int findProjectIndex(long projectUID){
		Iterator<ProjectItem> items = projectList.iterator();
		int index = 0;

		while(true){
			if(!items.hasNext()){
				return -1;
			}else{
				if(items.next().getProjectUID()==projectUID){
					return index;
				}else{
					index++;
				}
			}
		}
	}

	private void addToList(ProjectItem item) {
		projectList.add(item);
	}

	// TODO functions below are not completed .
	public boolean update(long projectUID, int part) {

		return DataBaseInterface.updatePart(findProject(projectUID), part);
	}

	public ProjectItem getProject(long projectUID) {
		ProjectItem item = findProject(projectUID);

		if (item == null) {
			item = DataBaseInterface.getProjectItem(projectUID);

			if (item == null) {
				return null;
			} else {
				addToList(item);
			}
		}

		return item;
	}

	public ProjectItem createProject(int projectType) {
		ProjectItem item = null;

		item = DataBaseInterface.createProject(projectType);
		if (item != null) {
			addToList(item);
		}

		return item;
	}

	public boolean saveProject(long projectUID) {
		ProjectItem item = findProject(projectUID);
		boolean isSuccess = true;

		isSuccess &= DataBaseInterface.updatePart(item);
		isSuccess &= removeFromList(item);
		
		return isSuccess;
	}

	public boolean deleteProject(long projectUID) {
		int index=findProjectIndex(projectUID);
		boolean isSuccess = true;
		
		isSuccess&=DataBaseInterface.deleteProjectItem(projectUID);
		isSuccess&=removeFromList(index);
		
		return isSuccess;
	}
}
