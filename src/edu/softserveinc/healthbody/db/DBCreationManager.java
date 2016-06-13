package edu.softserveinc.healthbody.db;

import java.sql.SQLException;
import java.sql.Statement;

public class DBCreationManager {
	public static enum TableQueries {
		USERS_TABLE("CREATE TABLE IF NOT EXISTS \"users\"("
				+ "id_user serial primary key,"
				+ "login varchar(50),"
				+ "password varchar(25),"
				+ "first_name varchar(50),"
				+ "last_name varchar(50),"
				+ "\"e-mail\" varchar(50),"
				+ "age bigint,"
				+ "weight real,"
				+ "gender \"char\","
				+ "usable varchar(50),"
				+ "avatar varchar(50),"
				+ "google_field varchar(150),"
				+ "id_role bigint,"
				+ "status varchar(50))"),
		GROUPS_TABLE("CREATE TABLE IF NOT EXISTS \"groups\"("
				+ "id_group serial primary key,"
				+ "name varchar(50),"
				+ "description text,"
				+ "status varchar)"),
		COMPETION_TABLE("CREATE TABLE IF NOT EXISTS \"Competition\"("
				+ "idCompetition serial primary key,"
				+ "name varchar(50),"
				+ "description varchar(200),"
				+ "start date,"
				+ "finish date,"
				+ "idCriteria bigint)"),
		USER_GROUP_TABLE("CREATE TABLE IF NOT EXISTS \"UsersGroups\"("
				+ "id_criteria bigint)"
				+ "id_user_group serial primary key,"
				+ "id_user bigint,"
				+ "id_group bigint,"
				+ "member_group varchar(50),"
				+ "FOREIGN KEY (id_group)  REFERENCES \"groups\" (id_group),"
				+ "FOREIGN KEY (id_user) REFERENCES \"users\" (id_user))"),
		ROLE_TABLE("CREATE TABLE IF NOT EXISTS \"roles\"("
				+ "id_role serial primary key,"
				+ "name varchar(50),"
				+ "description varchar(200))"),
		META_DATA_TABLE("CREATE TABLE IF NOT EXISTS \"metadata\"("
				+ "id_meta_data serial primary key,"
				+ "last_synch varchar(50))");

		private String table;

		private TableQueries(String table) {
			this.table = table;
		}

		@Override
		public String toString() {
			return this.table;
		}
	}

	private static volatile DBCreationManager instance = null;

	private DBCreationManager() {
	}

	public static DBCreationManager getInstance() {
		if (instance == null) {
			synchronized (DBCreationManager.class) {
				if (instance == null) {
					instance = new DBCreationManager();
				}
			}
		}
		return instance;
	}

	public boolean deleteDatabase(Statement statement, String databaseName) throws SQLException {
		boolean result = false;
		String deleteConnectionsQuery = "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = \'"
				+ databaseName + "\' AND pid <> pg_backend_pid();";
		statement.execute(deleteConnectionsQuery + "DROP DATABASE " + databaseName + ";");
		return result;
	}

	public boolean createDatabase(Statement statement, String databaseName) throws SQLException {
		boolean result = false;
		if (!statement.execute("SELECT 1 from pg_database WHERE datname=\'"+databaseName+"\';")){
			result = statement.execute("CREATE DATABASE "+databaseName);   
		}
		return result;
	}

	public boolean createTable(Statement statement, String tableQuery) throws SQLException {
		boolean result = false;
		result = statement.execute(tableQuery);
		return result;
	}

}