package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.dao.RoleDao;
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

public class UserProfileServiceImpl implements IBaseService<UserDTO> {
	
	private static volatile UserProfileServiceImpl instance = null;
	
	protected final static String TRANSACTION_ERROR = "Transaction Error, Rollback";

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
	
	@Override
	public void insert(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException,
							QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		
		ConnectionManager.getInstance().beginTransaction();
		Role roles = RoleDao.get().getRoleByName(userDTO.getRoleName());
		
		try {
			UserDao.get().createUser(new User(0, userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(), userDTO.getLastname(),
					 userDTO.getEmail(), Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()), userDTO.getGender(), userDTO.getHealth(), userDTO.getPhotoURL(), userDTO.getGoogleApi(), roles.getIdRole(), userDTO.getStatus()));
			User user = UserDao.get().getUserByLoginName(userDTO.getLogin());
			Group group = GroupDao.get().getGroupByName(userDTO.getGroups().get(0).getName());
			UserGroupDao.get().createUserGroup(user, group);
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		
	}

	@Override
	public UserDTO get(String name) throws SQLException, JDBCDriverException, EmptyResultSetException, TransactionException, CloseStatementException {
		
		User user = null;
		Role role = null;
		Group group = null;
		List<UserGroup> ugs = new ArrayList<UserGroup>();
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
		ConnectionManager.getInstance().beginTransaction();
		try {
			 user = UserDao.get().getUserByLoginName(name);
			 role = RoleDao.get().getRoleById(user.getIdRole());
			 ugs = UserGroupDao.get().getUGbyId(user.getId());
			 for( UserGroup ug : ugs ){
			 group = GroupDao.get().getById(ug.getIdGroup());
			 groups.add(new GroupDTO(group.getName(), "", "", ""));
			 }
			 
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		
		return new UserDTO(user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(), role.getName(), user.getStatus(), "", groups);
	}
	
	public UserDTO getbyId(Integer id) throws SQLException, JDBCDriverException, TransactionException, CloseStatementException, EmptyResultSetException {
		User user = null;
		Role role = null;
		Group group = null;
		List<UserGroup> ugs = new ArrayList<UserGroup>();
		List<GroupDTO> groups = new ArrayList<GroupDTO>();
		
		ConnectionManager.getInstance().beginTransaction();
		try {
			 user = UserDao.get().getUserById(id);
			 role = RoleDao.get().getRoleById(user.getIdRole());
			 ugs = UserGroupDao.get().getUGbyId(user.getId());
			 for( UserGroup ug : ugs ){
			 group = GroupDao.get().getById(ug.getIdGroup());
			 groups.add(new GroupDTO(group.getName(), "", "", ""));
			 }
			 
		} catch (JDBCDriverException | DataBaseReadingException | QueryNotFoundException e) {
			ConnectionManager.getInstance().rollbackTransaction();
			throw new TransactionException(TRANSACTION_ERROR, e);
		}
		ConnectionManager.getInstance().commitTransaction();
		
		return new UserDTO(user.getLogin(), user.getPasswd(), user.getFirsName(), user.getLastName(), user.getMail(),
				user.getAge().toString(), user.getWeight().toString(), user.getGender(), user.getAvatar(), role.getName(), user.getStatus(), "", groups);
	}

	@Override
	public void update(UserDTO userDTO) throws SQLException, JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, TransactionException, CloseStatementException {
		
		ConnectionManager.getInstance().beginTransaction();
		Role role = RoleDao.get().getByFieldName(userDTO.getRoleName());
		try {	
			UserDao.get().updateUser(new User(0, userDTO.getLogin(), userDTO.getPassword(), userDTO.getFirstname(), userDTO.getLastname(),
					 userDTO.getEmail(), Integer.parseInt(userDTO.getAge()), Double.parseDouble(userDTO.getWeight()), userDTO.getGender(), userDTO.getHealth(), userDTO.getPhotoURL(), userDTO.getGoogleApi(), role.getIdRole(), userDTO.getStatus()));
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
