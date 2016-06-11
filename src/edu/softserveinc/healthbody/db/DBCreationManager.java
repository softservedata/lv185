package edu.softserveinc.healthbody.db;

import java.sql.SQLException;
import java.sql.Statement;

public class DBCreationManager {
	
	public static enum TableQueries {    
		USERS_TABLE ("CREATE TABLE \"Users\"("
					  +"id_user integer NOT NULL DEFAULT nextval(\'\"Users_id_seq\"\'::regclass),"
					  +"login character varying(50),"
					  +"password character varying(25),"
					  +"first_name character varying(50),"
					  +"last_name character varying(50),"
					  +"\"e-mail\" character varying(50),"
					  +"age bigint,"
					  +"weight real,"
					  +"gender \"char\","
					  +"usable character varying(50),"
					  +"avatar character varying,"
					  +"google_field text,"
					  +"id_role bigint,"
					  +"status character varying,"
					  +"CONSTRAINT pk_users_id PRIMARY KEY (id_user),"
					  +"CONSTRAINT fk_role_id FOREIGN KEY (id_role)"
					  +"REFERENCES \"Role\" (id_role)) MATCH SIMPLE"
					  +"ON UPDATE NO ACTION ON DELETE NO ACTION"),
		GROUPS_TABLE ("CREATE TABLE \"Groups\"("
					  +"id_group integer NOT NULL DEFAULT nextval(\'\"Groups_id_seq\"\'::regclass),"
					  +"name character varying(50),"
					  +"description text,"
					  +"status character varying,"
					  +"CONSTRAINT pk_groups_id PRIMARY KEY (id_group))"),
		COMPETION_TABLE ("CREATE TABLE \"Competition\"("
					  +"id_competition integer NOT NULL DEFAULT nextval(\'\"Contest_id_seq\"\'::regclass),"
					  +"name character varying(50),"
					  +"description text,"
					  +"start date,"
					  +"end date,"
					  +"id_criterias bigint,"
					  +"CONSTRAINT pk_contest_id PRIMARY KEY (id_competition))"),
		USER_GROUP_TABLE ("CREATE TABLE \"UsersGroups\"("
					  +"id_user_group integer NOT NULL DEFAULT nextval(\'\"UsersGroups_id_seq\"\'::regclass),"
					  +"id_user bigint,"
					  +"id_group bigint,"
					  +"member_group character varying,"
					  +"CONSTRAINT pk_users_group_id PRIMARY KEY (id_user_group),"
					  +"CONSTRAINT fk_usersgroups_group FOREIGN KEY (id_group)"
					  +"REFERENCES \"Groups\" (id_group) MATCH SIMPLE"
					  +"ON UPDATE NO ACTION ON DELETE NO ACTION,"
					  +"CONSTRAINT fk_usersgroups_user FOREIGN KEY (id_user)"
					  +"REFERENCES \"Users\" (id_user) MATCH SIMPLE"
					  +"ON UPDATE NO ACTION ON DELETE NO ACTION)");

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
    
    public boolean createDatabase(Statement statement) throws SQLException{
    	boolean result = false;
    	result = statement.execute("CREATE DATABASE healthbodydb WITH ENCODING='UTF8' CONNECTION LIMIT=-1;");
    	return result;
    }
    
    public boolean createTable(Statement statement, TableQueries tableQuery) throws SQLException{
    	boolean result = false;
    	result = statement.execute(tableQuery.name().toString());
    	return result;
    }
    
}