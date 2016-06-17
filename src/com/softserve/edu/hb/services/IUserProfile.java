package com.softserve.edu.hb.services;

import com.softserve.edu.hb.dto.UserDTO;

public interface IUserProfile {

	void insertUser(UserDTO userDTO);
	
	UserDTO getUser(String login);

	void updateUser(UserDTO userDTO);

	//void disableUser(UserDTO userDTO);

}
