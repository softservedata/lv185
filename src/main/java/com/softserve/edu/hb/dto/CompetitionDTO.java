package com.softserve.edu.hb.dto;

import java.util.List;

public class CompetitionDTO {
	
	private String name;
	private String count;
	private String startDate;
	private String finishDate;
	private List<String> groups;
	private List<String> logins;

	public CompetitionDTO(String name, String count, String startDate,
			String finishDate, List<String> groups, List<String> logins) {
		this.name = name;
		this.count = count;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.groups = groups;
		this.logins = logins;
	}

	public String getName() {
		return name;
	}

	public String getCount() {
		return count;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public List<String> getGroups() {
		return groups;
	}

	public List<String> getLogins() {
		return logins;
	}

}