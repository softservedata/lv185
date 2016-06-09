package com.softserve.edu.hb.entity;

import com.softserve.edu.hb.dao.IDaoCRUD.DaoQueries;

public class CompetitionsDB implements IEntity{

	public static enum CompetitionsDBQueries {
        INSERT(DaoQueries.INSERT, "INSERT INTO users (id_competitions, name, description, start, end, id_criterias) "
        		+ "VALUES (%s, '%s', '%s', '%s', '%s', %s);"),
        GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT id_competitions, name, description, start, end, id_criterias FROM competitions"
        		+ " WHERE id_competitions = %s;"),
        GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_competitions, name, description, start, end, id_criterias FROM competitions"
        		+ " WHERE %s = '%s';"),
        GET_ALL(DaoQueries.GET_ALL, "SELECT id_competitions, name, description, start, end, id_criterias FROM competitions;"),
        UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE competitions SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE competitions WHERE id_competitions = %s;"),
        DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE competitions WHERE %s = '%s';");
        //DELETE_USER_BY_PARTIAL_LOGIN("DELETE dbo.Users WHERE Login LIKE '%s%%';");
        private DaoQueries daoQuery;
        private String query;

        private CompetitionsDBQueries(DaoQueries daoQuery, String query) {
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
    
    private Integer id_competitions;
    private String name;
    private String description;
    private String start;
    private String end;
    private String id_criterias;
	public Integer getId_competitions() {
		return id_competitions;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getStart() {
		return start;
	}
	public String getEnd() {
		return end;
	}
	public String getId_criterias() {
		return id_criterias;
	}
	public void setId_competitions(Integer id_competitions) {
		this.id_competitions = id_competitions;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public void setId_criterias(String id_criterias) {
		this.id_criterias = id_criterias;
	}
	public CompetitionsDB(Integer id_competitions, String name, String description, String start, String end,
			String id_criterias) {
		super();
		this.id_competitions = id_competitions;
		this.name = name;
		this.description = description;
		this.start = start;
		this.end = end;
		this.id_criterias = id_criterias;
	}
	@Override
	public Integer getId() {
		
		return null;
	}
    
    
}