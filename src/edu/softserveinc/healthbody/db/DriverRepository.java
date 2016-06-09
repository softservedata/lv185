package edu.softserveinc.healthbody.db;

import java.sql.Driver;
import java.sql.SQLException;

public class DriverRepository {

	public static Driver getJdbcDriverPostgres() throws SQLException{
		Driver jdbcDriver = null;
		jdbcDriver = new org.postgresql.Driver();
		return jdbcDriver;
	}
}
