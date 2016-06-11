package edu.softserveinc.healthbody.dao;

import java.lang.reflect.Field;

import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.entity.User.UserDBQueries;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public final class UserDao extends AbstractDao<User> {
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
	protected String[] getFields(User entity) {
		String[] fields = new String[User.class.getDeclaredFields().length];
		Field[] fieldsEE = User.class.getDeclaredFields(); 
		for(int i =0;i<fieldsEE.length;i++){
			fieldsEE[i].setAccessible(true);
			try {
				fields[i] = fieldsEE[i].get(entity).toString();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return fields;
	}

	@Override
	protected User createInstance(String[] args) {
		// TODO Auto-generated method stub
		return new User(Integer.parseInt(args[0] == null ? "0" : args[0]), args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2], args[3] == null ? new String() : args[3],
				args[4] == null ? new String() : args[4], args[5] == null ? new String() : args[5],
				Integer.parseInt(args[6] == null ? "0" : args[1]), Integer.parseInt(args[7] == null ? "0" : args[1]),
				Integer.parseInt(args[8] == null ? "0" : args[1]));
	}

	public User getUserDBByLogin(String login) throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException {
		return getByField("Login", login).get(0);
	}
}
