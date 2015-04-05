package com.RS.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.RS.model.level.Modifiable;
import com.RS.model.level.NotPrintable;
import com.RS.model.level.NotViewable;
import com.RS.model.level.Removable;

//TODO ALL ProjectItem operates should use this class.

/*
 * the first item (index 0) in theContent.members is the author information.
 * */
class ProjectItemLib {
	private static ProjectItemLib singleton = null;
	private Hashtable<Long, ProjectInfo> projectList = new Hashtable<Long, ProjectInfo>();

	private ProjectItemLib() {

	}

	// TODO ProjectLib should be created by application when application is
	// started.
	protected static ProjectItemLib getProjectLib() {
		if (singleton == null) {
			singleton = new ProjectItemLib();
			return singleton;
		} else {
			return singleton;
		}
	}

	protected ProjectInfo removeFromList(long projectUID) {
		return projectList.remove(projectUID);

	}

	private void addToList(ProjectInfo item) {
		projectList.put(item.projectUID, item);
	}

	protected void getUserProjects(String UsrID, boolean isLogged,
	ArrayList<AccessLeveled> list) {
		DB db = new DB();
		Iterable<Long> set = null;
		// TODO add project to lib list
		// TODO fetch the projectUID(s) and corresponding access level from
		// database ,if(-1) then return,else
		// check if project exists else fetch project item from database
		// TODO check user access level for every project Item.
		// TODO add the semaphore
		set = db.getUserProjectUID(UsrID);

		for (long id : set) {
			ProjectInfo project = projectList.get(id);
			if (project == null) {
				project = db.loadProject(id);
			}
			if (project != null) {
				projectList.put(project.projectUID, project);
				project.inHandleCount++;
				list.add(db.wrapAccessLevel(project, UsrID, isLogged));
			}
		}
	}

	protected ProjectInfo createProject(String projectType, String userID) {
		ProjectInfo item = null;

		item = new TheFactory().getProjectFactory(projectType).create(userID);

		if (item != null) {
			addToList(item);
		}

		return item;
	}

	protected boolean deleteProject(long projectUID) {
		boolean isSuccess = false;
		isSuccess = projectList.remove(projectUID).deleteProject();

		return isSuccess;
	}

	protected void onUserExit(long projectUID) {
		ProjectInfo project = projectList.get(projectUID);
		synchronized (project) {
			project.inHandleCount--;
			if (project.inHandleCount <= 0) {
				projectList.remove(projectUID);
			}
		}
	}

	private static class DB extends DBConnection {
		AccessLeveled wrapAccessLevel(ProjectInfo project, String userID,
		boolean isLogged) {
			final String accessLevel = "isAuthor,modifiable,deletable";
			final String sql = "call testAccessLevelSet(?,?,?,?)";
			byte[] levelSet = null;
			ResultSet result = null;
			Connection con = null;

			try {
				con = getPool().getConnection();
				CallableStatement statement = con.prepareCall(sql);
				statement.setBoolean(1, isLogged);
				statement.setString(2, userID);
				statement.setLong(3, project.projectUID);
				statement.setString(4, accessLevel);
				statement.execute();

				result = statement.getResultSet();
				if (result.first()) {
					levelSet = StrtBytes(result.getString(1));
				} else {
					return project;
				}

				return wrapLevel(levelSet, project,
				AccessLeveled.posModifiable, AccessLeveled.posRemovable);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return project;
			} finally {
				close(con);
			}
		}

		private byte[] StrtBytes(String str) {
			byte[] result = new byte[str.length()];
			for (int i = 0; i < result.length; i++) {
				result[i] = Byte.parseByte(str.substring(0, 1));
				str = str.substring(1);
			}
			return result;
		}

		private AccessLeveled wrapLevel(byte[] levelSet, ProjectInfo project,
		int... levels) {

			AccessLeveled result = project;
			for (int i = 0; i < levelSet.length; i++) {
				if (levelSet[i] == 0) {
					continue;
				}
				switch (levels[i]) {
				case AccessLeveled.posModifiable:
					result = new Modifiable(result);
					break;
				case AccessLeveled.posNotPrintable:
					result = new NotPrintable(result);
					break;
				case AccessLeveled.posRemovable:
					result = new Removable(result);
					break;
				case AccessLeveled.posNotViewable:
					result = new NotViewable(result);
					break;
				}
			}
			return result;
		}

		private Iterable<Long> getUserProjectUID(String usrID) {
			final String sql = "select project from projectMemberRelation where member=?";
			Connection con = null;
			ArrayList<Long> result = new ArrayList<Long>();

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, usrID);
				statement.execute();
				ResultSet set = statement.getResultSet();
				while (set.next()) {
					result.add(set.getLong(1));
				}

				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally {
				close(con);
			}
		}

		private ProjectInfo loadProject(long id) {
			String projectType;
			final String sql = "select projectCategory from projectInfo where projectUID=? limit 1";
			PreparedStatement statement = null;
			ResultSet result = null;
			Connection con = null;

			try {
				con = getPool().getConnection();
				statement = con.prepareStatement(sql);
				statement.setLong(1, id);
				statement.execute();
				result = statement.getResultSet();
				if (result.first()) {
					projectType = result.getString(1);
					return new TheFactory().getProjectFactory(projectType).create(id);
				} else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally {
				close(con);
			}
		}
	}
}
