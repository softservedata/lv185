package com.softserve.edu.hb.entity;

public class UserDB implements IEntity {

    public static enum UserDBQueries {
        INSERT("INSERT INTO users (id_role, login, passwd) VALUES (%s, '%s', '%s');"),
        GET_BY_ID("SELECT id_user, id_role, login, passwd FROM users WHERE id_user = %s;"),
        GET_BY_FIELD("SELECT id_user, id_role, login, passwd FROM users WHERE %s = '%s';"),
        GET_ALL("SELECT id_user, id_role, login, passwd FROM users;"),
        UPDATE_BY_FIELD("UPDATE users SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID("DELETE users WHERE id_user = %s;"),
        DELETE_BY_FIELD("DELETE users WHERE %s = '%s';");
        //DELETE_USER_BY_PARTIAL_LOGIN("DELETE dbo.Users WHERE Login LIKE '%s%%';");
        private String query;

        private UserDBQueries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }
    
    private Integer idUser;
    private Integer idRole;
    private String login;
    private String passwd;
    
    // TODO Create Factory, Builder
	public UserDB(Integer idUser, Integer idRole, String login, String passwd) {
		this.idUser = idUser;
		this.idRole = idRole;
		this.login = login;
		this.passwd = passwd;
	}

	// setters
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	// getters
	
	public Integer getId() {
		return getIdUser();
	}
	
	public Integer getIdUser() {
		return idUser;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public String getLogin() {
		return login;
	}

	public String getPasswd() {
		return passwd;
	}
  
}
