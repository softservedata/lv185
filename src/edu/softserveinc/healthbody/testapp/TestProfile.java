package edu.softserveinc.healthbody.testapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;

public class TestProfile {
	
	public static void main(String[] args) throws SQLException, JDBCDriverException, TransactionException, QueryNotFoundException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {
		
		UserDTO userDTO1 = UserProfileServiceImpl.getInstance().get("Login 3");
		System.out.println("Firstname: "+ userDTO1.getFirstname() + "\nLastname: " +userDTO1.getLastname() +
				"\nLogin: " + userDTO1.getLogin() + "\nPassword: " + userDTO1.getPassword() + "\nE-mail: " + userDTO1.getEmail() +
				"\nWeight: " + userDTO1.getWeight() + "\nAge: " + userDTO1.getAge() + "\nGender: " + userDTO1.getGender() + "\nUserRole: " + userDTO1.getRoleName());
		System.out.print("User's groups:  ");
		for (GroupDTO group : userDTO1.getGroups()){
			System.out.print(group.getName() + "    ");
		}
		
		UserDTO userDTO = UserProfileServiceImpl.getInstance().getbyId(10);
	
		System.out.println("\n\nFirstname: "+ userDTO.getFirstname() + "\nLastname: " +userDTO.getLastname() +
				"\nLogin: " + userDTO.getLogin() + "\nPassword: " + userDTO.getPassword() + "\nE-mail: " + userDTO.getEmail() +
				"\nWeight: " + userDTO.getWeight() + " \nAge: " + userDTO.getAge() + "\nGender: " + userDTO.getGender() + "\nUserRole: " + userDTO.getRoleName());
		System.out.print("User's groups:  ");
		for (GroupDTO group : userDTO.getGroups()){
			System.out.print(group.getName() + "    ");
		}
		
		
	//	userDTO.setFirstname("Ira");
	//	userDTO.setPassword("fldhglr");
		UserProfileServiceImpl.getInstance().update(userDTO);
		System.out.println("\n\nFirstname: "+ userDTO.getFirstname() + "\nLastname: " +userDTO.getLastname() +
				"\nLogin: " + userDTO.getLogin() + "\nPassword: " + userDTO.getPassword() + "\nE-mail: " + userDTO.getEmail() +
				"\nWeight: " + userDTO.getWeight() +" \nAge: " + userDTO.getAge() + "\nGender: " + userDTO.getGender() + "\nUserRole: " + userDTO.getRoleName());
		System.out.print("User's groups:  ");
		for (GroupDTO group : userDTO.getGroups()){
			System.out.print(group.getName() + "    ");
		}
		
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		groups.add(new GroupDTO("Name group number 1", "10", "Description of group 1", "11"));
		UserDTO userDTO2 = new UserDTO("Bill", "Klinton", "President", "password", "SomeMail75@gmail.com", "67", "80.5","m", "photourl", "user", "active", "1000", groups);
	//	UserProfileServiceImpl.getInstance().insert(userDTO2);
	}

}

