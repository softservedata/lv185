package edu.softserveinc.healthbody.dto;

import java.util.List;

public class UserCompetitionsDTO {
	
	private String login;
	private List<String> groups;
	private String userScore;
	private String awardsName;
	private String timeReceivedAward;
	
	public UserCompetitionsDTO(String login, List<String> groups, String userScore, String awardsName,
			String timeReceivedAward) {
		
		this.login = login;
		this.groups = groups;
		this.userScore = userScore;
		this.awardsName = awardsName;
		this.timeReceivedAward = timeReceivedAward;
	}

	//getters
	public String getLogin() {
		return login;
	}

	public List<String> getGroups() {
		return groups;
	}

	public String getUserScore() {
		return userScore;
	}

	public String getAwardsName() {
		return awardsName;
	}

	public String getTimeReceivedAward() {
		return timeReceivedAward;
	}
	
	

}
