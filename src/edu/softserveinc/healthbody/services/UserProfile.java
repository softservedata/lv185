package edu.softserveinc.healthbody.services;

import edu.softserveinc.healthbody.dto.UserDTO;

public interface UserProfile {

	void insertUser(UserDTO userDTO);

	UserDTO getUser(String login);

	void updateUser(UserDTO userDTO);

}