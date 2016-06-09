package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.BasicCRUDDao.DaoQueries;

public class ContestDB implements IEntity {
	
	public static enum ContestsDBQueries {
		
    INSERT(DaoQueries.INSERT, "INSERT INTO contests (id_criterias, name, start, finish) VALUES (%s, '%s', '%s', '%s');"),
    GET_BY_ID(DaoQueries.GET_BY_ID, "SELECT * FROM contests WHERE id_contest = %s;"),
    GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT * FROM contests WHERE %s = '%s';"),
    GET_ALL(DaoQueries.GET_ALL, "SELECT * FROM contests;"),
    UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD, "UPDATE contests SET %s = '%s' WHERE %s = '%s';"),
    DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE contests WHERE id_contest = %s;"),
    DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE contests WHERE %s = '%s';");
    
	private DaoQueries daoQueries;
	private String query;
	
	private ContestsDBQueries(DaoQueries daoQueries, String query) {
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

	public Integer id_contest;
	public String name;
	public String start;
	public String finish;
	public String id_criterias;

	@Override
	public Integer getId() {

		return getId_contest();
	}

	//getters
	public Integer getId_contest() {
		return id_contest;
	}

	public String getName() {
		return name;
	}

	public String getStart() {
		return start;
	}

	public String getFinish() {
		return finish;
	}

	public String getId_criterias() {
		return id_criterias;
	}

	//setters
	public void setId_contest(Integer id_contest) {
		this.id_contest = id_contest;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public void setId_criterias(String id_criterias) {
		this.id_criterias = id_criterias;
	}

	
}
