package com.RS.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wkBule.docFiller.utils.LoadResource;
import wkBule.docFiller.utils.MessageDescription.ContentOntheWire;

import com.RS.model.DBConnection;
import com.google.protobuf.ByteString;

public class SocketFillerServer implements Runnable {
	private Date date = null;
	private static Logger log = LoggerFactory.getLogger(SocketFillerServer.class);
	private int port = 1234;
	

	public SocketFillerServer(){
		Properties property = new Properties();
		try {
			property.load(LoadResource.load("FillerServer.properties"));
		} catch (IOException e) {
			log.error("Cannot access settings file named FillerServer.properties",e);
		}
		port = Integer.parseInt(property.getProperty("port"));
	}

	public void setStartYear(java.util.Date date) {
		this.date = new Date(date.getTime());
	}

	@Override
	public void run() {
		DB db = new DB();
		ResultSet set = null;
		Socket client = null;
		InputStream clientIn = null;
		OutputStream clientOut = null;
		final byte NEXT = 1;

		try(ServerSocket server = new ServerSocket(port)) {
			set = db.getStreamedDataset(date);
			client = server.accept();
			clientIn = client.getInputStream();
			clientOut = client.getOutputStream();
			
			while(clientIn.read()==NEXT){
				ContentOntheWire.Builder contentInfo = ContentOntheWire.newBuilder();
				if(set.next()){
					String cat = set.getString("projectCategory");
					byte[] data = set.getBytes("projectDatacol");
					
					contentInfo.setEOT(false);
					contentInfo.setType(cat);
					contentInfo.setContent(ByteString.copyFrom(data));
					
					contentInfo.build().writeDelimitedTo(clientOut);
					clientOut.flush();
				}else{
					contentInfo.setEOT(true);
					
					contentInfo.build().writeDelimitedTo(clientOut);
					clientOut.flush();
					db.close();
					break;
				}
			}
			
		} catch (SQLException | IOException e) {
			db.close();
		}

	}

	private class DB extends DBConnection {
		Connection con = null;
		ResultSet getStreamedDataset(Date date) throws SQLException{
			final String sql = "select projectDatacol,projectCategory from "
			+ "projectInfo inner join projectData on projectInfo.projectUID=projectData.projectUID"
			+ " where projectInfo.startTime >= ?";
			con = getPool().getConnection();
			PreparedStatement statement = con.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
			statement.setDate(1,date);
			statement.setFetchSize(Integer.MIN_VALUE);
			statement.execute();
			return statement.getResultSet();
		}
		
		void close(){
			close(con);
		}

		 int getCount(Date date) {
			 final String sql="select count(*) from projectInfo where startTime >= ?";
			 Connection con = null;
			 
			 try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setDate(1, date);
				statement.execute();
				ResultSet result = statement.getResultSet();
				if(result.first()){
					return result.getInt(1);
				}
				return 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			} finally{
				close(con);
			}
			 
		}
	}

	public int getCount() {
		DB db = new DB();
		return db.getCount(date);
	}
}
