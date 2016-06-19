package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;

public interface CompetitionsAchievementsFilter {
	
	List<UserCompetitionsDTO> getAllCompetitionsAchievements(int partSize, int partNumber,
			String login, String competition, String userScore, String awardName, 
			String timeReceived);

}
