package edu.softserveinc.healthbody.services;


import java.sql.SQLException;

import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface IBaseService<TBaseDTO> {
	
	void insert(TBaseDTO baseDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException;
	
	TBaseDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException;
	
	void update(TBaseDTO baseDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException;
	
	//use just for test
	void test_delete(TBaseDTO baseDTO) throws SQLException, JDBCDriverException, QueryNotFoundException, DataBaseReadingException, CloseStatementException, TransactionException;

}
