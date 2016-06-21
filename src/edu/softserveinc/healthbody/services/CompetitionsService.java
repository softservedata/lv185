package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.CompetitionDTO;

public interface CompetitionsService extends BaseFilterService<CompetitionDTO> {

	// getAll

	// getAll + filters

	// getAll + active
	List<CompetitionDTO> getAllActive();

	List<CompetitionDTO> getAllByUser();

	List<CompetitionDTO> getAllActiveByUser();

	CompetitionDTO getCompetition();

}
