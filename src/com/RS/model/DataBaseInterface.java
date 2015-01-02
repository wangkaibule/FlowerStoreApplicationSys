package com.RS.model;

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
}
