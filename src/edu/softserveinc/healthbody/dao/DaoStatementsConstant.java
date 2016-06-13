package edu.softserveinc.healthbody.dao;

import edu.softserveinc.healthbody.dao.BasicDao.DaoQueries;

public class DaoStatementsConstant {
	public static enum UserDBQueries {
        INSERT(DaoQueries.INSERT, "INSERT INTO users (id_role, login, passwd, firstname, lastname, age, weight, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT * FROM users WHERE id_user = ?;"),
        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT * FROM users WHERE ? = ?;"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM users;"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE users SET ? = ? WHERE ? = ?;"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE users WHERE id_user = ?;"),
        DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE users WHERE %s = ?;");
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
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT * FROM groups WHERE id_group = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT * FROM groups WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM contests;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE groups SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE groups WHERE id_group = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE groups WHERE %s = ?;");
		
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
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE competitions WHERE %s = ?;");
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
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT * FROM metadata WHERE id_group = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT * FROM metadata WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM metadata;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE metadata SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE metadata WHERE id_meta_data = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE metadata WHERE %s = ?;");
		
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
}
 