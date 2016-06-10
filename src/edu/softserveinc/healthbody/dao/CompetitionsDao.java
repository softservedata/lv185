package com.softserve.edu.hb.dao;

import com.softserve.edu.hb.entity.CompetitionsDB;
import com.softserve.edu.hb.entity.CompetitionsDB.CompetitionsDBQueries;

public final class CompetitionsDao extends ADaoCRUD<CompetitionsDB> {
	private static volatile CompetitionsDao instance = null;

	private CompetitionsDao() {
		super();
		init();
	}

	public static CompetitionsDao get() {
		if (instance == null) {
			synchronized (CompetitionsDao.class) {
				if (instance == null) {
					instance = new CompetitionsDao();
				}
			}
		}
		return instance;
	}

	// TODO Create abstract method in ADao
	protected void init() {
		for (CompetitionsDBQueries competitionsDBQueries : CompetitionsDBQueries.values()) {
			sqlQueries.put(competitionsDBQueries.getDaoQuery(), competitionsDBQueries);
		}
	}

	protected CompetitionsDB createInstance(String[] args) {
		return new CompetitionsDB(
			Integer.parseInt(args[0] == null ? "0" : args[0]),
			args[1] == null ? new String() : args[1],
			args[2] == null ? new String() : args[2],
			args[3] == null ? new String() : args[3],
			args[4] == null ? new String() : args[4],
			Integer.parseInt(args[5] == null ? "0" : args[5]));
	}

	protected String[] getFields(CompetitionsDB entity) {
		//String[] fields = new String[UserDB.class.getDeclaredFields().length];
		String[] fields = new String[6];
		fields[0] = entity.getId_competitions().toString();
		fields[1] = entity.getName();
		fields[2] = entity.getDescription();
		fields[3] = entity.getStart().toString();
		fields[4] = entity.getEnd().toString();
		fields[5] = entity.getId_criterias().toString();
		return fields;
	}

	//	public CompetitionsDB getCompetitionsDBByLogin(String login) {
	//		return getByFieldName("Login", login).get(0);
	//	}

	// TODO DELETE Method
	// public boolean deleteById(Long id) {
	// return super.deleteById(id);
	// }

}
