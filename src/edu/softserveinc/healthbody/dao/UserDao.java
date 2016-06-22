package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import edu.softserveinc.healthbody.dao.DaoStatementsConstant.UserDBQueries;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
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

	protected void init() {
		for (UserDBQueries userDBQueries : UserDBQueries.values()) {
			sqlQueries.put(userDBQueries.getDaoQuery(), userDBQueries);
		}
	}

	@Override
	protected String[] getFields(User entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUser().toString());
		fields.add(entity.getLogin());
		fields.add(entity.getPasswd());
		fields.add(entity.getFirsName());
		fields.add(entity.getLastName());
		fields.add(entity.getGender());
		fields.add(entity.getWeight().toString());
		fields.add(entity.getAge().toString());
		fields.add(entity.getGoogleApi());
		fields.add(entity.getHealth());
		fields.add(entity.getAvatar());
		fields.add(entity.getStatus());
		fields.add(entity.getIdRole().toString());
		return (String[]) fields.toArray();
	}

	@Override
	protected User createInstance(String[] args) {
		return new User(Integer.parseInt(args[0] == null ? "0" : args[0]), 
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2], 
				args[3] == null ? new String() : args[3],
				args[4] == null ? new String() : args[4], 
				args[5] == null ? new String() : args[5],
				Double.parseDouble(args[6] == null ? "0" : args[6]), 
				Integer.parseInt(args[7] == null ? "0" : args[7]),
				Integer.parseInt(args[8] == null ? "0" : args[8]),
				args[9] == null ? new String() : args[9]);
	}

	public User getUserByLogin(String login) throws JDBCDriverException, DataBaseReadingException, QueryNotFoundException, EmptyResultSetException, CloseStatementException {
		return getByField(login, login).get(0);
	}
	
	public User getUserById(Integer id) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
		return getById(id);
	}
	
	public boolean createUser(User user) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.INSERT).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, DaoQueries.INSERT.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			int i = 1;
			pst.setString(i++, user.getLogin());
			pst.setString(i++, user.getPasswd());
			pst.setString(i++, user.getFirsName());
			pst.setString(i++, user.getLastName());
			pst.setString(i++, user.getGender());
			pst.setDouble(i++, user.getWeight());
			pst.setInt(i++, user.getAge());
			pst.setString(i++, user.getGoogleApi());
			pst.setString(i++, user.getHealth());
			pst.setString(i++, user.getAvatar());
			pst.setString(i++, user.getStatus());
			pst.setInt(i++, user.getIdRole());
			pst.setString(i++, user.getMail());
		
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
	public boolean updateUser(User user) throws DataBaseReadingException, JDBCDriverException, QueryNotFoundException {
		boolean result = false;
		String query = sqlQueries.get(DaoQueries.UPDATE).toString();
		if (query == null) {
			throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, DaoQueries.UPDATE.name()));
		}
		try (PreparedStatement pst = ConnectionManager.getInstance().getConnection().prepareStatement(query)) {
			int i = 1;
			pst.setString(i++, user.getPasswd());
			pst.setString(i++, user.getFirsName());
			pst.setString(i++, user.getLastName());
			pst.setString(i++, user.getGender());
			pst.setDouble(i++, user.getWeight());
			pst.setInt(i++, user.getAge());
			pst.setString(i++, user.getLogin());
			result = pst.execute();
		} catch (SQLException e) {
			throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
		}
		return result;
	}
	
}
