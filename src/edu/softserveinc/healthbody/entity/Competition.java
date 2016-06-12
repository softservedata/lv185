package edu.softserveinc.healthbody.entity;


public class Competition implements IEntity {

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
