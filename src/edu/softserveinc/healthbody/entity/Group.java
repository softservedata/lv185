package edu.softserveinc.healthbody.entity;


public class Group implements IEntity {

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