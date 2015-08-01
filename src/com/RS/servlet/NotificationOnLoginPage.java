package com.RS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wkBule.docFiller.utils.LoadResource;

import com.RS.model.DBConnection;

public class NotificationOnLoginPage {

	public static final String unit = "px";
	public static final int height = 326;
	public static final int width = 594;
	public static final int fontSize = 20;

	private String content = "";

	public NotificationOnLoginPage() {
		DB db = new DB();
		content = db.loadContent();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		DB db = new DB();
		this.content = content;
		db.setContent(content);
	}

	public String getUnit() {
		return unit;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getFontSize() {
		return fontSize;
	}

	private static class DB extends DBConnection {

		public String loadContent() {
			final String sql = "select content from AppInfo where name=\"notification\"";
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				ResultSet set = null;

				statement.execute();
				set = statement.getResultSet();

				if (set.first()) {
					return set.getString("content");
				} else {
					return "";
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				close(con);
			}
			return null;
		}

		public void setContent(String content) {
			final String sql = "update AppInfo set content=? where name=?";
			Connection con = null;

			try {
				con = getPool().getConnection();
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, content);
				statement.setString(2, "notification");
				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(con);
			}

		}

	}

	public static class HttpClient {
		private String path = "http://localhost:8080/FlowerStoreRsumeSubmitSys/appGetContentForFill";
		private Logger log = LoggerFactory.getLogger(HttpClient.class);
		private String content;

		public HttpClient() {
			Properties p = new Properties();
			try {
				p.load(LoadResource.load("appFiller.properties"));
				String strUrl = p.getProperty("serverURL");
				if (strUrl != null) {
					strUrl += p.getProperty("notifyHttpClient");
					path = strUrl;
				}

			} catch (IOException e) {

				log
				.warn("cannot access appFiller.properties, using default settings...");
			}
		}

		public String getContent() {
			String content = "";
			String url = path + "?method=notify";
			try {
				HttpURLConnection con = (HttpURLConnection) new URL(url)
				.openConnection();
				con.setRequestProperty("Content-Type","charset=utf-8");
				con.setRequestMethod("GET");
				con.connect();
				StringWriter writer = new StringWriter();
				IOUtils.copy(con.getInputStream(), writer);
				content = writer.toString();
				
				con.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.content = content;
			return content;
		}

		public void setContent(String content) {
			String url = path;  
			try {
				url+="?method=notifySet&content="+content;
				HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
				con.setRequestProperty("Content-Type", "charset=utf-8");
				con.setRequestMethod("GET");
				con.connect();
				con.getResponseCode();
				con.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
