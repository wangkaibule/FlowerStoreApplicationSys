package com.RS.model;

import com.RS.model.AppProjectContent.Content;

public class TeamMemberInfo {

	public String	name			= "新成员";
	public String	department		= "-";
	public String	profession		= "-";
	public String	studentID		= "-";
	public String	responsibility	= "-";
	public String	tel				= "-";

	public Content.MemberInfo.Builder builder(){
		return Content.MemberInfo.newBuilder()
		.setName(this.name)
		.setDepartment(this.department)
		.setProfession(this.profession)
		.setResponsibility(this.tel)
		.setStudentID(this.studentID)
		.setTel(this.tel);
	}
}
