package edu.softserveinc.healthbody.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import edu.softserveinc.healthbody.db.ConnectionDb;
import edu.softserveinc.healthbody.entity.IEntity;

abstract class AbstractDaoCRUD<TEntity extends IEntity> extends AbstractDaoRead<TEntity> implements BasicCRUDDao<TEntity> {

	// local variables from methods
	private Statement statement = null;

	protected AbstractDaoCRUD() {
		super();
	}
	
	
	// private methods to make less duplication
	private boolean executeStatement(String s) throws SQLException {
		statement = ConnectionDb.get().getConnection().createStatement();
		return statement.execute(s);
	}

	// create
	public boolean insert(TEntity entity) {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try {
			result = executeStatement(String.format(query,
					(Object[]) Arrays.copyOfRange(getFields(entity), 1, getFields(entity).length)));
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}
		return result;
	}

	// update
	@Override
	public boolean updateByField(String fieldName, String text, String fieldCondition, String textCondition) {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE_BY_FIELD).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.UPDATE_BY_FIELD.name()));
		}
		try {
			result = executeStatement(String.format(query, fieldName, text, fieldCondition, textCondition));
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
		}
		return result;
	}
	//delete
	@Override
	public boolean deleteById(Integer id) {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID.name()));
		}
		try {
			result = executeStatement(String.format(query, id));
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
		}
		return result;
	}

	@Override
	public boolean deleteByField(String fieldCondition, String textCondition) {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_FIELD).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.DELETE_BY_FIELD.name()));
		}
		try {
			result = executeStatement(String.format(query, fieldCondition, textCondition));
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
		}
		return result;
	}
	
	public boolean delete(TEntity entity) {
		return deleteById(entity.getId());
	}
}
