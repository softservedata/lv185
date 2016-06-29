package edu.softserveinc.healthbody.services;

import java.util.List;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public interface GroupService extends BaseFilterService<GroupDTO>{
	
	String getDescriptionOfGroup(GroupDTO groupDTO);
	
	void update(GroupDTO groupDTO, String count, String description, String scoreGroup)throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException;

	GroupDTO getGroup(String name) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException;

	List<GroupDTO> getAll(int partNumber, int partSize, List<String> filters) throws QueryNotFoundException,
			JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException;
}
