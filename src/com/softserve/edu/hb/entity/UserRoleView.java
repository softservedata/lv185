package com.softserve.edu.hb.entity;

import com.softserve.edu.hb.dao.IDaoCRUD.DaoQueries;

public class UserRoleView {

	public static enum UserRoleViewFields {
		USERS_ID_USER("users.id_user"),
		USERS_LOGIN("users.login"),
		ROLES_NAME("roles.name");
        private String field;

        private UserRoleViewFields(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return field;
        }
    }

	public static enum UserRoleViewQueries {
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role WHERE users.id_user = %s;"),
        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role WHERE %s = '%s';"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT users.id_user, users.login, roles.name FROM users INNER JOIN roles ON users.id_role = roles.id_role;");
        private DaoQueries daoQuery;
        private String query;

        private UserRoleViewQueries(DaoQueries daoQuery, String query) {
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

    private Integer idUser;
    private String login;
	private String roleName;

	public UserRoleView(Integer idUser, String login, String roleName) {
		this.idUser = idUser;
		this.login = login;
		this.roleName = roleName;
	}

	// setters

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	// getters

	public Integer getIdUser() {
		return idUser;
	}

	public String getLogin() {
		return login;
	}

	public String getRoleName() {
		return roleName;
	}

}
