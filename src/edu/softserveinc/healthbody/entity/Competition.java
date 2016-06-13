package edu.softserveinc.healthbody.entity;


public class Competition implements IEntity {

	private Integer id_competition;
	private String name;
	private String description;
	private String start;
	private String finish;
	private Integer id_criteria;

	public Competition(Integer id_competition, String name, String description,
			String start, String finish, Integer id_criteria) {
		this.id_competition = id_competition;
		this.name = name;
		this.description = description;
		this.start = start;
		this.finish = finish;
		this.id_criteria = id_criteria;
	}
	
	// setters

	public void setId_competitions(Integer id_competition) {
		this.id_competition = id_competition;
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

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public void setId_criteria(Integer id_criteria) {
		this.id_criteria = id_criteria;
	}

	// getters
	
	public Integer getId() {
		return getId_competition();
	}
	
	public Integer getId_competition() {
		return id_competition;
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

	public String getFinish() {
		return finish;
	}

	public Integer getId_criteria() {
		return id_criteria;
	}
	
}
