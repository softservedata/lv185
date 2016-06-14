package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.UserGroup;
import edu.softserveinc.healthbody.dao.DaoStatementsConstant.UserGroupViewQueries;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class UserGroupDao extends AbstractDao<UserGroup>{
	
	private static volatile UserGroupDao instance = null;

	
	public UserGroupDao() {
		super();
		init();
	}
	
	
	public static UserGroupDao get(){
		if (instance == null){
			synchronized (UserGroupDao.class) {
				if (instance == null){
					instance = new UserGroupDao();
				}
			}
		}
		return instance;
	}

	protected void init() {
		for(UserGroupViewQueries userGroupViewQueries:UserGroupViewQueries.values()){
			sqlQueries.put(userGroupViewQueries.getDaoQuery(),userGroupViewQueries);
		}
		
	}

	

	@Override
	protected UserGroup createInstance(String[] args) {
		return new UserGroup(Integer.parseInt(args[0] == null ? "0" : args[0]),
								   Integer.parseInt(args[1] == null ? "0" : args[1]), 
								   Integer.parseInt(args[2] == null ? "0" : args[2]),
								   Boolean.parseBoolean(args[3] == null ? "false" : args[3]));
	}


	@Override
	protected String[] getFields(UserGroup entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUserGroup().toString());
		fields.add(entity.getIdUser().toString());
		fields.add(entity.getIdGroup().toString());
		fields.add(entity.getMemberGgoup().toString());
		return (String[]) fields.toArray();
	}
	
	
	public boolean addUserToGroup(User user, Group group) throws QueryNotFoundException, JDBCDriverException, EmptyResultSetException, DataBaseReadingException{
		boolean result = false;
		String id_user_group = getIdByTwoEntities(String.valueOf(user.getIdUser()), String.valueOf(group.getIdGroup().toString()));
		if (id_user_group != null){
			updateByField("usersgroups.member_group", "true", "usersgroups.id_user_group", id_user_group);
		}		
		return result;
	}


	

}
