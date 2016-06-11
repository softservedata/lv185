package edu.softserveinc.healthbody.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.softserveinc.healthbody.dao.BasicCRUDDao.DaoQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

abstract class ADaoInit {
	protected abstract void init();
}

abstract class AbstractDaoRead<TEntity> implements BasicReadDao<TEntity> {
	protected final static String QUERY_NOT_FOUND = "Query not found %s";
	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";

	protected final HashMap<Enum<?>, Enum<?>> sqlQueries;

	private ResultSet resultSet = null;
	private String[] queryResult;
	private int i;

	protected AbstractDaoRead() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
	}

	protected abstract TEntity createInstance(String[] args);
	protected abstract String[] getFields(TEntity entity);

	// executing query

	// loop
	private String[] getQueryResultArr(String[] queryResult) throws SQLException {
		while (resultSet.next()) {
			for (i = 0; i < queryResult.length; i++) {
				queryResult[i] = resultSet.getString(i + 1);
			}
		}
		return queryResult;
	}

	public TEntity getById(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException {
		TEntity entity = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_ID).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_BY_ID.name()));
		}
		try (Statement pst = ConnectionManager.getInstance().getConnection().createStatement();
				ResultSet resultSet = pst.executeQuery(String.format(query, id))) {
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			entity = createInstance(getQueryResultArr(queryResult));
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);

		}
		return entity;
	}

	@Override
	public List<TEntity> getByField(String fieldname, String text) throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_BY_FIELD).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_BY_FIELD.name()));
		}
		try (Statement pst = ConnectionManager.getInstance().getConnection().createStatement();
				ResultSet resultSet = pst.executeQuery(String.format(query, fieldname, text))) {
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			all.add(createInstance(getQueryResultArr(queryResult)));
		} catch (SQLException e) {

			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		if (all.isEmpty()) {
			throw new EmptyResultSetException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}

	@Override
	public List<TEntity> getAll() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_ALL.name()));
		}
		try (Statement pst = ConnectionManager.getInstance().getConnection().createStatement();
				ResultSet resultSet = pst.executeQuery(String.format(query))) {
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			all.add(createInstance(getQueryResultArr(queryResult)));
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		if (all.isEmpty()) {
			throw new EmptyResultSetException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}
	public String getIdByTwoEntities(String idFirstEntity, String idSecondEntity) throws JDBCDriverException, EmptyResultSetException{
		String id = null;
		String query = sqlQueries.get(DaoQueries.GET_ID_BY_FIELDS).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_ID_BY_FIELDS.name()));
		}
		try (Statement pst = ConnectionManager.getInstance().getConnection().createStatement();
				ResultSet resultSet = pst.executeQuery(String.format(query, idFirstEntity, idSecondEntity))) {
			queryResult = new String[resultSet.getMetaData().getColumnCount()];			
			id = getFields(createInstance(getQueryResultArr(queryResult)))[0];			
		} catch (SQLException e) {
			throw new EmptyResultSetException(DATABASE_READING_ERROR, e);
		}
		
		return id;
	}
}
