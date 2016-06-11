package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.BasicDao.DaoQueries;

public class User implements IEntity {
	
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

	private Integer idUser;
	private String login;
	private String passwd;
	private String firsName;
	private String lastName;
	private String mail;
	private String gender;
	private Integer weight;
	private Integer age;
	private String googleApi;
	private String health;
	private String avatar;
	private String status;
	private Integer idRole;

	public User(Integer idUser, String login, String passwd, String firsName, String lastName, String gender,
			Integer weight, Integer age, Integer idRole) {
		super();
		this.idUser = idUser;
		this.login = login;
		this.passwd = passwd;
		this.firsName = firsName;
		this.lastName = lastName;
		this.gender = gender;
		this.weight = weight;
		this.age = age;
		this.idRole = idRole;
	}

	@Override
	public Integer getId() {

		return idUser;
	}

	// setters
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setGoogleApi(String googleApi) {
		this.googleApi = googleApi;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	// getters
	public Integer getIdUser() {
		return getId();
	}

	public String getLogin() {
		return login;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getFirsName() {
		return firsName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMail() {
		return mail;
	}

	public String getGender() {
		return gender;
	}

	public Integer getWeight() {
		return weight;
	}

	public Integer getAge() {
		return age;
	}

	public String getGoogleApi() {
		return googleApi;
	}

	public String getHealth() {
		return health;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getStatus() {
		return status;
	}

	public Integer getIdRole() {
		return idRole;
	}

}
