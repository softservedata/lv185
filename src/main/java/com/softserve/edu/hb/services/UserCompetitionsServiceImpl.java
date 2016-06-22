package com.softserve.edu.hb.services;

import java.util.List;
import java.util.Map;

import com.softserve.edu.hb.dto.CompetitionDTO;

public class UserCompetitionsServiceImpl extends CompetitionsServiceImpl implements BaseFilterService<CompetitionDTO>{

	public List<CompetitionDTO> getAllUsers(int partNumber, int partSize, Map<String, String> filters) {
		filters.put(CompetitionsServiceKeys.USER.toString(), "user");
		List<CompetitionDTO> userCompetitions = super.getAllUsers(partNumber, partSize, filters);
		//TODO
		return userCompetitions;
	}

}
