package edu.softserveinc.healthbody.services;

import java.util.List;
import java.util.Map;

public interface BaseFilterService<TBaseDTO> {
	
	List<TBaseDTO> getAll(int partNumber, int partSize, Map<String, String> filters);
	
	void update(List<TBaseDTO> baseDTOs);

}
