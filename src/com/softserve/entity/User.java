package com.softserve.entity;

public class User {
	
	private Integer id_user;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private Integer age;
	private String avatar;
	private String status;
	private Integer id_role;
	
	public User(){
		
	}
	
	public User(Integer id_user, String login, String password, String firstName, String lastName,
			String email, String gender, Integer age, String avatar, String status, Integer id_role) {
		
		this.id_user = id_user;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.avatar = avatar;
		this.status = status;
		this.id_role = id_role;
		
	}
	// setters
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId_role(Integer id_role) {
		this.id_role = id_role;
	}
	
	// getters
	public Integer getId_user() {
		return id_user;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getStatus() {
		return status;
	}

	public Integer getId_role() {
		return id_role;
	}

}
