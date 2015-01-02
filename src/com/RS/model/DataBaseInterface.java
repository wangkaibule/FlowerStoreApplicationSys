package com.RS.model;

import java.util.ArrayList;

public class DataBaseInterface {
	

	public boolean DBisValidUser(String UserName, String password) {

		return true;
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
