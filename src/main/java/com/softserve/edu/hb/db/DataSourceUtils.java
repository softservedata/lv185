package com.softserve.edu.hb.db;

import java.util.ArrayList;
import java.util.List;

public final class DataSourceUtils {
	private final static String DATASOURCE_FILE_EMPTY = "DataSource File %s is Empty";
	private final static String DEFAULT_CSV = "/resources/DataSource.csv";
	//
	private final static String LINE_COMMENT = "#";
	private final static String LINE_JDBCDRIVER = "jdbcdrivername";
	private final static String LINE_URL = "connectionurl";
	private final static String LINE_USERNAME = "username";
	private final static String LINE_PASSWORD = "password";
	// TODO Change for Maven Project
	private final static String PROJECT_ROOT = "/";
	private final static String PROJECT_BIN = "/bin";
	//
	private String filename;
	private IExternalData externalData;

	public DataSourceUtils() {
		filename = DEFAULT_CSV;
		externalData = new CSVUtils();
	}

	public DataSourceUtils(String filename, IExternalData externalData) {
		this.filename = filename;
		this.externalData = externalData;
	}
	
	public String getFilename() {
		return filename;
	}

	public IExternalData getExternalData() {
		return externalData;
	}

	public List<DataSource> getAllDataSources() {
		// TODO Chanche for maven project
		//return getAllDataSources(this.getClass().getResource(getFilename()).getPath().substring(1));
		String classPath = this.getClass().getResource(PROJECT_ROOT).getPath().substring(1);
		classPath = classPath.substring(0, classPath.lastIndexOf(PROJECT_BIN)) + getFilename();
		return getAllDataSources(classPath);
	}

	public List<DataSource> getAllDataSources(String absoluteFilePath) {
		List<DataSource> dataSources = new ArrayList<DataSource>();
		for (List<String> row : getExternalData().getAllCells(absoluteFilePath)) {
			if (row.get(0).toLowerCase().contains(LINE_COMMENT)
					|| row.get(0).toLowerCase().contains(LINE_JDBCDRIVER)
					|| row.get(1).toLowerCase().contains(LINE_URL)
					|| row.get(2).toLowerCase().contains(LINE_USERNAME)
					|| row.get(3).toLowerCase().contains(LINE_PASSWORD)) {
				continue;
			}
			dataSources.add(new DataSource(
					JdbcDriverRepository.getInstance().getDriverByName(row.get(0)),
					row.get(1),row.get(2),row.get(3)));
		}
		if (dataSources.size() == 0) {
			throw new RuntimeException(
					String.format(DATASOURCE_FILE_EMPTY, getFilename()));
		}
		return dataSources;
	}

}
