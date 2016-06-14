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
	public static enum UserGroupQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO usersgroups (id_user, id_group, member_group) VALUES (?, ?, ?);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, groups.name FROM users INNER JOIN groups ON users.id_group = groups.id_group WHERE users.id_user = ?;"),
        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usersgroups.id_user_group FROM usersgroups WHERE usersgroups.id_user = ? AND usersgroups.id_group = ?;"),		
        GET_ALL(DaoQueries.GET_ALL, "SELECT usersgroups.id_user_group, usersgroups.id_user, usersgroups.id_groups, usersgroups.member_group FROM usersgroups;"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usersgroups SET ? = ? WHERE ? = ?;"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE usersgroups WHERE id_group = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE usersgroups WHERE ? = ?;");
        private DaoQueries daoQuery;
        private String query;

        private UserGroupQueries(DaoQueries daoQuery, String query) {
        	this.daoQuery = daoQuery;
            this.query = query;
        }

        public DaoQueries getDaoQuery() {
            return daoQuery;
        }

        @Override
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
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT * FROM metadata WHERE id_group = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT * FROM metadata WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM metadata;"),
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
	public static enum UserGroupViewQueries {
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, groups.name FROM users INNER JOIN groups ON users.id_group = groups.id_group WHERE users.id_user = ?;"),
        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usersgroups.id_user_group FROM usersgroups, users, groups WHERE usersgroups.id_user = ? AND "
					 																										+"usersgroups.id_group = ?;"),		
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usersgroup SET ? = ? WHERE ? = ?;"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role;");
        private DaoQueries daoQuery;
        private String query;

        private UserGroupViewQueries(DaoQueries daoQuery, String query) {
        	this.daoQuery = daoQuery;
            this.query = query;
        }

        public DaoQueries getDaoQuery() {
            return daoQuery;
        }

        @Override
        public String toString() {
            return query;
        }
        public static enum UserCompetitionsDBQueries {
    		INSERT(DaoQueries.INSERT, "INSERT INTO UserCompetitions (id_user, id_competition, user_score, id_awards, time_received) VALUES (?, ?, ?, ?, ?, ?);"),
    		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_userCompetitions, id_user, id_competition, user_score, id_awards, time_received FROM UserCompetitions WHERE id_userCompetitions = ?;"),
    		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_userCompetitions, id_user, id_competition, user_score, id_awards, time_received FROM UserCompetitions WHERE ? = ?;"),
    		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_userCompetitions, id_user, id_competition, user_score, id_awards, time_received FROM UserCompetitions;"),
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
        public static enum CriteriaDBQueries {
    		INSERT(DaoQueries.INSERT, "INSERT INTO criterias (name, metrics, get_google) VALUES (?, ?, ?);"),
    		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT name, metrics, get_google FROM criterias WHERE id_criteria = ?;"),
    		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT name, metrics, get_google FROM criterias WHERE ? = ?;"),
    		GET_ALL(DaoQueries.GET_ALL, "SELECT name, metrics, get_google FROM criterias;"),
    		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE criterias SET ? = ? WHERE ? = ?;"),
    		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE criterias WHERE id_criteria = ?;"),
    		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE criterias WHERE ? = ?;");
    		
    		private DaoQueries daoQuery;
    		private String query;
    		
    		private CriteriaDBQueries(DaoQueries daoQuery, String query) {
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
}
 