package com.softserve.edu.hb.services;

import java.util.List;

import com.softserve.edu.hb.dto.UserDTO;

public interface IUserFilters {

	List<UserDTO> getAllUsers(int partSize, int partNumber,
			String firstname, String lastname, String age, String weight,
			String group, String competition);

	void updateUsers(List<UserDTO> usersDTO);
	
}