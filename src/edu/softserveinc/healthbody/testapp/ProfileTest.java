package edu.softserveinc.healthbody.testapp;

import java.sql.SQLException;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;

public class ProfileTest {
	
	public static void main(String[] args) throws SQLException, JDBCDriverException, TransactionException, QueryNotFoundException, DataBaseReadingException, CloseStatementException {
		
		UserDTO userDTO = UserProfileServiceImpl.getInstance().getbyId(3);
	//	User user = UserDao.get().getUserById(3);
		System.out.println("Login: " + userDTO.getLogin());
	}

}
