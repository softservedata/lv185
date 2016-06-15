package edu.softserveinc.healthbody.testapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBCreationManager.TableQueries;

public class TestDBCreationManager {

	private static Logger logger = LogManager.getLogger(TestDBCreationManager.class.getName());
	private static Connection con = null;
	private static String username = "postgres";
	private static String password = "root";
	private static String URL = "jdbc:postgresql://localhost:5432/";
	private static String databaseName = "healthbodydb";
	private static String deleteDatabase = "false";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		logger.info("TestDBCreationManager starts...");
		DriverManager.registerDriver(new org.postgresql.Driver());
		con = DriverManager.getConnection(URL, username, password);
		if (con != null) {
			logger.info("Connection Successful! \n");
		}
		if (con == null) {
			System.exit(0);
		}
		
		// This method check if value deleteDatabase = true than before creating database we delete this database
		Statement st = con.createStatement();
		if("true".equals(deleteDatabase)){
			try {
				DBCreationManager.getInstance().deleteDatabase(st, databaseName);
				logger.info("Database - " + databaseName + " was deleted");
			} catch (SQLException e) {
				logger.error("Database wasn't deleted", e);
			}
		}

		try {
			DBCreationManager.getInstance().createDatabase(st, databaseName);
			logger.info("Database - " + databaseName + " was created");
		} catch (SQLException e) {
			logger.error("Database didn't create", e);
		}
		
		try {
			con = DriverManager.getConnection(URL + databaseName, username, password);
			st = con.createStatement();
			for (TableQueries query : TableQueries.values()) {
				logger.info("Creating " + query.name());
				DBCreationManager.getInstance().createTable(st, query.toString());
			}
		} catch (SQLException e) {
			logger.error("Error creating database tables.", e);

		}

		if (st != null)
			st.close();
		if (con != null)
			con.close();
	}
}
