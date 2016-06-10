package com.softserve.dao;

public interface BaseDAO<T> {
	
	void create(T object);
	
	T readById(Integer id);
	
	void update(T object);
	
	void delete(T object);

}
