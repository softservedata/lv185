package edu.softserveinc.healthbody.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dao.DaoStatementsConstant.CompetitionsViewQueries;
import edu.softserveinc.healthbody.db.CloseHelper;
import edu.softserveinc.healthbody.db.ConnectionManager;
import edu.softserveinc.healthbody.entity.CompetitionsView;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public class CompetitionsViewDao extends AbstractDaoRead<CompetitionsView> {

	private static volatile CompetitionsViewDao instance = null;

	public CompetitionsViewDao() {
		super();
		init();
	}

	public static CompetitionsViewDao get() {
		if (instance == null) {
			synchronized (CompetitionsViewDao.class) {
				if (instance == null) {
					instance = new CompetitionsViewDao();
				}
			}
		}
		return instance;
	}

	protected void init() {
		for (CompetitionsViewQueries competitionsViewQueries : CompetitionsViewQueries.values()) {
			sqlQueries.put(competitionsViewQueries.getDaoQuery(), competitionsViewQueries);
		}
	}

	@Override
	public CompetitionsView createInstance(String[] args) {
		return new CompetitionsView(Integer.parseInt(args[0] == null ? "0" : args[0]),
						args[1] == null ? "0" : args[1],
						args[2] == null ? "0" : args[2],
						args[3] == null ? "0" : args[3],
						args[4] == null ? "0" : args[4],
						Integer.parseInt(args[5] == null ? "0" : args[5]));
	}

	@Override
	protected String[] getFields(CompetitionsView entity) {
		List<String> fields = new ArrayList<>();
		fields.add(entity.getIdCompetition().toString());
		fields.add(entity.getName());
		fields.add(entity.getDescription());
		fields.add(entity.getStart());
		fields.add(entity.getFinish());
		fields.add(entity.getUsersCount().toString());
		return (String[]) fields.toArray();
	}
	
	public List<CompetitionsView> getActiveCompetitionsView(int partNumber, int partSize) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
			List<CompetitionsView> result = new ArrayList<>();
			PreparedStatement pst = null;
			ResultSet resultSet = null;
			String query = sqlQueries.get(CompetitionsViewQueries.GET_ALL_ACTIVE).toString();
			if (query == null) {
				throw new QueryNotFoundException(String.format(QUERY_NOT_FOUND, CompetitionsViewQueries.GET_ALL_ACTIVE.name()));
			}
			if ((partNumber >= 0) && (partSize > 0)) {
				query = query.substring(0, query.lastIndexOf(";")) + SQL_LIMIT;
			}
			try {
				pst = ConnectionManager.getInstance().getConnection().prepareStatement(query);
				pst.setInt(1, (partNumber-1)*partSize);
				pst.setInt(2, partSize);
				resultSet = pst.executeQuery();
				String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];
				while (resultSet.next()) {
					result.add(createInstance(getQueryResultArr(queryResult)));
				}
			} catch (SQLException e) {
				throw new DataBaseReadingException(DATABASE_READING_ERROR, e);
			}
			finally {
				CloseHelper.close(resultSet);
				CloseHelper.close(pst);
			}
			if (result.isEmpty()) {
				throw new EmptyResultSetException(String.format(EMPTY_RESULTSET, query));
			}
			return result;
	}

}
