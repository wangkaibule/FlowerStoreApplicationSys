package com.RS.model;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import sun.security.pkcs11.Secmod.DbMode;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javafx.beans.property.adapter.JavaBeanProperty;
import javafx.scene.chart.PieChart.Data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

public class CurrentUserInformation implements java.io.Serializable {

	private String userId;
	private String userName;
	private ArrayList<ProjectItem> projects;

	public CurrentUserInformation(String userId, String password) {
		projects = new ArrayList<ProjectItem>();
		DataBaseInterface db = new DataBaseInterface();

		this.userId = userId;
		this.userName = db.getUserName(userId);
		db.getCurrentUserProjectItems(userId, password, projects);
	}

	public CurrentUserInformation(String usrName) {

		projects = new ArrayList<ProjectItem>();
		DataBaseInterface db = new DataBaseInterface();

		this.userId = userId;
		this.userName = db.getUserName(userId);
		db.getCurrentUserProjectItems(userId, projects);

	}

	public boolean deleteProjectItem(long projectUID) {
		ProjectItem[] projectArray = (ProjectItem[]) projects.toArray();
		boolean isSuccess = false;
		DataBaseInterface db = new DataBaseInterface();

		for (int i = 0; i < projectArray.length; i++) {
			if (projectArray[i].getProjectUID() == projectUID) {
				if (((AccessLeveled) projectArray[i]).getLevel().isRemovable()) {
					isSuccess = db.deleteProjectItem(projectUID);
					break;
				}
			}
		}

		return isSuccess;
	}

	public ProjectItem addProjectItem() {
		return new ProjectItem();
		// TODO:also add this project object to the info projects arraylists.
	}

	// make this class to be able to become a JavaBean

	// getters
	public String getUserName() {

		return userName;
	}

	public String getUserId() {

		return userId;
	}

	public ArrayList<ProjectItem> getProjects() {
		return projects;
	}

	public ProjectItem getProjectItem(long ProjectUID) {
		return new ProjectItem();
		// TODO:add real code in this method
	}
	// setters ... actually I don't want any others outside of this class to
	// modify anything.

}
