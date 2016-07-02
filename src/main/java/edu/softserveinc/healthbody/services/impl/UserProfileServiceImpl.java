package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.dao.RoleDao;
import edu.softserveinc.healthbody.dao.UserCompetitionsDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.dao.UserGroupDao;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.Role;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserGroup;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.IBaseService;
import edu.softserveinc.healthbody.testapp.TestProfile;

public class UserProfileServiceImpl implements IBaseService<UserDTO> {
	
	private static volatile UserProfileServiceImpl instance = null;
	
	protected final static String TRANSACTION_ERROR = "Transaction Error, Rollback";
	
	private static Logger logger = LoggerFactory.getLogger(TestProfile.class.getName());

	private UserProfileServiceImpl() {
	}
	
	public static UserProfileServiceImpl getInstance() {
		if(instance == null) {
			synchronized (UserProfileServiceImpl.class) {
				if (instance == null) {
					instance = new UserProfileServiceImpl();
				}
			}
		}
		return instance;
	}
	
	//create user
	@Override
	public void insert(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
							QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		if (userDTO == null) {
			logger.error("you didn't enter user");
			throw new IllegalArgumentException();
		}
		else {
			ConnectionManager.getInstance().beginTransaction();
			Role roles = RoleDao.get().getRoleByName(userDTO.getRoleName());
		
			try {
				UserDao.get().createUser(new User(0, userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(), userDTO.getLastname(),
					 userDTO.getEmail(), Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()), userDTO.getGender(),
					 userDTO.getHealth(), userDTO.getPhotoURL(), userDTO.getGoogleApi(), roles.getIdRole(), userDTO.getStatus(), false));
				User user = UserDao.get().getUserByLoginName(userDTO.getLogin());
				Group group = GroupDao.getInstance().getGroupByName(userDTO.getGroups().get(0).getName());
				UserGroupDao.get().createUserGroup(user, group);
			} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction();
				throw new TransactionException(TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction();
		}
	}

	//get user by login
	@Override
	public UserDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException {
		if (name == null) {
			logger.error("You didn't enter login");
			throw new IllegalArgumentException();
		}
		else {
			User user = null;
			Role role = null;
			Group group = null;
			List<UserGroup> ugs = new ArrayList<UserGroup>();
			List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
			ConnectionManager.getInstance().beginTransaction();
			try {
				user = UserDao.get().getUserByLoginName(name);
				if (user == null) {
					logger.error("Such user doesn't exist");
					throw new IllegalArgumentException();
				}
				else {
					role = RoleDao.get().getRoleById(user.getIdRole());
					ugs = UserGroupDao.get().getUGbyId(user.getId());
					for( UserGroup ug : ugs ){
						group = GroupDao.getInstance().getById(ug.getIdGroup());
						groups.add(new GroupDTO(group.getName(), "", "", ""));
					}
				}
			 } catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				 ConnectionManager.getInstance().rollbackTransaction();
				 throw new TransactionException(TRANSACTION_ERROR, e);
			 }
			ConnectionManager.getInstance().commitTransaction();
		
			return new UserDTO(user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(), role.getName(), user.getStatus(), "", groups, String.valueOf(user.getIsDisabled()));
		}
	}
	
	//get user by id
	public UserDTO getbyId(Integer id) throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException {
		User user = null;
		Role role = null;
		Group group = null;
		List<UserGroup> ugs = new ArrayList<UserGroup>();
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
		ConnectionManager.getInstance().beginTransaction();
		try {
			 user = UserDao.get().getUserById(id);
			 if (user == null) {
				 logger.error("Such user doesn't exist");
				 throw new IllegalArgumentException();
			 }
			 else {
				 role = RoleDao.get().getRoleById(user.getIdRole());
				 ugs = UserGroupDao.get().getUGbyId(user.getId());
				 for( UserGroup ug : ugs ){
					 group = GroupDao.getInstance().getById(ug.getIdGroup());
					 groups.add(new GroupDTO(group.getName(), "", "", ""));
				 }
			 }			 
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		
		return new UserDTO(user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(), role.getName(), user.getStatus(), "", groups, String.valueOf(user.getIsDisabled()));
	}

	//Update user
	@Override
	public void update(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		if (userDTO == null) {
			logger.error("you didn't enter user");
			throw new IllegalArgumentException();
		}
		else {
			ConnectionManager.getInstance().beginTransaction();
			Role role = RoleDao.get().getByFieldName(userDTO.getRoleName());
			try {	
				UserDao.get().updateUser(new User(0, userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(), userDTO.getLastname(),
					 userDTO.getEmail(), Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()), userDTO.getGender(),
					 userDTO.getHealth(), userDTO.getPhotoURL(), userDTO.getGoogleApi(), role.getIdRole(), userDTO.getStatus(), false));
			}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
				ConnectionManager.getInstance().rollbackTransaction();
				throw new TransactionException(TRANSACTION_ERROR, e);
			}
			ConnectionManager.getInstance().commitTransaction();
		}
	}

	//Delete user for tests
	@Override
	public void delete(UserDTO userDTO) throws SQLException, JDBCDriverException, QueryNotFoundException, DataBaseReadingException, CloseStatementException, TransactionException {
		ConnectionManager.getInstance().beginTransaction();
		try {
			User user = UserDao.get().getUserByLoginName(userDTO.getLogin());
			UserGroupDao.get().deleteByUserId(user.getId());
			UserCompetitionsDao.get().deleteByUserId(user.getId());
			UserDao.get().deleteUserForTests(user.getId());
		}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
	}
	
	//lock and unlock user (for lock - isDisabled = true, for unlock - isDisabled = false)
	public void lock(UserDTO userDTO, boolean isDisabled) throws SQLException, JDBCDriverException, QueryNotFoundException, DataBaseReadingException, TransactionException, CloseStatementException {
		if (userDTO == null) {
			logger.error("You didn't enter user");
			throw new IllegalArgumentException();
		}
		else {
			User user = UserDao.get().getUserByLoginName(userDTO.getLogin());
			if (String.valueOf(user.getIsDisabled()).equals(String.valueOf(isDisabled))) {
				logger.error("You entered incorrect isDisabled");
				if (isDisabled == true) {
					logger.error("User is already locked");
					throw new IllegalArgumentException();
				}
				else {
					logger.error("User is already unlocked");
					throw new IllegalArgumentException();
				}
			}
			else {
				ConnectionManager.getInstance().beginTransaction();
				try {
					UserDao.get().lockUser(isDisabled, userDTO.getLogin());
				}catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
					ConnectionManager.getInstance().rollbackTransaction();
					throw new TransactionException(TRANSACTION_ERROR, e);
				}
		ConnectionManager.getInstance().commitTransaction();
			}
		}
	}
}
