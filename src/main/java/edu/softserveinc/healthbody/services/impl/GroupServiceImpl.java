package edu.softserveinc.healthbody.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.dao.GroupDao;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.entity.Group;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.IGroupService;

public class GroupServiceImpl implements IGroupService{
	
	private enum GroupServiceKeys {
		NAME("name"),
		COUNT("count"),
		DESCRIPTION("description"),
		SCORE_GROUP("scoreGroup");

		private String keys;

		private GroupServiceKeys(String keys) {
			this.keys = keys;
		}

		@Override
		public String toString() {
			return keys;
		}

	}
	
	private static final String GROUP_NAME = "name";
	
	private static volatile GroupServiceImpl instance = null;
	
	private GroupServiceImpl() {
	}
	
	public static GroupServiceImpl getInstance() {
		if(instance == null) {
			synchronized (GroupServiceImpl.class) {
				if (instance == null) {
					instance = new GroupServiceImpl();
				}
			}
		}
		return instance;
	}	

	@Override
	public List<GroupDTO> getAll(int partNumber, int partSize, List<String> filters) throws QueryNotFoundException, 
					JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		Map<String, String> mapFilters = fillFilters(filters);
		List<GroupDTO> resultGroup = new ArrayList<GroupDTO>();
		for (Group group : GroupDao.get().getFilterRange((partNumber - 1) * partSize, partSize, mapFilters)) {
			resultGroup.add(new GroupDTO(group.getName(), String.valueOf(group.getCount()), group.getDescription(), group.getScoreGroup()));
		}		
		return resultGroup;	
	}
	
	@Override
	public GroupDTO getGroup(String name) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException{
		 Group group = GroupDao.get().getGroupByName(name);
		 return new GroupDTO(group.getName(), String.valueOf(group.getCount()), group.getDescription(), group.getScoreGroup());
	}	
	
	@Override
	public String getDescriptionOfGroup(GroupDTO groupDTO) {
		return groupDTO.getDescriptions();
	}
	
	@Override
	public void update(GroupDTO groupDTO, String count, String description, String scoreGroup) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		GroupDao.get().editGroup(GroupDao.get().getByField(GROUP_NAME, groupDTO.getName()).get(0), count, description, scoreGroup);
	}
	

	private Map<String, String> fillFilters(List<String> filters) {
		Map<String, String> map = new HashMap<>();
		map.put(GroupServiceKeys.NAME.toString(), filters.get(0));
		map.put(GroupServiceKeys.COUNT.toString(), filters.get(1));
		map.put(GroupServiceKeys.DESCRIPTION.toString(), filters.get(2));
		map.put(GroupServiceKeys.SCORE_GROUP.toString(), filters.get(3));
		return map;
	}

	@Override
	public List<GroupDTO> getAll(int partNumber, int partSize, Map<String, String> filters)
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException,
			CloseStatementException, SQLException, TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

}
