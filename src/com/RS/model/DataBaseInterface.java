package com.RS.model;

public class DataBaseInterface {
	public CurrentUserInformation DBgetUserInfomation(String UserName) {
		return new CurrentUserInformation();
	}

	public boolean DBisValidUser(String UserName, String password) {
		
		return true;
	}

	public DataBaseInterface() {
		
	}
}
