package edu.softserveinc.healthbody.dao;

import edu.softserveinc.healthbody.dao.DaoStatementsConstant.MetaDataDBQueries;
import edu.softserveinc.healthbody.entity.MetaData;

public class MetaDataDao extends AbstractDao<MetaData> {
	
	private static volatile MetaDataDao instance = null;
	
	

	private MetaDataDao() {
		init();
	}
	
	public MetaDataDao get() {
		if(instance == null) {
			synchronized (MetaDataDao.class) {
				if(instance == null) {
					instance = new MetaDataDao();
				}
				
			}
		}
		return instance;
	}

	protected void init() {
		for (MetaDataDBQueries metaDataDBQueries : MetaDataDBQueries.values()) {
			sqlQueries.put(metaDataDBQueries.getDaoQuery(), metaDataDBQueries);
		}

	}
	@Override
	protected String[] getFields(MetaData entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MetaData createInstance(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
