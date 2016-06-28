package edu.softserveinc.healthbody.services.oldInterfaces;

import java.util.List;

import edu.softserveinc.healthbody.dto.CompetitionDTO;

public interface ICompetitionFilters {

	List<CompetitionDTO> getAllCompetitions(int partSize, int partNumber,
			String status, String name, String startDate, String finishDate,
			String group, String user);

}
