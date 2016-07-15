package edu.softserveinc.healthbody.testapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBPopulateManager;

public class TestDBCreationManager {
	
	private static final String DELETE_CREATE_DATABASE_ERROR = "Problem with creating or deleting database";
	private static final String DELETE_DATABASE_ERROR = "Problem with deleting database";
	private static final String CREATE_DATABASE_ERROR = "Problem with creating database";
	private static final String CREATE_TABLEE_ERROR = "Problem with creating tables and populating data";

	private static Logger logger = LoggerFactory.getLogger(TestDBCreationManager.class.getName());
	private static String username = "postgres";
	private static String password = "root";
	private static String URL = "jdbc:postgresql://localhost:5432/";
	private static String databaseName = "healthbodydb";
	private static String dropDatabase = "true";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		logger.info("TestDBCreationManager starts...");
		DriverManager.registerDriver(new org.postgresql.Driver());

		// This method check if value deleteDatabase = true than before creating database we will delete this database
		try (Connection con = DriverManager.getConnection(URL, username, password);
				Statement st = con.createStatement()){
			if(dropDatabase.equals("true")){
				if (!DBCreationManager.getInstance().dropDatabase(st, databaseName)){
					logger.info("Couldn't delete database, because encounter some problem!");
					throw new RuntimeException(DELETE_DATABASE_ERROR);
				}
			} else {
				logger.error("Database wasn't deleted");			
			}
			if (!DBCreationManager.getInstance().createDatabase(st, databaseName)){
				logger.info("Couldn't create database, because encounter some problem!");
				throw new RuntimeException(CREATE_DATABASE_ERROR);
			}
		} catch (SQLException e) {
			logger.error(DELETE_CREATE_DATABASE_ERROR, e);
			throw new RuntimeException(DELETE_CREATE_DATABASE_ERROR, e); 
		}

		try(Connection con = DriverManager.getConnection(URL + databaseName, username, password);
				Statement st = con.createStatement()){
			DBCreationManager dbCReationManager = DBCreationManager.getInstance();
			for (String query : dbCReationManager.getListOfQueries()) {
				logger.info("Creating " + query.split("\"")[1]);
				dbCReationManager.createTable(st, query);
			}
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
		} catch (SQLException e) {
			logger.error(CREATE_TABLEE_ERROR, e);
			throw new RuntimeException(CREATE_TABLEE_ERROR, e);  
		}
	}
}
