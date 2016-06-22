package edu.softserveinc.healthbody.services;

import java.util.List;

import edu.softserveinc.healthbody.dto.UserDTO;

public interface UsersService extends BaseFilterService<UserDTO> {

	//������ ������������ �� �����
	//������ ������������ ��� ��� ��������� � ��������
	//������ ������������ �� ���������� � �����
	//������ ������������ �� ��������
	
	List<UserDTO> getAll();

	List<UserDTO> getAllnotinCompetition(int competId);

	List<UserDTO> getAllnotinGroup(int groupId);
	
	List<UserDTO> getAllinCompetition(int competId);

	

}