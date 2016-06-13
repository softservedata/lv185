package edu.softserveinc.healthbody.entity;

public class MetaData implements IEntity {

	private Integer idMetaData;
	private String lastSynch;

	@Override
	public Integer getId() {
		return getIdMetaData();
	}

	// getters
	public Integer getIdMetaData() {
		return idMetaData;
	}

	public String getLastSynch() {
		return lastSynch;
	}

	// setters
	public void setIdMetaData(Integer idMetaData) {
		this.idMetaData = idMetaData;
	}

	public void setLastSynch(String lastSynch) {
		this.lastSynch = lastSynch;
	}

}
