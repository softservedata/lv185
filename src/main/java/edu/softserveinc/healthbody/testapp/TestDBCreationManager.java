package edu.softserveinc.healthbody.testapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBPopulateManager;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class TestDBCreationManager {

	private static Logger logger = LoggerFactory.getLogger(TestDBCreationManager.class.getName());
	private static Connection con = null;
	private static String username = "postgres";
	private static String password = "root";
	private static String URL = "jdbc:postgresql://localhost:5432/";
	private static String databaseName = "healthbodydb";
	private static String dropDatabase = "false";

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
		
		// This method check if value deleteDatabase = true than before creating database we will delete 
		// this database
		Statement st = con.createStatement();
		if(dropDatabase.equals("true")){
			try {
				DBCreationManager.getInstance().dropDatabase(st, databaseName);
				logger.info("Database - " + databaseName + " was deleted");
				
			} catch (SQLException e) {
				logger.error("Database wasn't deleted", e);
				System.exit(0); 
			}
		}
		try {
			DBCreationManager.getInstance().createDatabase(st, databaseName);
			logger.info("Database - " + databaseName + " was created");
		} catch (SQLException e) {
			logger.error("Database didn't create", e);
			System.exit(0); 
		}
		
		try {
			con = DriverManager.getConnection(URL + databaseName, username, password);
			st = con.createStatement();
			DBCreationManager dbCReationManager = DBCreationManager.getInstance();
			for (String query : dbCReationManager.getListOfQueries()) {
				logger.info("Creating " + query.split("\"")[1]);
				dbCReationManager.createTable(st, query);
			}
			
		} catch (SQLException e) {
			logger.error("Error creating database tables.", e);
			System.exit(0); 
		}
		
		try {				
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
		} catch (JDBCDriverException | QueryNotFoundException | DataBaseReadingException | SQLException e) {
			logger.error("Error populating database tables.", e);
		}		
		
		if (st != null)
			st.close();
		if (con != null)
			con.close();
	}
}
