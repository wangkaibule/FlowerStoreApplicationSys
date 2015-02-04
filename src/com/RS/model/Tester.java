package com.RS.model;

import java.util.ArrayList;

import com.RS.model.AppProjectContent.Content;
import com.RS.model.level.Modifiable;
import com.RS.model.level.Removable;

public class Tester {

	public static void fillProjectListAccessLeveled(
	ArrayList<AccessLeveled> list, String userName) {
		ApplicationProject newP = new ApplicationProject();
		AccessLeveled level = newP;
		Content.Builder content = newP.getContent();

		content.setBackgroundDescription("wangkaibule");
		content.setCategory(ApplicationProject.getCategorycreationtrain());
		content.setEquipmentNeeds("wangkaibuleEquipment");
		content.setFinancialNeed("wangkaibuleFinancial");
		content.setFinishTime("2020-1-1");
		content.setGoalExpectation("wangkaibuleGoal");
		content.setName("wangkaibuleName");
		content.setProjectDescription("wangkaibule");

		list.add(level);

	}

	public static void fillProjectListAccessLeveled(
	ArrayList<AccessLeveled> list, String userName, String pwd) {
		ApplicationProject newP = new ApplicationProject();
		AccessLeveled level = newP;
		Content.Builder content = newP.getContent();
		
		content.setBackgroundDescription("wangkaibule");
		content.setCategory(ApplicationProject.getCategorycreationtrain());
		content.setEquipmentNeeds("wangkaibuleEquipment");
		content.setFinancialNeed("wangkaibuleFinancial");
		content.setFinishTime("2020-1-1");
		content.setGoalExpectation("wangkaibuleGoal");
		content.setName("wangkaibuleName");
		content.setProjectDescription("wangkaibule");

		level = new Removable(level);
		level = new Modifiable(level);
		list.add(level);
	}

	public static ProjectInfo createAppllicationProject() {
		ApplicationProject project = new ApplicationProject();
		Content.Builder content = project.getContent();

		content.setName("A New Application Project");
		project.setProjectUID(0);
		return project;
	}
}
