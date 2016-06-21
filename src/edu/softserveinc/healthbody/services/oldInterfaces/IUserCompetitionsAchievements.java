package edu.softserveinc.healthbody.services.oldInterfaces;

import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;

public interface IUserCompetitionsAchievements {
	
	void insertUserCompetitions(UserCompetitionsDTO userCompetitionsDTO);
	
	UserCompetitionsDTO getUserCompetitions(String nameCompetition);
	

}
