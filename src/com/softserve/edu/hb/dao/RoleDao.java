package com.softserve.edu.hb.dao;

import com.softserve.edu.hb.entity.RoleDB;
import com.softserve.edu.hb.entity.RoleDB.RoleDBQueries;

public final class RoleDao extends ADao<RoleDB> {
	private static volatile RoleDao instance = null;

	private RoleDao() {
		super();
		init();
	}

	public static RoleDao get() {
		if (instance == null) {
			synchronized (RoleDao.class) {
				if (instance == null) {
					instance = new RoleDao();
				}
			}
		}
		return instance;
	}

	// TODO Create abstract method in ADao
	protected void init() {
		for (RoleDBQueries roleDBQueries : RoleDBQueries.values()) {
			sqlQueries.put(roleDBQueries.getDaoQuery(), roleDBQueries);
		}
	}

	protected RoleDB createInstance(String[] args) {
		return new RoleDB(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1]);
	}

	protected String[] getFields(RoleDB entity) {
		String[] fields = new String[2];
		fields[0] = entity.getIdRole().toString();
		fields[1] = entity.getName();
		return fields;
	}

}
