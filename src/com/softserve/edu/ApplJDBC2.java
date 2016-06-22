package com.softserve.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplJDBC2 {
    private static Connection con = null;
    private static String username = "root";
    private static String password = "root";
    //private static String URL = "jdbc:mysql://localhost:3306/lv185";
    ////private static String URL = "jdbc:mysql://localhost:3306";
    private static String URL = "jdbc:mysql://localhost:3306/lv185";
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Start...");
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        con = DriverManager.getConnection(URL, username, password);
        if (con != null) {
            System.out.println("Connection Successful! \n"); }
        if (con == null) {
            System.exit(0); }
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        ////st.execute("CREATE SCHEMA `proba`");
        // Add
        st.execute("INSERT INTO users (id_role, login, passwd) VALUES (1,'ivan3','1234');");
        //st.execute("INSERT INTO users (id_role, login, passwd) VALUES (1,'ivan2','12346');");
        //
        System.out.println();
        if (st != null)
            st.close();
        con.commit();
        //
        // Add
        st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users;");
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
        //
        if (con != null)
            con.close();
    }

}
