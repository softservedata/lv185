package com.softserve.edu.hb.db;

import java.sql.Driver;
import java.sql.SQLException;

public final class DataSourceRepository {
	private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
	//
	private static volatile DataSourceRepository instance = null;

	private DataSourceRepository() {
	}

	public static DataSourceRepository get() {
		if (instance == null) {
			synchronized (DataSourceRepository.class) {
				if (instance == null) {
					instance = new DataSourceRepository();
				}
			}
		}
		return instance;
	}

//	public DataSource getJtdsMsSqlLocal() {
//		return new DataSource(new net.sourceforge.jtds.jdbc.Driver(),
//				"jdbc:jtds:sqlserver://CLASS02/Lv185;instance=SQLEXPRESS;", "db185", "db185");
//	}

	// TODO Develop new class and move method
	private Driver getJdbcDriverMySql() {
		Driver jdbcDriver = null;
		try {
			jdbcDriver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			throw new RuntimeException(FAILED_JDBC_DRIVER, e);
		}
		return jdbcDriver;
	}
	
	public DataSource getConnectorMySqlLocalHost() {
		return new DataSource(getJdbcDriverMySql(), "jdbc:mysql://localhost:3306/healthbody", "root", "volodja77");
	}

	@Deprecated //TODO
    public DataSource getConnectorMySqlByCVS() {
		// TODO
		String connectionUrl = null; //= DataSourceUtils.get().getConnectionUrl();
		String username = null;
		String password = null;
		return new DataSource(getJdbcDriverMySql(), connectionUrl, username, password);
    }

	@Deprecated //TODO
    public DataSource getConnectorMySqlByProperties() {
		// TODO
		String connectionUrl = null; //= DataSourceUtils.get().getConnectionUrl();
		String username = null;
		String password = null;
		return new DataSource(getJdbcDriverMySql(), connectionUrl, username, password);
    }

}
