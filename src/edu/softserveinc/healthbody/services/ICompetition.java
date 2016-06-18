package edu.softserveinc.healthbody.services;

import edu.softserveinc.healthbody.dto.CompetitionDTO;

public interface ICompetition {

	void insertCompetition(CompetitionDTO competitionDTO);

	CompetitionDTO getCompetitions(String name);

	void updateCompetition(CompetitionDTO competitionDTO);

}