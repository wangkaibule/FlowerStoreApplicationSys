package com.RS.model;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wkBule.docFiller.utils.LoadResource;

public class DBPoolFactory {
	private static Logger log = LoggerFactory.getLogger(DBPoolFactory.class);

	public static DataSource getDataSource() {
		PoolProperties property = new PoolProperties();
			org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
			property
			.setUrl("jdbc:mysql://localhost/wangkaibuletest?useUnicode=true&characterEncoding=UTF-8");
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
			ds.setPoolProperties(property);

			return ds;
	}
}
