package edu.softserveinc.healthbody;

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
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class UserViewTest {
	private static Logger logger = LoggerFactory.getLogger(UserViewTest.class.getName());
	
	@BeforeSuite
	public void setUpBeforeSuite() {
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
	public void testUserViewGetAll() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		try {
			List<UserDTO> ud1 = uvs.getAll(1, 5);
			logger.info("In getAll: " + Arrays.toString(ud1.toArray()));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("GetAll didn't work", e);
		}
	}
	
	//List<UserDTO> with such partNumber doesn't exist
	@Test (expectedExceptions = TransactionException.class)
	public void testGetListUserDTOByPartNumberNotExist() throws JDBCDriverException, SQLException, TransactionException {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
			List<UserDTO> ud2 = uvs.getAll(1150, 5);
			logger.info("In testGetListUserDTOByPartNumberNotExist: " + Arrays.toString(ud2.toArray()));
	}
	
	@Test
	public void testUserViewGetAllbyAdmin() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		try {
			List<UserDTO> ud3 = uvs.getAllbyAdmin(1, 5);
			logger.info("In getAllbyAdmin: " + Arrays.toString(ud3.toArray()));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("GetAllbyAdmin didn't work", e);
		}
	}
	
	@Test
	public void testUserViewGetAllinCompetition() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		try {
			List<UserDTO> ud4 = uvs.getAllinCompetition(1, 5);
			logger.info("In getAllinCompetition: " + Arrays.toString(ud4.toArray()));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("GetAllinCompetition didn't work", e);
		}
	}
	
	@Test
	public void testUserViewGetAllinGroup() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		try {
			List<UserDTO> ud5 = uvs.getAllinGroup(1, 5);
			logger.info("In getAllinGroup: " + Arrays.toString(ud5.toArray()));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("GetAllinGroup didn't work", e);
		}
	}
	
	@Test
	public void testUserViewGetAlltoAddInCompetition() {
		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		try {
			List<UserDTO> ud6 = uvs.getAlltoAddInCompetition(1, 5);
			logger.info("In getAlltoAddInCompetition: " + Arrays.toString(ud6.toArray()));
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("GetAlltoAddInCompetition didn't work", e);
		}
	}

}
