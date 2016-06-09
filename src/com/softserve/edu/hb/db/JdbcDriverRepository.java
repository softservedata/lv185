package com.softserve.edu.hb.db;

import java.sql.Driver;
import java.sql.SQLException;

public class JdbcDriverRepository {
	private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
	//
	private static volatile JdbcDriverRepository instance = null;

	private JdbcDriverRepository() {
	}

	public static JdbcDriverRepository getInstance() {
		if (instance == null) {
			synchronized (JdbcDriverRepository.class) {
				if (instance == null) {
					instance = new JdbcDriverRepository();
				}
			}
		}
		return instance;
	}

	public Driver getDefault() {
		return getMySql();
	}
	
	public Driver getMySql() {
		Driver jdbcDriver = null;
		try {
			jdbcDriver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			throw new RuntimeException(FAILED_JDBC_DRIVER, e);
		}
		return jdbcDriver;
	}

}
