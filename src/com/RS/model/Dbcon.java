/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.RS.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Dbcon {
	Properties props = new Properties();
	FileInputStream fis =null;
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int i = 0;
    
    public void getStatement(String sql){
        try{
        	fis = new FileInputStream("db.properties");
        	props.load(fis);
        	
            Class.forName(props.getProperty("DB_DRIVER_CLASS")).newInstance();
            con = DriverManager.getConnection(props.getProperty("DB_URL"),
                    props.getProperty("DB_USERNAME"),
                    props.getProperty("DB_PASSWORD"));
            stmt = con.prepareStatement(sql);         
        }
        catch(IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
            System.out.println("数据库连接失败！！" + ex.getMessage());
        }
    }
    
    /**
     * 执行sql语句的数据库查询
     * @param sql
     * @return
     */
    public ResultSet executeQuery(String sql, String... p){
    	int j = 1;
    	getStatement(sql);
        try{
        	for(String count : p){
        		stmt.setString(j++, count);
        	}
            rs = stmt.executeQuery();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("数据库查询失败" + ex.getMessage());
        }
        
        return rs;
    }
    
    /**
     * 执行sql语句的数据库更新
     */
    public int executeUpdate(String sql, String...p){
    	int j = 1;
    	getStatement(sql);
        try{
        	for(String count : p){
        		stmt.setString(j++, count);
        	}
            i = stmt.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("数据库更新失败" + ex.getMessage());
        }
        
        return i;
    }
    
    /**
     *  关闭数据库连接
     */
    public void Close(){
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dbcon.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
