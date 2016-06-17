package com.softserve.edu.hb.services;

import com.softserve.edu.hb.dto.GroupDTO;

public interface IGroup {

	void insertGroup(GroupDTO groupDTO);
	
	GroupDTO getGroup(String name);

	void updateGroup(GroupDTO groupDTO);
	
	void deleteGroup(GroupDTO groupDTO);

}