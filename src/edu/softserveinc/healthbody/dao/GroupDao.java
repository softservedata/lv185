package edu.softserveinc.healthbody.dao;

import java.lang.reflect.Field;
import java.util.List;

import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.entity.Group.GroupDBQueries;

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
		protected Group createInstance(String[] args) {
			return new Group(
					Integer.parseInt(args[0] == null ? "0" : args[0]),
					args[1] == null ? new String() : args[1],
					args[2] == null ? new String() : args[2],
					args[3] == null ? new String() : args[3]);
		}

		@Override
		protected String[] getFields(Group entity) {
			String[] fields = new String[Group.class.getDeclaredFields().length];
			Field[] fieldsEE = Group.class.getDeclaredFields(); 
			for(int i =0;i<fieldsEE.length;i++){
				fieldsEE[i].setAccessible(true);
				try {
					fields[i] = fieldsEE[i].get(entity).toString();
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}					
			}
			return fields;
		}
		
		public boolean createGroup(Group group) throws JDBCDriverException, QueryNotFoundException, DataBaseReadingException{
			return insert(group);
		}
		
		public boolean editGroup(Group group, String id, String name, String description, String status) throws JDBCDriverException, DataBaseReadingException{
			String[] fields = getFields(group);	
			boolean result = false;
			updateByField(fields[0], id, fields[1]	, name);
			updateByField(fields[0], id, fields[2]	, description);
			updateByField(fields[0], id, fields[3]	, status);
			if (fields[1] == name && fields[2] == description && fields[3] == status){
				result = true;			
			}
			return result;
		}
		
		public boolean deleteGroup(Group group) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException{
			return delete(group);
		}
		
		public List<Group> view() throws JDBCDriverException, DataBaseReadingException, EmptyResultSetException{
			return getAll();
		}
		


	}
