package edu.softserveinc.healthbody.webservice;

import java.sql.SQLException;
import java.util.List;

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
import edu.softserveinc.healthbody.services.impl.UserProfileServiceImpl;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class HealthBodyServiceAxis {
private static Logger logger = LoggerFactory.getLogger(HealthBodyServiceImpl.class.getName());
	
	
	public void createUser(UserDTO userDTO) {
		try {
			UserProfileServiceImpl.getInstance().insert(userDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			logger.error("create user failed", e);
		}
	}

	
	public UserDTO getUserByLogin(String login) {
		try {
			return UserProfileServiceImpl.getInstance().get(login);
		} catch (SQLException | JDBCDriverException | EmptyResultSetException | TransactionException
				| CloseStatementException e) {
			logger.error("get user by login failed", e);
		}
		return null;
	}

	
	public void updateUser(UserDTO userDTO) {
		try {
			UserProfileServiceImpl.getInstance().update(userDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			logger.error("update user failed", e);
		}
	}

	
	public void lockUser(UserDTO userDTO, boolean isDisabled) {
		try {
			UserProfileServiceImpl.getInstance().lock(userDTO, isDisabled);
		} catch (SQLException | JDBCDriverException | QueryNotFoundException | DataBaseReadingException
				| TransactionException | CloseStatementException e) {
			logger.error("lock user failed", e);
		}
	}

	
	public List<UserDTO> getAllUsers(int partNumber, int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("get all users failed", e);
		}
		return null;
	}

	
	public List<UserDTO> getAllUserstoAddInCompetition(int partNumber, int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAlltoAddInCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("get all users to add in competition failed", e);
		}
		return null;
	}

	
	public List<UserDTO> getAllUsersinCompetition(int partNumber, int partSize) {
		UsersViewServiceImpl user = new UsersViewServiceImpl();
		try {
			return user.getAllinCompetition(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("get all users in competition failed", e);
		}
		return null;
	}

	
	public List<GroupDTO> getAllGroups(int partNumber, int partSize) {
		try {
			return GroupServiceImpl.getInstance().getAll(partNumber, partSize);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | EmptyResultSetException
				| CloseStatementException e) {
			logger.error("get all groups failed", e);
		}
		return null;
	}


	public GroupDTO getGroupByName(String name) {
		try {
			return GroupServiceImpl.getInstance().getGroup(name);
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | CloseStatementException e) {
			logger.error("get group by name failed", e);
		}
		return null;
	}

	
	public String getDescriptionOfGroup(GroupDTO groupDTO) {
		return  GroupServiceImpl.getInstance().getDescriptionOfGroup(groupDTO);
	}

	
	public void updateGroup(GroupDTO groupDTO) {
		try {
			GroupServiceImpl.getInstance().update(groupDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			logger.error("update group failed", e);
		}
	}

	
	public List<CompetitionDTO> getAllCompetitions(int partNumber, int partSize) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAll(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("get all competitions failed", e);
		}
		return null;
	}

	
	public List<CompetitionDTO> getAllActiveCompetitions(int partNumber, int partSize) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAllActive(partNumber, partSize);
		} catch (JDBCDriverException | SQLException | TransactionException e) {
			logger.error("get all active competitions failed", e);
		}
		return null;
	}

	
	public List<CompetitionDTO> getAllCompetitionsByUser(int partNumber, int partSize, String login) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAllByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			logger.error("get all competitions by user failed", e);
		}
		return null;
	}

	
	public List<CompetitionDTO> getAllActiveCompetitionsByUser(int partNumber, int partSize, String login) {
		CompetitionsViewServiceImpl competitionView = new CompetitionsViewServiceImpl();
		try {
			return competitionView.getAllActiveByUser(partNumber, partSize, login);
		} catch (IllegalAgrumentCheckedException | SQLException | JDBCDriverException | TransactionException e) {
			logger.error("get all active competitions by user failed", e);
		}
		return null;
	}

	public void createCompetition(CompetitionDTO competitionDTO) {
		CompetitionsServiceImpl competition = new CompetitionsServiceImpl();
		try {
			competition.insert(competitionDTO);
		} catch (SQLException | JDBCDriverException | DataBaseReadingException | QueryNotFoundException
				| EmptyResultSetException | TransactionException | CloseStatementException e) {
			logger.error("create competition failed", e);
		}

	}

}
