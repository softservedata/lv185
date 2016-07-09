package edu.softserveinc.healthbody.webservice;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface HealthBodyWS {

	// Competitions
	@WebMethod
	void insert(CompetitionDTO competitionDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
			QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException;

	@WebMethod
	CompetitionDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException,
			TransactionException, CloseStatementException;

	@WebMethod
	void update(CompetitionDTO competitionDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
			QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException;

	@WebMethod
	void delete(CompetitionDTO competitionDTO);

	@WebMethod
	List<CompetitionDTO> getAllCompetitions(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	@WebMethod
	List<CompetitionDTO> getAllActive(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	@WebMethod
	List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;

	@WebMethod
	List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException;

	// Groups
	@WebMethod
	String getDescriptionOfGroup(GroupDTO groupDTO);

	@WebMethod
	GroupDTO getGroup(String name)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException;

	@WebMethod
	List<GroupDTO> getAllGroups(int partNumber, int partSize) throws QueryNotFoundException, JDBCDriverException,
			DataBaseReadingException, EmptyResultSetException, CloseStatementException;

	@WebMethod
	void update(GroupDTO groupDTO) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException,
			CloseStatementException, SQLException, EmptyResultSetException, TransactionException;

	// Users
	@WebMethod
	List<UserDTO> getAllUsers(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	@WebMethod
	List<UserDTO> getAllbyAdmin(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	@WebMethod
	List<UserDTO> getAlltoAddInCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	@WebMethod
	List<UserDTO> getAllinGroup(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

	@WebMethod
	List<UserDTO> getAllinCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException;

}