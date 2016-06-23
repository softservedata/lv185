package edu.softserveinc.healthbody.services.impl;

import java.sql.Date;
import java.sql.SQLException;

import edu.softserveinc.healthbody.dao.CompetitionDao;
import edu.softserveinc.healthbody.dao.CriteriaDao;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.CompetitionsService;

public class CompetitionsServiceImpl implements CompetitionsService {

	@Override
	public void insert(CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
		int idCriteria = CriteriaDao.get().getByFieldName(competitionDTO.getNameCriteria()).getIdCriteria();
		CompetitionDao.get().createCompetition(new Competition(0, competitionDTO.getName(), competitionDTO.getDescription(), Date.valueOf(competitionDTO.getStartDate()), Date.valueOf(competitionDTO.getFinishDate()), idCriteria));

	}
	// TODO 
	@Override
	public CompetitionDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException,
			TransactionException, CloseStatementException {
		return null;
	}
	// TODO 
	@Override
	public void update(CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
	}
	// TODO 
	@Override
	public void delete(CompetitionDTO competitionDTO) {
	}

}
