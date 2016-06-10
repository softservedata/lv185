package com.softserve.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplJDBC2 {
    private static Connection con = null;
    private static String username = "root";
    private static String password = "root";
    //private static String URL = "jdbc:mysql://localhost:3306/lv185";
    private static String URL = "jdbc:mysql://localhost:3306";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Start...");
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        con = DriverManager.getConnection(URL, username, password);
        if (con != null) {
            System.out.println("Connection Successful! \n"); }
        if (con == null) {
            System.exit(0); }
        Statement st = con.createStatement();
        st.execute("CREATE SCHEMA `proba`");
        //
        System.out.println();
        if (st != null)
            st.close();
        if (con != null)
            con.close();
    }

}
