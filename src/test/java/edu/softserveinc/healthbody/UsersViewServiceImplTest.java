package edu.softserveinc.healthbody;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class UsersViewServiceImplTest {
	private static Logger logger = LoggerFactory.getLogger(UsersViewServiceImplTest.class.getName());
	private static final String EXCEPTION_CATCHED = "Exception catched while running test method.";

	@Test
	public void testUserViewGetAll() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud1;
		try {
			ud1 = uvs.getAll(1, 2);
			assertNotNull(ud1);
			assertEquals(ud1.size(), 2);
			logger.info("testUserViewGetAll");
			logger.info(ud1.toString());
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}

	@Test
	public void testUserViewGetAllbyAdmin() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud3;
		try {
			ud3 = uvs.getAllbyAdmin(1, 2);
			assertNotNull(ud3);
			assertEquals(ud3.size(), 2);
			logger.info("testUserViewGetAllbyAdmin");
			logger.info(ud3.toString());
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}

	@Test
	public void testUserViewGetAllinCompetition() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud4;
		try {
			ud4 = uvs.getAllinCompetition(1, 2);
			assertNotNull(ud4);
			assertEquals(ud4.size(), 2);
			logger.info("testUserViewGetAllinCompetition");
			logger.info(ud4.toString());
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}

	@Test
	public void testUserViewGetAllinGroup() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud5;
		try {
			ud5 = uvs.getAllinGroup(1, 2);
			assertNotNull(ud5);
			assertEquals(ud5.size(), 2);
			logger.info("testUserViewGetAllinGroup");
			logger.info(ud5.toString());
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}

	@Test
	public void testUserViewGetAlltoAddInCompetition() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud6;
		try {
			ud6 = uvs.getAlltoAddInCompetition(1, 2);
			assertNotNull(ud6);
			assertEquals(ud6.size(), 2);
			logger.info("testUserViewGetAlltoAddInCompetition");
			logger.info(ud6.toString());
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}

}
