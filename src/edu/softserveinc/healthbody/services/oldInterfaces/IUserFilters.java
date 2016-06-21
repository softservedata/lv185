package edu.softserveinc.healthbody.services.oldInterfaces;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;

public interface IUserFilters {

	List<UserDTO> getAllUsers(int partSize, int partNumber, String firstname, String lastname, String age,
			String weight, String group, String competition);

	void updateUsers(List<UserDTO> usersDTO);

}