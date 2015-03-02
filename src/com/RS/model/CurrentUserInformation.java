package com.RS.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.RS.model.level.Modifiable;
import com.RS.model.level.Removable;

public class CurrentUserInformation {

	// TODO MANAGER wants to loose the limitation about the number of projects
	// of a user.
	private String userId;
	private String userName;
	private boolean inProgress;
	private boolean loggedin;
	private ArrayList<AccessLeveled> projects;

	public CurrentUserInformation(String userId, boolean loggedin) {
		projects = new ArrayList<AccessLeveled>();
		DB db = new DB();

		this.userId = userId;
		this.userName = db.getUserName(userId);
		this.inProgress = db.getUserProgressStatus(userId);
		this.loggedin = loggedin;
		ProjectItemLib lib = ProjectItemLib.getProjectLib();
		lib.getUserProjects(userId, loggedin, projects);
	}
	

	public boolean deleteProjectItem(long projectUID) {
		Iterator<AccessLeveled> tempProjects = projects.iterator();
		boolean isSuccess = false;

		for (int i = 0; tempProjects.hasNext(); i++) {
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
		ProjectInfo project = lib.createProject(projectType,userId);

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

		for (AccessLeveled project : projects) {

			if (project.getProjectUID() == ProjectUID) {
				return project;
			}
		}
		return null;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public void onDestroy() {
		for (AccessLeveled project : projects) {
			project.getProjectItem().updateOnUsrExit();
		}
	}

	private static class DB extends DBConnection {

		public String getUserName(String userId) {
			final String sql = "select memberRealName from `memberInfo` where memberID=? limit 1";
			ResultSet result = null;

			try {
				PreparedStatement statement = getPool().getConnection()
				.prepareStatement(sql);
				statement.setString(1, userId);
				statement.execute();
				result = statement.getResultSet();

				if (result.first()) {
					return result.getString(1);
				} else {
					return null;
				}
			} catch (SQLException e) {

				e.printStackTrace();
				return null;
			}
		}

		public boolean getUserProgressStatus(String userId) {
			final String sql = "select isInProgress from `memberInfo` where memberID=? limit 1";
			ResultSet result = null;

			try {
				PreparedStatement statement = getPool().getConnection()
				.prepareStatement(sql);
				statement.setString(1, userId);
				statement.execute();
				result = statement.getResultSet();

				if (result.first()) {
					return result.getString(1) == "Y" ? true : false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

	}
}
