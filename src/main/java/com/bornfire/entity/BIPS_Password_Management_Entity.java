package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BIPS_PASSWORD_MANAGEMENT")
public class BIPS_Password_Management_Entity {
	
	private String unit_id;
	private String unit_type;
	private String unit_name;
	private String	merchant_user_id;
	private String	merchant_name;
	private String	merchant_legal_user_id;
	private String	merchant_corporate_name;
	private String	password;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	password_expiry_date;
	private String	password_life;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	account_expiry_date;
	private String	user_disable_flag;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	user_disable_from_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	user_disable_to_date;
	private String	del_flag;
	private String	user_status;
	private String	login_status;
	private String	login_channel;
	private BigDecimal	mobile_no;
	private BigDecimal	alternate_mobile_no;
	private String	email_address;
	private String	alternate_email_id;
	@Id
	private String	merchant_rep_id;
	private String	pwlog_flg;
	private String	mer_representative_name;
	private BigDecimal	no_of_concurrent_users;
	private BigDecimal	no_of_active_devices;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date	modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date	verify_time;
	private String	maker_or_checker;
	private Integer	no_of_attmp;
	private String	user_locked_flg;
	private String	entry_flag;
	private String	modify_flag;
	
	
	
	

		

	public String getEntry_flag() {
		return entry_flag;
	}

	public void setEntry_flag(String entry_flag) {
		this.entry_flag = entry_flag;
	}

	public String getModify_flag() {
		return modify_flag;
	}

	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getUnit_type() {
		return unit_type;
	}

	public void setUnit_type(String unit_type) {
		this.unit_type = unit_type;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getMerchant_user_id() {
		return merchant_user_id;
	}

	public void setMerchant_user_id(String merchant_user_id) {
		this.merchant_user_id = merchant_user_id;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getMerchant_legal_user_id() {
		return merchant_legal_user_id;
	}

	public void setMerchant_legal_user_id(String merchant_legal_user_id) {
		this.merchant_legal_user_id = merchant_legal_user_id;
	}

	public String getMerchant_corporate_name() {
		return merchant_corporate_name;
	}

	public void setMerchant_corporate_name(String merchant_corporate_name) {
		this.merchant_corporate_name = merchant_corporate_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getPassword_expiry_date() {
		return password_expiry_date;
	}

	public void setPassword_expiry_date(Date password_expiry_date) {
		this.password_expiry_date = password_expiry_date;
	}

	public String getPassword_life() {
		return password_life;
	}

	public void setPassword_life(String password_life) {
		this.password_life = password_life;
	}

	public Date getAccount_expiry_date() {
		return account_expiry_date;
	}

	public void setAccount_expiry_date(Date account_expiry_date) {
		this.account_expiry_date = account_expiry_date;
	}

	public String getUser_disable_flag() {
		return user_disable_flag;
	}

	public void setUser_disable_flag(String user_disable_flag) {
		this.user_disable_flag = user_disable_flag;
	}

	public Date getUser_disable_from_date() {
		return user_disable_from_date;
	}

	public void setUser_disable_from_date(Date user_disable_from_date) {
		this.user_disable_from_date = user_disable_from_date;
	}

	public Date getUser_disable_to_date() {
		return user_disable_to_date;
	}

	public void setUser_disable_to_date(Date user_disable_to_date) {
		this.user_disable_to_date = user_disable_to_date;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getLogin_status() {
		return login_status;
	}

	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}

	public String getLogin_channel() {
		return login_channel;
	}

	public void setLogin_channel(String login_channel) {
		this.login_channel = login_channel;
	}

	public BigDecimal getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(BigDecimal mobile_no) {
		this.mobile_no = mobile_no;
	}

	public BigDecimal getAlternate_mobile_no() {
		return alternate_mobile_no;
	}

	public void setAlternate_mobile_no(BigDecimal alternate_mobile_no) {
		this.alternate_mobile_no = alternate_mobile_no;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getAlternate_email_id() {
		return alternate_email_id;
	}

	public void setAlternate_email_id(String alternate_email_id) {
		this.alternate_email_id = alternate_email_id;
	}

	public String getMerchant_rep_id() {
		return merchant_rep_id;
	}

	public void setMerchant_rep_id(String merchant_rep_id) {
		this.merchant_rep_id = merchant_rep_id;
	}

	public String getPwlog_flg() {
		return pwlog_flg;
	}

	public void setPwlog_flg(String pwlog_flg) {
		this.pwlog_flg = pwlog_flg;
	}

	public String getMer_representative_name() {
		return mer_representative_name;
	}

	public void setMer_representative_name(String mer_representative_name) {
		this.mer_representative_name = mer_representative_name;
	}

	public BigDecimal getNo_of_concurrent_users() {
		return no_of_concurrent_users;
	}

	public void setNo_of_concurrent_users(BigDecimal no_of_concurrent_users) {
		this.no_of_concurrent_users = no_of_concurrent_users;
	}

	public BigDecimal getNo_of_active_devices() {
		return no_of_active_devices;
	}

	public void setNo_of_active_devices(BigDecimal no_of_active_devices) {
		this.no_of_active_devices = no_of_active_devices;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public Date getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public String getMaker_or_checker() {
		return maker_or_checker;
	}

	public void setMaker_or_checker(String maker_or_checker) {
		this.maker_or_checker = maker_or_checker;
	}

	public Integer getNo_of_attmp() {
		return no_of_attmp;
	}

	public void setNo_of_attmp(Integer no_of_attmp) {
		this.no_of_attmp = no_of_attmp;
	}

	public String getUser_locked_flg() {
		return user_locked_flg;
	}

	public void setUser_locked_flg(String user_locked_flg) {
		this.user_locked_flg = user_locked_flg;
	}


	public BIPS_Password_Management_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BIPS_Password_Management_Entity(String unit_id, String unit_type, String unit_name, String merchant_user_id,
			String merchant_name, String merchant_legal_user_id, String merchant_corporate_name, String password,
			Date password_expiry_date, String password_life, Date account_expiry_date, String user_disable_flag,
			Date user_disable_from_date, Date user_disable_to_date, String del_flag, String user_status,
			String login_status, String login_channel, BigDecimal mobile_no, BigDecimal alternate_mobile_no,
			String email_address, String alternate_email_id, String merchant_rep_id, String pwlog_flg,
			String mer_representative_name, BigDecimal no_of_concurrent_users, BigDecimal no_of_active_devices,
			String entry_user, String modify_user, String verify_user, Date entry_time, Date modify_time,
			Date verify_time, String maker_or_checker, Integer no_of_attmp, String user_locked_flg, String entry_flag,
			String modify_flag) {
		super();
		this.unit_id = unit_id;
		this.unit_type = unit_type;
		this.unit_name = unit_name;
		this.merchant_user_id = merchant_user_id;
		this.merchant_name = merchant_name;
		this.merchant_legal_user_id = merchant_legal_user_id;
		this.merchant_corporate_name = merchant_corporate_name;
		this.password = password;
		this.password_expiry_date = password_expiry_date;
		this.password_life = password_life;
		this.account_expiry_date = account_expiry_date;
		this.user_disable_flag = user_disable_flag;
		this.user_disable_from_date = user_disable_from_date;
		this.user_disable_to_date = user_disable_to_date;
		this.del_flag = del_flag;
		this.user_status = user_status;
		this.login_status = login_status;
		this.login_channel = login_channel;
		this.mobile_no = mobile_no;
		this.alternate_mobile_no = alternate_mobile_no;
		this.email_address = email_address;
		this.alternate_email_id = alternate_email_id;
		this.merchant_rep_id = merchant_rep_id;
		this.pwlog_flg = pwlog_flg;
		this.mer_representative_name = mer_representative_name;
		this.no_of_concurrent_users = no_of_concurrent_users;
		this.no_of_active_devices = no_of_active_devices;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.maker_or_checker = maker_or_checker;
		this.no_of_attmp = no_of_attmp;
		this.user_locked_flg = user_locked_flg;
		this.entry_flag = entry_flag;
		this.modify_flag = modify_flag;
	}



	@Override
	public String toString() {
		return "BIPS_Password_Management_Entity [unit_id=" + unit_id + ", unit_type=" + unit_type + ", unit_name="
				+ unit_name + ", merchant_user_id=" + merchant_user_id + ", merchant_name=" + merchant_name
				+ ", merchant_legal_user_id=" + merchant_legal_user_id + ", merchant_corporate_name="
				+ merchant_corporate_name + ", password=" + password + ", password_expiry_date=" + password_expiry_date
				+ ", password_life=" + password_life + ", account_expiry_date=" + account_expiry_date
				+ ", user_disable_flag=" + user_disable_flag + ", user_disable_from_date=" + user_disable_from_date
				+ ", user_disable_to_date=" + user_disable_to_date + ", del_flag=" + del_flag + ", user_status="
				+ user_status + ", login_status=" + login_status + ", login_channel=" + login_channel + ", mobile_no="
				+ mobile_no + ", alternate_mobile_no=" + alternate_mobile_no + ", email_address=" + email_address
				+ ", alternate_email_id=" + alternate_email_id + ", merchant_rep_id=" + merchant_rep_id + ", pwlog_flg="
				+ pwlog_flg + ", mer_representative_name=" + mer_representative_name + ", no_of_concurrent_users="
				+ no_of_concurrent_users + ", no_of_active_devices=" + no_of_active_devices + ", entry_user="
				+ entry_user + ", modify_user=" + modify_user + ", verify_user=" + verify_user + ", entry_time="
				+ entry_time + ", modify_time=" + modify_time + ", verify_time=" + verify_time + ", maker_or_checker="
				+ maker_or_checker + ", no_of_attmp=" + no_of_attmp + ", user_locked_flg=" + user_locked_flg
				+ ", entry_flag=" + entry_flag + ", modify_flag=" + modify_flag + "]";
	}



	public boolean isAccountNonExpired1() {
		if (this.getAccount_expiry_date().after(new Date())) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean ispasswordnotexpiry() {
	    Date expiryDate = getPassword_expiry_date();
	    Date currentDate = new Date();
	    
	    if (expiryDate.after(currentDate)) {
	        return true;  // Password has expired
	    } else {
	        return false; // Password has not expired
	    }
	}
	
	public boolean isUserStatus() {
	    String userStatus = getUser_status();
	    String login = getLogin_status();
	    if ("Active".equals(userStatus) && "N".equals(login)) {
	        return true;
	    } else {
	        return false;
	    }
	}


	public boolean isAccountNonLocked() {
		boolean status=true;
		if (this.getUser_locked_flg().equals("Y")) {
			status=false;
		} else {
			status=true;
		}

		return status;
	}


	public boolean isCredentialsNonExpired1() {
		
		return true;
		
		
	}

	
	
	

		

	
}
	
	
