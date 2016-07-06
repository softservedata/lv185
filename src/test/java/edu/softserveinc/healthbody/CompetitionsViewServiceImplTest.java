package edu.softserveinc.healthbody;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

import java.sql.SQLException;
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

import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class CompetitionsViewServiceImplTest {
	private static Logger logger = LoggerFactory.getLogger(CompetitionsViewServiceImplTest.class.getName());
  
	@BeforeSuite
	@Parameters("testdatabase")
	public void setUpBeforeSuite() throws JDBCDriverException{
		CreationDropDBForTest.getInstance().setUpBeforeSuite("healthbodydbtest");
	}
	
	@AfterSuite
	@Parameters("testdatabase")
	public void setUpAfterSuite() throws JDBCDriverException{
		CreationDropDBForTest.getInstance().tearDownAfterSuite("healthbodydbtest");
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		logger.info("Filling database with test data...");
		String failMessage = "Error filling: ";
		int failedTables = 0;
		if (!DBPopulateManager.getInstance().populateUsersTable()) { //1
			failedTables += 1;
		};
//		DBPopulateManager.getInstance().populateGroupsTable(); //2
//		DBPopulateManager.getInstance().populateUserGroupsTable(); //4
		if (!DBPopulateManager.getInstance().populateAwardsTable()) { //8
			failedTables += 8;
		};
		if (!DBPopulateManager.getInstance().populateCompetitionsTable()) { //16
			failedTables += 16;
		};
//		DBPopulateManager.getInstance().populateCriteriaTable(); //32
//		DBPopulateManager.getInstance().populateGroupCompetitionsTable(); //64
//		DBPopulateManager.getInstance().populateMetaDataTable(); //128
//		DBPopulateManager.getInstance().populateRolesTable(); //256
		if (!DBPopulateManager.getInstance().populateUserCompetitionsTable()) { //512
			failedTables += 512;
		};
		if (failedTables > 0) {
			failMessage += convertBinaryFlagToTableNames(failedTables);
			logger.error(failMessage);
			fail(failMessage);
		} else {
			logger.info("Filling database with test data ends successfully...");
		}
	}
	
	/**
	 * Converts binary flag to the list of tables that were failed to fill.
	 * Masks:
	 * 1   - Users
	 * 2   - Groups
	 * 4   - UserGroups
	 * 8   - Awards
	 * 16  - Competitions
	 * 32  - Criteria 
	 * 64  - GroupCompetitions
	 * 128 - MetaData
	 * 256 - Roles
	 * 512 - UserCompetitions
	*/
	
	private String convertBinaryFlagToTableNames(int flag){
		String[] tables = {"Users", "Groups", "UserGroups", "Awards", "Competitions", "Criteria",
				"GroupCompetitions", "MetaData", "Roles", "UserCompetitions"};
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			if ((flag & 1) == 1) {
				sb.append(tables[i]).append("; ");
			}
			flag = flag >> 1;
		}
		return sb.toString();
	}
	
	@AfterClass
	public void tearDownAfterClass() throws SQLException, JDBCDriverException {
		DBPopulateManager.getInstance().deleteAllFromTables();
	}
	
	@Test
	public void testGetAll() throws JDBCDriverException, SQLException, TransactionException {
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
	public void testGetAllByUser(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, userlogin);
		System.out.println("testGetAllByUser");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserNullLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, null);
		assertNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserEmptyLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, "");
		assertNull(result);
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUserBadPaginationParams(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
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
	public void testGetAllActiveByUserNullLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllActiveByUser(1, 10, null);
		assertNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserEmptyLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllActiveByUser(1, 10, "");
		assertNull(result);
	}
}

