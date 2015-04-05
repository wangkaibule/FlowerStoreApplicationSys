package com.RS.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DBConnection {
	private static DataSource pool = null;
	private static Logger log = LoggerFactory.getLogger(DBConnection.class);

	protected DBConnection() {
		if (pool != null) {

		} else {
			PoolProperties property = new PoolProperties();
			Properties p = new Properties();

			try {
				property.setUrl(p.getProperty("url"));
				property.setUrl("jdbc:mysql://localhost/wangkaibuletest?useUnicode=true&characterEncoding=UTF-8");
				property.setDriverClassName("com.mysql.jdbc.Driver");
				property.setUsername("root");
				property.setPassword("wangkaibule");
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
	
	protected void close(Connection con){
			try {
				con.close();
			} catch (SQLException e) {
				log.warn("Exception occurred during close the connection:",e);
			}
	}
}
