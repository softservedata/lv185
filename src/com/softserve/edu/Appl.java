package com.softserve.edu;

import com.softserve.edu.hb.db.ConnectionManager;
import com.softserve.edu.hb.db.DataSourceRepository;
import com.softserve.edu.hb.dto.UserDTO;
import com.softserve.edu.hb.services.UserUIService;

public class Appl {
	
	public static void main(String[] args) {
		//ConnectionManager.getInstance(DataSourceRepository.getInstance().getMySqlLocalHost());
		//ConnectionManager.getInstance(DataSourceRepository.getInstance().getFirstConnectorByCVS());
		ConnectionManager.getInstance(DataSourceRepository.getInstance().getConnectorByProperties());
		for (UserDTO userDTO : UserUIService.get().getAllUsers()) {
		//for (UserDTO userDTO : UserUIService.get().getUsersByRole("manager")) {
			System.out.println("Login = " + userDTO.getLoginUser()
				+ "\tRole = " + userDTO.getRoleName());	
		}
		//new DataSourceUtils().getAllDataSources();
		UserUIService.get().insertUser(new UserDTO("Tester2", "manager"));
		ConnectionManager.closeAllConnections();
	}

}
