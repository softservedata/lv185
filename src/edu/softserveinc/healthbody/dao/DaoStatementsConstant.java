package edu.softserveinc.healthbody.dao;

import edu.softserveinc.healthbody.dao.BasicDao.DaoQueries;

public class DaoStatementsConstant {
	public static enum UserDBQueries {
        INSERT(DaoQueries.INSERT, "INSERT INTO users (id_role, login, passwd, firstname, lastname, age, weight, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_role, login, passwd, firstname, lastname, age, weight, gender FROM users WHERE id_user = ?;"),
        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_role, login, passwd, firstname, lastname, age, weight, gender FROM users WHERE ? = ?;"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT id_role, login, passwd, firstname, lastname, age, weight, gender FROM users;"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE users SET ? = ? WHERE ? = ?;"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE users WHERE id_user = ?;"),
        DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE users WHERE ? = ?;");
		private DaoQueries daoQuery;
		private String query;
		
		private UserDBQueries(DaoQueries daoQuery, String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}

		public DaoQueries getDaoQuery() {
			return daoQuery;
		}

		public String toString() {
			return query;
		}
	}
	public static enum GroupDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO groups (name, description, status) VALUES (?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT name, description, status FROM groups WHERE id_group = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT name, description, status FROM groups WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT name, description, status FROM groups;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE groups SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE groups WHERE id_group = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE groups WHERE ? = ?;");
		
		private DaoQueries daoQuery;
		private String query;
		
		private GroupDBQueries(DaoQueries daoQuery, String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		public String toString() {
			return query;
		}
	}
	public static enum CompetitionDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO competitions (id_competition, name, description, start, end, id_criteria) VALUES (?, ?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_competition, name, description, start, end, id_criteria FROM competitions WHERE id_competition = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_competition, name, description, start, end, id_criteria FROM competitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_competition, name, description, start, end, id_criteria FROM competitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE competitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE competitions WHERE id_competition = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE competitions WHERE ? = ?;");
		private DaoQueries daoQuery;
		private String query;

		private CompetitionDBQueries(DaoQueries daoQuery, String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}

		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		public String toString() {
			return query;
		}
	}
	public static enum MetaDataDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO metadata (last_synch) VALUES (?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_metadata, last_synch FROM metadata WHERE id_metadata = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_metadata, last_synch FROM metadata WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_metadata, last_synch FROM metadata;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE metadata SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE metadata WHERE id_meta_data = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE metadata WHERE ? = ?;");
		
		private DaoQueries daoQuery;
		private String query;
		
		private MetaDataDBQueries(DaoQueries daoQuery, String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		public String toString() {
			return query;
		}
	}
	
	public static enum GroupCompetitionsDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO GroupCompetitions (idGroupCompetitions,idGroup,idCompetition) VALUES (?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT idGroupCompetitions,idGroup,idCompetition FROM GroupCompetitions WHERE idGroupCompetitions = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT idGroupCompetitions,idGroup,idCompetition FROM GroupCompetitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT idGroupCompetitions,idGroup,idCompetition FROM GroupCompetitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE GroupCompetitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE GroupCompetitions WHERE idGroupCompetitions = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE GroupCompetitions WHERE ? = ?;");
		private DaoQueries daoQuery;
		private String query;

		private GroupCompetitionsDBQueries(DaoQueries daoQuery, String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}

		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		public String toString() {
			return query;
		}
	}

	public static enum UserCompetitionsDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO UserCompetitions (id_user, id_group, user_score, id_awards, time_received) VALUES (?, ?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_userCompetitions, id_user, id_group, user_score, id_awards, time_received FROM UserCompetitions WHERE id_userCompetitions = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_userCompetitions, id_user, id_group, user_score, id_awards, time_received FROM UserCompetitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_userCompetitions, id_user, id_group, user_score, id_awards, time_received FROM UserCompetitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE UserCompetitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE UserCompetitions WHERE id_userCompetitions = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE UserCompetitions WHERE ? = ?;");
		private DaoQueries daoQuery;
		private String query;

		private UserCompetitionsDBQueries(DaoQueries daoQuery, String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		public String toString() {
			return query;
		}
	}
		

	public static enum RoleDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO roles (name, description) VALUES (?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT name, description FROM roles WHERE id_role = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT name, description FROM roles WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT name, description FROM roles;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE roles SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE roles WHERE id_role = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE roles WHERE ? = ?;");
		
		private DaoQueries daoQuery;
		private String query;
		
		private RoleDBQueries(DaoQueries daoQuery, String query) {

			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		public String toString() {
			return query;
		}
	}
}
 