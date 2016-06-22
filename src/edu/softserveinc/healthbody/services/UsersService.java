package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;

public interface UsersService extends BaseFilterService<UserDTO> {

	//������ ������������ �� �����
	//������ ������������ ��� ��� ��������� � ��������
	//������ ������������ �� ���������� � �����
	//������ ������������ �� ��������
	
	List<UserDTO> getAllbyAdmin();

	List<UserDTO> getAlltoAddinCompetition();

	List<UserDTO> getAllbyGroup(int groupId);
	
	List<UserDTO> getAllbyCompetition(int competId);

	

}