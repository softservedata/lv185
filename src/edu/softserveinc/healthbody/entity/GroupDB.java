package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.BasicCRUDDao.DaoQueries;

public class GroupDB implements IEntity {
	
	public static enum GroupDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO groups (name, description, status) VALUES ('%s', '%s', '%s');"),
		GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT * FROM groups WHERE id_group = %s;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT * FROM groups WHERE %s = '%s';"),
		GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM contests;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE groups SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE groups WHERE id_group = %s;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE groups WHERE %s = '%s';");
		
		private DaoQueries daoQueries;
		private String query;
		
		private GroupDBQueries(DaoQueries daoQueries, String query) {
			this.daoQueries = daoQueries;
			this.query = query;
		}
		
		public DaoQueries getDaoQuerie() {
			return daoQueries;
		}
		
		public String toString() {
			return query;
		}
	}

	public Integer id_group;
	public String name;
	public String description;
	public String status;
	@Override
	public Integer getId() {
		return getId_group();
	}
	
	//getters
	public Integer getId_group() {
		return id_group;
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
	public void setId_group(Integer id_group) {
		this.id_group = id_group;
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