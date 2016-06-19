package edu.softserveinc.healthbody.services;

import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;

public interface IUserCompetitionsAchievements {
	
	void insertUserCompetitions(UserCompetitionsDTO userCompetitionsDTO);
	
	UserCompetitionsDTO getUserCompetitions(String nameCompetition);
	

}
