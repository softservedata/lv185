package edu.softserveinc.healthbody.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.dao.CompetitionDao;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.services.CompetitionsService;
import edu.softserveinc.healthbody.services.KeysForFilters;

public class CompetitionsServiceImpl implements CompetitionsService {

	@Override
	public List<CompetitionDTO> getAll(int partNumber, int partSize, Map<String, String> filters) 
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		
		fillFilters(filters);
		List<CompetitionDTO> competitionDTOs = new ArrayList<CompetitionDTO>();
		for (Competition competition : CompetitionDao.get().getFilterRange((partNumber - 1) * partSize, partSize,
				filters)) {
			competitionDTOs.add(new CompetitionDTO(competition.getName(), "count", competition.getStart(),
					competition.getFinish(), null, null));
		}
		//

		return competitionDTOs;
	}
	@Override
	public List<CompetitionDTO> getAllActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllActiveByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompetitionDTO getCompetition() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(List<CompetitionDTO> competitionDTOs) {
		competitionDTOs.add(new CompetitionDTO(null, null, null, null, null, null));
	}

	private void fillFilters(Map<String, String> filters) {
		filters.put(KeysForFilters.CompetitionsServiceKeys.NAME.toString(), "name");
		filters.put(KeysForFilters.CompetitionsServiceKeys.COUNT.toString(), "count");
		filters.put(KeysForFilters.CompetitionsServiceKeys.START_DATE.toString(), "startDate");
		filters.put(KeysForFilters.CompetitionsServiceKeys.FINISH_DATE.toString(), "finishDate");
		filters.put(KeysForFilters.CompetitionsServiceKeys.GROUPS.toString(), "groups");
		filters.put(KeysForFilters.CompetitionsServiceKeys.LOGINS.toString(), "logins");
	}

}
