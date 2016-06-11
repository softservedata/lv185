package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.BasicDao.DaoQueries;

public class Group implements IEntity {
	
	public static enum GroupDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO groups (name, description, status) VALUES ('%s', '%s', '%s');"),
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
		
		@Override
		public String toString() {
			return query;
		}
	}

	private Integer idGroup;
	private String name;
	private String description;
	private String status;
	
	public Group(Integer idGroup, String name, String description, String status) {
		super();
		this.idGroup = idGroup;
		this.name = name;
		this.description = description;
		this.status = status;
	}

	@Override
	public Integer getId() {
		return getIdGroup();
	}
	
	//getters
	public Integer getIdGroup() {
		return idGroup;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getStatus() {
		return status;
	}
	
	//setters
	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}