package edu.softserveinc.healthbody.services;

import java.sql.SQLException;

import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface BaseService<TBaseDTO> {
	
	void insert(TBaseDTO baseDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException;
	
	TBaseDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException;
	
	void update(TBaseDTO baseDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException;
	
	void delete(TBaseDTO baseDTO);

}
