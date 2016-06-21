package edu.softserveinc.healthbody.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import edu.softserveinc.healthbody.dao.RoleDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.Role;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;

public class UserProfileService implements BaseService<UserDTO> {
	
	private static volatile UserProfileService instance = null;
	
	protected final static String TRANSACTION_ERROR = "Transaction Error, Rollback";

	private UserProfileService() {
	}
	
	public static UserProfileService getInstance() {
		if(instance == null) {
			synchronized (UserProfileService.class) {
				if (instance == null) {
					instance = new UserProfileService();
				}
			}
		}
		return instance;
	}
	
	//TODO add scores, groups, competitions
	public void insert(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
							QueryNotFoundException, EmptyResultSetException, TransactionException {
		
		ConnectionManager.getInstance().beginTransaction();
		List<Role> roles = RoleDao.get().getByField("name", userDTO.getRoleName());
		try {
			UserDao.get().createUser(new User(0, userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(),userDTO.getLastname(),
				userDTO.getGender(),Integer.parseInt(userDTO.getWeight()), Integer.parseInt(userDTO.getAge()), roles.get(0).getId()));
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		
	}

	@Override
	public UserDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException {
		
		User user = null;
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
		ConnectionManager.getInstance().beginTransaction();
		try {
			 user = UserDao.get().getUserByLogin(name);
			 
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		
		return new UserDTO(user.getFirsName(), user.getLastName(), user.getLogin(), user.getPasswd(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), "", "", "", "", groups);
	}

	@Override
	public void update(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException {
		
		ConnectionManager.getInstance().beginTransaction();
		List<Role> roles = RoleDao.get().getByField("name", userDTO.getRoleName());
		try {	
			UserDao.get().updateUser(new User(0,  userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(),userDTO.getLastname(),
					userDTO.getGender(),Integer.parseInt(userDTO.getWeight()), Integer.parseInt(userDTO.getAge()), roles.get(0).getId()));
		}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
	}

	@Override
	public void delete(UserDTO baseDTO) {
		// TODO Auto-generated method stub
		
	}

	
}
