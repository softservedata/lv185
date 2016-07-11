package edu.softserveinc.healthbody.webservice.impl;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import edu.softserveinc.healthbody.webservice.HealthBodyWS;

@WebService(endpointInterface = "edu.softserveinc.healthbody.webservice.HealthBodyWS")
public class HealthBodyWSImpl implements HealthBodyWS {

	private static Logger logger = LoggerFactory.getLogger(HealthBodyWSImpl.class.getName());

	// public static void init() {
	// Endpoint.publish("http://localhost:8080/ws/hello", new
	// HealthBodyWSImpl());
	// }
	//
	//
	//
	// public HealthBodyWSImpl() {
	// init();
	// }

	@Override
	public void insert(CompetitionDTO competitionDTO) {
		CompetitionsServiceImpl cServiceImpl = new CompetitionsServiceImpl();
		try {
			cServiceImpl.insert(competitionDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
	}

	@Override
	public CompetitionDTO get(String name) {
		return null;
	}

	/*@Override
	public void update(CompetitionDTO competitionDTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(CompetitionDTO competitionDTO) {
		// TODO Auto-generated method stub

	}
*/
	@Override
	public List<CompetitionDTO> getAllCompetitions(int partNumber, int partSize) {
		try {
			return new CompetitionsViewServiceImpl().getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllActive(int partNumber, int partSize) {
		try {
			return new CompetitionsViewServiceImpl().getAllActive(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllByUser(int partNumber, int partSize, String login) {
		try {
			return new CompetitionsViewServiceImpl().getAllByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<CompetitionDTO> getAllActiveByUser(int partNumber, int partSize, String login) {
		try {
			return new CompetitionsViewServiceImpl().getAllActiveByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public String getDescriptionOfGroup(GroupDTO groupDTO) {
		return GroupServiceImpl.getInstance().getDescriptionOfGroup(groupDTO);
	}

	@Override
	public GroupDTO getGroup(String name) {
		try {
			return GroupServiceImpl.getInstance().getGroup(name);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | CloseStatementException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<GroupDTO> getAllGroups(int partNumber, int partSize) {
		try {
			return GroupServiceImpl.getInstance().getAll(partNumber, partSize);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | EmptyResultSetException
				| CloseStatementException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public void update(GroupDTO groupDTO) {
		try {
			GroupServiceImpl.getInstance().update(groupDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
	}
	@Override
	public List<UserDTO> getAllUsers(int partNumber, int partSize) {
		try {
			return new UsersViewServiceImpl().getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<UserDTO> getAllbyAdmin(int partNumber, int partSize) {
		try {
			return new UsersViewServiceImpl().getAllbyAdmin(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<UserDTO> getAlltoAddInCompetition(int partNumber, int partSize) {
		try {
			return new UsersViewServiceImpl().getAlltoAddInCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<UserDTO> getAllinGroup(int partNumber, int partSize) {
		try {
			return new UsersViewServiceImpl().getAllinGroup(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

	@Override
	public List<UserDTO> getAllinCompetition(int partNumber, int partSize) {
		try {
			return new UsersViewServiceImpl().getAllinCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			logger.error("create user failed", e);
		}
		return null;
	}

}