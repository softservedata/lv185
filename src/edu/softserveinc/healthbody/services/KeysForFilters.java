package edu.softserveinc.healthbody.services;

public class KeysForFilters {

	public enum CompetitionsServiceKeys {

		NAME("name"),
		COUNT("count"),
		START_DATE("startDate"),
		FINISH_DATE("finishDate"),
		GROUPS("groups"),
		LOGINS("logins");

		private String keys;

		private CompetitionsServiceKeys(String keys) {
			this.keys = keys;
		}

		@Override
		public String toString() {
			return keys;
		}
		
	}
	}

	
	
	