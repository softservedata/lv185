package edu.softserveinc.healthbody;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class UserViewTest {
	private static Logger logger = LoggerFactory.getLogger(UserViewTest.class.getName());
	
	@Test
	public void testUserView() {

		///before
		
		
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();

		try {
			List<UserDTO> ud1 = uvs.getAll(1, 5);
			//ud1.size();
			
			
			
			
			List<UserDTO> ud2 = uvs.getAll(5, 1);
			
			
			
//			
//			List<UserDTO> ud2 = uvs.getAllbyAdmin(1, 5);
			List<UserDTO> ud3 = uvs.getAllinCompetition(1, 5);
//			List<UserDTO> ud4 = uvs.getAllinGroup(1, 5);
//			List<UserDTO> ud5 = uvs.getAlltoAddInCompetition(1, 5);
//
			logger.info("In getAll: " + Arrays.toString(ud1.toArray()));
			logger.info("In getAllbyAdmin: " + Arrays.toString(ud2.toArray()));
			logger.info("In getAllinCompetition: " + Arrays.toString(ud3.toArray()));
//			logger.info("In getAllinGroup: " + Arrays.toString(ud4.toArray()));
//			logger.info("In getAllinGroup: " + Arrays.toString(ud5.toArray()));
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | EmptyResultSetException
				| CloseStatementException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("GetAll didn't work", e);
		}
	}
}
