package edu.softserveinc.healthbody.dto;

import java.util.List;

public class UserCompetitionsDTO {
	
	private String login;
	private List<String> competitions;
	private String userScore;
	private String awardsName;
	private String timeReceivedAward;
	
	public UserCompetitionsDTO() {}
	
	public UserCompetitionsDTO(String login, List<String> competitions, String userScore, String awardsName,
			String timeReceivedAward) {
		
		this.login = login;
		this.competitions = competitions;
		this.userScore = userScore;
		this.awardsName = awardsName;
		this.timeReceivedAward = timeReceivedAward;
	}

	//getters
	public String getLogin() {
		return login;
	}

	public List<String> getCompetitions() {
		return competitions;
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
