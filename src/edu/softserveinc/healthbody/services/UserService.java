package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.List;

import edu.softserveinc.healthbody.dao.RoleDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.Role;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public class UserService {
	private static volatile UserService instance = null;
	
	protected final static String TRANSACTION_ERROR = "Transaction Error, Rollback";

	private UserService() {
	}
	
	public UserService getInstance() {
		if(instance == null) {
			synchronized (UserService.class) {
				if (instance == null) {
					instance = new UserService();
				}
			}
		}
		return instance;
	}
	
	//TODO add scores, groups, competitions
	public boolean insertUser(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException {
		boolean result = false;
		ConnectionManager.getInstance().beginTransaction();
		List<Role> roles = RoleDao.get().getByField("name", userDTO.getRoleName());
		try {
		result = UserDao.get().insert(new User(0, userDTO.getLoginUser(), "passwd", "firsName", "lastName", "gender", 80, 25, roles.get(0).getId()));
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		return result;
	}
}
