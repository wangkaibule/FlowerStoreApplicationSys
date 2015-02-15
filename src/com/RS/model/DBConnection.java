package com.RS.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public abstract class DBConnection {
	private static DataSource pool = null;

	protected DBConnection() {
		if (pool != null) {

		} else {
			PoolProperties property = new PoolProperties();
			Properties p = new Properties();

			try {
				property.setUrl(p.getProperty("url"));
				property.setUrl("jdbc:mysql://85.10.205.173:3306/wangkaibuletest");
				property.setDriverClassName("com.mysql.jdbc.Driver");
				property.setUsername("wangkaibule");
				property.setPassword("wangkai713");
				property.setJmxEnabled(true);
				property.setTestWhileIdle(false);
				property.setTestOnBorrow(true);
				property.setValidationQuery("SELECT 1");
				property.setTestOnReturn(false);
				property.setValidationInterval(30000);
				property.setTimeBetweenEvictionRunsMillis(30000);
				property.setMaxActive(100);
				property.setInitialSize(10);
				property.setMaxWait(10000);
				property.setRemoveAbandonedTimeout(60);
				property.setMinEvictableIdleTimeMillis(30000);
				property.setMinIdle(10);
				property.setLogAbandoned(true);
				property.setRemoveAbandoned(true);
				property.setJdbcInterceptors(
				"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
				"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
				DataSource datasource = new DataSource();
				pool = new DataSource();
				pool.setPoolProperties(property);
				// property.setDbProperties(p);
				// p.load(getClass().getResourceAsStream(
				// "/com/properties/db.properties"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected DataSource getPool() {
		return pool;
	}
}
