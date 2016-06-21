package edu.softserveinc.healthbody.services;

import java.util.List;
import java.util.Map;

import edu.softserveinc.healthbody.exceptions.DataBaseReadingException;
import edu.softserveinc.healthbody.exceptions.EmptyResultSetException;
import edu.softserveinc.healthbody.exceptions.JDBCDriverException;
import edu.softserveinc.healthbody.exceptions.QueryNotFoundException;

public interface BaseFilterService<TBaseDTO> {
	
	List<TBaseDTO> getAll(int partNumber, int partSize, Map<String, String> filters) throws QueryNotFoundException, JDBCDriverException, DataBaseReadingException, EmptyResultSetException;
	
	void update(List<TBaseDTO> baseDTOs);

}
