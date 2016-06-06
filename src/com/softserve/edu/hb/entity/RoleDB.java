package com.softserve.edu.hb.entity;

public class RoleDB implements IEntity {

	public static enum RoleDBQueries {
		INSERT("INSERT INTO roles (name) VALUES ('%s');"),
		GET_BY_ID("SELECT id_role, name FROM roles WHERE id_role = %s;"),
		GET_BY_FIELD("SELECT id_role, name FROM roles WHERE %s = '%s';"),
		GET_ALL("SELECT id_role, name FROM roles;"),
		UPDATE_BY_FIELD("UPDATE roles SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID("DELETE roles WHERE id_role = %s;"),
		DELETE_BY_FIELD("DELETE roles WHERE %s = '%s';");
		private String query;

		private RoleDBQueries(String query) {
			this.query = query;
		}

		@Override
		public String toString() {
			return query;
		}
	}

	private Integer idRole;
	private String name;

	public RoleDB(Integer idRole, String name) {
		super();
		this.idRole = idRole;
		this.name = name;
	}

	// setters
	
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public void setName(String name) {
		this.name = name;
	}

	// getters
	
	public Integer getId() {
		return getIdRole();
	}

	public Integer getIdRole() {
		return idRole;
	}

	public String getName() {
		return name;
	}

}
