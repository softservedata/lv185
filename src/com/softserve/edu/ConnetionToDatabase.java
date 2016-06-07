package com.softserve.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnetionToDatabase {
//	initialization
	 private static Connection con = null;
	    private static String username = "postgres";
	    private static String password = "Marot1165";
	    private static String URL = "jdbc:postgresql://localhost:5432/HealthLife";
	    
	    public static void main(String[] args) throws SQLException, ClassNotFoundException {

	    	//print that program is started
	    	
	    	System.out.println("Start...");

//	    	Requires that you initialize a driver so you can open a communications channel with the database.
	    	DriverManager.registerDriver(new org.postgresql.Driver());
	        
//	    	Requires using the DriverManager.getConnection() method 
//	    	to create a Connection object, which represents a physical connection with the database.
//			sub-protocol for connection driver manager with specific driver 
	        con = DriverManager.getConnection(URL, username, password);
	        if (con != null) {
	            System.out.println("Connection Successful! \n"); }
	        if (con == null) {
	            System.exit(0); }
	  	        
	        Statement st = con.createStatement();
	
	        ResultSet rs = st.executeQuery("SELECT * FROM roles");
	       
	        int columnCount = rs.getMetaData().getColumnCount();
	        while (rs.next()) {
	            for (int i = 1; i <= columnCount; i++) {
	                System.out.print(rs.getString(i) + "\t");
	            }
	            System.out.println();
	        }
	        System.out.println();
	        if (rs != null)
	            rs.close();
	        if (st != null)
	            st.close();
	        if (con != null)
	            con.close();
	    }
	}

