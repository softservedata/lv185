package com.softserve.edu.hb.entity;

import com.softserve.edu.hb.dao.IDao.DaoQueries;

public class GroupDB implements IEntity{

	public static enum GroupDBQueries {
        INSERT(DaoQueries.INSERT, "INSERT INTO groups (name, description, status) VALUES (%s, '%s', '%s');"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_group, name, description, status FROM groups WHERE id_group = %s;"),
        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_group, name, description, status FROM groups WHERE %s = '%s';"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT id_group, name, description, status FROM groups;"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE groups SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE groups WHERE id_group = %s;"),
        DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE groups WHERE %s = '%s';");
       
		
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
	


	public GroupDB(Integer idGroup, String name, String description, String status) {
		super();
		this.idGroup = idGroup;
		this.name = name;
		this.description = description;
		this.status = status;
	}

	
	// Getters
	
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



	// Setters
	
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




	@Override
	public Integer getId() {
		return getIdGroup();
	}

}
