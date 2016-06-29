package edu.softserveinc.healthbody;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

public class GroupTest {
	
	@Test
	public void testGetAll() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
		List<String> filters = new ArrayList<>();
		filters.add("Name group number");
		GroupServiceImpl groupService = GroupServiceImpl.getInstance();
		System.out.println("All Group with name like \"Name group number\": " + Arrays.toString(groupService.getAll(0, 3, filters).toArray()));
	}

	@Test
	public void testGetDescriptionOfGroup() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {		
		GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 3");		
		String actual = groupDTO.getDescriptions();
		String expected = "Description of group 3";
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testUpdateGroupDTOStringStringString() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException {
		GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 3");	
		GroupServiceImpl.getInstance().update(groupDTO, "44", "New description", "50");
		GroupDTO expectedGroupDTO = new GroupDTO("Name group number 3", "44", "New description", "50");
		Assert.assertEquals(expectedGroupDTO,groupDTO);
	}

}
