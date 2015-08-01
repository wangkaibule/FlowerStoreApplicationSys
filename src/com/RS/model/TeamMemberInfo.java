package com.RS.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.RS.model.project.application.AppProjectContent.Content;

public class TeamMemberInfo {

	public String name = "新成员";
	public String department = "-";
	public String profession = "-";
	public String studentID = "-";
	public String responsibility = "-";
	public String tel = "-";

	private boolean isInitiate = false;

	public TeamMemberInfo(String userId) {
		DB db = new DB();
		isInitiate = db.fillInfo(this, userId);
	}

	public TeamMemberInfo() {

	}

	public Content.MemberInfo.Builder builder() {
		if(isInitiate){
		return Content.MemberInfo.newBuilder().setName(this.name)
		.setDepartment(this.department).setProfession(this.profession)
		.setResponsibility(this.tel).setStudentID(this.studentID)
		.setTel(this.tel);
		}else{
			return null;
		}
	}

	private static class DB extends DBConnection {
		boolean fillInfo(TeamMemberInfo info, String userId) {
			final String sql = "select * from `memberInfo` where memberID=?";
			ResultSet result = null;
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, userId);
				statement.execute();
				result = statement.getResultSet();

				if (result.first()) {
					info.name = result.getString("memberRealName");
					info.studentID = result.getString("memberID");
					DB db = new DB();
					info.department = db.getDepartment(info.studentID);
					info.profession = db.getProfession(info.studentID);
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				close(con);
			}
		}

		String getDepartment(String id) {
			final String p = "\\d{4}(\\d\\d)\\d{6}";
			final String sql = "select departmentName from departmentInfo where departmentID=?";

			Matcher m = Pattern.compile(p).matcher(id);
			String departmentID = null;
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				if (m.matches()) {
					departmentID = m.group(1);
				} else {
					departmentID = "";
				}
				statement.setString(1, departmentID);
				statement.execute();
				ResultSet set = statement.getResultSet();

				if (set.first()) {
					return set.getString(1);
				} else {
					return "-";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "-";
			} finally {
				close(con);
			}

		}

		String getProfession(String id) {
			final String p = "\\d{4}(\\d\\d)(\\d\\d)\\d{4}";
			final String sql = "select professionName from professionInfo where department=? and professionID=?";

			Matcher m = Pattern.compile(p).matcher(id);
			String departmentID = null;
			String professionID = null;
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				if (m.matches()) {
					departmentID = m.group(1);
					professionID = m.group(2);
				} else {
					departmentID = "";
					professionID = "";
				}
				statement.setString(1, departmentID);
				statement.setString(2, professionID);
				statement.execute();
				ResultSet set = statement.getResultSet();

				if (set.first()) {
					return set.getString(1);
				} else {
					return "-";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "-";
			} finally {
				close(con);
			}
		}
	}
}
