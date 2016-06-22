package com.softserve.edu.hb.services;

public interface BaseService<TBaseDTO> {

	void insert(TBaseDTO baseDTO);
	
	TBaseDTO get(String name);

	void update(TBaseDTO baseDTO);
	
	void delete(TBaseDTO baseDTO);

}