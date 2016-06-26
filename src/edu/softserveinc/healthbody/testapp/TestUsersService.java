package edu.softserveinc.healthbody.testapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UsersServiceImpl;

public class TestUsersService {
	
	private static Logger logger = LoggerFactory.getLogger(TestUsersService.class.getName());
	private static Connection con = null;
	static Map<String, String> filters = new HashMap<String, String>();

	public static void main(String[] args) throws SQLException, ClassNotFoundException, QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException, TransactionException {
			
		logger.info("TestUsersService starts");

		UsersServiceImpl us = new UsersServiceImpl();
		
		List<UserDTO> ud1 = us.getAll(1, 5, filters);
		List<UserDTO> ud2 = us.getAllbyAdmin(1, 5, filters);
		List<UserDTO> ud3 = us.getAllinCompetition(1, 5, filters);
		List<UserDTO> ud4 = us.getAllinGroup(1, 5, filters);
		List<UserDTO> ud5 = us.getAlltoAddInCompetition(1, 5, filters);
		
		System.out.println("In getAll: " + Arrays.toString(ud1.toArray()));
		System.out.println("In getAllbyAdmin: " + Arrays.toString(ud2.toArray()));
		System.out.println("In getAllinCompetition: " + Arrays.toString(ud3.toArray()));
		System.out.println("In getAllinGroup: " + Arrays.toString(ud4.toArray()));
		System.out.println("In getAllinGroup: " + Arrays.toString(ud5.toArray()));

		DriverManager.registerDriver(new org.postgresql.Driver());
		if (con != null) {
			logger.info("Connection Successful! \n");
		}
		if (con == null) {
			System.exit(0);
		}
		Statement st = con.createStatement();
		logger.info("Using database...");
		st = con.createStatement();
		
		if (st != null) {
			st.close();
		}
		if (con != null) {
			con.close();
		}
	}
	
}