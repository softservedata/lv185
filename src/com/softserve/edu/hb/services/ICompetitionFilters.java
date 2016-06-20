package com.softserve.edu.hb.services;

import java.util.List;

import com.softserve.edu.hb.dto.CompetitionDTO;

@Deprecated
public interface ICompetitionFilters {

	List<CompetitionDTO> getAllCompetitions(int partSize, int partNumber,
			String status, String name, String startDate, String finishDate,
			String group, String user);

}
