package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.constants.ServiceConstants;
import edu.softserveinc.healthbody.dao.UsersViewDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.UsersView;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.IUsersViewService;

public class UsersViewServiceImpl implements IUsersViewService {

	/*
	 * The getAll method is used for returning all users. Also used as basic
	 * method for following getAllbyAdmin,getAlltoAddInCompetition,
	 * getAllinGroup, getAllinCompetition.
	 **/
	@Override
	public List<UserDTO> getAll(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
			for (UsersView usersView : UsersViewDao.getInstance().getAllUsersView(partNumber, partSize)) {
				userDTO.add(new UserDTO(usersView.getFirsName(), usersView.getLastName(), usersView.getLogin(),
						usersView.getPasswd(), usersView.getMail(), usersView.getAge().toString(),
						usersView.getWeight().toString(), usersView.getGender(), usersView.getAvatar(),
						usersView.getRoleName(), usersView.getStatus(), usersView.getScore().toString(), null, null));
			}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException
				| DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return userDTO;
	}

	/*
	 * The getAllbyAdmin method is used for returning extended list of all users
	 * for admin role on a pages where users lists presented.
	 **/
	@Override
	public List<UserDTO> getAllbyAdmin(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return getAll(partNumber, partSize);
	}

	/*
	 * The getAlltoAddInCompetition used for returning part of users list in
	 * competitions UI when user path's by competition -> description-> (press
	 * button) invite user.
	 **/
	@Override
	public List<UserDTO> getAlltoAddInCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
				for (UsersView usersView : UsersViewDao.getInstance().getAllUsersView(partNumber, partSize)) {
					userDTO.add(new UserDTO(usersView.getFirsName(), usersView.getLastName(), null, null, null,
							usersView.getAge().toString(), usersView.getWeight().toString(), null, usersView.getAvatar(), null,
							null, usersView.getScore().toString(), null, null));
				}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException
				| DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();		
		return userDTO;
	}

	/*
	 * The getAllinGroup used for returning part of users list in Groups UI when
	 * user path's by main -> groups-> description of groups-> ->(press button)
	 * invite user to group.
	 **/
	@Override
	public List<UserDTO> getAllinGroup(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		return getAlltoAddInCompetition(partNumber, partSize);
	}

	/*
	 * The getAllinCompetition used for returning part of users list in
	 * competitions UI
	 **/
	@Override
	public List<UserDTO> getAllinCompetition(int partNumber, int partSize)
			throws JDBCDriverException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		ConnectionManager.getInstance().beginTransaction();
		try {
				for (UsersView usersView : UsersViewDao.getInstance().getAllUsersView(partNumber, partSize)) {
					userDTO.add(new UserDTO(usersView.getFirsName(), usersView.getLastName(), null, null, null,
							usersView.getAge().toString(), usersView.getWeight().toString(), null, usersView.getAvatar(), null,
							null, usersView.getScore().toString(), null, null));
				}
		} catch (CloseStatementException | QueryNotFoundException | EmptyResultSetException
				| DataBaseReadingException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(ServiceConstants.TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();	
		return userDTO;
	}

}