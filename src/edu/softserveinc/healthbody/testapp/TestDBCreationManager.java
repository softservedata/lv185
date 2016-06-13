package edu.softserveinc.healthbody.testapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import edu.softserveinc.healthbody.db.DBCreationManager;
import edu.softserveinc.healthbody.db.DBCreationManager.TableQueries;

public class TestDBCreationManager {

	private static Connection con = null;
	private static String username = "postgres";
	private static String password = "root";

	private static String URL = "jdbc:postgresql://localhost:5432/";
	private static String databaseName = "healthbodydb";

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println("Start...");
		DriverManager.registerDriver(new org.postgresql.Driver());
		con = DriverManager.getConnection(URL, username, password);
		if (con != null) {
			System.out.println("Connection Successful! \n");
		}
		if (con == null) {
			System.exit(0);
		}
		Statement st = con.createStatement();
		try {
			System.out.println("Trying to delete database: " + databaseName);
			DBCreationManager.getInstance().deleteDatabase(st, databaseName);
			System.out.println("Database deleted: " + databaseName);
		} catch (SQLException e) {
			if (e.getErrorCode() == 0) { // Database does not exist
				System.out.println("SQL Error...");
			} else { // Something else happened
				e.printStackTrace();
			}
		}
		try {
			System.out.println("Trying to create database: " + databaseName);
			DBCreationManager.getInstance().createDatabase(st, databaseName);
			System.out.println("Database created: " + databaseName);
		} catch (SQLException e) {
			if (e.getErrorCode() == 0) { // Database exists
				System.out.println("Database exists...");
			} else { // Something else happened
				e.printStackTrace();
			}
		}
		con = DriverManager.getConnection(URL + databaseName, username, password);
		st = con.createStatement();
		for (TableQueries query : TableQueries.values()) {
			System.out.println("Creating " + query.name());
			DBCreationManager.getInstance().createTable(st, query.toString());
		}

		if (st != null)
			st.close();
		if (con != null)
			con.close();
	}
}
