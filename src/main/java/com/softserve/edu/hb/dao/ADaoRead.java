package com.softserve.edu.hb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softserve.edu.hb.dao.IDaoCRUD.DaoQueries;
import com.softserve.edu.hb.db.ConnectionManager;

abstract class ADaoInit {
	protected abstract void init();
}

abstract class ADaoRead<TEntity> extends ADaoInit implements IDaoRead<TEntity> {
	protected final static String QUERY_NOT_FOUND = "Query not found %s";
	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";
	//
	protected final static String SQL_WHERE = " where";
	protected final static String SQL_AND = " and";
	protected final static String SQL_LIKE = " %s like '%%%s%%';";
	protected final static String SQL_LIMIT = " limit %s, %s;";
	//
	protected final HashMap<Enum<?>, Enum<?>> sqlQueries;

	protected ADaoRead() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
		// TODO Call init
	}

	// TODO Use Builder
	protected abstract TEntity createInstance(String[] args);

	// TODO Create abstract method init
	//protected abstract void init();

	// Read
	public TEntity getById(Integer id) {
		TEntity entity = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_ID).toString();
		String[] queryResult;
		int i;
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "GET_BY_ID"));
			 throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_BY_ID.name()));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(String.format(query, id));
			if (resultSet.next()) {
				queryResult = new String[resultSet.getMetaData().getColumnCount()];
				for (i = 0; i < queryResult.length; i++) {
					queryResult[i] = resultSet.getString(i + 1);
				}
				entity = createInstance(queryResult);
			} else {
				//throw new GeneralCustomException(String.format(EMPTY_RESULTSET, query));
				 throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
			}
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		return entity;
	}

	public List<TEntity> getByFieldName(String fieldName, String text) {
		List<TEntity> all = new ArrayList<TEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_FIELD).toString();
		String[] queryResult;
		int i;
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "GET_BY_FIELD"));
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_BY_FIELD.name()));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(String.format(query, fieldName, text));
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				//queryResult = new String[resultSet.getMetaData().getColumnCount()];
				for (i = 0; i < queryResult.length; i++) {
					// System.out.println("\t\t ***
					// queryResult["+i+"]="+resultSet.getString(i+1));
					queryResult[i] = resultSet.getString(i + 1);
				}
				all.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		if (all.isEmpty()) {
			//throw new GeneralCustomException(String.format(EMPTY_RESULTSET, query));
			throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}

	public List<TEntity> getAll() {
		List<TEntity> all = new ArrayList<TEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		String[] queryResult;
		int i;
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "GET_ALL"));
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_ALL.name()));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(query);
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				//queryResult = new String[resultSet.getMetaData().getColumnCount()];
				for (i = 0; i < queryResult.length; i++) {
					queryResult[i] = resultSet.getString(i + 1);
				}
				all.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		if (all.isEmpty()) {
			//throw new GeneralCustomException(String.format(EMPTY_RESULTSET, query));
			 throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}

    public List<TEntity> getFilterRange(int partNumber, int partSize, Map<String, String> filters) {
		List<TEntity> all = new ArrayList<TEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		String[] queryResult;
		int i;
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "GET_ALL"));
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_ALL.name()));
		}
		//
		boolean isWhereFirst = true;
		for (String fieldName : filters.keySet()) {
			if ((filters.get(fieldName) != null)
					&& (!filters.get(fieldName).isEmpty())) {
				if (isWhereFirst) {
					query = query.substring(0, query.lastIndexOf(";")) + SQL_WHERE;
					isWhereFirst = false;
				} else {
					query = query.substring(0, query.lastIndexOf(";")) + SQL_AND;
				}
				query = query + String.format(SQL_LIKE, fieldName, filters.get(fieldName));
			}
		}
		if ((partNumber >= 0) && (partSize > 0)) {
			query = query.substring(0, query.lastIndexOf(";"))
					+ String.format(SQL_LIMIT, partNumber, partSize);
		}
		System.out.println("Query = " + query);
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(query);
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				//queryResult = new String[resultSet.getMetaData().getColumnCount()];
				for (i = 0; i < queryResult.length; i++) {
					queryResult[i] = resultSet.getString(i + 1);
				}
				all.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		if (all.isEmpty()) {
			//throw new GeneralCustomException(String.format(EMPTY_RESULTSET, query));
			 throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
    }

}
