package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.softserveinc.healthbody.dao.DaoStatementsConstant;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class DBPopulateManager {
	
	private static volatile DBPopulateManager instance = null;

	private DBPopulateManager() {
	}

	public static DBPopulateManager getInstance() {
		if (instance == null) {
			synchronized (DBPopulateManager.class) {
				if (instance == null) {
					instance = new DBPopulateManager();
				}
			}
		}
		return instance;
	}
	
	public boolean populateUserTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 10; j++){
				pst.setString(1, "Login "+j);	
				pst.setString(2, "password "+j);
				pst.setString(3, "Name of "+j+" user");	
				pst.setString(4, "LastName of "+j+" user");
				pst.setString(5, "SomeMail"+j+"@gmail.com");	
				pst.setInt(6, 25+j);
				pst.setDouble(7, 55.6+j);	
				pst.setString(8, (j%2 == 0) ? "m":"w") ;
				pst.setString(9, "health "+j);
				pst.setString(10, "urlavatar "+j);	
				pst.setString(11, "googleApi "+j);
				pst.setInt(12, 3);	
				pst.setString(13, "active "+j);
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating database tables.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateGroupTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.GroupDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 3; j++){				
				pst.setString(1, "Name group number "+j);	
				pst.setString(2, "Description of group "+j);
				pst.setString(3, "active");	
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating database tables.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateUserGroupTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserGroupQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 15; j++){
				pst.setInt(1, (j%10==0) ? 10 : j%10);	
				pst.setInt(2, (j<=8) ? 1 : (j<=12 ? 3 : 2));
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating database tables.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateAwordTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.AwardDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 4; j++){
				pst.setString(1, "Name aword "+j);
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating database tables.");
		}
				
		return successfulInsert;
	}

}
