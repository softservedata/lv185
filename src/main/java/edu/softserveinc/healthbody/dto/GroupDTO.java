package edu.softserveinc.healthbody.dto;

public class GroupDTO {

	private String name;
	private String count;
	private String descriptions;
	private String scoreGroup;
	// private String status;

	


	public GroupDTO(String name, String count, String descriptions, String scoreGroup) {
		super();
		this.name = name;
		this.count = count;
		this.descriptions = descriptions;
		this.scoreGroup = scoreGroup;
		
	}

	public String getName() {
		return name;
	}
	public String getCount() {
		return count;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public String getScoreGroup() {
		return scoreGroup;
	}
	

}