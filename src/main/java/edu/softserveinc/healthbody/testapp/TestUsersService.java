package edu.softserveinc.healthbody.testapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class TestUsersService {
	
	private static Logger logger = LoggerFactory.getLogger(TestUsersService.class.getName());
	private static Connection con = null;

	public static void main(String[] args) throws SQLException, ClassNotFoundException, QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException, TransactionException {
			
		logger.info("TestUsersService starts");

		UsersViewServiceImpl uvs = new UsersViewServiceImpl();
		
		List<UserDTO> ud1 = uvs.getAll(1, 5);
		List<UserDTO> ud2 = uvs.getAllbyAdmin(1, 5);
		List<UserDTO> ud3 = uvs.getAllinCompetition(1, 5);
		List<UserDTO> ud4 = uvs.getAllinGroup(1, 5);
		List<UserDTO> ud5 = uvs.getAlltoAddInCompetition(1, 5);
		
		logger.info("In getAll: " + Arrays.toString(ud1.toArray()));
		logger.info("In getAllbyAdmin: " + Arrays.toString(ud2.toArray()));
		logger.info("In getAllinCompetition: " + Arrays.toString(ud3.toArray()));
		logger.info("In getAllinGroup: " + Arrays.toString(ud4.toArray()));
		logger.info("In getAllinGroup: " + Arrays.toString(ud5.toArray()));

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