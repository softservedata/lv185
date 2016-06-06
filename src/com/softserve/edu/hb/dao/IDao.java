package com.softserve.edu.hb.dao;

import java.util.List;

public interface IDao<TEntity> {

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
