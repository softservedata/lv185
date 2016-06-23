package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.dao.RoleDao;
import edu.softserveinc.healthbody.dao.UserCompetitionsDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.dao.UserGroupDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserCompetitions;
import edu.softserveinc.healthbody.entity.UserGroup;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.KeysForFilters;
import edu.softserveinc.healthbody.services.UsersService;

public class UsersServiceImpl implements UsersService {
	protected final static String TRANSACTION_ERROR = "Transaction Error, Rollback";

	@Override
	public List<UserDTO> getAll(int partNumber, int partSize, Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException, SQLException, TransactionException {
		fillFilters(filters);
		UserCompetitions uc = null;
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
			ConnectionManager.getInstance().beginTransaction();
			try {
				for (User user : UserDao.get().getFilterRange((partNumber - 1) * partSize, partSize,
						filters)) {
					uc = (UserCompetitions) UserCompetitionsDao.get().getByField("idUser", user.getId().toString());
					
					userDTOs.add(new UserDTO(null, null, user.getFirsName(), user.getLastName(), null, user.getAge().toString(), user.getWeight().toString(), null, 
							user.getAvatar(), null, null, uc.getUserScore().toString(), null)); 
				}
					
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction();
				throw new TransactionException(TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction();
			
			
			
		return userDTOs;
	}

	@Override
	public List<UserDTO> getAllbyAdmin(int partNumber, int partSize, Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAlltoAddInCompetition(int partNumber, int partSize, Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllinGroup(int partNumber, int partSize, Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllinCompetition(int partNumber, int partSize, Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(List<UserDTO> userDTOs) {
		userDTOs.add(new UserDTO(null, null, null, null, null, null, null, null, null, null, null, null, null));
	}
	
	private void fillFilters(Map<String, String> filters) {
		filters.put(KeysForFilters.UsersServiceKeys.FIRST_NAME.toString(), "firstname");
		filters.put(KeysForFilters.UsersServiceKeys.LAST_NAME.toString(), "lastname");
		filters.put(KeysForFilters.UsersServiceKeys.LOGIN.toString(), "login");
		filters.put(KeysForFilters.UsersServiceKeys.PASSWORD.toString(), "password");
		filters.put(KeysForFilters.UsersServiceKeys.EMAIL.toString(), "email");
		filters.put(KeysForFilters.UsersServiceKeys.AGE.toString(), "age");
		filters.put(KeysForFilters.UsersServiceKeys.WEIGHT.toString(), "weight");
		filters.put(KeysForFilters.UsersServiceKeys.GENDER.toString(), "gender");
		filters.put(KeysForFilters.UsersServiceKeys.PHOTO_URL.toString(), "photoURL");
		filters.put(KeysForFilters.UsersServiceKeys.ROLE_NAME.toString(), "roleName");
		filters.put(KeysForFilters.UsersServiceKeys.STATUS.toString(), "status");
		filters.put(KeysForFilters.UsersServiceKeys.SCORE.toString(), "score");
		filters.put(KeysForFilters.UsersServiceKeys.GROUPS.toString(), "groups");
	}

}