package edu.softserveinc.healthbody.services.oldInterfaces;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;

public interface ICompetitionsAchievementsFilter {
	
	List<UserCompetitionsDTO> getAllCompetitionsAchievements(int partSize, int partNumber,
			String login, String competition, String userScore, String awardName, 
			String timeReceived);

}
