package com.RS.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.RS.model.AppProjectContent.Content;

public class ApplicationProject extends ProjectInfo {

	private static final int categoryUndefined = -1;
	private static final int categoryCreationTrain = 0;
	private static final int categoryBussinessTrain = 1;
	private static final int categoryBussinessPractice = 2;

	private static final String sqlLoadEntity = "select ....";

	private Content.Builder theContent = null;
	private boolean memberDeleted = false;
	private List<Integer> deletedMemberIndex = null;

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

	public static int getCategoryUndefined() {
		// TODO Auto-generated method stub
		return categoryUndefined;
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
		// TODO Auto-generated method stub
		return theContent.getMembers(0).getName();
	}

	@Override
	public void fillAuthorInfo(TeamMemberInfo info) {
		theContent.addMembers(info.builder());
	}

	@Override
	public Content.Builder getContent() {
		// TODO Auto-generated method stub
		return theContent;
	}

	@Override
	protected void storeContent() {
		if(!modified){
			return;
		}else{
			if(memberDeleted){
				//TODO TO BE CONTINUED tomorrow...
			}
		}
	}

	public void deleteTeamMember(int index) {
		if (deletedMemberIndex == null) {
			modified = true;
			memberDeleted = true;
			deletedMemberIndex = new ArrayList<Integer>();
		}

		deletedMemberIndex.add(index);
	}

	private List<Content.MemberInfo> recreateMemberBuilder() {
		List<Content.MemberInfo> origList = theContent.getMembersList();
		int origListSize = origList.size();
		int deletedListSize = deletedMemberIndex.size();
		List<Content.MemberInfo> deleteList = new ArrayList<Content.MemberInfo>();

		theContent.clearMembers();

		int j = 0;
		for (int i = 0; i < origListSize ; i++) {
			if ( j <= deletedListSize && deletedMemberIndex.get(j) == i) {
				deleteList.add(origList.get(i));
				j++;
				continue;
			} else {
				theContent.addMembers(origList.get(i));
			}
		}
		return deleteList;
	}
}
