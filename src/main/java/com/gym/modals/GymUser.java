package com.gym.modals;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class GymUser {

	@Id
	private String id;

	private String userName;
	private String password;
	private String role;

	public GymUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GymUser(String id, String userName, String password, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "GymUser [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}

	
}
