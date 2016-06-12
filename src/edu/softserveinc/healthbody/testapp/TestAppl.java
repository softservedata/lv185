package edu.softserveinc.healthbody.testapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBCreationManager.TableQueries;
import edu.softserveinc.helthbody.log.Log;

public class TestAppl {
	//TODO
	private static Logger logger = Log.init(TestAppl.class.getName());
	private static Connection con = null;
	private static String username = "postgres";
	private static String password = "root";
	private static String URL = "jdbc:postgresql://localhost:5432/";
	private static String databaseName = "healthbodydb";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		logger.info("TestAppl starts...");
		DriverManager.registerDriver(new org.postgresql.Driver());

		con = DriverManager.getConnection(URL, username, password);
		if (con != null) {
			logger.info("Connection Successful! \n");
		}
		if (con == null) {
			System.exit(0);
		}
		Statement st = con.createStatement();
		try {
			DBCreationManager.getInstance().createDatabase(st);
			logger.info("Database created...");
		} catch (SQLException e) {
			if (e.getErrorCode() == 0) { // Database exists
				logger.info("Database exists...");
			} else { // Something else happened
				logger.error("Database didn't create", e);
			}
		}
		con = DriverManager.getConnection(URL + databaseName, username, password);
		logger.info("Using database...");
		for (TableQueries query : TableQueries.values()) {
			//And here we crush..... 
			//TODO Check correctness of SQL Queries in DBCreationManager
			DBCreationManager.getInstance().createTable(st, query);
		}
		logger.info("Tables created in database...");
		if (st != null) {
			st.close();
		}
		if (con != null) {
			con.close();
		}
	}

}
