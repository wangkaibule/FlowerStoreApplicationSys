package com.RS.model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseInterface {
	
	static public void executePSQL(String sql,String ... parameters){
		;
	}
	
	static public CurrentUserInformation DBgetUserInfomation(String UserName) {
		return new CurrentUserInformation(UserName);
	}

	static public boolean DBisValidUser(String userName, String password) {

		return true;
	}
	
	static public boolean isUserNameExit(String regName, String regID){
		String sql = "select * from LeaderTable where name=? and userId";
		return true;
	}
	
	static public void executeUpdate(String regName, String regID, String regPassword){
		
	}
	
	public DataBaseInterface() {

	}

	static public void getCurrentUserProjectItems(String userId,
			ArrayList<AccessLeveled> projects) {

	}

	static public void getCurrentUserProjectItems(String userId, String password,ArrayList<AccessLeveled> projects) {

	}

	static public String getUserName(String userId) {
		return "";
	}
	
	static public boolean deleteProjectItem(long projectUID){
		return true;
	}
	
	static public boolean getUserProgressStatus(String userId){
		return true ;
	}
	static public boolean updatePart(ProjectInfo project,int part){
		
		String sql="SELECT ";
		return true;
	}

	public static ProjectInfo getProjectItem(long projectUID) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ProjectInfo createProject(int projectType) {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean updatePart(ProjectInfo projectItem) {
		// TODO Auto-generated method stub
		return false;
	}
}
