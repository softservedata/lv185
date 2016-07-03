package edu.softserveinc.healthbody;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.db.DataSource;
import edu.softserveinc.healthbody.db.DataSourceRepository;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class TestCompetitionsView {
	private static Logger logger = LoggerFactory.getLogger(TestCompetitionsView.class.getName());
  
	@BeforeSuite
	@Parameters("testdatabase")
	public void setUpBeforeSuite(@Optional("healthbodydbtest") String testdatabase) throws JDBCDriverException {
		Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
		try (
				Statement st = con.createStatement()) {
			if (!DBCreationManager.getInstance().dropDatabase(st, testdatabase)) {
				logger.error("Couldn't delete test database.");
				System.exit(0); 
			}
			if (!DBCreationManager.getInstance().createDatabase(st, testdatabase)){
				logger.error("Couldn't create test database.");
				System.exit(0); 
			}
		} catch (SQLException e) {
			logger.error("Problem with deleting/creating database.", e);
			System.exit(0); 
		}
		con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostTest()).getConnection();
		try (
				Statement st = con.createStatement()) {
			DBCreationManager dbCReationManager = DBCreationManager.getInstance();
			for (String query : dbCReationManager.getListOfQueries()) {
				logger.info("Creating " + query.split("\"")[1]);
				dbCReationManager.createTable(st, query);
			}
		} catch (SQLException e) {
			logger.error("Problem with creating tables data", e);
			System.exit(0); 
		}
	}
	
	@AfterSuite
	@Parameters("testdatabase")
	public void tearDownAfterSuite(@Optional("healthbodydbtest") String testdatabase) throws JDBCDriverException {
		Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
		try (Statement st = con.createStatement()){
			if (!DBCreationManager.getInstance().dropDatabase(st, testdatabase)){
					logger.error("Couldn't delete database, because encounter some problem!");
					System.exit(0); 
				}
				else {
				logger.info("Database was deleted");			
			}
		} catch (SQLException e) {
			logger.error("Problem with deleting database", e);
			System.exit(0); 
		}
	}
	
	@BeforeClass
	public void setUpBeforeClass() throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException {
		DataSource ds = DataSourceRepository.getInstance().getPostgresLocalHostTest();
		DBPopulateManager.getInstance(ds).populateUsersTable();
		DBPopulateManager.getInstance(ds).populateGroupsTable();
		DBPopulateManager.getInstance(ds).populateUserGroupsTable();
		DBPopulateManager.getInstance(ds).populateAwardsTable();
		DBPopulateManager.getInstance(ds).populateCompetitionsTable();
		DBPopulateManager.getInstance(ds).populateCriteriaTable();
		DBPopulateManager.getInstance(ds).populateGroupCompetitionsTable();
		DBPopulateManager.getInstance(ds).populateMetaDataTable();
		DBPopulateManager.getInstance(ds).populateRolesTable();
		DBPopulateManager.getInstance(ds).populateUserCompetitionsTable();
		logger.info("Populated All tables");					
	}
	
	@AfterClass
	public void tearDownAfterClass() throws SQLException, JDBCDriverException {
		DataSource ds = DataSourceRepository.getInstance().getPostgresLocalHostTest();
		DBPopulateManager.getInstance(ds).deleteAllFromTables();
	}
	
	@Test
	public void testGetAll() throws JDBCDriverException, SQLException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result = cv.getAll(1, 2);
		System.out.println("testGetAll");
		System.out.println(result);
		assertNotNull(result);
		assertEquals(result.size(), 2);
	}

	@Test
	public void testGetAllActive() throws JDBCDriverException, SQLException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result = cv.getAllActive(1, 10);
		System.out.println("testGetAllActive");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUser(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, userlogin);
		System.out.println("testGetAllByUser");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserNullLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, null);
		assertNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserEmptyLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, "");
		assertNull(result);
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUserBadPaginationParams(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
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
//		TODO Is it correct that empty result set causes exception - check and fix 		
//		result = cv.getAllByUser(2_000_000_000, 2_000_000_000, userlogin);
//		assertEquals(getAllSize, result.size());
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllActiveByUser(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result = cv.getAllActiveByUser(1, 10, userlogin);
		System.out.println("testGetAllActiveByUser");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserNullLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllActiveByUser(1, 10, null);
		assertNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserEmptyLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllActiveByUser(1, 10, "");
		assertNull(result);
	}
}

