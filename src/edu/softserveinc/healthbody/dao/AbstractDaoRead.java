package edu.softserveinc.healthbody.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.softserveinc.healthbody.dao.BasicCRUDDao.DaoQueries;
import edu.softserveinc.healthbody.db.ConnectionDb;
import edu.softserveinc.healthbody.entity.GroupDB;
import edu.softserveinc.healthbody.entity.UserDB;

abstract class ADaoInit {
	protected abstract void init();
}

abstract class AbstractDaoRead<TEntity> extends ADaoInit implements BasicReadDao<TEntity>{
	protected final static String QUERY_NOT_FOUND = "Query not found %s";
	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";
	
	protected final HashMap<Enum<?>, Enum<?>> sqlQueries;
	
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String[] queryResult;
	private int i;
	
	protected AbstractDaoRead() {
		this.sqlQueries =new  HashMap<Enum<?>, Enum<?>>();
	}
	
	protected abstract TEntity createInstance(String[] args);
	
	protected abstract String[] getFields(TEntity entity);

	//executing query
	private String[] executeQueryStatement(String s) throws SQLException {
		statement = ConnectionDb.get().getConnection().createStatement();
		resultSet = statement.executeQuery(s);
		return new String[resultSet.getMetaData().getColumnCount()];
	}
	
	//loop
	private String[] getQueryResultArr(String[] queryResult) throws SQLException {
		while (resultSet.next()) {
			for (i = 0; i < queryResult.length; i++) {
				queryResult[i] = resultSet.getString(i + 1);
			}
		}
		return queryResult;
	}
	
	public TEntity getById(Integer id) {
		TEntity entity = null;
		String query = sqlQueries.get(DaoQueries.GET_BY_ID).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_BY_ID.name()));
		}
		try {
			queryResult = executeQueryStatement(String.format(query, id));
			entity = createInstance(getQueryResultArr(queryResult));
//			if (resultSet.next()) {
//				for (i = 0; i < queryResult.length; i++) {
//					queryResult[i] = resultSet.getString(i + 1);
//				}
//				entity = createInstance(queryResult);
//			} else {
//				throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
//			}
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e2) {
					e2.getMessage();
				}
			}
		}

		return entity;
	}

	@Override
	public List<TEntity> getByField(String fieldname, String text) {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_BY_FIELD).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_BY_FIELD.name()));
		}
		try {
			queryResult = executeQueryStatement(String.format(query, fieldname, text));
			all.add(createInstance(getQueryResultArr(queryResult)));
//			while (resultSet.next()) {
//				for (i = 0; i < queryResult.length; i++) {
//					queryResult[i] = resultSet.getString(i + 1);
//				}
//				all.add(createInstance(queryResult));
//			}
		} catch (SQLException e) {

			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
		}
		if (all.isEmpty()) {
			throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}

	@Override
	public List<TEntity> getAll() {
		List<TEntity> all = new ArrayList<>();
		String query = sqlQueries.get(DaoQueries.GET_ALL).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_ALL.name()));
		}
		try {
			queryResult = executeQueryStatement(query);
			all.add(createInstance(getQueryResultArr(queryResult)));
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					ex.getMessage();
				}
			}
		}
		if (all.isEmpty()) {
			throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}
	
	public String getIdByTwoEntities(String idFirstEntity, String idSecondEntity){
		String id = null;
		String query = sqlQueries.get(DaoQueries.GET_ID_BY_FIELDS).toString();
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, DaoQueries.GET_ID_BY_FIELDS.name()));
		}
		try {
			queryResult = executeQueryStatement(String.format(query, idFirstEntity, idSecondEntity));			
			id = getFields(createInstance(getQueryResultArr(queryResult)))[0];			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

}
