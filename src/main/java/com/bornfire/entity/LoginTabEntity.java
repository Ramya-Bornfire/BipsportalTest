package com.bornfire.entity;

public class LoginTabEntity {
	private String userid;
	private String password;
	private String ip_address;
	private String device_id;
	private String device_type;
	private String os_version;
	private String app_version;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}
	public LoginTabEntity(String userid, String password, String ip_address, String device_id, String device_type,
			String os_version, String app_version) {
		super();
		this.userid = userid;
		this.password = password;
		this.ip_address = ip_address;
		this.device_id = device_id;
		this.device_type = device_type;
		this.os_version = os_version;
		this.app_version = app_version;
	}
	public LoginTabEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoginTabEntity [userid=" + userid + ", password=" + password + ", ip_address=" + ip_address
				+ ", device_id=" + device_id + ", device_type=" + device_type + ", os_version=" + os_version
				+ ", app_version=" + app_version + "]";
	}
	
	
}
