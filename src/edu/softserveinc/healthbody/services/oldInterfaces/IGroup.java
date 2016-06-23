package edu.softserveinc.healthbody.services.oldInterfaces;

import edu.softserveinc.healthbody.dto.GroupDTO;

public interface IGroup {

	void insertGroup(GroupDTO groupDTO);
	
	GroupDTO getGroup(String name);

	void updateGroup(GroupDTO groupDTO);
	
	void deleteGroup(GroupDTO groupDTO);

}