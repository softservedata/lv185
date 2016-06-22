package edu.softserveinc.healthbody.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.softserveinc.healthbody.exceptions.CloseStatementException;

public class CloseHelper {
	
	private final static String FAILED_CLOSE = "Failed to close";
	
	public static void close(PreparedStatement pst) throws CloseStatementException {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                throw new CloseStatementException(FAILED_CLOSE, e);
            }
        }
    }
 
    public static void close(ResultSet resultSet) throws CloseStatementException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
            	throw new CloseStatementException(FAILED_CLOSE, e);
            }
        }
    }
}


