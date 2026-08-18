package com.bornfire.entity;

public class LogoutEntity {

	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public LogoutEntity(String user_id) {
		super();
		this.user_id = user_id;
	}

	public LogoutEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
