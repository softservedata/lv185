package edu.softserveinc.healthbody.services.oldInterfaces;

import java.util.List;

import edu.softserveinc.healthbody.dto.GroupDTO;

public interface IGroupFilter {
	
	List<GroupDTO> Group(int partSize, int partNumber, String name);

}
