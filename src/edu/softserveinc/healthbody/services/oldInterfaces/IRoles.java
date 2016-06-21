package edu.softserveinc.healthbody.services.oldInterfaces;

import java.util.List;

import edu.softserveinc.healthbody.dto.RoleDTO;

public interface IRoles {

	void insertRole(RoleDTO roleDTO);

	List<RoleDTO> getAllRoles(String name);

	void updateRole(RoleDTO roleDTO);
}
