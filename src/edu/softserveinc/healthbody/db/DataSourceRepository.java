package edu.softserveinc.healthbody.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public class DataSourceRepository {
	private static final String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
	
	private static volatile DataSourceRepository instance = null;
	Properties props = new Properties();
	FileInputStream in = null;

	private DataSourceRepository() {

	}
	
	public static DataSourceRepository getInstance() {
		if(instance == null) {
			synchronized (DataSourceRepository.class) {
				if(instance == null) {
					instance = new DataSourceRepository();
				}
			}
		}
		return instance;
	}
	
	public DataSource getPostgresLocalHost() throws JDBCDriverException {
		try {
			in = new FileInputStream("resources/database.properties");
			props.load(in);
		} catch (IOException e) {
			throw new JDBCDriverException(FAILED_JDBC_DRIVER, e);
		}
		return new DataSource(DriverRepository.getInstance().getPostgresDriver(),  props.getProperty("db.url") , props.getProperty("db.user"), props.getProperty("db.password"));
	}
}
