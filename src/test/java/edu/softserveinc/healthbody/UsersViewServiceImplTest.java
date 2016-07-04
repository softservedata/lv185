package edu.softserveinc.healthbody;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class UsersViewServiceImplTest {
	private static Logger logger = LoggerFactory.getLogger(UsersViewServiceImplTest.class.getName());
	
	@BeforeSuite
	public void setUpBeforeSuite() {
		try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "root");
				Statement st = con.createStatement()){
				if (!DBCreationManager.getInstance().dropDatabase(st, "healthbodydb")){
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
		try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "root");
				Statement st = con.createStatement()){
			if (!DBCreationManager.getInstance().createDatabase(st, "healthbodydb")){
				logger.error("Couldn't create database, because encounter some problem!");
				System.exit(0); 
			}
		} catch (SQLException e) {
			logger.error("Problem with creating database", e);
			System.exit(0); 
		}
		try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/healthbodydb", "postgres", "root");
				Statement st = con.createStatement()){
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
	public void tearDownAfterSuite() {
		try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "root");
				Statement st = con.createStatement()){
				if (!DBCreationManager.getInstance().dropDatabase(st, "healthbodydb")){
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
	public void testUserViewGetAll() throws JDBCDriverException, SQLException, TransactionException {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud1 = uvs.getAll(1, 2);
		logger.info("testUserViewGetAll");
		logger.info(ud1.toString());
		assertNotNull(ud1);
		assertEquals(ud1.size(), 2);
	}
	
	//List<UserDTO> with such partNumber doesn't exist
	@Test (expectedExceptions = TransactionException.class)
	public void testGetListUserDTOByPartNumberNotExist() throws JDBCDriverException, SQLException, TransactionException {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
			List<UserDTO> ud2 = uvs.getAll(1150, 5);
			logger.info("In testGetListUserDTOByPartNumberNotExist: " + Arrays.toString(ud2.toArray()));
	}
	
	@Test
	public void testUserViewGetAllbyAdmin() throws JDBCDriverException, SQLException, TransactionException {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud3 = uvs.getAllbyAdmin(1, 2);
		logger.info("testUserViewGetAllbyAdmin");
		logger.info(ud3.toString());
		assertNotNull(ud3);
		assertEquals(ud3.size(), 2);	
	}
	
	@Test
	public void testUserViewGetAllinCompetition() throws JDBCDriverException, SQLException, TransactionException {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud4 = uvs.getAllinCompetition(1, 2);
		logger.info("testUserViewGetAllinCompetition");
		logger.info(ud4.toString());
		assertNotNull(ud4);
		assertEquals(ud4.size(), 2);	
	}
	
	@Test
	public void testUserViewGetAllinGroup() throws JDBCDriverException, SQLException, TransactionException {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud5 = uvs.getAllinGroup(1, 2);
		logger.info("testUserViewGetAllinGroup");
		logger.info(ud5.toString());
		assertNotNull(ud5);
		assertEquals(ud5.size(), 2);	
	}
	
	@Test
	public void testUserViewGetAlltoAddInCompetition() throws JDBCDriverException, SQLException, TransactionException {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		List<UserDTO> ud6 = uvs.getAlltoAddInCompetition(1, 2);
		logger.info("testUserViewGetAlltoAddInCompetition");
		logger.info(ud6.toString());
		assertNotNull(ud6);
		assertEquals(ud6.size(), 2);	
	}

}
