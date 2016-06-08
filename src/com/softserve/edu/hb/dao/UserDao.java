package com.softserve.edu.hb.dao;

import com.softserve.edu.hb.entity.UserDB;
import com.softserve.edu.hb.entity.UserDB.UserDBQueries;

public final class UserDao extends ADaoCRUD<UserDB> {
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

	// TODO Create abstract method in ADao
	protected void init() {
		for (UserDBQueries userDBQueries : UserDBQueries.values()) {
			sqlQueries.put(userDBQueries.getDaoQuery(), userDBQueries);
		}
	}

	protected UserDB createInstance(String[] args) {
		return new UserDB(
			Integer.parseInt(args[0] == null ? "0" : args[0]),
			Integer.parseInt(args[1] == null ? "0" : args[1]),
			args[2] == null ? new String() : args[2],
			args[3] == null ? new String() : args[3]);
	}

	protected String[] getFields(UserDB entity) {
		//String[] fields = new String[UserDB.class.getDeclaredFields().length];
		String[] fields = new String[4];
		fields[0] = entity.getIdUser().toString();
		fields[1] = entity.getIdRole().toString();
		fields[2] = entity.getLogin();
		fields[3] = entity.getPasswd();
		return fields;
	}

	public UserDB getUserDBByLogin(String login) {
		return getByFieldName("Login", login).get(0);
	}

	// TODO DELETE Method
	// public boolean deleteById(Long id) {
	// return super.deleteById(id);
	// }

}
