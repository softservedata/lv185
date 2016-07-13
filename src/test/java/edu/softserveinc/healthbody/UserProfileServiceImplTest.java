package edu.softserveinc.healthbody;

import static org.testng.Assert.fail;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
	private static final String EXCEPTION_CATCHED = "Exception catched while running test method.";
	
	@BeforeClass
	public void populateTestData(){
		new CreateDropTestDatabase().populateDBTables();
	}
	
	@AfterClass
	public void CleanTableAfterTest() throws SQLException, JDBCDriverException{
		DBPopulateManager.getInstance().deleteAllFromTables();
		logger.info("Aftertest block Userviewserviceimpl worked");
	}
	@Test
	public void testGetUserByLogin() {
		UserDTO userDTO1;
		try {
			userDTO1 = UserProfileServiceImpl.getInstance().get("Login 3");
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
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
	
	//User Login couldn't be null
	@Test  (expectedExceptions = IllegalArgumentException.class) 
	public void testGetUserByLoginNull() {
		try {
			UserProfileServiceImpl.getInstance().get(null);
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
	
	//Such user doesn't exist
	@Test
	public void testGetUserByLoginNotExist() {
		UserDTO userDTO;
		try {
			userDTO = UserProfileServiceImpl.getInstance().get("Marisol");
			assertEquals(null, userDTO);
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
  
	@Test
	public void testGetUserById() {
		UserDTO userDTO;
		try {
			userDTO = UserProfileServiceImpl.getInstance().getById(10);
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
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
	
	@Test
	public void testUpdateUser() {
		UserDTO userDTO2;
		try {
			userDTO2 = UserProfileServiceImpl.getInstance().getById(5);
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
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException | DataBaseReadingException | QueryNotFoundException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testUpdateUserNull() {
		UserDTO userDTO2 = null;
		try {
			UserProfileServiceImpl.getInstance().update(userDTO2);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
		
	public void testInsertUser() {
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		groups.add(new GroupDTO("Name group number 1", "10", "Description of group 1", "11"));
		UserDTO userDTO3 = new UserDTO("President", "password", "Bill", "Klinton", "SomeMail75@gmail.com", "67", "80.5","m", "photourl", "user", "active", "1000", groups, "false");
	 	try {
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
	 	} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
	 		logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testInsertUserNull() {
		UserDTO userDTO3 = null;
		try {
			UserProfileServiceImpl.getInstance().insert(userDTO3);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
					
	@Test
	public void testLockUser() {
		UserDTO userDTO5;
		try {
			userDTO5 = UserProfileServiceImpl.getInstance().get("Login 2");
			userDTO5.setIsDisabled("true");
			UserProfileServiceImpl.getInstance().lock(userDTO5, true);
			assertEquals("true", userDTO5.getIsDisabled());
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException | QueryNotFoundException | DataBaseReadingException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
		
	}
	
	//You didn't enter user
	@Test (expectedExceptions = IllegalArgumentException.class)
	public void testLockUserNull() {
		UserDTO userDTO5 = null;
		try {
			UserProfileServiceImpl.getInstance().lock(userDTO5, true);
		} catch (SQLException | JDBCDriverException | QueryNotFoundException | DataBaseReadingException
				| TransactionException | CloseStatementException e) {
			logger.error(EXCEPTION_CATCHED, e);
			fail(EXCEPTION_CATCHED, e);
		}
	}
}
