package edu.softserveinc.healthbody.dao;

import java.lang.reflect.Field;

import edu.softserveinc.healthbody.entity.UserDB;
import edu.softserveinc.healthbody.entity.UserDB.UserDBQueries;

public final class UserDao extends AbstractDaoCRUD<UserDB> {
	private static volatile UserDao instance = null;

	private UserDao() {
		super();
		init();
	}

	public static UserDao get() {
		if (instance == null) {
			synchronized (UserDao.class) {
				if (instance == null) {
					instance = new UserDao();
				}
			}
		}
		return instance;
	}

	private void init() {
		for (UserDBQueries userDBQueries : UserDBQueries.values()) {
			sqlQueries.put(userDBQueries.getDaoQuery(), userDBQueries);
		}
	}

	@Override
	protected String[] getFields(UserDB entity) {
		String[] fields = new String[UserDB.class.getDeclaredFields().length];
		Field[] fieldsEE = UserDB.class.getDeclaredFields(); 
		for(int i =0;i<fieldsEE.length;i++){
			fieldsEE[i].setAccessible(true);
			try {
				fields[i] = fieldsEE[i].get(entity).toString();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
//		for (int i = 1; i < fields.length; i++) {
//			if (fields[i] instanceof String) {
//				if (fields[i] == null) {
//					fields[i] = new String();
//				} else {
//					fields[i] = fields[i];
//				}
//			} else {
//				if (fields[0] == null) {
//					Integer.parseInt(fields[i] = "0");
//				} else {
//					Integer.parseInt(fields[i] = fields[i]);
//				}
//			}
//		}
		return fields;
	}

	@Override
	protected UserDB createInstance(String[] args) {
		// TODO Auto-generated method stub
		return new UserDB(Integer.parseInt(args[0] == null ? "0" : args[0]), args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2], args[3] == null ? new String() : args[3],
				args[4] == null ? new String() : args[4], args[5] == null ? new String() : args[5],
				Integer.parseInt(args[6] == null ? "0" : args[1]), Integer.parseInt(args[7] == null ? "0" : args[1]),
				Integer.parseInt(args[8] == null ? "0" : args[1]));
	}

	public UserDB getUserDBByLogin(String login) {
		return getByField("Login", login).get(0);
	}
}
