package edu.softserveinc.healthbody.competitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.constants.TestConstants;
import edu.softserveinc.healthbody.db.CreateDropTestDatabase;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.log.LoggerWrapper;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class CompetitionsViewServiceImplTest {
	
	@BeforeClass
	public void populateTestData(){
		new CreateDropTestDatabase().populateDBTables();
	}
	
	@AfterClass
	public void CleanTableAfterTest() throws SQLException, JDBCDriverException{
		DBPopulateManager.getInstance().deleteAllFromTables();
		LoggerWrapper.info(this.getClass(), "Aftertest block UserviewServiceimpl worked");
	}
	
	@Test
	public void testGetAll() {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAll(1, 2);
			assertNotNull(result);
			assertEquals(result.size(), 2);
//			LoggerWrapper.info("testGetAll");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAll");
//			System.out.println(result);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	public void testGetAllActive() {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActive(1, 10);
			assertNotNull(result);
//			LoggerWrapper.info("testGetAllActive");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAllActive");
//			System.out.println(result);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUser(@Optional("Login 7") String userlogin) {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 10, userlogin);
			assertNotNull(result);
//			LoggerWrapper.info("testGetAllByUser");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAllByUser");
//			System.out.println(result);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserNullLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 10, null);
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserEmptyLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 10, "");
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUserBadPaginationParams(@Optional("Login 7") String userlogin) {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllByUser(1, 100, userlogin);
			assertNotNull(result);
			int getAllSize = result.size();
			result = cv.getAllByUser(-1, 1, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(1, -1, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(-1, -1, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(0, 0, userlogin);
			assertEquals(getAllSize, result.size());
			result = cv.getAllByUser(2_000_000_000, 2_000_000_000, userlogin);
			assertEquals(0, result.size());
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllActiveByUser(@Optional("Login 7") String userlogin) {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActiveByUser(1, 10, userlogin);
			assertNotNull(result);
//			LoggerWrapper.info("testGetAllActiveByUser");
//			LoggerWrapper.info(result.toString());
//			System.out.println("testGetAllActiveByUser");
//			System.out.println(result);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
//		System.out.println("testGetAllActiveByUser");
//		System.out.println(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserNullLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActiveByUser(1, 10, null);
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserEmptyLogin() throws IllegalAgrumentCheckedException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		try {
			result = cv.getAllActiveByUser(1, 10, "");
			assertNull(result);
		} catch (SQLException | JDBCDriverException | TransactionException e) {
			LoggerWrapper.error(this.getClass(), TestConstants.EXCEPTION_CATCHED + e);
			fail(TestConstants.EXCEPTION_CATCHED, e);
		}
	}
	
}
