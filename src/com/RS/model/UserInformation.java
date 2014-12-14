package com.RS.model;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import sun.security.pkcs11.Secmod.DbMode;

import com.RS.model.MPC;
import com.sun.org.apache.regexp.internal.recompile;

import javafx.scene.chart.PieChart.Data;

import java.sql.ResultSet;

public class UserInformation implements java.io.Serializable{

	private String UserName;
	private String UserId;
	private boolean isAurthorized;
	private boolean isLogged;
	
	
	public UserInformation(ResultSet DataBaseResultSet) throws SQLException{
		final int writable = MPC.UserAurthorization_Writable;
		final int readonly = MPC.UserAurthorization_ReadOnly;
		
		if(!DataBaseResultSet.first()){
			throw new SQLException();
		}else{
			UserName = DataBaseResultSet.getString(MPC.DBTableColumnLable_UserName);
			UserId = DataBaseResultSet.getString(MPC.DBTableColumnLable_UserId);
			
			switch(DataBaseResultSet.getInt(MPC.DBTableColumnLable_UserAurthorization)){
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
	
	public UserInformation(){
		this.UserId ="";
		this.UserName="";
		this.isAurthorized = false;
	}
	
	public String getUserName(){
	
		return UserName;
	}
	
	public String getUserId(){
		
		return UserId;
	}
	public boolean isWritable(){
		
		return isAurthorized;
	}
	
	public boolean FillInfoWith(UserInformation TempInfo){

			this.isAurthorized = TempInfo.isAurthorized;
			if(TempInfo.UserId != null){
				this.UserId = TempInfo.UserId;
			}else return false;
			
			if(TempInfo.UserName != null){
				this.UserName = TempInfo.UserName;
			}else return false;
			
			return true;
		
	}
	
	public void setAsLogged(){
		this.isLogged = true;
	}
}
