package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;

public interface UsersService extends BaseFilterService<UserDTO> {

	List<UserDTO> getAll();
	
	List<UserDTO> getAllbyAdmin();

	List<UserDTO> getAlltoAddInCompetition();

	List<UserDTO> getAllinGroup();
	
	List<UserDTO> getAllinCompetition();

	

}