package edu.softserveinc.healthbody.dto;

public class UserDTO {

	private String loginUser;
	private String roleName;
	private String[] groups;
	private String[] competitions;
	private Integer score;

	

	public UserDTO(String loginUser, String roleName, String[] groups, String[] competitions, Integer score) {
		this.loginUser = loginUser;
		this.roleName = roleName;
		this.groups = groups;
		this.competitions = competitions;
		this.score = score;
	}


	// setters
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setGroups(String[] groups) {
		this.groups = groups;
	}

	public void setCompetisions(String[] competitions) {
		this.competitions = competitions;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	// getters
	public String getLoginUser() {
		return loginUser;
	}

	public String getRoleName() {
		return roleName;
	}

	public String[] getGroups() {
		return groups;
	}

	public String[] getCompetitions() {
		return competitions;
	}

	public Integer getScore() {
		return score;
	}

}
