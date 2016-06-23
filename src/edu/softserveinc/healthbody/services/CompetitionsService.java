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

	 List<CompetitionDTO> getAll(int partNumber, int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException;

	// getAll + active
	List<CompetitionDTO> getAllActive(int partNumber, int partSize) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException;

	List<CompetitionDTO> getAllByUser();

	List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException;

	CompetitionDTO getCompetition();

}
