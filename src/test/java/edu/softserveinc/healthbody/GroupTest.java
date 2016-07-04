package edu.softserveinc.healthbody;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

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
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.db.DataSourceRepository;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

public class GroupTest {
	private static Logger logger = LoggerFactory.getLogger(GroupTest.class.getName());
	private static final String TEST_DATABASE = "healthbodydb";
	
	@BeforeSuite
	public void setUpBeforeSuite() throws JDBCDriverException {
		try (Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
				Statement st = con.createStatement()){
			if (!DBCreationManager.getInstance().dropDatabase(st, TEST_DATABASE)) {
				logger.error("Couldn't delete test database.");
				System.exit(0); 
			}
			if (!DBCreationManager.getInstance().createDatabase(st, TEST_DATABASE)){
				logger.error("Cannot create database, because encounter some problem!");
				System.exit(0); 
			}
		} catch (SQLException e) {
			logger.error("Problem with creating database", e);
			System.exit(0); 
		}
		Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHost()).getConnection();
		try(Statement st = con.createStatement()){
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
	public void tearDownAfterSuite() throws JDBCDriverException {
		try (Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
				Statement st = con.createStatement()){
				if (!DBCreationManager.getInstance().dropDatabase(st, TEST_DATABASE)){
					logger.error("Cannot delete database, because encounter some problem!");
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
		DBPopulateManager.getInstance().populateUsersTable();
		DBPopulateManager.getInstance().populateGroupsTable();
		DBPopulateManager.getInstance().populateUserGroupsTable();
		DBPopulateManager.getInstance().populateAwardsTable();
		DBPopulateManager.getInstance().populateCompetitionsTable();
		DBPopulateManager.getInstance().populateCriteriaTable();
		DBPopulateManager.getInstance().populateGroupCompetitionsTable();
		DBPopulateManager.getInstance().populateMetaDataTable();
		DBPopulateManager.getInstance().populateRolesTable();
		DBPopulateManager.getInstance().populateUserCompetitionsTable();
		logger.info("Populated All tables");	
	}
	
	@AfterClass
	public void tearDownAfterClass() throws SQLException, JDBCDriverException {
		DBPopulateManager.getInstance().deleteAllFromTables();
	}

	@Test
	public void testGetAll() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException,
			EmptyResultSetException, CloseStatementException {
		GroupServiceImpl groupService = GroupServiceImpl.getInstance();
		int partNumber = 1;
		int partSize = 2;
		List<GroupDTO> groupAll = groupService.getAll(partNumber, partSize);
		logger.info("Printing all range of GroupDTO from " + partNumber + " to " + partSize);
		logger.info("[");
		for (GroupDTO group:groupAll){
			logger.info("  "+group.getName()+"   "+group.getCount()+"   "+group.getDescriptions()+"   "
					+group.getScoreGroup()+",");
		}
		logger.info("]");
	}

	 @Test
	 public void testGetDescriptionOfGroup() throws QueryNotFoundException,
	 JDBCDriverException, DataBaseReadingException, CloseStatementException {
	 GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 2");
	 String actual = groupDTO.getDescriptions();
	 String expected = "Description of group 2";
	 assertEquals(expected, actual);
	 }

	@Test
	public void testUpdateGroupDTOStringStringString()
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException,
			EmptyResultSetException, SQLException, TransactionException {
		GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 3");
		groupDTO.setCount("44");
		groupDTO.setDescriptions("New description");
		groupDTO.setScoreGroup("50");
		GroupServiceImpl.getInstance().update(groupDTO);
		assertNotNull(groupDTO);
		assertEquals("Name group number 3", groupDTO.getName());
		assertEquals("44", groupDTO.getCount());
		assertEquals("New description", groupDTO.getDescriptions());
		assertEquals("50", groupDTO.getScoreGroup());
	}

}
