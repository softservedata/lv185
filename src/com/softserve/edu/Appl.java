package com.softserve.edu;

import com.softserve.edu.hb.db.ConnectionUtils;
import com.softserve.edu.hb.db.DataSourceRepository;
import com.softserve.edu.hb.dto.UserDTO;
import com.softserve.edu.hb.services.UserUIService;

public class Appl {
	
	public static void main(String[] args) {
		ConnectionUtils.get(DataSourceRepository.get().getConnectorMySqlLocalHost());
		for (UserDTO userDTO : UserUIService.get().getAllUsers()) {
		//for (UserDTO userDTO : UserUIService.get().getUsersByRole("manager")) {
			System.out.println("Login = " + userDTO.getLoginUser()
				+ "\tRole = " + userDTO.getRoleName());	
		}
	}

}
