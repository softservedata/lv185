package edu.softserveinc.healthbody.entity;

public class UserGroup implements IEntity{

	private Integer idUserGroup;
    private Integer idUser;
	private Integer idGroup;
	private Boolean memberGgoup;
	
	public UserGroup(Integer idUserGroup, Integer idUser, Integer idGroup, Boolean memberGgoup) {
		this.idUserGroup = idUserGroup;
		this.idUser = idUser;
		this.idGroup = idGroup;
		this.memberGgoup = memberGgoup;
	}

	// setters

	public void setIdUserGroup(Integer idUserGroup) {
		this.idUserGroup = idUserGroup;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	
	public void setMemberGgoup(Boolean memberGgoup) {
		this.memberGgoup = memberGgoup;
	}
	

	// getters

	public Integer getIdUserGroup() {
		return idUserGroup;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public Integer getIdGroup() {
		return idGroup;
	}
	public Boolean getMemberGgoup() {
		return memberGgoup;
	}
	
	
	@Override
	public Integer getId() {
		return getIdUser();
	}

}
