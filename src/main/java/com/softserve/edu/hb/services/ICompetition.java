package com.softserve.edu.hb.services;

import com.softserve.edu.hb.dto.CompetitionDTO;

@Deprecated
public interface ICompetition {
	
	void insertCompetition(CompetitionDTO competitionDTO);
	
	CompetitionDTO getCompetitions(String name);
	
	void updateCompetition(CompetitionDTO competitionDTO);
	
}