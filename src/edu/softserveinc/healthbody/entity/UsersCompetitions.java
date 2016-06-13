package edu.softserveinc.healthbody.entity;

public class UsersCompetitions implements IEntity {
	
	public static enum UsersCompetitionsFields {
		USERSCOMPETITIONS_ID("userscompetitions.id_user_competition"),
		USERS_ID("userscompetitions.id_user"),
		COMPETITIONS_ID("userscompetitions.id_competition");
        private String field;

        private UsersCompetitionsFields(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return field;
        }
    }
	
	private Integer idUserCompetition;
    private Integer idUser;
	private Integer idGroup;
	private Integer userScore;
	private Integer idAwardsTypes;
	private String timeReceived;
	
	public UsersCompetitions(Integer idUserCompetition, Integer idUser, Integer idGroup, Integer userScore,
			Integer idAwardsTypes, String timeReceived) {
		
		this.idUserCompetition = idUserCompetition;
		this.idUser = idUser;
		this.idGroup = idGroup;
		this.userScore = userScore;
		this.idAwardsTypes = idAwardsTypes;
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

	public void setIdAwardsTypes(Integer idAwardsTypes) {
		this.idAwardsTypes = idAwardsTypes;
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

	public Integer getIdAwardsTypes() {
		return idAwardsTypes;
	}

	public String getTimeReceived() {
		return timeReceived;
	}

	
	

}
