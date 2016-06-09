package edu.softserveinc.healthbody.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDb {

	Connection connection = null;
	Statement statement = null;
	{

		try {
			connection = ConnectionDb.get().getConnection();
			statement = connection.createStatement();
			String sql = "CREATE DATA BASE healthbody";
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.getMessage();
		}
	}
}
