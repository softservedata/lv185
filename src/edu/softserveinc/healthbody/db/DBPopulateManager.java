package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

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
	
	public boolean populateUsersTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
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
			System.out.println("Error populating users table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateGroupsTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
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
			System.out.println("Error populating groups table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateUsersGroupsTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserGroupQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 15; j++){
				pst.setInt(1, (j%10==0) ? 10 : j%10);	
				pst.setInt(2, (j<=8) ? 1 : (j<=12 ? 3 : 2));
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating usersgroups table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateAwordsTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.AwardDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 4; j++){
				pst.setString(1, "Name aword "+j);
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating awords table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateCompetitionsTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.CompetitionDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 3; j++){				
				pst.setString(1, "Name competition "+j);	
				pst.setString(2, "Description of competition "+j);
				pst.setDate(3, new Date(System.currentTimeMillis()));
				pst.setDate(4, new Date(System.currentTimeMillis()-30*24*60*60*1000));
				pst.setInt(5, (int)(Math.random()*2+1));	
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating competitions table.");
		}
				
		return successfulInsert;
	}

	public boolean populateCriteriaTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.CriteriaDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 4; j++){				
				pst.setString(1, "Name criteria "+j);	
				pst.setDouble(2, 4.5+j);
				pst.setString(3, "get google "+j);	
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating criteria table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateGroupsCompetitionsTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.GroupCompetitionsDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 5; j++){
				pst.setInt(1, (j%2==0) ? 1 : (j%3==0 ? 3 : 2));	
				pst.setInt(2, (j%2==0) ? 2 : (j%3==0 ? 1 : 3));
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating groupscompetitions table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateMetaDataTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.MetaDataDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 8; j++){
				pst.setString(1, "meta data "+j);
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating metadata table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateRolesTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.RoleDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 3; j++){				
				pst.setString(1, j==1 ? "admin" : (j==2 ? "manager" : "user"));	
				pst.setString(2, j==1 ? "admin description" : (j==2 ? "manager description" : "user description"));
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating roles table.");
		}
				
		return successfulInsert;
	}
	
	public boolean populateUsersCompetitionsTable(Connection con) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException, SQLException{
		boolean successfulInsert = false;
		String query = DaoStatementsConstant.UserCompetitionsDBQueries.INSERT.toString();
		
		try (PreparedStatement pst = con.prepareStatement(query)) {
			for (int j = 1; j <= 20; j++){
				pst.setInt(1, (j%10==0) ? 10 : j%10);	
				pst.setInt(2, (j%3==0) ? 2 : (j%2==0 ? 1 : 3));
				pst.setInt(3, (j%4==0) ? 4 : (j%2==0 ? 3 : (j%3==0 ? 1 : 3)));	
				pst.setInt(4, (int)(Math.random()*10+1));
				pst.setString(5, "time "+j);
				successfulInsert = pst.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error populating userscompetitions table.");
		}
				
		return successfulInsert;
	}
}
