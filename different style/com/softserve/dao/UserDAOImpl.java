package com.softserve.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.logging.Logger;
import java.sql.SQLException;

import com.softserve.entity.User;

public class UserDAOImpl implements BaseDAO<User>{
	
	private PreparedStatement ps = null;
	private ResultSet rs;
	//private static Logger log = Log.init("UserDAOImpl");
	private static ConnectionManager connection = null;
	private static UserDAOImpl userDAO = null;
	
	private UserDAOImpl() {
		
	}
	
	public static UserDAOImpl getInstance(ConnectionManager connect) {
		if (userDAO == null) {
			userDAO = new UserDAOImpl();
			connection = connect;
		}
		return userDAO;
	}
	
	@Override
	public void create(User object) {
		
		boolean result = false;
		try (Connection conn = connection.getConnection()) {
            try {
                conn.setAutoCommit(false);
                if ((object.getId_role() == null) || (object.getEmail() == null)
                        || (object.getLogin() == null)) {
                    logger.error("You not enter login, e-mail or role");
                    throw new IllegalArgumentException();
                } else {
                    logger.debug("Creating user");
                    ps = conn.prepareStatement(
                            "insert into users(id_Role, Login, Email, Password, FirstName, LastName," +
                    " Gender, Age, Avatar, Status) values(?,?,?,?,?,?,?,?,?,?);");
                    ps.setString(1, object.getId_role().toString());
                    ps.setString(2, object.getLogin());
                    ps.setString(3, object.getEmail());
                    try {
                        ps.setString(4,
                                PasswordHelper.encrypt(object.getPassword()));
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        logger.error("No such algorithm exception!", e);
                    }
                    ps.setString(5, object.getFirstName());
                    ps.setString(6, object.getLastName());
                    ps.setString(7, object.getGender());
                    ps.setString(8, object.getAge().toString());
                    ps.setString(9, object.getAvatar());
                    ps.setString(10, object.getStatus());
                    ps.execute();
                    ps.close();
                    conn.commit();
                }
            } catch (SQLException e) {
                logger.error("Error. Rollback changes", e);
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.error("create user failed", e);
        }
      
	}
	@Override
	public User readById(Integer id) {
		String query =
                "select * from users where id_User = ?;";
        User user = null;
        try (Connection conn = connection.getConnection()) {
            try {
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                user = resultSet(rs);
                conn.commit();
            } catch (SQLException e) {
                logger.error("Error. Rollback changes", e);
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.error("read by key failed", e);
        }
        return user;
		
	}
	@Override
	public void update(User object) {
		try (Connection conn = connection.getConnection()) {
            try {
                conn.setAutoCommit(false);
                ps = conn.prepareStatement("update users set FirstName=?,"
                        + " LastName=?, Age=?, Email=?, Password=?, Gender=?,"
                        + " Avatar=?, id_Role=?, Status=? where Login=?;");
                ps.setString(1, object.getFirstName());
                ps.setString(2, object.getLastName());
                ps.setString(3, object.getAge().toString());
                ps.setString(4, object.getEmail());
                ps.setString(5, (object.getPassword()));
                ps.setString(6, object.getGender());
                ps.setString(7, object.getAvatar());
                ps.setString(8, object.getId_role().toString());
                ps.setString(9, object.getStatus());
                ps.setString(10, object.getLogin());
                
                ps.execute();
                
                conn.commit();
                logger.debug("User updated");
            } catch (SQLException e) {
                logger.error("Error. Rollback changes", e);
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.error("Update user failed", e);
        }
		
	}
	@Override
	public void delete(User object) {
		try (Connection conn = connection.getConnection()) {
            try {
                conn.setAutoCommit(false);
                logger.debug("Deleting user");
                ps = conn.prepareStatement(
                        "delete from users where login=?");
                ps.setString(1, object.getLogin());
                ps.execute();
                conn.commit();
            } catch (SQLException e) {
                logger.error("Error. Rollback changes", e);
                conn.rollback();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.error("delete user failed", e);
        }
		
	}
	
	private User resultSet(ResultSet rs) {
        User user = null;
        try {
            while (rs.next()) {
                user = new User();
                user.setId_user(rs.getInt("id_User"));
                user.setLogin(rs.getString("Login"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setStatus(rs.getString("Status"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setGender(rs.getString("Gender"));
                user.setAge(rs.getInt("Age"));
                user.setAvatar(rs.getString("Avatar"));
                user.setId_role(rs.getInt("id_Role"));
            }
        } catch (SQLException e) {
            logger.error("ResultSet failed", e);
        }
        return user;
	}

}
