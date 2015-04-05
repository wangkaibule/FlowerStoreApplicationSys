package com.RS.model.project.application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.RS.model.DBConnection;
import com.RS.model.ProjectFactory;
import com.RS.model.ProjectInfo;
import com.RS.model.TeamMemberInfo;
import com.RS.model.project.application.AppProjectContent.Content;

public class ApplicationProject extends ProjectInfo {

	private static final int categoryUndefined = -1;
public static final int categoryCreationTrain = 0;
	public static final int categoryBussinessTrain = 1;
	public static final int categoryBussinessPractice = 2;
	public static final String projectType="AP";
	
	private static Logger log = LoggerFactory.getLogger(ApplicationProject.class);

	private Content.Builder theContent = null;
	private boolean memberModified = false;
	
	public ApplicationProject(){
		
	}

	public ApplicationProject(long projectUID, boolean isNew) {
		pItem = this;
		this.projectUID = projectUID;

		if (!isNew) {
			DB db = new DB();
			try {
				theContent = Content.parseFrom(db.loadContent(projectUID))
				.toBuilder();
			} catch (IOException e) {
				theContent = Content.newBuilder();
				e.printStackTrace();
			}
		} else {
			theContent = Content.newBuilder();
			theContent.setName("新项目");
		}
	}

	public int getCategoryCreationTrain() {
		return categoryCreationTrain;
	}

	public int getCategoryBussinessTrain() {
		return categoryBussinessTrain;
	}

	public int getCategoryBussinessPractice() {
		return categoryBussinessPractice;
	}

	public int getCategoryUndefined() {
		return categoryUndefined;
	}

	public void setMemberModified() {
		memberModified = true;
	}

	@Override
	public String getProjectType() {
		return projectType;
	}

	@Override
	public String getProjectTitle() {

		return theContent.getName();
	}

	@Override
	public String getAuthorName() {
		return theContent.getMembers(0).getName();
	}

	@Override
	public void fillAuthorInfo(TeamMemberInfo info) {
		theContent.addMembers(info.builder());
	}

	@Override
	public Content.Builder getContent() {

		return theContent;
	}

	@Override
	protected void storeContent() {

		if (!modified) {
			return;
		} else {
			DB db = new DB();
			if (memberModified) {
				db.modifyMemberRelationship(theContent.getMembersList(),
				projectUID);
				memberModified = false;
			}
			db.writeContent(theContent, projectUID, theContent.getMembers(0)
			.getStudentID());
			modified = false;
		}
	}

	public Content getViewer() {
		return theContent.build();
	}

	public void deleteTeamMember(int[] index) {
		modified = true;
		memberModified = true;

		for (int i : index) {
			theContent.removeMembers(i);
		}
	}

	@Override
	protected boolean onDeleteProject() {
		DB db = new DB();

		return db.deleteItem(projectUID);
	}

	private static class DB extends DBConnection {

		void modifyMemberRelationship(List<Content.MemberInfo> list,
		long projectUID) {
			final String sql1 = "delete from projectMemberRelation where project=?";
			final String sql = "call addMemberRelationship(?,?,?)";
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement deleteStatement = con.prepareStatement(sql1);

				deleteStatement.setLong(1, projectUID);

				PreparedStatement addRelationStatement = con
				.prepareStatement(sql);

				for (Content.MemberInfo info : list) {
					if (info.hasName() && info.hasStudentID()) {

						addRelationStatement.setString(1, info.getName());
						addRelationStatement.setString(2, info.getStudentID());
						addRelationStatement.setLong(3, projectUID);
						addRelationStatement.addBatch();
					}
				}

				deleteStatement.execute();
				addRelationStatement.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(con);
			}
		}

		boolean writeContent(Content.Builder content, long projectUID,
		String userID) {
			final String sql = "replace into projectData (authorID,projectUID,projectDatacol) value(?,?,?)";
			Connection con = null;

			try {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				content.build().writeTo(stream);
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);

				statement.setString(1, userID);
				statement.setLong(2, projectUID);
				statement.setBlob(3,
				new ByteArrayInputStream(stream.toByteArray()));

				statement.execute();
				return true;
			} catch (Exception e) {
				log.warn("Exception occurred during write project content into DB:",e);
				return false;
			} finally {
				close(con);
			}
		}

		InputStream loadContent(long projectUID) {
			final String sql = "select projectDatacol from projectData where projectUID=? limit 1";
			ResultSet result = null;
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setLong(1, projectUID);
				statement.execute();
				result = statement.getResultSet();
				if (result.first()) {
					return result.getBlob(1).getBinaryStream();
				} else {
					return null;
				}
			} catch (SQLException e) {
				log.warn("Exception occurred during load content from DB:",e);
				return null;
			} finally {
				close(con);
			}
		}

		boolean deleteItem(long projectUID) {
			final String sql = "delete from projectInfo where projectUID=?";
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con
				.prepareStatement(sql);
				statement.setLong(1, projectUID);
				return statement.executeUpdate() > 0;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				close(con);
			}
		}
	}

	public static class Factory implements ProjectFactory {

		@Override
		public ProjectInfo create(String userID) {
			InnerDB db = new InnerDB();
			long projectID = db.uidAlloc(userID);
			if (projectID > 0) {
				ApplicationProject project = new ApplicationProject(projectID,
				true);
				project.setMemberModified();
				project.setModified();
				return project;
			} else {
				return null;
			}
		}

		@Override
		public ProjectInfo create(long projectUID) {

			return new ApplicationProject(projectUID, false);
		}

		private static class InnerDB extends DBConnection {

			long uidAlloc(String userID) {
				final String sql = "insert into projectInfo (authorID,projectCategory) values(?,?)";
				final String sql2 = "select last_insert_id()";
				ResultSet result = null;
				Connection con = null;

				try {
					con = getPool().getConnection();
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, userID);
					statement.setString(2, projectType);

					statement.execute();

					statement = con.prepareStatement(sql2);
					statement.execute();
					result = statement.getResultSet();
					if (result.first()) {
						return result.getLong(1);
					} else {
						return -1;
					}

				} catch (SQLException e) {
					log.error("Exception occurred during allocating a uid for project",e);
					e.printStackTrace();
					return -1;
				} finally {
					close(con);
				}
			}
		}
	}

}
