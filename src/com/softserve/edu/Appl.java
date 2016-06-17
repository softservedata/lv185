package com.softserve.edu;

import com.softserve.edu.hb.db.ConnectionManager;
import com.softserve.edu.hb.db.DataSourceRepository;
import com.softserve.edu.hb.dto.UserDTO;
import com.softserve.edu.hb.services.UserIUService;

public class Appl {
	
	public static void main(String[] args) {
		//ConnectionManager.getInstance(DataSourceRepository.getInstance().getMySqlLocalHost());
		//ConnectionManager.getInstance(DataSourceRepository.getInstance().getFirstConnectorByCVS());
		ConnectionManager.getInstance(DataSourceRepository.getInstance().getConnectorByProperties());
		for (UserDTO userDTO : UserIUService.get().getAllUsers()) {
		//for (UserDTO userDTO : UserUIService.get().getUsersByRole("manager")) {
			System.out.println("Login = " + userDTO.getLogin()
				+ "\tRole = " + userDTO.getRoleName());	
		}
		//new DataSourceUtils().getAllDataSources();
		UserIUService.get().insertUser(new UserDTO("", "", "Tester2",
				"", "", "", "", "", "",
				"manager", "", "", null));
		ConnectionManager.closeAllConnections();
	}

}
