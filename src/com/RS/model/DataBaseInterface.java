package com.RS.model;

import java.util.ArrayList;

public class DataBaseInterface {
	
	public CurrentUserInformation DBgetUserInfomation(String UserName) {
		return new CurrentUserInformation();
	}

	public boolean DBisValidUser(String userName, String password) {

		return true;
	}
	
	public boolean isUserNameExit(String regName, String regID){
		String sql = "select * from LeaderTable where name=? and userId"
		return false;
	}
	
	public void executeUpdate(String regName, String regID, String regPassword){
		
	}
	
	public DataBaseInterface() {

	}

	public void getCurrentUserProjectItems(String userId,
			ArrayList<ProjectItem> projects) {

	}

	public void getCurrentUserProjectItems(String userId, String password,ArrayList<ProjectItem> projects) {

	}

	public String getUserName(String userId) {
		return "";
	}
	
	public boolean deleteProjectItem(long projectUID){
		return true;
	}
}
