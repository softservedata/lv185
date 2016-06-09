package edu.softserveinc.healthbody.dao;

import java.util.List;

public interface BasicReadDao<TEntity> {
	
	TEntity getById(Integer id);

	List<TEntity> getByField(String fieldname, String text);

	List<TEntity> getAll();
}
