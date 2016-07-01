package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dao.UsersViewDao;
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

	@Override
	public List<UserDTO> getAll(int partNumber, int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException {
		List<UserDTO> userDTO = new ArrayList<>();
		for (UsersView usersView : UsersViewDao.get().getAllUsersView(partNumber,
				partSize)) {
			userDTO.add(new UserDTO(usersView.getFirsName(), usersView.getLastName(), usersView.getLogin(),
					usersView.getPasswd(), usersView.getMail(), usersView.getAge().toString(),
					usersView.getWeight().toString(), usersView.getGender(), usersView.getAvatar(),
					usersView.getRoleName(), usersView.getStatus(), usersView.getScore().toString(), null));
		}
		return userDTO;
	}
	
	@Override
	public List<UserDTO> getAllbyAdmin(int partNumber, int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException, SQLException, TransactionException {
		return getAll(partNumber, partSize);
	}

	@Override
	public List<UserDTO> getAlltoAddInCompetition(int partNumber, int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		for (UsersView usersView : UsersViewDao.get().getAllUsersView(partNumber,
				partSize)) {
			userDTO.add(new UserDTO(usersView.getFirsName(), usersView.getLastName(), null,
					null, null, usersView.getAge().toString(), usersView.getWeight().toString(),
					null, usersView.getAvatar(), null, null, usersView.getScore().toString(), null));
		}
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllinGroup(int partNumber, int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException, SQLException, TransactionException {
		return getAlltoAddInCompetition(partNumber, partSize);
	}

	@Override
	public List<UserDTO> getAllinCompetition(int partNumber, int partSize)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException, SQLException, TransactionException {
		List<UserDTO> userDTO = new ArrayList<>();
		for (UsersView usersView : UsersViewDao.get().getAllUsersView(partNumber,
				partSize)) {
			userDTO.add(new UserDTO(usersView.getFirsName(), usersView.getLastName(), null,
					null, null, usersView.getAge().toString(), usersView.getWeight().toString(), null,
					usersView.getAvatar(), null, null, usersView.getScore().toString(), null));
		}
		return userDTO;
	}

}