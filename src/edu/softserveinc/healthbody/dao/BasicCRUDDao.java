package edu.softserveinc.healthbody.dao;

public interface BasicCRUDDao<TEntity> extends BasicReadDao<TEntity> {
	
	public static enum DaoQueries {
		INSERT,
		GET_BY_ID,
		GET_BY_FIELD,
		GET_ALL,
		UPDATE_BY_FIELD,
		DELETE_BY_ID,
		DELETE_BY_FIELD;
	}
	
	boolean insert(TEntity entity);
	
	boolean updateByField(String fieldName, String text, String fieldCondition, String textCondition);
	
	boolean deleteById(Integer id);
	
	boolean deleteByField(String fieldCondition, String textCondition);
}
