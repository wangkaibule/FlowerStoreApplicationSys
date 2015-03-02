package com.RS.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RS.model.AppProjectContent.Content;

public class ApplicationProject extends ProjectInfo {

	private static final int categoryUndefined = -1;
	private static final int categoryCreationTrain = 0;
	private static final int categoryBussinessTrain = 1;
	private static final int categoryBussinessPractice = 2;

	private Content.Builder theContent = null;
	private boolean memberModified = false;
	private List<Integer> deletedMemberIndex = null;

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

	public static int getCategorycreationtrain() {
		return categoryCreationTrain;
	}

	public static int getCategorybussinesstrain() {
		return categoryBussinessTrain;
	}

	public static int getCategorybussinesspractice() {
		return categoryBussinessPractice;
	}

	public static int getCategoryUndefined() {
		return categoryUndefined;
	}

	public void setMemberModified() {
		memberModified = true;
	}

	@Override
	public int getProjectType() {
		return projectTypeApplication;
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
			}
			db.writeContent(theContent, projectUID, theContent.getMembers(0)
			.getStudentID());
		}
	}

	public void deleteTeamMember(int index) {
		if (deletedMemberIndex == null) {
			modified = true;
			memberModified = true;
			deletedMemberIndex = new ArrayList<Integer>();
		}

		deletedMemberIndex.add(index);
	}
	
	public void commitMemberChange(){
		List<Content.MemberInfo> deletedList = recreateMemberBuilder();
		DB db = new DB();
		
		db.deleteMemberRelationship(deletedList, projectUID);
	}

	private List<Content.MemberInfo> recreateMemberBuilder() {
		List<Content.MemberInfo> origList = theContent.getMembersList();
		int origListSize = origList.size();
		int deletedListSize = deletedMemberIndex.size();
		List<Content.MemberInfo> deleteList = new ArrayList<Content.MemberInfo>();

		theContent.clearMembers();

		int j = 0;
		for (int i = 0; i < origListSize; i++) {
			if (j <= deletedListSize && deletedMemberIndex.get(j) == i) {
				deleteList.add(origList.get(i));
				j++;
				continue;
			} else {
				theContent.addMembers(origList.get(i));
			}
		}
		return deleteList;
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
			final String sql = "call addMemberRelationship(?,?)";
			try {
				CallableStatement statement = getPool().getConnection()
				.prepareCall(sql1);
				
				statement.setLong(1, projectUID);
				statement.addBatch(sql);

				for (Content.MemberInfo info : list) {
					statement.setString(1, info.getStudentID());
					statement.setLong(2, projectUID);
					statement.addBatch();
				}

				statement.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		void deleteMemberRelationship(List<Content.MemberInfo> list,
		long projectUID) {
			final String sql = "call deleteMemberRelationship(?,?)";

			try {
				CallableStatement statement = getPool().getConnection()
				.prepareCall(sql);

				for (Content.MemberInfo info : list) {
					statement.setString(1, info.getStudentID());
					statement.setLong(2, projectUID);
					statement.addBatch();
				}

				statement.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		void writeContent(Content.Builder content, long projectUID,
		String userID) {
			final String sql = "replace into projectData (authorID,projectUID,projectDatacol) value(?,?,?)";

			try {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				content.build().writeTo(stream);
				PreparedStatement statement = getPool().getConnection()
				.prepareStatement(sql);

				statement.setString(1, userID);
				statement.setLong(2, projectUID);
				statement.setBlob(3,
				new ByteArrayInputStream(stream.toByteArray()));

				statement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		InputStream loadContent(long projectUID) {
			final String sql = "select projectDatacol from projectData where projectUID=? limit 1";
			ResultSet result = null;

			try {
				PreparedStatement statement = getPool().getConnection()
				.prepareStatement(sql);
				statement.setLong(1, projectUID);
				statement.execute();
				result = statement.getResultSet();
				if (result.first()) {
					return result.getBlob(1).getBinaryStream();
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		boolean deleteItem(long projectUID) {
			final String sql = "delete from projectInfo where projectUID=?";
			try {
				PreparedStatement statement = getPool().getConnection()
				.prepareStatement(sql);
				statement.setLong(1, projectUID);
				return statement.executeUpdate()>0;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}
