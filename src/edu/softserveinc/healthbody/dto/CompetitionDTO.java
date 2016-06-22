package edu.softserveinc.healthbody.dto;

import java.util.List;

public class CompetitionDTO {

	private String name;
	private String count;
	private String startDate;
	private String finishDate;
	private List<String> groups;
	private List<String> logins;

	public CompetitionDTO(String name, String count, String startDate, String finishDate, List<String> groups,
			List<String> logins) {
		super();
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

	@Override
	public String toString() {
		return "CompetitionDTO [name=" + name + ", count=" + count + ", startDate=" + startDate + ", finishDate="
				+ finishDate + ", groups=" + groups + ", logins=" + logins + "]" + System.lineSeparator();
	}
	
	

}