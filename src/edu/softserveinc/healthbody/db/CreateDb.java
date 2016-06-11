package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public class CreateDb {

	Connection connection = null;
	Statement statement = null;
	{

		try {
			connection = ConnectionManager.getInstance().getConnection();
			statement = connection.createStatement();
			String sql = "CREATE DATA BASE healthbody";
			statement.executeUpdate(sql);
		} catch (JDBCDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
