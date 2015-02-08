package com.RS.model;

import java.util.ArrayList;
import java.util.Iterator;

//TODO ALL ProjectItem operates should use this class.

/*
 * Project will be saved in database as many parts.
 * the first parts (no. 0 part) should be the title of projects and the list of team members
 * */
public class ProjectItemLib {
	private static ProjectItemLib	singleton	= null;
	private ArrayList<ProjectInfo>	projectList	= new ArrayList<ProjectInfo>();

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

	private boolean removeFromList(ProjectInfo item) {
		return projectList.remove(item);

	}

	public void removeFromList(int index) {
		if (index > -1) {
			projectList.remove(index);
		} else {
			return;
		}
	}

	private ProjectInfo findProject(long projectUID) {
		Iterator<ProjectInfo> i = projectList.iterator();
		ProjectInfo item = null;

		while (i.hasNext()) {
			item = i.next();
			if (item.getProjectUID() == projectUID) {
				break;
			}
		}

		return item;
	}

	private int findProjectIndex(long projectUID) {
		Iterator<ProjectInfo> items = projectList.iterator();
		int index = 0;

		while (true) {
			if (!items.hasNext()) {
				return -1;
			} else {
				if (items.next().getProjectUID() == projectUID) {
					return index;
				} else {
					index++;
				}
			}
		}
	}

	private void addToList(ProjectInfo item) {
		projectList.add(item);
	}

	// TODO functions below are not completed .
	public boolean update(long projectUID, int part) {

		return DataBaseInterface.updatePart(findProject(projectUID), part);
	}

	public ProjectInfo getProject(long projectUID) {
		ProjectInfo item = findProject(projectUID);

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

	public void getUserProjects(String UsrName, String pwd,
	ArrayList<AccessLeveled> list) {
		String sql = "select ....";
		// TODO add project to lib list
		// TODO fetch the projectUID(s) and corresponding access level from
		// database ,if(-1) then return,else
		// check if project exists else fetch project item from database
		// TODO check user access level for every project Item.
		if (pwd == null) {
			Tester.fillProjectListAccessLeveled(list, UsrName);
		} else {
			Tester.fillProjectListAccessLeveled(list, UsrName, pwd);
		}
	}

	public ProjectInfo createProject(int projectType) {
		ProjectInfo item = null;

		item = TheFactory.getProjectFactory(projectType).create();
		
		if (item != null) {
			addToList(item);
		}

		return item;
	}

	public boolean saveProject(long projectUID) {
		ProjectInfo item = findProject(projectUID);
		boolean isSuccess = true;

		isSuccess &= DataBaseInterface.updatePart(item);
		isSuccess &= removeFromList(item);

		return isSuccess;
	}

	public boolean deleteProject(long projectUID) {
		int index = findProjectIndex(projectUID);
		boolean isSuccess = true;

		isSuccess &= DataBaseInterface.deleteProjectItem(projectUID);
		removeFromList(index);

		return isSuccess;
	}
}
