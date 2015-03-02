package com.RS.model;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Hashtable;
import java.util.List;

import org.apache.catalina.realm.NullRealm;

import sun.security.krb5.internal.crypto.NullEType;

import com.RS.model.level.Modifiable;
import com.RS.model.level.Printable;
import com.RS.model.level.Removable;
import com.RS.model.level.Viewable;
import com.sun.accessibility.internal.resources.accessibility;

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
		ResultSet set = null;
		// TODO add project to lib list
		// TODO fetch the projectUID(s) and corresponding access level from
		// database ,if(-1) then return,else
		// check if project exists else fetch project item from database
		// TODO check user access level for every project Item.
		// TODO add the semaphore
		set = db.getUserProjectUID(UsrID);

		try {
			while (set.next()) {
				long id = set.getLong(1);
				ProjectInfo project = projectList.get(id);
				if (project == null) {
					project = db.loadProject(id);
					projectList.put(project.projectUID, project);
				}
				project.inHandleCount++;
				list.add(db.wrapAccessLevel(project, UsrID, isLogged));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e){
			return;
		}
	}

	protected ProjectInfo createProject(int projectType,String userID) {
		ProjectInfo item = null;

		item = TheFactory.getProjectFactory(projectType).create(userID);

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
	
	protected void onUserExit(long projectUID){
		ProjectInfo project = projectList.get(projectUID);
		synchronized (project) {
			project.inHandleCount--;
			if(project.inHandleCount<=0){
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

			try {
				CallableStatement statement = getPool().getConnection()
				.prepareCall(sql);
				statement.setBoolean(1, isLogged);
				statement.setString(2, userID);
				statement.setLong(3, project.projectUID);
				statement.setString(4, accessLevel);
				statement.execute();
				
				result = statement.getResultSet();
				if(result.first()){
					levelSet = StrtBytes(result.getString(1));     
				}else{
					return null;
				}
				
				return wrapLevel(levelSet, project,
				AccessLeveled.posModifiable, AccessLeveled.posRemovable);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

		private byte[] StrtBytes(String str) {
			byte[] result = new byte[str.length()];
			for(int i=0;i<result.length;i++){
				result[i]=Byte.parseByte(str.substring(0, 1));
				str = str.substring(1);
			}
			return result;
		}

		private AccessLeveled wrapLevel(byte[] levelSet, ProjectInfo project,
		int... levels) {
			AccessLeveled result = project;
			for (int i =0;i<levelSet.length;i++) {
				if(levelSet[i]==0){
					continue;
				}
				switch (levels[i]) {
				case AccessLeveled.posModifiable:
					result = new Modifiable(result);
					break;
				case AccessLeveled.posPrintable:
					result = new Printable(result);
					break;
				case AccessLeveled.posRemovable:
					result = new Removable(result);
					break;
				case AccessLeveled.posViewable:
					result = new Viewable(result);
					break;
				}
			}
			return result;
		}

		private ResultSet getUserProjectUID(String usrID) {
			final String sql = "select project from projectMemberRelation where member=?";

			try {
				PreparedStatement statement = getPool().getConnection()
				.prepareStatement(sql);
				statement.setString(1, usrID);
				statement.execute();
				return statement.getResultSet();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

		private ProjectInfo loadProject(long id) {
			int projectType;
			final String sql = "select projectCategory from projectInfo where projectUID=? limit 1";
			PreparedStatement statement = null;
			ResultSet result = null;

			try {
			statement = getPool().getConnection().prepareStatement(sql);
			statement.setLong(1, id);
			statement.execute();
			result = statement.getResultSet();
				if (result.first()) {
					projectType = result.getInt(1);
					return TheFactory.getProjectFactory(projectType).create(id);
				}else{
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
}
