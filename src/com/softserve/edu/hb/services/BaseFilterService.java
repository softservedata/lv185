package com.softserve.edu.hb.services;

import java.util.List;
import java.util.Map;

public interface BaseFilterService<TBaseDTO> {

	List<TBaseDTO> getAllUsers(int partNumber, int partSize, Map<String, String> filters);

	void updateUsers(List<TBaseDTO> baseDTO);

}