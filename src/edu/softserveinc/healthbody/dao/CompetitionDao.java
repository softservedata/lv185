package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.dao.DaoStatementsConstant.CompetitionDBQueries;;

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
		List<String> fields = new ArrayList<>();
		fields.add(entity.getId_competitions().toString());
		fields.add(entity.getName());
		fields.add(entity.getDescription());
		fields.add(entity.getStart().toString());
		fields.add(entity.getEnd().toString());
		fields.add(entity.getId_criterias().toString());
		return (String[]) fields.toArray();
	}

	//	public CompetitionDB getCompetitionsDBByLogin(String login) {
	//		return getByFieldName("Login", login).get(0);
	//	}

	// TODO DELETE Method
	// public boolean deleteById(Long id) {
	// return super.deleteById(id);
	// }

}
