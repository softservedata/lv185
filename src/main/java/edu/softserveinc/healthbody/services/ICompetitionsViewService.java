package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.List;


import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public interface ICompetitionsViewService {

	List<CompetitionDTO> getAll(int partNumber, int partSize) throws JDBCDriverException, SQLException, TransactionException;

	List<CompetitionDTO> getAllActive(int partNumber, int partSize) throws JDBCDriverException, SQLException, TransactionException;

	List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;

	List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;
}
