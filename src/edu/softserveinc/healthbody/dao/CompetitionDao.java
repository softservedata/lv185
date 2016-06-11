package edu.softserveinc.healthbody.dao;

import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.entity.Competition.CompetitionDBQueries;

public final class CompetitionDao extends AbstractDao<Competition> {
	private static volatile CompetitionDao instance = null;

	private CompetitionDao() {
		super();
		init();
	}

	public static CompetitionDao get() {
		if (instance == null) {
			synchronized (CompetitionDao.class) {
				if (instance == null) {
					instance = new CompetitionDao();
				}
			}
		}
		return instance;
	}

	// TODO Create abstract method in ADao
	protected void init() {
		for (CompetitionDBQueries competitionDBQueries : CompetitionDBQueries.values()) {
			sqlQueries.put(competitionDBQueries.getDaoQuery(), competitionDBQueries);
		}
	}

	protected Competition createInstance(String[] args) {
		return new Competition(
			Integer.parseInt(args[0] == null ? "0" : args[0]),
			args[1] == null ? new String() : args[1],
			args[2] == null ? new String() : args[2],
			args[3] == null ? new String() : args[3],
			args[4] == null ? new String() : args[4],
			Integer.parseInt(args[5] == null ? "0" : args[5]));
	}

	protected String[] getFields(Competition entity) {
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

	//	public CompetitionDB getCompetitionsDBByLogin(String login) {
	//		return getByFieldName("Login", login).get(0);
	//	}

	// TODO DELETE Method
	// public boolean deleteById(Long id) {
	// return super.deleteById(id);
	// }

}
