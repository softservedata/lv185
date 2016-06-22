package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public interface CompetitionsService extends BaseFilterService<CompetitionDTO> {

	// getAll

	// getAll + filters

	// getAll + active
	List<CompetitionDTO> getAllActive() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException;

	List<CompetitionDTO> getAllByUser();

	List<CompetitionDTO> getAllActiveByUser();

	CompetitionDTO getCompetition();

}
