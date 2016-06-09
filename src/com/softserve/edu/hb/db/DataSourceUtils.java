package com.softserve.edu.hb.db;

import java.util.ArrayList;
import java.util.List;

public final class DataSourceUtils {
	private final static String DATASOURCE_FILE_EMPTY = "DataSource File %s is Empty";
	//
	private String filename;
	private IExternalData externalData;

	public DataSourceUtils() {
		filename = "/resources/DataSource.csv";
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
		String classPath = this.getClass().getResource("/").getPath().substring(1);
		classPath = classPath.substring(0, classPath.lastIndexOf("/bin")) + getFilename();
		return getAllDataSources(classPath);
	}

	public List<DataSource> getAllDataSources(String absoluteFilePath) {
		List<DataSource> dataSources = new ArrayList<DataSource>();
		for (List<String> row : getExternalData().getAllCells(absoluteFilePath)) {
			if (row.get(0).toLowerCase().contains("#")
					|| row.get(0).toLowerCase().contains("jdbcdrivername")
					|| row.get(1).toLowerCase().contains("connectionurl")
					|| row.get(2).toLowerCase().contains("username")
					|| row.get(3).toLowerCase().contains("password")) {
				continue;
			}
			// TODO Check jdbcdrivername
			dataSources.add(new DataSource(
					JdbcDriverRepository.getInstance().getMySql(),
					row.get(1),row.get(2),row.get(3)));
		}
		if (dataSources.size() == 0) {
			throw new RuntimeException(
					String.format(DATASOURCE_FILE_EMPTY, getFilename()));
		}
		return dataSources;
	}

}
