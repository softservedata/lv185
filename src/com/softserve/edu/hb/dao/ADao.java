package com.softserve.edu.hb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.softserve.edu.hb.db.ConnectionUtils;
import com.softserve.edu.hb.entity.IEntity;

abstract class ADaoInit {
	protected abstract void init();
}

abstract class ADao<TEntity extends IEntity> extends ADaoInit implements IDao<TEntity> {
	private final static String QUERY_NOT_FOUND = "Query not found %s";
	private final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	private final static String DATABASE_READING_ERROR = "Database Reading Error";
	//
	protected final HashMap<Enum<?>, Enum<?>> sqlQueries;

	protected ADao() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
		// TODO Call init
	}

	// TODO Use Builder
	protected abstract TEntity createInstance(String[] args);

	protected abstract String[] getFields(TEntity entity);

	// TODO Create abstract method init
	//protected abstract void init();

	// Create
	public boolean insert(TEntity entity) {
		boolean result = false;
		Statement statement = null;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "INSERT"));
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try {
			statement = ConnectionUtils.get().getConnection().createStatement();
			// TODO CHECK!
			result = statement.execute(String.format(query,
					(Object[]) Arrays.copyOfRange(getFields(entity), 1, getFields(entity).length)));
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		// TODO result must be return if delete Ok
		return result;
	}

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
			statement = ConnectionUtils.get().getConnection().createStatement();
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
			statement = ConnectionUtils.get().getConnection().createStatement();
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
			statement = ConnectionUtils.get().getConnection().createStatement();
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

	// Update
	public boolean updateByFieldName(String fieldName, String text, String fieldCondition, String textCondition) {
		boolean result = false;
		Statement statement = null;
		String query = sqlQueries.get(DaoQueries.UPDATE_BY_FIELD).toString();
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "UPDATE_BY_FIELD"));
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.UPDATE_BY_FIELD.name()));
		}
		try {
			statement = ConnectionUtils.get().getConnection().createStatement();
			// TODO Use statement.executeUpdate
			result = statement.execute(String.format(query, fieldName, text, fieldCondition, textCondition));
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		// TODO result must be return if delete Ok
		return result;
	}

	// Delete
	public boolean deleteById(Integer id) {
		// System.out.println("\t\t\tdeleteById DONE");
		boolean result = false;
		Statement statement = null;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID).toString();
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "DELETE_BY_ID"));
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID.name()));
		}
		try {
			statement = ConnectionUtils.get().getConnection().createStatement();
			//System.out.println("DAO query: " + String.format(query, id));
			result = statement.execute(String.format(query, id));
			//System.out.println("DAO result : " + result);
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		// TODO result must be return if delete Ok
		return result;
	}

	public boolean deleteByFieldName(String fieldCondition, String textCondition) {
		boolean result = false;
		Statement statement = null;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_FIELD).toString();
		if (query == null) {
			//throw new GeneralCustomException(String.format(QUERY_NOT_FOUND, "DELETE_BY_ID"));
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.DELETE_BY_FIELD.name()));
		}
		try {
			statement = ConnectionUtils.get().getConnection().createStatement();
			result = statement.execute(String.format(query, fieldCondition, textCondition));
		} catch (SQLException e) {
			//throw new GeneralCustomException(DATABASE_READING_ERROR, e);
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					// TODO Warning
				}
			}
		}
		// TODO result must be return if delete Ok
		return result;
	}
	
	public boolean delete(TEntity entity) {
		return deleteById(entity.getId());
	}

}
