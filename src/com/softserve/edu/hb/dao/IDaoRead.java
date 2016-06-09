package com.softserve.edu.hb.dao;


import java.util.List;

public interface IDaoRead<TEntity> {

	// Read
    TEntity getById(Integer id);

    List<TEntity> getByFieldName(String fieldName, String text);

    // TEntity getByFieldName(String fieldName, Integer value);

    List<TEntity> getAll();

	}
	
