package com.softserve.edu.hb.dao;

import java.util.List;
import java.util.Map;

public interface IDaoRead<TEntity> {

	// Read
    TEntity getById(Integer id);

    List<TEntity> getByFieldName(String fieldName, String text);

    // TEntity getByFieldName(String fieldName, Integer value);

    List<TEntity> getAll();

    List<TEntity> getFilterRange(int partNumber, int partSize, Map<String, String> filters);

}
