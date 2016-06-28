package com.softserveinc.healthbody;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;

public class ProfileTest {
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
	  
	  System.out.println("\n\nFirstname: "+ userDTO1.getFirstname() + "\nLastname: " +userDTO1.getLastname() +
				"\nLogin: " + userDTO1.getLogin() + "\nPassword: " + userDTO1.getPassword() + "\nE-mail: " + userDTO1.getEmail() +
				"\nWeight: " + userDTO1.getWeight() + "\nAge: " + userDTO1.getAge() + "\nGender: " + userDTO1.getGender() + "\nUserRole: " + userDTO1.getRoleName());
		System.out.print("User's groups:  ");
		for (GroupDTO group : userDTO1.getGroups()) {
			System.out.print(group.getName() + "     ");
		}
  }
  @Test
  public void testGetUserById() throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException {
	
	  UserDTO userDTO = UserProfileServiceImpl.getInstance().getbyId(10);
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
	  
		System.out.println("\n\nFirstname: "+ userDTO.getFirstname() + "\nLastname: " +userDTO.getLastname() +
				"\nLogin: " + userDTO.getLogin() + "\nPassword: " + userDTO.getPassword() + "\nE-mail: " + userDTO.getEmail() +
				"\nWeight: " + userDTO.getWeight() + " \nAge: " + userDTO.getAge() + "\nGender: " + userDTO.getGender() + "\nUserRole: " + userDTO.getRoleName());
		System.out.print("User's groups:  ");
		for (GroupDTO group : userDTO.getGroups()) {
			System.out.print(group.getName() + "     ");
		}
  }
  @Test
  public void testUpdateUser() throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException, DataBaseReadingException, QueryNotFoundException {
	  
	  UserDTO userDTO2 = UserProfileServiceImpl.getInstance().getbyId(5);
	  
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
	  
		System.out.println("\n\nFirstname: "+ userDTO2.getFirstname() + "\nLastname: " +userDTO2.getLastname() +
				"\nLogin: " + userDTO2.getLogin() + "\nPassword: " + userDTO2.getPassword() + "\nE-mail: " + userDTO2.getEmail() +
				"\nWeight: " + userDTO2.getWeight() + " \nAge: " + userDTO2.getAge() + "\nGender: " + userDTO2.getGender() + "\nUserRole: " + userDTO2.getRoleName());
		System.out.print("User's groups:  ");
		for (GroupDTO group : userDTO2.getGroups()) {
			System.out.print(group.getName() + "     ");
		}
  }
  @Test
  public void testInsertUser() throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
	  List<GroupDTO> groups = new ArrayList<GroupDTO>();
	  groups.add(new GroupDTO("Name group number 1", "10", "Description of group 1", "11"));
	  UserDTO userDTO3 = new UserDTO("President", "password", "Bill", "Klinton", "SomeMail75@gmail.com", "67", "80.5","m", "photourl", "user", "active", "1000", groups);
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
	  	  
	  System.out.println("\n\nFirstname: "+ userDTO4.getFirstname() + "\nLastname: " +userDTO4.getLastname() +
				"\nLogin: " + userDTO4.getLogin() + "\nPassword: " + userDTO4.getPassword() + "\nE-mail: " + userDTO4.getEmail() +
				"\nWeight: " + userDTO4.getWeight() + "\nAge: " + userDTO4.getAge() + "\nGender: " + userDTO4.getGender() + "\nUserRole: " + userDTO4.getRoleName());
	  System.out.print("User's groups:  ");
		for (GroupDTO group : userDTO4.getGroups()) {
			System.out.print(group.getName() + "     ");
		}
  }
}
