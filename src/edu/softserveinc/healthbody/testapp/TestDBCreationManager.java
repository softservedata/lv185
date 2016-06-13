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
			DBCreationManager.getInstance().createDatabase(st, databaseName);
			System.out.println("Database - "+databaseName+" was created");
		
			for (TableQueries query : TableQueries.values()) {
				System.out.println("Creating " + query.name());
				DBCreationManager.getInstance().createTable(st, query.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (st != null)
			st.close();
		if (con != null)
			con.close();
	}
}
