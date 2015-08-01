package com.RS.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wkBule.docFiller.utils.LoadResource;

public abstract class DBConnection {
	private static DataSource pool = null;
	private static Logger log = LoggerFactory.getLogger(DBConnection.class);

	protected DBConnection() {
		if (pool != null) {

		} else {
			PoolProperties property = new PoolProperties();
			Properties p = new Properties();

			try {
				p.load(LoadResource.load("properties/db.properties"));
				pool = (DataSource) Class
				.forName(p.getProperty("RSdbPoolFactory"))
				.getMethod("getDataSource", (Class<?>[]) null)
				.invoke(null, (Object[]) null);
			} catch (IOException e) {
				log.error("errors when try to load file db.properties",e);
			} catch (Exception e){
				log.error("errors when try to load dbPoolFactory",e);
			}

		}
	}

	protected DataSource getPool() {
		return pool;
	}

	protected void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			log.warn("Exception occurred during close the connection:", e);
		}
	}
}
