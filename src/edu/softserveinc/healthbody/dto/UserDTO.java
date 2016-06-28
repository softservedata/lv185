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
	private String health;
	private String googleApi;
	private String status;
	private String score;
	private List<GroupDTO> groups;

	public UserDTO(String login, String password, String firstname, String lastname, String email, String age,
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
	
	//getters
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

	public String getHealth() {
		return health;
	}

	
	//setters
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setGroups(List<GroupDTO> groups) {
		this.groups = groups;
	}
	
	public void setHealth(String health) {
		this.health = health;
	}

	public String getGoogleApi() {
		return googleApi;
	}

	public void setGoogleApi(String googleApi) {
		this.googleApi = googleApi;
	}

}