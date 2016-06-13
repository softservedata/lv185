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
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Start...");
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection(URL, username, password);
        if (con != null) {
            System.out.println("Connection Successful! \n"); }
        if (con == null) {
            System.exit(0); }
        Statement st = con.createStatement();
        DBCreationManager.getInstance().createDB(st, "health");
        DBCreationManager.getInstance().createTable(st, TableQueries.USERS_TABEL.toString());
        DBCreationManager.getInstance().createTable(st, TableQueries.GROUPS_TABEL.toString());
        DBCreationManager.getInstance().createTable(st, TableQueries.COMPETION_TABEL.toString());
        DBCreationManager.getInstance().createTable(st, TableQueries.USER_GROUP_TABEL.toString());

        if (st != null)
            st.close();
        if (con != null)
            con.close();
    }
}
