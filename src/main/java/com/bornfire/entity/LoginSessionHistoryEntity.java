package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "login_session_history")
public class LoginSessionHistoryEntity {

    @Id
    private String session_id;
    private String merchant_id;
    private String user_id;
    private String unit_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date login_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date logout_date;
    private String session_status;
    private String ip_address;
    private String device_id;
    private String device_type;
    private String os_version;
    private String app_version;
    private String session_token;
    private BigDecimal failed_attempts;    
	private String failure_reason;
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public Date getLogin_date() {
		return login_date;
	}
	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}
	public Date getLogout_date() {
		return logout_date;
	}
	public void setLogout_date(Date logout_date) {
		this.logout_date = logout_date;
	}
	public String getSession_status() {
		return session_status;
	}
	public void setSession_status(String session_status) {
		this.session_status = session_status;
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
	public String getSession_token() {
		return session_token;
	}
	public void setSession_token(String session_token) {
		this.session_token = session_token;
	}
	public BigDecimal getFailed_attempts() {
		return failed_attempts;
	}
	public void setFailed_attempts(BigDecimal failed_attempts) {
		this.failed_attempts = failed_attempts;
	}
	public String getFailure_reason() {
		return failure_reason;
	}
	public void setFailure_reason(String failure_reason) {
		this.failure_reason = failure_reason;
	}
	public LoginSessionHistoryEntity(String session_id, String merchant_id, String user_id, String unit_id,
			Date login_date, Date logout_date, String session_status, String ip_address, String device_id,
			String device_type, String os_version, String app_version, String session_token, BigDecimal failed_attempts,
			String failure_reason) {
		super();
		this.session_id = session_id;
		this.merchant_id = merchant_id;
		this.user_id = user_id;
		this.unit_id = unit_id;
		this.login_date = login_date;
		this.logout_date = logout_date;
		this.session_status = session_status;
		this.ip_address = ip_address;
		this.device_id = device_id;
		this.device_type = device_type;
		this.os_version = os_version;
		this.app_version = app_version;
		this.session_token = session_token;
		this.failed_attempts = failed_attempts;
		this.failure_reason = failure_reason;
	}
	public LoginSessionHistoryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoginSessionHistoryEntity [session_id=" + session_id + ", merchant_id=" + merchant_id + ", user_id="
				+ user_id + ", unit_id=" + unit_id + ", login_date=" + login_date + ", logout_date=" + logout_date
				+ ", session_status=" + session_status + ", ip_address=" + ip_address + ", device_id=" + device_id
				+ ", device_type=" + device_type + ", os_version=" + os_version + ", app_version=" + app_version
				+ ", session_token=" + session_token + ", failed_attempts=" + failed_attempts + ", failure_reason="
				+ failure_reason + "]";
	}
	
	

}
