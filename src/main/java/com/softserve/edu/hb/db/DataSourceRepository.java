package com.softserve.edu.hb.db;

public final class DataSourceRepository {
	private static volatile DataSourceRepository instance = null;

	private DataSourceRepository() {
	}

	public static DataSourceRepository getInstance() {
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

	public DataSource getDefault() {
		return new DataSource(JdbcDriverRepository.getInstance().getMySql(),
				"jdbc:mysql://localhost:3306/lv185", "root", "root");
	}

	public DataSource getMySqlLocalHost() {
		return new DataSource(JdbcDriverRepository.getInstance().getMySql(),
				"jdbc:mysql://localhost:3306/lv185", "root", "root");
	}

    public DataSource getFirstConnectorByCVS() {
		// TODO for List
		return new DataSourceUtils().getAllDataSources().get(0);
    }

    public DataSource getConnectorByProperties() {
		return new DataSourcePropertiesUtil().getDataSource();
    }

}
