package edu.softserveinc.healthbody.testapp;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.CompetitionsServiceImpl;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class TestAppl {
	private static Logger logger = LoggerFactory.getLogger(TestAppl.class.getName());

	public static void main(String[] args) throws JDBCDriverException, SQLException, TransactionException, IllegalAgrumentCheckedException {
	
		
		logger.info("TestAppl starts...");

		CompetitionsViewServiceImpl cs = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> ls1 = cs.getAllActive(1, 20);
		List<CompetitionDTO> ls2 = cs.getAllActiveByUser(1, 20, "Login 7");
		List<CompetitionDTO> ls3 = cs.getAllByUser(1, 20, "Login 7");

		
		CompetitionsServiceImpl competitionsServiceImpl = new CompetitionsServiceImpl();
		
		//Rewrite update/insert/delete methods
		List<CompetitionDTO> ls4 = cs.getAll(1, 30);
		try {
			competitionsServiceImpl.insert(new CompetitionDTO("we are the champions"," " ,"2016-06-23" , "2016-07-23", "nice", "Name criteria 1", null, null));
		} catch (TransactionException | CloseStatementException | EmptyResultSetException | DataBaseReadingException | QueryNotFoundException e) {
			logger.error("Insert didn't work", e);
		}
		
		logger.info("In getAllActive: " + Arrays.toString(ls1.toArray()));
		logger.info("In getAllActiveByUser: " + Arrays.toString(ls2.toArray()));
		logger.info("In getAllByUser: " + Arrays.toString(ls3.toArray()));
		logger.info("In getAll: " + Arrays.toString(ls4.toArray()));
	}
}