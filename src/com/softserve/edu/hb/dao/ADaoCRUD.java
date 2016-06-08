package com.softserve.edu.hb.dao;	

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import com.softserve.edu.hb.db.ConnectionUtils;
import com.softserve.edu.hb.entity.IEntity;

abstract class ADaoCRUD<TEntity extends IEntity> extends ADaoRead<TEntity> implements IDaoCRUD<TEntity> {

	protected ADaoCRUD() {
		super();
	}

	// TODO Use Builder
	protected abstract String[] getFields(TEntity entity);

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
//
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


