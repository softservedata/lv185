package com.softserve.edu.hb.services;

import java.util.List;
import java.util.Map;

import com.softserve.edu.hb.dto.CompetitionDTO;

public class CompetitionsServiceImpl implements BaseFilterService<CompetitionDTO> {

    public static enum CompetitionsServiceKeys {
    	STATUS("status"),
    	NAME("name"),
    	STARTDATE("startDate"),
    	FINISHDATE("finishDate"),
    	GROUP("group"),
    	USER("user"),
    	SCORE("score");
        private String key;

        private CompetitionsServiceKeys(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }
    }

	public List<CompetitionDTO> getAllUsers(int partNumber, int partSize, Map<String, String> filters) {
		return null;
	}

	public void updateUsers(List<CompetitionDTO> baseDTO) {
	}


}
