package edu.softserveinc.healthbody;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.exceptions.IllegalAgrumentCheckedException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.ICompetitionsViewService;
import edu.softserveinc.healthbody.services.impl.CompetitionsViewServiceImpl;

public class CompetitionsViewServiceImplTest {
	@Test
	public void testGetAll() throws JDBCDriverException, SQLException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result = cv.getAll(1, 2);
		System.out.println("testGetAll");
		System.out.println(result);
		assertNotNull(result);
		assertEquals(result.size(), 2);
	}

	@Test
	public void testGetAllActive() throws JDBCDriverException, SQLException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result = cv.getAllActive(1, 10);
		System.out.println("testGetAllActive");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUser(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, userlogin);
		System.out.println("testGetAllByUser");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserNullLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, null);
		assertNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllByUserEmptyLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 10, "");
		assertNull(result);
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllByUserBadPaginationParams(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllByUser(1, 100, userlogin);
		assertNotNull(result);
		int getAllSize = result.size();
		result = cv.getAllByUser(-1, 1, userlogin);
		assertEquals(getAllSize, result.size());
		result = cv.getAllByUser(1, -1, userlogin);
		assertEquals(getAllSize, result.size());
		result = cv.getAllByUser(-1, -1, userlogin);
		assertEquals(getAllSize, result.size());
		result = cv.getAllByUser(0, 0, userlogin);
		assertEquals(getAllSize, result.size());
//		TODO Is it correct that empty result set causes exception - check and fix 		
//		result = cv.getAllByUser(2_000_000_000, 2_000_000_000, userlogin);
//		assertEquals(getAllSize, result.size());
	}

	@Test
	@Parameters("userlogin")
	public void testGetAllActiveByUser(@Optional("Login 7") String userlogin) throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException{
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result = cv.getAllActiveByUser(1, 10, userlogin);
		System.out.println("testGetAllActiveByUser");
		System.out.println(result);
		assertNotNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserNullLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllActiveByUser(1, 10, null);
		assertNull(result);
	}

	@Test(expectedExceptions = IllegalAgrumentCheckedException.class)
	public void testGetAllActiveByUserEmptyLogin() throws IllegalAgrumentCheckedException, SQLException, JDBCDriverException, TransactionException {
		ICompetitionsViewService cv = new CompetitionsViewServiceImpl();
		List<CompetitionDTO> result;
		result = cv.getAllActiveByUser(1, 10, "");
		assertNull(result);
	}
}

