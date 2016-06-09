package edu.softserveinc.healthbody.dao;

import edu.softserveinc.healthbody.entity.GroupDB;
import edu.softserveinc.healthbody.entity.GroupDB.GroupDBQueries;

public final class GroupDao extends AbstractDaoCRUD<GroupDB> {
	
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

		protected GroupDB createInstance(String[] args) {
			return new GroupDB(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2],
				args[3] == null ? new String() : args[3]);
		}

		protected String[] getFields(GroupDB entity) {
			//String[] fields = new String[UserDB.class.getDeclaredFields().length];
			String[] fields = new String[4];
			fields[0] = entity.getIdGroup().toString();
			fields[1] = entity.getName();
			fields[2] = entity.getDescription();
			fields[3] = entity.getStatus();
			return fields;
		}

		public GroupDB getGroupDBByName(String name) {
			return getByField("Name", name).get(0);
		}
		
		public GroupDB getAllGroups () {
			return getAll().get(0);
		}

		// TODO DELETE Method
		// public boolean deleteById(Long id) {
		// return super.deleteById(id);
		// }

}


