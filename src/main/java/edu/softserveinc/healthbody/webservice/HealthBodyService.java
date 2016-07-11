package edu.softserveinc.healthbody.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserDTO;

@WebService
public interface HealthBodyService {
	
	@WebMethod
	void createUser(UserDTO userDTO);
	
	@WebMethod
	UserDTO getUserByLogin(String login);
	
	@WebMethod
	void updateUser(UserDTO userDTO);
	
	@WebMethod
	void lockUser(UserDTO userDTO, boolean isDisabled);
	
	@WebMethod
	List<UserDTO> getAllUsers(int partNumber, int partSize);
	
	@WebMethod
	List<UserDTO> getAllUserstoAddInCompetition(int partNumber, int partSize);
	
	@WebMethod
	List<UserDTO> getAllUsersinCompetition(int partNumber, int partSize);
	
	@WebMethod
	List<GroupDTO> getAllGroups(int partNumber, int partSize);
	
	@WebMethod
	GroupDTO getGroupByName(String name);
	
	@WebMethod
	String getDescriptionOfGroup(GroupDTO groupDTO);
	
	@WebMethod
	void updateGroup(GroupDTO groupDTO);
	
	@WebMethod
	List<CompetitionDTO> getAllCompetitions(int partNumber, int partSize);
	
	@WebMethod
	List<CompetitionDTO> getAllActiveCompetitions(int partNumber, int partSize);
	
	@WebMethod
	List<CompetitionDTO> getAllCompetitionsByUser(int partNumber, int partSize, String login);
	
	@WebMethod
	List<CompetitionDTO> getAllActiveCompetitionsByUser(int partNumber, int partSize, String login);
	
	@WebMethod
	void createCompetition(CompetitionDTO competitionDTO);

}
