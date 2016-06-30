package edu.softserveinc.healthbody;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import edu.softserveinc.healthbody.dto.UserDTO;
import edu.softserveinc.healthbody.exceptions.CloseStatementException;
import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;
import edu.softserveinc.healthbody.exceptions.TransactionException;
import edu.softserveinc.healthbody.services.impl.UsersViewServiceImpl;

public class UserViewTest {
	@Test
	public void testUserView() {

		UsersViewServiceImpl uvs = new UsersViewServiceImpl();

		try {
			List<UserDTO> ud1 = uvs.getAll(1, 5);
			List<UserDTO> ud2 = uvs.getAllbyAdmin(1, 5);
			List<UserDTO> ud3 = uvs.getAllinCompetition(1, 5);
			List<UserDTO> ud4 = uvs.getAllinGroup(1, 5);
			List<UserDTO> ud5 = uvs.getAlltoAddInCompetition(1, 5);

			System.out.println("In getAll: " + Arrays.toString(ud1.toArray()));
			System.out.println("In getAllbyAdmin: " + Arrays.toString(ud2.toArray()));
			System.out.println("In getAllinCompetition: " + Arrays.toString(ud3.toArray()));
			System.out.println("In getAllinGroup: " + Arrays.toString(ud4.toArray()));
			System.out.println("In getAllinGroup: " + Arrays.toString(ud5.toArray()));
		} catch (QueryNotFoundException | JDBCDriverException | DataBaseReadingException | EmptyResultSetException
				| CloseStatementException | SQLException | TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
