package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;

public interface UsersService extends BaseFilterService<UserDTO> {

	//Список користувачів від адміна
	//Список користувачів всіх для додавання у змагання
	//Список користувачів із запрошення у групу
	//Список користувачів із змагання
	
	List<UserDTO> getAllbyAdmin();

	List<UserDTO> getAlltoAddinCompetition();

	List<UserDTO> getAllbyGroup(int groupId);
	
	List<UserDTO> getAllbyCompetition(int competId);

	

}