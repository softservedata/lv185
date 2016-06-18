package edu.softserveinc.healthbody.dto;

import java.util.List;

public class UserDTO {

	private String firstname;
	private String lastname;
	private String login;
	private String password;
	private String email;
	private String age;
	private String weight;
	private String gender;
	private String photoURL;
	private String roleName;
	private String status;
	private String score;
	private List<GroupDTO> groups;

	public UserDTO(String firstname, String lastname, String login, String password, String email, String age,
			String weight, String gender, String photoURL, String roleName, String status, String score,
			List<GroupDTO> groups) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.age = age;
		this.weight = weight;
		this.gender = gender;
		this.photoURL = photoURL;
		this.roleName = roleName;
		this.status = status;
		this.score = score;
		this.groups = groups;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getAge() {
		return age;
	}

	public String getWeight() {
		return weight;
	}

	public String getGender() {
		return gender;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getStatus() {
		return status;
	}

	public String getScore() {
		return score;
	}

	public List<GroupDTO> getGroups() {
		return groups;
	}

}