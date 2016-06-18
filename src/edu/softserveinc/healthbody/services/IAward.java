package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.AwardDTO;

public interface IAward {

	void insertAward(AwardDTO awardDTO);

	List<AwardDTO> getAllAwards(String name);

	void updateAward(AwardDTO awardDTO);
}