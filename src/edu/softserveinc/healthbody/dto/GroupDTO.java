package edu.softserveinc.healthbody.dto;

public class GroupDTO {

	private String name;
	private String count;
	private String scoreGroup;
	private String descriptions;
	// private String status;

	public GroupDTO(String name, String count, String scoreGroup, String descriptions) {
		this.name = name;
		this.count = count;
		this.scoreGroup = scoreGroup;
		this.descriptions = descriptions;
	}

	public String getName() {
		return name;
	}

	public String getCount() {
		return count;
	}

	public String getScoreGroup() {
		return scoreGroup;
	}

	public String getDescriptions() {
		return descriptions;
	}

}