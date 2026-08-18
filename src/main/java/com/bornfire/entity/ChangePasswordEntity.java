package com.bornfire.entity;

public class ChangePasswordEntity {

	private String user_id;
	private String oldpassword;
	private String newpassword;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public ChangePasswordEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePasswordEntity(String user_id, String oldpassword, String newpassword) {
		super();
		this.user_id = user_id;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
	}
	
	
	
}
