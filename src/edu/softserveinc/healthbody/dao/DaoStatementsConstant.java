package edu.softserveinc.healthbody.dao;

import edu.softserveinc.healthbody.dao.BasicDao.DaoQueries;

public class DaoStatementsConstant {
	public static enum UserDBQueries {
        INSERT(DaoQueries.INSERT, "INSERT INTO users (login, password, firstname, lastname, \"e-mail\", age, weight, gender, health, avatar, "
        		+ "google_field, id_role, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", age, weight, gender, health, avatar, google_field, id_role, status FROM users WHERE id_user = ?;"),
        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", age, weight, gender, health, avatar, google_field, id_role, status FROM users WHERE ? = ?;"),
        GET_BY_FIELD_NAME(DaoQueries.GET_BY_FIELD_NAME, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", age, weight, gender, health, avatar, google_field, id_role, status FROM users WHERE login = ?;"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT id_user, login, password, firstname, lastname, \"e-mail\", age, weight, gender, health, avatar, google_field, id_role, status FROM users;"),
        UPDATE(DaoQueries.UPDATE, "UPDATE users SET password = ?, firstname = ?, lastname = ?, gender = ?, weight = ?, age = ? WHERE login = ?"),
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
		INSERT(DaoQueries.INSERT, "INSERT INTO groups (name, count, description, scoreGroup, status) VALUES (?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_group, name, count, description, scoreGroup, status FROM groups WHERE id_group = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_group, name, count, description, scoreGroup, status FROM groups WHERE ? = ?;"),
		GET_BY_FIELD_NAME(DaoQueries.GET_BY_FIELD_NAME, "SELECT id_group, name, count, description, scoreGroup, status FROM groups WHERE name = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_group, name, count, description, scoreGroup, status FROM groups;"),
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
		INSERT(DaoQueries.INSERT, "INSERT INTO usergroups (id_user, id_group) VALUES (?, ?);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_user_group, id_user, id_group FROM usergroups WHERE id_user = ?;"),
        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usergroups.id_user_group FROM usergroups WHERE usergroups.id_user = ? AND usergroups.id_group = ?;"),		
        GET_ALL(DaoQueries.GET_ALL, "SELECT usergroups.id_user_group, usergroups.id_user, usergroups.id_groups, usergroups.member_group FROM usergroups;"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usergroups SET ? = ? WHERE ? = ?;"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE usergroups WHERE id_group = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE usergroups WHERE ? = ?;");
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
		INSERT(DaoQueries.INSERT, "INSERT INTO competitions (name, description, start, finish, id_criteria) VALUES (?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_competition, name, description, start_date, finish_date, id_criteria FROM competitions WHERE id_competition = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_competition, name, description, start_date, finish_date, id_criteria FROM competitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_competition, name, description, start_date, finish_date, id_criteria FROM competitions;"),
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
		GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM metadata;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE metadata SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE metadata WHERE id_metadata = ?;"),
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
	public static enum AwardDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO awards (name) VALUES (?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_award, name FROM awards WHERE id_award = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_award, name FROM awards WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_award, name FROM awards;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE awards SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE awards WHERE id_award = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE awards WHERE ? = ?;");
		
		private DaoQueries daoQuery;
		private String query;
		
		private AwardDBQueries(DaoQueries daoQuery, String query) {
			this.daoQuery = daoQuery;
			this.query = query;
		}
		
		public DaoQueries getDaoQuery() {
			return daoQuery;
		}
		public String toString() {
			return query;
		}
}	public static enum GroupCompetitionsDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO groupcompetitions (id_group, id_competition) VALUES (?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_group_competition, id_group, id_competition FROM groupcompetitions WHERE id_group_competition = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_group_competition, id_group, id_competition FROM groupcompetitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_group_competition, id_group, id_competition FROM groupcompetitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE groupcompetitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE groupcompetitions WHERE id_group_competition = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE groupcompetitions WHERE ? = ?;");
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
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_role, name, description FROM roles WHERE id_role = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_role, name, description FROM roles WHERE ? = ?;"),
		GET_BY_FIELD_NAME(DaoQueries.GET_BY_FIELD_NAME, "SELECT id_role, name, description FROM roles WHERE name = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_role, name, description FROM roles;"),
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
	
    public static enum UserCompetitionsDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO usercompetitions (id_user, id_competition, id_award, user_score, time_received) VALUES (?, ?, ?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_user_competition, id_user, id_competition, user_score, id_award, time_received FROM usercompetitions WHERE id_user_competition = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_user_competition, id_user, id_competition, user_score, id_award, time_received FROM usercompetitions WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_user_competition, id_user, id_competition, user_score, id_award, time_received FROM usercompetitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE usercompetitions SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE usercompetitions WHERE id_user_competition = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE usercompetitions WHERE ? = ?;");
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
		INSERT(DaoQueries.INSERT, "INSERT INTO criteria (name, metrics, get_google) VALUES (?, ?, ?);"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_criteria, name, metrics, get_google FROM criteria WHERE id_criteria = ?;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_criteria, name, metrics, get_google FROM criteria WHERE ? = ?;"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT id_criteria, name, metrics, get_google FROM criteria;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE criteria SET ? = ? WHERE ? = ?;"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE criteria WHERE id_criteria = ?;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE criteria WHERE ? = ?;");
		
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
    
    public static enum UserGroupViewQueries {
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, groups.name FROM users INNER JOIN groups ON users.id_group = groups.id_group WHERE users.id_user = ?;"),
        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usergroups.id_user_group FROM usergroups, users, groups WHERE usergroups.id_user = ? AND "
					 																										+"usergroups.id_group = ?;"),		
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE usergroups SET ? = ? WHERE ? = ?;"),
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

        public String toString() {
            return query;
        }
	}
    
    public static enum CompetitionsViewQueries {
		GET_ALL_ACTIVE("SELECT competitions.id_competition, competitions.name, competitions.description, competitions.start, competitions.finish, COUNT(usercompetitions.id_user)"
				+ " FROM competitions"
				+ " JOIN usercompetitions ON competitions.id_competition = usercompetitions.id_competition"
				+ " WHERE competitions.finish >= NOW()"
				+ " GROUP BY competitions.id_competition, competitions.name, competitions.start, competitions.finish"
				+ ";"),
		GET_ALL_BY_USER("SELECT id_competition from competitions;"),
		GET_ALL_ACTIVE_BY_USER("SELECT id_competition from competitions;"),
		GET_ALL("SELECT id_competition from competitions;");
		
		private String query;
		
		private CompetitionsViewQueries(String query) {
			this.query = query;
		}

		public CompetitionsViewQueries getDaoQuery() {
            return this;
        }
		
		public String toString() {
			return query;
		}
	}
    
    
}
 