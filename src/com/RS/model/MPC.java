package com.RS.model;

/** 
 * business logic static constants 
 * */

final public class MPC implements java.io.Serializable{//Model Public Constants
	static final public String DBTableColumnLable_UserName = "Name";
	static final public String DBTableColumnLable_Password = "Passwd";
	static final public String DBTableColumnLable_UserId = "UserId";
	static final public String DBTableColumnLable_UserAurthorization = "AccessLevel";
	
	static final public int UserAurthorization_Writable = 1;
	static final public int UserAurthorization_ReadOnly = 0;
	
	
	static final public String sessionUsrInfo = "UserInformation";
}
