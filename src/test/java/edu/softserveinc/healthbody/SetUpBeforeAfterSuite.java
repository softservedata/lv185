package edu.softserveinc.healthbody;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Optional;

import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DataSourceRepository;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;


public class SetUpBeforeAfterSuite {
	//Before suite
	public void setUpBeforeSuite(@Optional("healthbodydbtest") String testdatabase) throws JDBCDriverException {
		logger.info("Setting up database...");
		try (Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
				Statement st = con.createStatement()) {
			if (!DBCreationManager.getInstance().dropDatabase(st, testdatabase)) {
				String failMessage = "Test database does not exist.";
				logger.info(failMessage);
				//Not epic fail :) Let's go ahead.
				//fail(failMessage);
			}
			if (!DBCreationManager.getInstance().createDatabase(st, testdatabase)){
				String failMessage = "Couldn't create test database.";
				logger.error(failMessage);
				fail(failMessage);
			}
		} catch (SQLException e) {
			String failMessage = "Problem with deleting/creating database.";
			logger.error(failMessage);
			fail(failMessage, e);
		}
		Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostTest()).getConnection();
		try (Statement st = con.createStatement()) {
			DBCreationManager dbCReationManager = DBCreationManager.getInstance();
			for (String query : dbCReationManager.getListOfQueries()) {
				logger.info("Creating table " + query.split("\"")[1]);
				dbCReationManager.createTable(st, query);
			}
		} catch (SQLException e) {
			String failMessage = "Problem with creating tables data";
			logger.error(failMessage);
			fail(failMessage, e);
		}
		logger.info("Setting up database ends successfully...");
	}

	// After Suite	
	public void tearDownAfterSuite(@Optional("healthbodydbtest") String testdatabase) throws JDBCDriverException {
		Connection con = ConnectionManager.getInstance(DataSourceRepository.getInstance().getPostgresLocalHostNoDatabase()).getConnection();
		try (Statement st = con.createStatement()){
			if (!DBCreationManager.getInstance().dropDatabase(st, testdatabase)) {
				String failMessage = "Couldn't delete test database.";
				logger.error(failMessage);
				fail(failMessage);
			} else {
				logger.info("Database was deleted");
			}
		} catch (SQLException e) {
			String failMessage = "Problem with deleting test database.";
			logger.error(failMessage);
			fail(failMessage, e);
		}
	}
}
