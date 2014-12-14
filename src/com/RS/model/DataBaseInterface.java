package com.RS.model;

public class DataBaseInterface {
	public UserInformation DBgetUserInfomation(String UserName) {
		return new UserInformation();
	}

	public boolean DBisValidUser(String UserName, String password,UserInformation info) {
		
		return true;
	}

	public DataBaseInterface() {
		
	}
}
