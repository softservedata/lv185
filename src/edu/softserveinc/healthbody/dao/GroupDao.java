package edu.softserveinc.healthbody.dao;

import java.util.ArrayList;
import java.util.List;

import edu.softserveinc.healthbody.dao.DaoStatementsConstant.GroupDBQueries;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;;

public final class GroupDao extends AbstractDao<Group> {
	
	private static volatile GroupDao instance = null;

		private GroupDao() {
			super();
			init();
		}

		public static GroupDao get() {
			if (instance == null) {
				synchronized (GroupDao.class) {
					if (instance == null) {
						instance = new GroupDao();
					}
				}
			}
			return instance;
		}

		protected void init() {
			for (GroupDBQueries groupDBQueries : GroupDBQueries.values()) {
				sqlQueries.put(groupDBQueries.getDaoQuery(), groupDBQueries);
			}
		}

		@Override
		public Group createInstance(String[] args) {
			return new Group(
					Integer.parseInt(args[0] == null ? "0" : args[0]),
					args[1] == null ? new String() : args[1],
					Integer.parseInt(args[2] == null ? "0" : args[2]),
					args[3] == null ? new String() : args[3],
					args[4] == null ? new String() : args[4],
					args[5] == null ? new String() : args[5]);
		}

		@Override
		protected String[] getFields(Group entity) {
			List<String> fields = new ArrayList<>();
			fields.add(entity.getIdGroup().toString());
			fields.add(entity.getName());
			fields.add(entity.getCount().toString());
			fields.add(entity.getDescription());
			fields.add(entity.getScoreGroup());
			fields.add(entity.getStatus());
			return (String[]) fields.toArray();
		}
		
		public boolean createGroup(Group group) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException{
			return insert(group);
		}
		
		public boolean editGroup(Group group, String count, String description, String scoreGroup) 
										throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
			String[] fields = getFields(group);	
			boolean result = false;
			updateByField(fields[1], group.getName(), fields[2]	, count);
			updateByField(fields[1], group.getName(), fields[3]	, description);
			updateByField(fields[1], group.getName(), fields[4]	, scoreGroup);
			if (fields[2] == count && fields[3] == description && fields[4] == scoreGroup){
				result = true;			
			}
			return result;
		}
		
		public boolean deleteGroup(Group group) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
			return delete(group);
		}
		
		public List<Group> view() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
			return getAll();
		}
		
		public Group getGroupByName(String name) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {
			return getByFieldName(name);
		}

 }

