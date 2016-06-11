package edu.softserveinc.healthbody.dao;

import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public interface BasicCRUDDao<TEntity> extends BasicReadDao<TEntity> {
	
	public static enum DaoQueries {
		INSERT,
		GET_BY_ID,
		GET_BY_FIELD,
		GET_ID_BY_FIELDS,
		GET_ALL,
		UPDATE_BY_FIELD,
		DELETE_BY_ID,
		DELETE_BY_FIELD;
	}
	
	boolean insert(TEntity entity) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException;
	
	boolean updateByField(String fieldName, String text, String fieldCondition, String textCondition) throws JDBCDriverException, DataBaseReadingException;
	
	boolean deleteById(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException;
	
	boolean deleteByField(String fieldCondition, String textCondition) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException;
}
