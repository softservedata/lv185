package com.softserve.edu.hb.services;

import java.util.List;

import com.softserve.edu.hb.dto.GroupDTO;

@Deprecated
public interface IGroupFilter {
	
	List<GroupDTO> Group(int partSize, int partNumber, String name);

}
