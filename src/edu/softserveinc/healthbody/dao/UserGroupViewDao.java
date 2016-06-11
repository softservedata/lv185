package edu.softserveinc.healthbody.dao;

import java.lang.reflect.Field;

import edu.softserveinc.healthbody.entity.GroupDB;
import edu.softserveinc.healthbody.entity.UserDB;
import edu.softserveinc.healthbody.entity.UserGroupViewDB;
import edu.softserveinc.healthbody.entity.UserGroupViewDB.UserGroupViewQueries;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;

public class UserGroupViewDao extends AbstractDao<UserGroupViewDB>{
	
	private static volatile UserGroupViewDao instance = null;
	
	public UserGroupViewDao() {
		super();
		init();
	}
	
	
	private static UserGroupViewDao get(){
		if (instance == null){
			synchronized (UserGroupViewDao.class) {
				if (instance == null){
					instance = new UserGroupViewDao();
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
	protected UserGroupViewDB createInstance(String[] args) {
		return new UserGroupViewDB(Integer.parseInt(args[0] == null ? "0" : args[0]),
								   Integer.parseInt(args[1] == null ? "0" : args[1]), 
								   Integer.parseInt(args[2] == null ? "0" : args[2]),
								   Boolean.parseBoolean(args[3] == null ? "false" : args[3]));
	}


	@Override
	protected String[] getFields(UserGroupViewDB entity) {
		String[] fields = new String[UserGroupViewDB.class.getDeclaredFields().length];
		Field[] fieldsEE = UserGroupViewDB.class.getDeclaredFields(); 
		for(int i =0;i<fieldsEE.length;i++){
			fieldsEE[i].setAccessible(true);
			try {
				fields[i] = fieldsEE[i].get(entity).toString();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}			
		}
		return fields;
	}
	
	
	public boolean addUserToGroup(UserDB user, GroupDB group) throws JDBCDriverException, EmptyResultSetException, DataBaseReadingException{
		boolean result = false;
		String id_user_group = getIdByTwoEntities(String.valueOf(user.getIdUser()), String.valueOf(group.getIdGroup().toString()));
		if (id_user_group != null){
			updateByField("usersgroups.member_group", "true", "usersgroups.id_user_group", id_user_group);
		}		
		return result;
	}
	

}
