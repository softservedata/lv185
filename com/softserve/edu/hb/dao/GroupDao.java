package com.softserve.edu.hb.dao;

import java.util.List;

import com.softserve.edu.hb.entity.GroupDB;
import com.softserve.edu.hb.entity.GroupDB.GroupDBQueries;

public class GroupDao extends ADao<GroupDB>{
	
	private static volatile GroupDao instance = null;

	public GroupDao() {
		super();
		init();
	}
	

	public static GroupDao get(){
		if (instance == null){
			synchronized (GroupDao.class) {
				if (instance == null){
					instance = new GroupDao();
				}
			}
		}
		return instance;
	}
	
	
	@Override
	protected void init() {
		for (GroupDBQueries groupDBQueries : GroupDBQueries.values()) {
			sqlQueries.put(groupDBQueries.getDaoQuery(), groupDBQueries);
		}		
	}
	
	
	
	@Override
	protected GroupDB createInstance(String[] args) {
		return new GroupDB(
				Integer.parseInt(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2],
				args[3] == null ? new String() : args[3]);
	}

	@Override
	protected String[] getFields(GroupDB entity) {
		String[] fields = new String[4];
		fields[0] = entity.getIdGroup().toString();
		fields[1] = entity.getName();
		fields[2] = entity.getDescription();
		fields[3] = entity.getStatus();
		return fields;
	}
	
	public boolean createGroup(GroupDB group){
		return insert(group);
	}
	
	public boolean editGroup(GroupDB group, String id, String name, String description, String status){
		String[] fields = getFields(group);		
		updateByFieldName(fields[0], id, fields[1]	, name);
		updateByFieldName(fields[0], id, fields[2]	, description);
		updateByFieldName(fields[0], id, fields[3]	, status);
		if (fields[1] == name && fields[2] == description && fields[3] == status){
			return true;			
		} else {
			return false;
		}
	}
	
	public boolean deleteGroup(GroupDB group){
		return delete(group);
	}
	
	public List<GroupDB> view(){
		return getAll();
	}


}
