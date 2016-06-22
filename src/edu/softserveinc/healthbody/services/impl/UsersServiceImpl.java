package edu.softserveinc.healthbody.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.dao.CompetitionDao;
import edu.softserveinc.healthbody.dao.UserDao;
import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.entity.Competition;
import edu.softserveinc.healthbody.entity.User;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.services.KeysForFilters;
import edu.softserveinc.healthbody.services.UsersService;

public class UsersServiceImpl implements UsersService {

	@Override
	public List<UserDTO> getAll(int partNumber, int partSize, Map<String, String> filters) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException {
		fillFilters(filters);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : UserDao.get().getFilterRange((partNumber - 1) * partSize, partSize,
				filters)) {
			userDTOs.add(new UserDTO(null, null, user.getFirsName(), user.getLastName(), null, user.getAge().toString(), user.getWeight().toString(), null, 
					user.getAvatar(), null, null, null, null));
		}
		return userDTOs;
	}
	
	@Override
	public List<UserDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UserDTO> getAllnotinCompetition(int competId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UserDTO> getAllnotinGroup(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<UserDTO> getAllinCompetition(int competId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(List<UserDTO> baseDTOs) {
		// TODO Auto-generated method stub
		
	}
	
	private void fillFilters(Map<String, String> filters) {
		filters.put(KeysForFilters.UsersServiceKeys.FIRST_NAME.toString(), "firstname");
		filters.put(KeysForFilters.UsersServiceKeys.LAST_NAME.toString(), "lastname");
		filters.put(KeysForFilters.UsersServiceKeys.LOGIN.toString(), "login");
		filters.put(KeysForFilters.UsersServiceKeys.PASSWORD.toString(), "password");
		filters.put(KeysForFilters.UsersServiceKeys.EMAIL.toString(), "email");
		filters.put(KeysForFilters.UsersServiceKeys.AGE.toString(), "age");
		filters.put(KeysForFilters.UsersServiceKeys.WEIGHT.toString(), "weight");
		filters.put(KeysForFilters.UsersServiceKeys.GENDER.toString(), "gender");
		filters.put(KeysForFilters.UsersServiceKeys.PHOTO_URL.toString(), "photoURL");
		filters.put(KeysForFilters.UsersServiceKeys.ROLE_NAME.toString(), "roleName");
		filters.put(KeysForFilters.UsersServiceKeys.STATUS.toString(), "status");
		filters.put(KeysForFilters.UsersServiceKeys.SCORE.toString(), "score");
		filters.put(KeysForFilters.UsersServiceKeys.GROUPS.toString(), "groups");
	}

}