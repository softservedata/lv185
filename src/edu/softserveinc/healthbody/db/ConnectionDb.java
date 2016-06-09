package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
	private final static String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";
	
	private static volatile ConnectionDb instance = null;
	
	private Connection connection = null;
	private DataSource dataSource;
	
	
	
	public ConnectionDb(DataSource dataSource) {
//		try {
//			dataSource = DataSourceRepository.get().getConnectionPostgresLocalHost();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		this.dataSource = dataSource;
	}
	
	public static ConnectionDb get() {
		if(instance == null){
			throw new RuntimeException(FAILED_REGISTRATE_DRIVER);
		}
		return instance;
	}
	public static ConnectionDb get(DataSource dataSource) {
		if(instance == null){
			synchronized (DataSource.class) {
				if(instance == null) {
					instance = new ConnectionDb(dataSource);
					try {
						DriverManager.registerDriver(dataSource.getJdbcDriver());
					} catch (SQLException e) {
						throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
					}
				}
			}
		}
		return instance;
	}

	public Connection getConnection() {
		if(connection == null){
			synchronized (DataSource.class) {
				if(connection == null) {
					try {
						connection = DriverManager.getConnection(dataSource.getConnectionUrl(), dataSource.getUser(),dataSource.getPasswrd());
					} catch (SQLException e) {
						throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
					}
				}
			}
		}
		return connection;
	}
	
	public static void closeConnection() {
		if (instance == null) {
			if(instance.getConnection()!=null) {
				try {
					instance.getConnection().close();
				}catch (SQLException e) {
					throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
				}
			}
		}
	}
	
}
