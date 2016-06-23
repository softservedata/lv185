package edu.softserveinc.healthbody.db;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBCreationManager {
	public static enum TableQueries {
		USERS_TABLE("CREATE TABLE IF NOT EXISTS \"users\"("
				+ "id_user serial primary key,"
				+ "login varchar(50),"
				+ "password varchar(25),"
				+ "firstname varchar(50),"
				+ "lastname varchar(50),"
				+ "\"e-mail\" varchar(50),"
				+ "age bigint,"
				+ "weight real,"
				+ "gender \"char\","
				+ "health varchar(50),"
				+ "avatar varchar(50),"
				+ "google_field varchar(150),"
				+ "id_role bigint,"
				+ "status varchar(50))"),
		ROLE_TABLE("CREATE TABLE IF NOT EXISTS \"roles\"("
				+ "id_role serial primary key,"
				+ "name varchar(50),"
				+ "description varchar(200))"),
		GROUPS_TABLE("CREATE TABLE IF NOT EXISTS \"groups\"("
				+ "id_group serial primary key,"
				+ "name varchar(50),"
				+ "count bigint,"
				+ "description varchar(50),"
				+ "scoreGroup varchar(50),"
				+ "status varchar(50))"),
		COMPETION_TABLE("CREATE TABLE IF NOT EXISTS \"competitions\"("
				+ "id_competition serial primary key,"
				+ "name varchar(50),"
				+ "description varchar(200),"
				+ "start date,"
				+ "finish date,"
				+ "id_criteria bigint)"),
		AWARD_TABLE("CREATE TABLE IF NOT EXISTS \"awards\"("
				+ "id_award serial primary key,"
				+ "name varchar(50))"),
		CRITERIA_TABLE("CREATE TABLE IF NOT EXISTS \"criteria\"("
				+ "id_criteria serial primary key,"
				+ "name varchar(50),"
				+ "metrics real,"
				+ "get_google varchar(200))"),
		USER_GROUP_TABLE("CREATE TABLE IF NOT EXISTS \"usergroups\"("
				+ "id_user_group serial primary key,"
				+ "id_user bigint,"
				+ "id_group bigint,"
				+ "FOREIGN KEY (id_group)  REFERENCES \"groups\" (id_group),"
				+ "FOREIGN KEY (id_user) REFERENCES \"users\" (id_user))"),
		GROUP_COMPETITION_TABLE("CREATE TABLE IF NOT EXISTS \"groupcompetitions\"("
				+ "id_group_competition serial primary key,"
				+ "id_group bigint,"
				+ "id_competition bigint,"
				+ "FOREIGN KEY (id_group)  REFERENCES \"groups\" (id_group),"
				+ "FOREIGN KEY (id_competition) REFERENCES \"competitions\" (id_competition))"),
		USER_COMPETITION_TABLE("CREATE TABLE IF NOT EXISTS \"usercompetitions\"("
				+ "id_user_competition serial primary key,"
				+ "id_user bigint,"
				+ "id_competition bigint,"
				+ "id_award bigint,"
				+ "user_score bigint,"
				+ "time_received varchar(50),"
				+ "FOREIGN KEY (id_user) REFERENCES \"users\" (id_user),"
				+ "FOREIGN KEY (id_competition)  REFERENCES \"competitions\" (id_competition),"
				+ "FOREIGN KEY (id_award) REFERENCES \"awards\" (id_award))"),
		META_DATA_TABLE("CREATE TABLE IF NOT EXISTS \"metadata\"("
				+ "id_metadata serial primary key,"
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
	private static Logger logger = LogManager.getLogger(DBCreationManager.class.getName());

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

	public boolean dropDatabase(Statement statement, String databaseName) throws SQLException {
		boolean result = false;
		String deleteConnectionsQuery = "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = \'"
				+ databaseName + "\' AND pid <> pg_backend_pid();";
		result = statement.execute(deleteConnectionsQuery + "DROP DATABASE " + databaseName + ";");
		return result;
	}

	public boolean createDatabase(Statement statement, String databaseName) throws SQLException {
		boolean result = false;
		statement.execute("select datname from pg_catalog.pg_database where datname = \'" + databaseName + "\';");
		if (statement.getResultSet().next()){
			System.out.println("Database exists!!!");
		} else {
			logger.info("Creating database " + databaseName);
			result = statement.execute("CREATE DATABASE " + databaseName);
			logger.info("Database " + databaseName + " created.");
		}

		return result;
	}

	public boolean createTable(Statement statement, String tableQuery) throws SQLException {
		boolean result = false;
		result = statement.execute(tableQuery);
		return result;
	}
	

}