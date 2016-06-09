package edu.softserveinc.healthbody.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceRepository {

	private static volatile DataSourceRepository instance = null;
	Properties props = new Properties();
	FileInputStream in = null;

	private DataSourceRepository() {

	}
	
	public static DataSourceRepository get() {
		if(instance == null) {
			synchronized (DataSourceRepository.class) {
				if(instance == null) {
					instance = new DataSourceRepository();
				}
			}
		}
		return instance;
	}
	
	public DataSource getConnectionPostgresLocalHost() throws SQLException {
		try {
			in = new FileInputStream("resources/database.properties");
			props.load(in);
		} catch (IOException e) {
			e.getMessage();
		}
		return new DataSource(DriverRepository.getJdbcDriverPostgres(),  props.getProperty("db.url") , props.getProperty("db.user"), props.getProperty("db.password"));
	}
}
