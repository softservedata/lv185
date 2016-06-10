package edu.softserveinc.healthbody.db;

import java.sql.Driver;

public class DriverRepository {
	private static final String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
	private static volatile DriverRepository instance = null;

	private DriverRepository() {
	}
	
	public static DriverRepository getInstance() {
		if(instance == null) {
			synchronized (DriverRepository.class) {
				if(instance == null) {
					instance = new DriverRepository();
				}				
			}
		}
		return instance;
	}

	public  Driver getPostgresDriver() {
		Driver jdbcDriver = null;
		 try {
		jdbcDriver = new org.postgresql.Driver();
		 } catch (Exception e) {
			 throw new RuntimeException(FAILED_JDBC_DRIVER, e);
			}
		return jdbcDriver;
	}
	
	public Driver getDefault() {
		return getPostgresDriver();
	}
}
