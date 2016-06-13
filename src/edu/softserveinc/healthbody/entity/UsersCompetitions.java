package edu.softserveinc.healthbody.entity;

public class UsersCompetitions implements IEntity {
	
	private Integer idUserCompetition;
    private Integer idUser;
	private Integer idGroup;
	private Integer userScore;
	private Integer idAwards;
	private String timeReceived;
	
	public UsersCompetitions(Integer idUserCompetition, Integer idUser, Integer idGroup, Integer userScore,
			Integer idAwards, String timeReceived) {
		
		this.idUserCompetition = idUserCompetition;
		this.idUser = idUser;
		this.idGroup = idGroup;
		this.userScore = userScore;
		this.idAwards = idAwards;
		this.timeReceived = timeReceived;
	}

	//setters
	public void setIdUserCompetition(Integer idUserCompetition) {
		this.idUserCompetition = idUserCompetition;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}

	public void setIdAwardsTypes(Integer idAwards) {
		this.idAwards = idAwards;
	}

	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}
	
	
	@Override
	public Integer getId() {
		return getIdUserCompetition();
	}
	
	//getters
	public Integer getIdUserCompetition() {
		return idUserCompetition;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public Integer getIdGroup() {
		return idGroup;
	}

	public Integer getUserScore() {
		return userScore;
	}

	public Integer getIdAwards() {
		return idAwards;
	}

	public String getTimeReceived() {
		return timeReceived;
	}

	
	

}
