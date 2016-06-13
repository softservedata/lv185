package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dao.DaoStatementsConstant.CompetitionDBQueries;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;;

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

	protected void init() {
		for (CompetitionDBQueries competitionDBQueries : CompetitionDBQueries.values()) {
			sqlQueries.put(competitionDBQueries.getDaoQuery(), competitionDBQueries);
		}
	}
	
	@Override
	protected Competition createInstance(String[] args) {
		return new Competition(
			Integer.parseInt(args[0] == null ? "0" : args[0]),
			args[1] == null ? new String() : args[1],
			args[2] == null ? new String() : args[2],
			args[3] == null ? new String() : args[3],
			args[4] == null ? new String() : args[4],
			Integer.parseInt(args[5] == null ? "0" : args[5]));
	}
	
	@Override
	protected String[] getFields(Competition entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getId_competition().toString());
		fields.add(entity.getName());
		fields.add(entity.getDescription());
		fields.add(entity.getStart().toString());
		fields.add(entity.getFinish().toString());
		fields.add(entity.getId_criteria().toString());
		return (String[]) fields.toArray();
	}

	public boolean createCompetition(Competition competition) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException{
		return insert(competition);
	}
	
	public boolean editGroup(Competition competition, String id_competition, String name, String description,
			String start, String finish, String id_criteria) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
		String[] fields = getFields(competition);	
		boolean result = false;
		updateByField(fields[0], id_competition, fields[1]	, name);
		updateByField(fields[0], id_competition, fields[2]	, description);
		updateByField(fields[0], id_competition, fields[3]	, start);
		updateByField(fields[0], id_competition, fields[4]	, finish);
		updateByField(fields[0], id_competition, fields[5]	, id_criteria);
		if (fields[3] != null) result = false;
		else if (fields[1] == name && fields[2] == description && fields[3] == start && fields[4] == finish && fields[5] == id_criteria){
			result = true;			
		}
		return result;
	}
	
	public List<Competition> view() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException{
		return getAll();
	}

}
