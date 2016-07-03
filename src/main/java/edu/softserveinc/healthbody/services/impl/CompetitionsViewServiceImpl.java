package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.softserveinc.healthbody.dao.CompetitionsViewDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;

public class CompetitionsViewServiceImpl implements ICompetitionsViewService {

	private final static Logger logger = LoggerFactory.getLogger(CompetitionsViewServiceImpl.class.getName());
	protected final static String TRANSACTION_ERROR = "Transaction error. Rollback.";

	@Override
	public List<CompetitionDTO> getAll(int partNumber, int partSize) throws JDBCDriverException, SQLException, TransactionException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.get().getAllCompetitionsView(partNumber,
					partSize)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllActive(int partNumber, int partSize) throws JDBCDriverException, SQLException, TransactionException {
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.get().getActiveCompetitionsView(partNumber,
					partSize)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			logger.error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.get().getCompetitionsByUserView(partNumber,
					partSize, login)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

	@Override
	public List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		if (login == null || login.isEmpty()) {
			String errorStr = "Illegal parameter. \"login\" is empty or null.";
			logger.error(errorStr);
			throw new IllegalAgrumentCheckedException(errorStr);
		}
		List<CompetitionDTO> competitionDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (CompetitionsView competitionsView : CompetitionsViewDao.get().getActiveCompetitionsByUserView(partNumber,
					partSize, login)) {
				competitionDTO.add(new CompetitionDTO(competitionsView.getName(),
						competitionsView.getUsersCount().toString(), competitionsView.getStart(),
						competitionsView.getFinish(), competitionsView.getDescription(), null, new ArrayList<String>(),
						new ArrayList<String>()));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException | DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return competitionDTO;
	}

}
