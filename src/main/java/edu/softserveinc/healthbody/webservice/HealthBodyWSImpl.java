package edu.softserveinc.healthbody.webservice;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;

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
import edu.softserveinc.healthbody.services.impl.CompetitionsServiceImpl;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

@WebService(endpointInterface = "edu.softserveinc.healthbody.webservice.HealthBodyWS")
public class HealthBodyWSImpl implements HealthBodyWS {
	
//	public static void init() {
//		   Endpoint.publish("http://localhost:8080/ws/hello", new HealthBodyWSImpl());
//	    }
//	
//	
//
//	public HealthBodyWSImpl() {
//		init();
//	}



	@Override
	public void insert(CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
      CompetitionsServiceImpl cServiceImpl = new CompetitionsServiceImpl();
      cServiceImpl.insert(competitionDTO);
	}

	@Override
	public CompetitionDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException,
			TransactionException, CloseStatementException {
		return null;
	}

	@Override
	public void update(CompetitionDTO competitionDTO)
			throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException,
			EmptyResultSetException, TransactionException, CloseStatementException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CompetitionDTO competitionDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CompetitionDTO> getAllCompetitions(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return new CompetitionsViewServiceImpl().getAll(partNumber, partSize);
	}

	@Override
	public List<CompetitionDTO> getAllActive(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return  new CompetitionsViewServiceImpl().getAllActive(partNumber, partSize);
	}

	@Override
	public List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		return  new CompetitionsViewServiceImpl().getAllByUser(partNumber, partSize, login);
	}

	@Override
	public List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login)
			throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		return  new CompetitionsViewServiceImpl().getAllActiveByUser(partNumber, partSize, login);
	}

	@Override
	public String getDescriptionOfGroup(GroupDTO groupDTO) {
		return GroupServiceImpl.getInstance().getDescriptionOfGroup(groupDTO);
	}

	@Override
	public GroupDTO getGroup(String name)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		return GroupServiceImpl.getInstance().getGroup(name);
	}

	@Override
	public List<GroupDTO> getAllGroups(int partNumber, int partSize) throws QueryNotFoundException, JDBCDriverException,
			DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		return GroupServiceImpl.getInstance().getAll(partNumber, partSize);
	}

	@Override
	public void update(GroupDTO groupDTO) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException,
			CloseStatementException, SQLException, EmptyResultSetException, TransactionException {
		GroupServiceImpl.getInstance().update(groupDTO);
	}

	@Override
	public List<UserDTO> getAllUsers(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return new UsersViewServiceImpl().getAll(partNumber, partSize);
	}

	@Override
	public List<UserDTO> getAllbyAdmin(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
				return new UsersViewServiceImpl().getAllbyAdmin(partNumber, partSize);
	}

	@Override
	public List<UserDTO> getAlltoAddInCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return new UsersViewServiceImpl().getAlltoAddInCompetition(partNumber, partSize);
	}

	@Override
	public List<UserDTO> getAllinGroup(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return new UsersViewServiceImpl().getAllinGroup(partNumber, partSize);
	}

	@Override
	public List<UserDTO> getAllinCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return new UsersViewServiceImpl().getAllinCompetition(partNumber, partSize);
	}

}