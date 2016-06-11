package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.IEntity;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

abstract class AbstractDao<TEntity extends IEntity> extends AbstractDaoRead<TEntity> implements BasicDao<TEntity> {

	protected AbstractDao() {
		super();
	}

	protected abstract String[] getFields(TEntity entity);
	
	private boolean executeStatement(String s) throws SQLException, JDBCDriverException {
				try (Statement statement = ConnectionManager.getInstance().getConnection().createStatement()) {
		
					return statement.execute(s);
				}
			}
		

	// create
	public boolean insert(TEntity entity) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try {
			result = executeStatement(String.format(query,
					(Object[]) Arrays.copyOfRange(getFields(entity), 1, getFields(entity).length)));
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		return result;
	}

	// update
	@Override
	public boolean updateByField(String fieldName, String text, String fieldCondition, String textCondition)
			throws JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE_BY_FIELD).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.UPDATE_BY_FIELD.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			pst.setString(1, fieldName);
			pst.setString(2, text);
			pst.setString(3, fieldCondition);
			pst.setString(4, textCondition);
			pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		return result;
	}

	// delete
	@Override
	public boolean deleteById(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, DaoQueries.DELETE_BY_ID.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			pst.setInt(1, id);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		return result;
	}

	@Override
	public boolean deleteByField(String fieldCondition, String textCondition)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.DELETE_BY_FIELD).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, DaoQueries.DELETE_BY_FIELD.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			pst.setString(1, fieldCondition);
			pst.setString(2, textCondition);
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		return result;
	}
	public boolean delete(TEntity entity) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		return deleteById(entity.getId());
	}
}
