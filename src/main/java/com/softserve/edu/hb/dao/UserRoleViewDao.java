package com.softserve.edu.hb.dao;

import com.softserve.edu.hb.entity.UserRoleView;
import com.softserve.edu.hb.entity.UserRoleView.UserRoleViewQueries;

public final class UserRoleViewDao extends ADaoRead<UserRoleView> {
	private static volatile UserRoleViewDao instance = null;

	private UserRoleViewDao() {
		super();
		init();
	}

	public static UserRoleViewDao get() {
		if (instance == null) {
			synchronized (UserRoleViewDao.class) {
				if (instance == null) {
					instance = new UserRoleViewDao();
				}
			}
		}
		return instance;
	}

	// TODO Create abstract method in ADao
	protected void init() {
		for (UserRoleViewQueries userRoleViewQueries : UserRoleViewQueries.values()) {
			sqlQueries.put(userRoleViewQueries.getDaoQuery(), userRoleViewQueries);
		}
	}

	protected UserRoleView createInstance(String[] args) {
		return new UserRoleView(
			Integer.parseInt(args[0] == null ? "0" : args[0]),
			args[1] == null ? new String() : args[1],
			args[2] == null ? new String() : args[2]);
	}

}
