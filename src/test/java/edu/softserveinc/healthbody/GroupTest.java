package edu.softserveinc.healthbody;


import java.sql.SQLException;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

public class GroupTest {
	
//	@Test
//	public void testGetAll() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException, CloseStatementException{
//		List<String> filters = new ArrayList<>();
//		filters.add("Name group number");
//		filters.add("");
//		filters.add("");
//		filters.add("");
//		GroupServiceImpl groupService = GroupServiceImpl.getInstance();
//		System.out.println("All Group with name like \"Name group number\": " + Arrays.toString(groupService.getAll(0, 3, filters).toArray()));
//	}
//
//	@Test
//	public void testGetDescriptionOfGroup() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException {		
//		GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 3");		
//		String actual = groupDTO.getDescriptions();
//		String expected = "Description of group 3";
//		Assert.assertEquals(expected, actual);
//	}

	@Test
	public void testUpdateGroupDTOStringStringString() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException, EmptyResultSetException, SQLException, TransactionException {
		GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 3");	
		groupDTO.setCount("44");
		groupDTO.setDescriptions("New description");
		groupDTO.setScoreGroup("50");
		GroupServiceImpl.getInstance().update(groupDTO);
		assertNotNull(groupDTO);
		assertEquals("Name group number 3", groupDTO.getName());
		assertEquals("44", groupDTO.getCount());
		assertEquals("New description", groupDTO.getDescriptions());
		assertEquals("50", groupDTO.getScoreGroup());
	}

}
