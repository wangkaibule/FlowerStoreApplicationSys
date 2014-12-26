package com.RS.model;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import sun.security.pkcs11.Secmod.DbMode;

import com.RS.model.MPC;
import com.sun.org.apache.regexp.internal.recompile;

import javafx.beans.property.adapter.JavaBeanProperty;
import javafx.scene.chart.PieChart.Data;

import java.sql.ResultSet;

public class CurrentUserInformation implements java.io.Serializable {

	private String UserName;
	private String UserId;
	private boolean isAurthorized;
	private boolean isLogged;

	public void setUserInformation(ResultSet DataBaseResultSet)
			throws SQLException {
		final int writable = MPC.UserAurthorization_Writable;
		final int readonly = MPC.UserAurthorization_ReadOnly;

		if (!DataBaseResultSet.first()) {
			throw new SQLException();
		} else {
			UserName = DataBaseResultSet
					.getString(MPC.DBTableColumnLable_UserName);
			UserId = DataBaseResultSet.getString(MPC.DBTableColumnLable_UserId);

			switch (DataBaseResultSet
					.getInt(MPC.DBTableColumnLable_UserAurthorization)) {
			case writable:
				isAurthorized = true;
				break;
			case readonly:
				isAurthorized = false;
				break;
			default:
				isAurthorized = false;
			}

			isLogged = false;

		}
	}

	public CurrentUserInformation(String usrName, String password) {

	}

	public CurrentUserInformation(String usrName) {

	}

	// make this class to be able to become a JavaBean
	public CurrentUserInformation() {
		this.UserId = "";
		this.UserName = "";
		this.isAurthorized = false;
	}

	// getters
	public String getUserName() {

		return UserName;
	}

	public String getUserId() {

		return UserId;
	}

	public boolean isWritable() {

		return isAurthorized;
	}

	public boolean isLogged() {
		return isLogged;
	}

	// setters ... actually I don't want any others outside of this class to
	// modify anything.

}
