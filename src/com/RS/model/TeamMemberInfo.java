package com.RS.model;

public class TeamMemberInfo {

	private String	name			= "";
	private String	department		= "";
	private String	profession		= "";
	private String	studentID		= "";
	private String	responsibility	= "";
	private String	tel				= "";

	public TeamMemberInfo() {

	}
	
	public TeamMemberInfo(String userID){
		
	}

	public TeamMemberInfo(long projectUID) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
