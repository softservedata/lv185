package edu.softserveinc.healthbody.competitionservice;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.CompetitionsServiceImpl;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class CompetitionsTest {
	private static Logger logger = LoggerFactory.getLogger(CompetitionsTest.class.getName());
	
  @Test
  public void testInsertCompetition() {
	  
	  CompetitionsViewServiceImpl cs = new CompetitionsViewServiceImpl();
	  CompetitionsServiceImpl competitionsServiceImpl = new CompetitionsServiceImpl();	  
	  try {
		competitionsServiceImpl.insert(new CompetitionDTO("we are the champions"," " ,"2016-06-23" , "2016-07-23", "nice", "Name criteria 1", null, null));
	} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
			| EmptyResultSetException | TransactionException | CloseStatementException e) {
		logger.error("Insert didn't work", e);
	}
		try {
			List<CompetitionDTO> ls4 = cs.getAll(1, 30);
			logger.info("In getAll: " + Arrays.toString(ls4.toArray()));
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | EmptyResultSetException
				| CloseStatementException e) {
			// TODO Auto-generated catch block
			logger.error("GetAll didn't work", e);
		}
  }
}
