package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.IBasicDao.DaoQueries;

public class UserGroupView implements IEntity{

		public static enum UserGroupViewFields {
			USERSGROUPS_ID("usersgroups.id_user_group"),
			USERS_ID("usersgroups.id_user"),
			GROUPS_ID("usersgroups.id_group");
	        private String field;

	        private UserGroupViewFields(String field) {
	            this.field = field;
	        }

	        @Override
	        public String toString() {
	            return field;
	        }
	    }

		public static enum UserGroupViewQueries {
	        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role WHERE users.id_user = ?;"),
	        GET_ID_BY_FIELDS(DaoQueries.GET_ID_BY_FIELDS, "SELECT usersgroups.id_user_group FROM usersgroups, users, groups WHERE usersgroups.id_user = ? AND "
						 																										+"usersgroups.id_group = ?;"),		
//	        											  "SELECT usersgroups.id_user_group FROM usersgroup INNER JOIN users ON usersgroups.id_user = %s INNER JOIN groups ON usersgroups.id_group = %s;"),
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
	    }

	    private Integer idUserGroup;
	    private Integer idUser;
		private Integer idGroup;
		private Boolean memberGgoup;

		public UserGroupView(Integer idUserGroup, Integer idUser, Integer idGroup, Boolean memberGgoup) {
			this.idUserGroup = idUserGroup;
			this.idUser = idUser;
			this.idGroup = idGroup;
			this.memberGgoup = memberGgoup;
		}

		// setters

		public void setIdUserGroup(Integer idUserGroup) {
			this.idUserGroup = idUserGroup;
		}

		public void setIdUser(Integer idUser) {
			this.idUser = idUser;
		}

		public void setIdGroup(Integer idGroup) {
			this.idGroup = idGroup;
		}

		// getters

		public void setMemberGgoup(Boolean memberGgoup) {
			this.memberGgoup = memberGgoup;
		}

		public Integer getIdUserGroup() {
			return idUserGroup;
		}

		public Integer getIdUser() {
			return idUser;
		}

		public Integer getIdGroup() {
			return idGroup;
		}

		public Boolean getMemberGgoup() {
			return memberGgoup;
		}

		@Override
		public Integer getId() {
			return idUserGroup;
		}

	
}
