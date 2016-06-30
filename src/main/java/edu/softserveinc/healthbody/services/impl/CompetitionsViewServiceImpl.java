package edu.softserveinc.healthbody.services.impl;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dao.CompetitionsViewDao;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;

public class CompetitionsViewServiceImpl implements ICompetitionsViewService {

	@Override
	public List<CompetitionDTO> getAll(int partNumber, int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		for (CompetitionsView competitionsView : CompetitionsViewDao.get().getAllCompetitionsView(partNumber,
				partSize)) {
			competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
					competitionsView.getUsersCount().toString(), competitionsView.getStart(),
					competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(), new ArrayList<String>()));
		}
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllActive(int partNumber, int partSize) throws QueryNotFoundException,
			JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		for (CompetitionsView competitionsView : CompetitionsViewDao.get().getActiveCompetitionsView(partNumber,
				partSize)) {
			competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
					competitionsView.getUsersCount().toString(), competitionsView.getStart(),
					competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(), new ArrayList<String>()));
		}
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		for (CompetitionsView competitionsView : CompetitionsViewDao.get()
				.getCompetitionsByUserView(partNumber, partSize, login)) {
			competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
					competitionsView.getUsersCount().toString(), competitionsView.getStart(),
					competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(), new ArrayList<String>()));
		}
		return competitionDTO;
	}


	@Override
	public List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		for (CompetitionsView competitionsView : CompetitionsViewDao.get()
				.getActiveCompetitionsByUserView(partNumber, partSize, login)) {
			competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
					competitionsView.getUsersCount().toString(), competitionsView.getStart(),
					competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(), new ArrayList<String>()));
		}
		return competitionDTO;
	}

}
