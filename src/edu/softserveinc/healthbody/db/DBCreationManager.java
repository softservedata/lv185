package edu.softserveinc.healthbody.db;

import java.sql.SQLException;
import java.sql.Statement;

public class DBCreationManager {
	
	public static enum TableQueries {    
		USERS_TABEL ("CREATE TABLE IF NOT EXISTS \"Users\"("
					  +"id_user serial primary key,"
					  +"login varchar(50),"
					  +"password varchar(25),"
					  +"first_name varchar(50),"
					  +"last_name varchar(50),"
					  +"\"e-mail\" varchar(50),"
					  +"age bigint,"
					  +"weight real,"
					  +"gender \"char\","
					  +"usable varchar(50),"
					  +"avatar varchar,"
					  +"google_field varchar(150),"
					  +"id_role bigint,"
					  +"status varchar)"),
		GROUPS_TABEL ("CREATE TABLE IF NOT EXISTS \"Groups\"("
					  +"id_group serial primary key,"
					  +"name varchar(50),"
					  +"description text,"
					  +"status varchar)"),
		COMPETION_TABEL ("CREATE TABLE IF NOT EXISTS \"Competition\"("
					  +"id_competition serial primary key,"
					  +"name varchar(50),"
					  +"description varchar(250),"
					  +"start date,"
					  +"finish date,"
					  +"id_criteria bigint)"),
		USER_GROUP_TABEL ("CREATE TABLE IF NOT EXISTS \"UsersGroups\"("
					  +"id_user_group serial primary key,"
					  +"id_user bigint,"
					  +"id_group bigint,"
					  +"member_group varchar,"
					  +"FOREIGN KEY (id_group)  REFERENCES \"Groups\" (id_group),"
					  +"FOREIGN KEY (id_user) REFERENCES \"Users\" (id_user))");

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
    
    public void createDB(Statement statement, String nameDB) throws SQLException{
    	if (!statement.execute("SELECT 1 from pg_database WHERE datname=\'"+nameDB+"\';")){
    		statement.executeUpdate("CREATE DATABASE "+nameDB);    		
    	}
    }
    
    
    public boolean createTable(Statement statement, String tableQuery) throws SQLException{
    	boolean result = false;
    	result = statement.execute(tableQuery);
    	return result;
    }
    
}