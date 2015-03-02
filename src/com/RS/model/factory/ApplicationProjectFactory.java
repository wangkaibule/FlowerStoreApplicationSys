package com.RS.model.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RS.model.ApplicationProject;
import com.RS.model.DBConnection;
import com.RS.model.ProjectInfo;
import com.RS.model.Tester;

public class ApplicationProjectFactory implements ProjectFactory {

	@Override
	public ProjectInfo create(String userID) {
		// TODO Auto-generated method stub
		// return new ApplicationProject();
		DB db = new DB();
		long projectID = db.uidAlloc(userID);
		if(projectID > 0 ){
			ApplicationProject project = new ApplicationProject(projectID,true);
			project.setMemberModified();
			project.setModified();
			return project;
		}else{
			return null;
		}
	}

	@Override
	public ProjectInfo create(long projectUID) {

		return new ApplicationProject(projectUID,false);
	}

	private static class DB extends DBConnection {
		long uidAlloc(String userID) {
			final String sql = "insert into projectInfo (authorID,projectCategory) values(?,?)";
			final String sql2 = "select last_insert_id()";
			ResultSet result = null;
			
			try {
				Connection con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, userID);
				statement.setInt(2, ProjectInfo.projectTypeApplication);
				
				statement.execute();

				statement = con.prepareStatement(sql2);
				statement.execute();
				result = statement.getResultSet();
				if(result.first()){
					return result.getLong(1);
				}else{
					return -1;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
	}
}
