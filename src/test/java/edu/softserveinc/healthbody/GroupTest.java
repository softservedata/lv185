package edu.softserveinc.healthbody;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.GroupServiceImpl;

public class GroupTest {
	private static Logger logger = LoggerFactory.getLogger(GroupTest.class.getName());

	@Test
	public void testGetAll() throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException,
			EmptyResultSetException, CloseStatementException {
		GroupServiceImpl groupService = GroupServiceImpl.getInstance();
		int partNumber = 1;
		int partSize = 2;
		List<GroupDTO> groupAll = groupService.getAll(partNumber, partSize);
		logger.info("Printing all range of GroupDTO from " + partNumber + " to " + partSize);
		logger.info("[");
		for (GroupDTO group:groupAll){
			logger.info("  "+group.getName()+"   "+group.getCount()+"   "+group.getDescriptions()+"   "
					+group.getScoreGroup()+",");
		}
		logger.info("]");
	}

	 @Test
	 public void testGetDescriptionOfGroup() throws QueryNotFoundException,
	 JDBCDriverException, DataBaseReadingException, CloseStatementException {
	 GroupDTO groupDTO = GroupServiceImpl.getInstance().getGroup("Name group number 3");
	 String actual = groupDTO.getDescriptions();
	 String expected = "New description";
	 assertEquals(expected, actual);
	 }

	@Test
	public void testUpdateGroupDTOStringStringString()
			throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, CloseStatementException,
			EmptyResultSetException, SQLException, TransactionException {
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
