package com.softserve.edu.hb.services;

import com.softserve.edu.hb.dao.UserDao;
import com.softserve.edu.hb.entity.UserDB;

public class UserService {
//    private static volatile UserService instance = null;
//    // Singleton testing is Very Difficult !!!
//
//    private UserService() {
//    }
//
//    public static UserService get() {
//        if (instance == null) {
//            synchronized (UserService.class) {
//                if (instance == null) {
//                    instance = new UserService();
//                }
//            }
//        }
//        return instance;
//    }
//
//	// Create
//	public boolean insertUser(IUser user) {
//        return UserDao.get().insert(ServiceUtils.get().user2UserDB(user));
//	}
//    
//    // Read
//    public IUser getUserByLogin(String login) {
//        //UserDB userDB = UserDao.get().getUserDBByLogin(login);
//        UserDB userDB = UserDao.get().getUserDBByLogin(login);
//        return ServiceUtils.get().userDB2IUser(userDB);
////        return ServiceUtils.get().userDB2IUser(userDB,
////        		RoleService.get().getRoleNameById(userDB.getRoleRef()));
//    }
//
//    public String getUserFirstnameByLogin(String login) {
//        //return UserDao.get().getUserDBByLogin(login).getFirstname();
//        return UserDao.get().getUserDBByLogin(login).getFirstname();
//    }
//
//    public Long getUserIdByLogin(String login) {
//        return UserDao.get().getUserDBByLogin(login).getUserId();
//    }
//
//    // Delete
//    public boolean deleteUsersByLogin(String login) {
//        //return UserDao.get().delete(UserDao.get().getUserDBByLogin(login));  
//        return UserDao.get().delete(UserDao.get().getUserDBByLogin(login));  
//    }
//
//    public boolean deleteUsersById(Long id) {
//        //return UserDao.get().deleteById(id);  
//        return UserDao.get().deleteById(id);  
//    }
//
}
