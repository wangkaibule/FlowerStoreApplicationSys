package com.RS.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RS.model.AppProjectContent.Content;

public class TeamMemberInfo {

	public String	name			= "新成员";
	public String	department		= "-";
	public String	profession		= "-";
	public String	studentID		= "-";
	public String	responsibility	= "-";
	public String	tel				= "-";

	public TeamMemberInfo(String userId) {
		DB db = new DB();
		db.fillInfo(this, userId);
	}
	
	public TeamMemberInfo(){
		
	}

	public Content.MemberInfo.Builder builder(){
		return Content.MemberInfo.newBuilder()
		.setName(this.name)
		.setDepartment(this.department)
		.setProfession(this.profession)
		.setResponsibility(this.tel)
		.setStudentID(this.studentID)
		.setTel(this.tel);
	}
	
	private static class DB extends DBConnection{
		void fillInfo(TeamMemberInfo info,String userId){
			final String sql ="select * from `memberInfo` where memberID=?";
			ResultSet result = null;
			
			try {
				PreparedStatement statement = getPool().getConnection().prepareStatement(sql);
				statement.setString(1, userId);
				statement.execute();
				result = statement.getResultSet();
				
				if(result.first()){
					info.name=result.getString("memberRealName");
					info.studentID=result.getString("memberID");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
