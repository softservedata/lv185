package com.softserve.edu.hb.db;

import java.sql.Driver;
import java.sql.SQLException;

interface IJdbcDriver {
	Driver getJdbcDriver();
}

public class JdbcDriverRepository {
	
	static enum JdbcDriverList {
		MSJDBCSQL("ms", new IJdbcDriver() {
				public Driver getJdbcDriver() {
					// TODO
					return JdbcDriverRepository.getInstance().getMySql();
				}
			}),
		SYBASEJTDSSQL("jtds", new IJdbcDriver() {
				public Driver getJdbcDriver() {
					// TODO
					return JdbcDriverRepository.getInstance().getMySql();
				}
			}),
		MYSQL("my", new IJdbcDriver() {
				public Driver getJdbcDriver() {
					System.out.println("Running ... JdbcDriverRepository.getInstance().getMySql()");
					return JdbcDriverRepository.getInstance().getMySql();
				}
			}),
		POSTGRESQL("postgre", new IJdbcDriver() {
				public Driver getJdbcDriver() {
					// TODO Change to Postgre
					return JdbcDriverRepository.getInstance().getMySql();
				}
			});
		private String name;
		private IJdbcDriver jdbcDriver;

		private JdbcDriverList(String name, IJdbcDriver jdbcDriver) {
			this.name = name;
			this.jdbcDriver = jdbcDriver;
		}

        public Driver getDriver() {
            return this.jdbcDriver.getJdbcDriver();
        }

		@Override
		public String toString() {
			return name;
		}
	}
	
	//
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

	public Driver getDriverByName(String driverName) {
		for (JdbcDriverList jdbcDriverList : JdbcDriverList.values()) {
			if (driverName.toLowerCase().contains(jdbcDriverList.toString())) {
				return jdbcDriverList.getDriver();
			}
		}
		return JdbcDriverRepository.getInstance().getDefault();
	}

}
