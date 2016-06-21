package edu.softserveinc.healthbody.dao;

import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public interface BasicReadDao<TEntity> {
	
	TEntity getById(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException;

	List<TEntity> getByField(String fieldname, String text) throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException;

	List<TEntity> getAll() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException;
	
	List<TEntity> getFilterRange(int partNumber, int partSize, Map<String, String> filters) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException;
}
