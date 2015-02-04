package com.RS.model;

import java.util.ArrayList;
import java.util.Iterator;

import com.RS.model.AppProjectContent.Content;

public class ApplicationProject extends ProjectInfo {

	private static final int categoryUndefined = -1;
	private static final int categoryCreationTrain = 0;
	private static final int categoryBussinessTrain = 1;
	private static final int categoryBussinessPractice = 2;

	private static final String sqlLoadEntity = "select ....";

	private ArrayList<TeamMemberInfo> members = new ArrayList<TeamMemberInfo>();
	private Content.Builder theContent = null;

	public ApplicationProject() {
		pItem = this;
		theContent = Content.newBuilder();
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

	@Override
	public int getProjectType() {
		return projectTypeApplication;
	}

	@Override
	public String getProjectTitle() {

		return theContent.getName();
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return members.get(0).getName();
	}

	@Override
	public void fillAuthorInfo(TeamMemberInfo info) {
		members.add(info);

	}

	@Override
	public Content.Builder getContent() {
		// TODO Auto-generated method stub
		return theContent;
	}

	public TeamMemberInfo addTeamMember() {
		TeamMemberInfo newInfo = new TeamMemberInfo();
		members.add(newInfo);

		return newInfo;
	}

	public void deleteTeamMember(int index) {
		members.remove(index);
	}
}
