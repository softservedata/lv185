package com.softserve.edu.hb.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSourcePropertiesUtil {
	
	public static enum DataSourcePropertiesUtilKeys {
		JDBC_DRIVER_NAME("jdbcDriverName"),
		CONNECTION_URL("connectionUrl"),
		USERNAME("username"),
		PASSWORD("password");
		private String field;

		private DataSourcePropertiesUtilKeys(String field) {
			this.field = field;
		}

		@Override
		public String toString() {
			return this.field;
		}
	}

	private static final String ERROR_READ = " File %s could not be read";
	private static final String ERROR_CLOSE = " File %s could not be closed";
	private static final String DEFAULT_PROPERTIES = "/resources/DataSource.properties";
	// TODO Change for Maven Project
	private final static String PROJECT_ROOT = "/";
	private final static String PROJECT_BIN = "/bin";
	//
	private String filename;

	public DataSourcePropertiesUtil() {
		filename = DEFAULT_PROPERTIES;
	}

	public DataSourcePropertiesUtil(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public DataSource getDataSource() {
		// TODO Chanche for maven project
		//return getDataSources(this.getClass().getResource(getFilename()).getPath().substring(1));
		String classPath = this.getClass().getResource(PROJECT_ROOT).getPath().substring(1);
		classPath = classPath.substring(0, classPath.lastIndexOf(PROJECT_BIN)) + getFilename();
		return getDataSource(classPath);

	}

	public DataSource getDataSource(String absoluteFilePath) {
		DataSource dataSource = null;
		Properties properties = new Properties();
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(absoluteFilePath);
			properties.load(fileInputStream);
		} catch (IOException e) {
			throw new RuntimeException(String.format(ERROR_READ, absoluteFilePath), e);
		}
		dataSource = new DataSource(
				JdbcDriverRepository.getInstance().getDriverByName(
						properties.getProperty(DataSourcePropertiesUtilKeys.JDBC_DRIVER_NAME.toString(), new String())),
				properties.getProperty(DataSourcePropertiesUtilKeys.CONNECTION_URL.toString(), new String()),
				properties.getProperty(DataSourcePropertiesUtilKeys.USERNAME.toString(), new String()),
				properties.getProperty(DataSourcePropertiesUtilKeys.PASSWORD.toString(), new String())
			);
		try {
			fileInputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(String.format(ERROR_CLOSE, absoluteFilePath), e);
		}
		return dataSource;
	}
	
}
