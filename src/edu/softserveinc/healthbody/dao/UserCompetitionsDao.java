package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dao.DaoStatementsConstant.UserCompetitionsDBQueries;
import edu.softserveinc.healthbody.entity.UserCompetitions;

public final class UserCompetitionsDao extends AbstractDao<UserCompetitions> {
	
	private static volatile UserCompetitionsDao instance = null;
	
	private UserCompetitionsDao() {
		super();
		init();
	}
	
	public static UserCompetitionsDao get() {
		if (instance == null) {
			synchronized (UserCompetitionsDao.class) {
				if (instance == null) {
					instance = new UserCompetitionsDao();
				}
			}
		}
		return instance;
	}
	
	@Override
	protected void init() {
		for (UserCompetitionsDBQueries userCompetitionsDBQueries : UserCompetitionsDBQueries.values()) {
			sqlQueries.put(userCompetitionsDBQueries.getDaoQuery(), userCompetitionsDBQueries);
		}
	}

	@Override
	protected UserCompetitions createInstance(String[] args) {
		return new UserCompetitions(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				Integer.parseInt(args[1] == null ? "0" : args[1]),
				Integer.parseInt(args[2] == null ? "0" : args[2]),
				Integer.parseInt(args[3] == null ? "0" : args[3]),
				Integer.parseInt(args[4] == null ? "0" : args[4]),
				args[5] == null ? new String() : args[5]);
	}
	@Override
	protected String[] getFields(UserCompetitions entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdUserCompetition().toString());
		fields.add(entity.getIdUser().toString());
		fields.add(entity.getIdGroup().toString());
		fields.add(entity.getUserScore().toString());
		fields.add(entity.getIdAwards().toString());
		fields.add(entity.getTimeReceived());
		
		return (String[]) fields.toArray();
	}


}