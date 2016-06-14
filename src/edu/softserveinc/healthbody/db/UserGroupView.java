package edu.softserveinc.healthbody.entity;

public class UserGroupView implements IEntity{

		public static enum UserGroupViewFields {
			USERSGROUPS_ID("usersgroups.id_user_group"),
			USERS_ID("usersgroups.id_user"),
			GROUPS_ID("usersgroups.id_group");
	        private String field;

	        private UserGroupViewFields(String field) {
	            this.field = field;
	        }

	        @Override
	        public String toString() {
	            return field;
	        }
	    }


	    private Integer idUserGroup;
	    private Integer idUser;
		private Integer idGroup;
		private Boolean memberGgoup;

		public UserGroupView(Integer idUserGroup, Integer idUser, Integer idGroup, Boolean memberGgoup) {
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

		// getters

		public void setMemberGgoup(Boolean memberGgoup) {
			this.memberGgoup = memberGgoup;
		}

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
			return idUserGroup;
		}

	
}
