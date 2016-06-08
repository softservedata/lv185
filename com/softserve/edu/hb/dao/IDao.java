package com.softserve.edu.hb.dao;

import java.util.List;

public interface IDao<TEntity> {

	public static enum DaoQueries {
		INSERT,
		GET_BY_ID,
		GET_BY_FIELD,
		GET_ALL,
		UPDATE_BY_FIELD,
		DELETE_BY_ID,
		DELETE_BY_FIELD;
	}
	
    // Create
    boolean insert(TEntity entity);

    // Read
    TEntity getById(Integer id);

    List<TEntity> getByFieldName(String fieldName, String text);

    // TEntity getByFieldName(String fieldName, Integer value);

    List<TEntity> getAll();

    // Update
    boolean updateByFieldName(String fieldName, String text, String fieldCondition, String textCondition);

    // Delete
    boolean deleteById(Integer id);

    boolean deleteByFieldName(String fieldCondition, String textCondition);

    boolean delete(TEntity entity);

}
