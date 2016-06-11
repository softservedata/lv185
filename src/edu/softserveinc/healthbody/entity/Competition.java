package edu.softserveinc.healthbody.entity;

import edu.softserveinc.healthbody.dao.BasicDao.DaoQueries;

public class Competition implements IEntity {

	public static enum CompetitionDBQueries {
		INSERT(DaoQueries.INSERT, "INSERT INTO competitions (id_competition, name, description, start, end, id_criteria) VALUES (%s, '%s', '%s', '%s', '%s', %s);"),
		GET_BY_ID(DaoQueries.GET_BY_ID,	"SELECT id_competition, name, description, start, end, id_criteria FROM competitions WHERE id_competition = %s;"),
		GET_BY_FIELD(DaoQueries.GET_BY_FIELD, "SELECT id_competition, name, description, start, end, id_criteria FROM competitions WHERE %s = '%s';"),
		GET_ALL(DaoQueries.GET_ALL,	"SELECT id_competition, name, description, start, end, id_criteria FROM competitions;"),
		UPDATE_BY_FIELD(DaoQueries.UPDATE_BY_FIELD,	"UPDATE competitions SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(DaoQueries.DELETE_BY_ID, "DELETE competitions WHERE id_competition = %s;"),
		DELETE_BY_FIELD(DaoQueries.DELETE_BY_FIELD, "DELETE competitions WHERE %s = '%s';");
		// DELETE_USER_BY_PARTIAL_LOGIN("DELETE dbo.competitions WHERE Login LIKE
		// '%s%%';");
		private DaoQueries daoQuery;
		private String query;

		private CompetitionDBQueries(DaoQueries daoQuery, String query) {
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
	private Integer id_criterias;

	// TODO Create Factory, Builder
	public Competition(Integer id_competitions, String name, String description,
			String start, String end, Integer id_criterias) {
		this.id_competitions = id_competitions;
		this.name = name;
		this.description = description;
		this.start = start;
		this.end = end;
		this.id_criterias = id_criterias;
	}
	
	// setters

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

	public void setId_criterias(Integer id_criterias) {
		this.id_criterias = id_criterias;
	}

	// getters
	
	public Integer getId() {
		return getId_competitions();
	}
	
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

	public Integer getId_criterias() {
		return id_criterias;
	}
	
}
