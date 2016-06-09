package com.softserve.edu.hb.dao;

	public interface IDaoCRUD<TEntity> extends IDaoRead<TEntity> {

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

	    // Update
	    boolean updateByFieldName(String fieldName, String text, String fieldCondition, String textCondition);

	    // Delete
	    boolean deleteById(Integer id);

	    boolean deleteByFieldName(String fieldCondition, String textCondition);

	    boolean delete(TEntity entity);

	}

