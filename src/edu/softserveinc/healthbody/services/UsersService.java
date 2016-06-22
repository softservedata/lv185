package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;

public interface UsersService extends BaseFilterService<UserDTO> {

	//Список користувачів від адміна
	//Список користувачів всіх для додавання у змагання
	//Список користувачів із запрошення у групу
	//Список користувачів із змагання
	
	List<UserDTO> getAll();

	List<UserDTO> getAllnotinCompetition(int competId);

	List<UserDTO> getAllnotinGroup(int groupId);
	
	List<UserDTO> getAllinCompetition(int competId);

	

}