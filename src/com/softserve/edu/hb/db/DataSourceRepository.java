package com.softserve.edu.hb.db;
import java.sql.Driver;


public final class DataSourceRepository {
	private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";

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



	private Driver getJdbcDriverMySql() {
		Driver jdbcDriver = null;
		try {
			jdbcDriver = new org.postgresql.Driver();
		} catch (RuntimeException e) {
			throw new RuntimeException(FAILED_JDBC_DRIVER, e);
		}
		return jdbcDriver;
	}
	
	public DataSource getConnectorMySqlLocalHost() {
		return new DataSource(getJdbcDriverMySql(), "jdbc:mysql://localhost:3306/lv185", "root", "root");
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
