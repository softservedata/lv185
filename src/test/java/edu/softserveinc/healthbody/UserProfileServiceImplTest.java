package edu.softserveinc.healthbody;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;

public class UserProfileServiceImplTest {

	private static Logger logger = LoggerFactory.getLogger(UserProfileServiceImplTest.class.getName());
  
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
	public void testGetUserByLogin() throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException {
		UserDTO userDTO1 = UserProfileServiceImpl.getInstance().get("Login 3");
		assertNotNull(userDTO1);
		assertEquals("Name of 3 user", userDTO1.getFirstname());
		assertEquals("LastName of 3 user", userDTO1.getLastname());
	 	assertEquals("Login 3", userDTO1.getLogin());
	 	assertEquals("password 3", userDTO1.getPassword());
	 	assertEquals("SomeMail3@gmail.com", userDTO1.getEmail());
	 	assertEquals("58.5999985", userDTO1.getWeight());
	 	assertEquals("28", userDTO1.getAge());
	 	assertEquals("w", userDTO1.getGender());
	 	assertEquals("user", userDTO1.getRoleName());
	 	logger.info("\n\nFirstname: "+ userDTO1.getFirstname() + "\nLastname: " +userDTO1.getLastname() +
				"\nLogin: " + userDTO1.getLogin() + "\nPassword: " + userDTO1.getPassword() + "\nE-mail: " + userDTO1.getEmail() +
				"\nWeight: " + userDTO1.getWeight() + "\nAge: " + userDTO1.getAge() + "\nGender: " + userDTO1.getGender() + "\nUserRole: " + userDTO1.getRoleName());
	 	logger.info("User's groups:  ");
	 	for (GroupDTO group : userDTO1.getGroups()) {
	 		logger.info(group.getName() + "     ");
	 	}
	}
	
	//User Login couldn't be null
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testGetUserByLoginNull() throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException {
		UserProfileServiceImpl.getInstance().get(null);
	}
	
	//Such user doesn't exist
	@Test
	public void testGetUserByLoginNotExist() throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException {
		UserDTO userDTO = UserProfileServiceImpl.getInstance().get("Marisol");
		assertEquals(null, userDTO);
	}
  
	@Test
	public void testGetUserById() throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException {
		UserDTO userDTO = UserProfileServiceImpl.getInstance().getById(10);
		assertNotNull(userDTO);
		assertEquals("Name of 10 user", userDTO.getFirstname());
		assertEquals("LastName of 10 user", userDTO.getLastname());
		assertEquals("Login 10", userDTO.getLogin());
		assertEquals("password 10", userDTO.getPassword());
		assertEquals("SomeMail10@gmail.com", userDTO.getEmail());
	 	assertEquals("65.5999985", userDTO.getWeight());
	 	assertEquals("35", userDTO.getAge());
	 	assertEquals("m", userDTO.getGender());
	 	assertEquals("user", userDTO.getRoleName());
	    logger.info("\n\nFirstname: "+ userDTO.getFirstname() + "\nLastname: " +userDTO.getLastname() +
				"\nLogin: " + userDTO.getLogin() + "\nPassword: " + userDTO.getPassword() + "\nE-mail: " + userDTO.getEmail() +
				"\nWeight: " + userDTO.getWeight() + " \nAge: " + userDTO.getAge() + "\nGender: " + userDTO.getGender() + "\nUserRole: " + userDTO.getRoleName());
	    logger.info("User's groups:  ");
		for (GroupDTO group : userDTO.getGroups()) {
			logger.info(group.getName() + "     ");
		}
	}
	
	@Test
	public void testUpdateUser() throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException, DataBaseReadingException, QueryNotFoundException {
		UserDTO userDTO2 = UserProfileServiceImpl.getInstance().getById(5);
		userDTO2.setAge("56");
		userDTO2.setFirstname("Ivan");
		userDTO2.setPassword("heufrb"); 
		UserProfileServiceImpl.getInstance().update(userDTO2);
	    assertNotNull(userDTO2);
	    assertEquals("Ivan", userDTO2.getFirstname());
	    assertEquals("LastName of 5 user", userDTO2.getLastname());
	    assertEquals("Login 5", userDTO2.getLogin());
	    assertEquals("heufrb", userDTO2.getPassword());
	    assertEquals("SomeMail5@gmail.com", userDTO2.getEmail());
	    assertEquals("60.5999985", userDTO2.getWeight());
	    assertEquals("56", userDTO2.getAge());
	    assertEquals("w", userDTO2.getGender());
	    assertEquals("user", userDTO2.getRoleName());
	    logger.info("\n\nFirstname: "+ userDTO2.getFirstname() + "\nLastname: " +userDTO2.getLastname() +
				"\nLogin: " + userDTO2.getLogin() + "\nPassword: " + userDTO2.getPassword() + "\nE-mail: " + userDTO2.getEmail() +
				"\nWeight: " + userDTO2.getWeight() + " \nAge: " + userDTO2.getAge() + "\nGender: " + userDTO2.getGender() + "\nUserRole: " + userDTO2.getRoleName());
	    logger.info("User's groups:  ");
		for (GroupDTO group : userDTO2.getGroups()) {
			logger.info(group.getName() + "     ");
		}
	}
	
	//You didn't enter user
		@Test (expectedExceptions = IllegalArgumentException.class)
		public void testUpdateUserNull() throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException, DataBaseReadingException, QueryNotFoundException {
			UserDTO userDTO2 = null;
			UserProfileServiceImpl.getInstance().update(userDTO2);
		}
		
	@Test
	public void testInsertUser() throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		groups.add(new GroupDTO("Name group number 1", "10", "Description of group 1", "11"));
		UserDTO userDTO3 = new UserDTO("President", "password", "Bill", "Klinton", "SomeMail75@gmail.com", "67", "80.5","m", "photourl", "user", "active", "1000", groups, "false");
	 	UserProfileServiceImpl.getInstance().insert(userDTO3);
	 	UserDTO userDTO4 = UserProfileServiceImpl.getInstance().get("President");
	 	assertNotNull(userDTO4);
	 	assertEquals("Bill", userDTO4.getFirstname());
	 	assertEquals("Klinton", userDTO4.getLastname());
	 	assertEquals("President", userDTO4.getLogin());
	 	assertEquals("password", userDTO4.getPassword());
	 	assertEquals("SomeMail75@gmail.com", userDTO4.getEmail());
	 	assertEquals("80.5", userDTO4.getWeight());
	 	assertEquals("67", userDTO4.getAge());
	 	assertEquals("m", userDTO4.getGender());
	 	logger.info("\n\nFirstname: "+ userDTO4.getFirstname() + "\nLastname: " +userDTO4.getLastname() +
				"\nLogin: " + userDTO4.getLogin() + "\nPassword: " + userDTO4.getPassword() + "\nE-mail: " + userDTO4.getEmail() +
				"\nWeight: " + userDTO4.getWeight() + "\nAge: " + userDTO4.getAge() + "\nGender: " + userDTO4.getGender() + "\nUserRole: " + userDTO4.getRoleName());
	 	logger.info("User's groups:  ");
		for (GroupDTO group : userDTO4.getGroups()) {
			logger.info(group.getName() + "     ");
		}
		UserProfileServiceImpl.getInstance().test_delete(userDTO4);
		logger.info("Delete user from database for test");
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testInsertUserNull() throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		UserDTO userDTO3 = null;
		UserProfileServiceImpl.getInstance().insert(userDTO3);
	}
					
	@Test
	public void testLockUser() throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException, QueryNotFoundException, DataBaseReadingException {
		UserDTO userDTO5 = UserProfileServiceImpl.getInstance().get("Login 2");
		userDTO5.setIsDisabled("true");
		UserProfileServiceImpl.getInstance().lock(userDTO5, true);
		assertEquals("true", userDTO5.getIsDisabled());
		
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testLockUserNull() throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		UserDTO userDTO5 = null;
		UserProfileServiceImpl.getInstance().lock(userDTO5, true);
	}
	
	//You entered incorrect isDisabled
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testLockUserIncorrectIsDisabled() throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		UserDTO userDTO5 = UserProfileServiceImpl.getInstance().get("Login 6");
		userDTO5.setIsDisabled("false");
		UserProfileServiceImpl.getInstance().lock(userDTO5, false);
	}
}
